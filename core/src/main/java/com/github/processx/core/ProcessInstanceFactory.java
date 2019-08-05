/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.common.bean.BeanCheckUtil;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.core.service.enums.NodeTypeEnum;
import com.github.processx.core.service.model.NodeDefinition;
import com.github.processx.core.service.model.ProcessDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程实例创建工厂
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/4 0:08
 */
public class ProcessInstanceFactory {
  /**
   * l流程实例创建
   *
   * @param bizNo 业务流水号
   * @param processName 流程名称
   * @param param 业务入参
   * @return 流程实例
   */
  public static ProcessInstance create(
      String bizNo, String processName, Map<String, String> param) {
    /** 获取指定流程信息 */
    ProcessDefinition processDefinition = ProcessLoader.getProcessDefinition(processName);

    /** 流程信息不存在 */
    if (processDefinition == null) {
      throw new ProcessxException(ProcessxResultEnum.PROCESS_DADA_NO_EXIST);
    }

    ProcessInstance process = new ProcessInstance();
    process.setBizNo(bizNo);
    process.setName(processDefinition.getName());
    process.setProcessId(processDefinition.getProcessId());
    process.setVersion(processDefinition.getVersion());
    process.setProcessFeature(processDefinition.getProcessFeature());

    List<NodeDefinition> nodeDefinitionList = processDefinition.getNodeDefinitionList();
    for (NodeDefinition nodeDefinition : nodeDefinitionList) {
      NodeInstance node = new NodeInstance();
      // 获取上一个节点信息
      List<Long> preNodeIdList = nodeDefinition.getPreNodeIdList();

      if (!BeanCheckUtil.checkNullOrEmpty(preNodeIdList)) {
        for (Long processId : preNodeIdList) {
          // 根据节点id获取节点信息
          NodeDefinition preNodeDefinition = ProcessLoader.getNodeDefinition(processId);
          node.setNodeId(preNodeDefinition.getNodeId());
          node.setName(preNodeDefinition.getName());
          node.setExecuteCompoment(preNodeDefinition.getExecuteCompoment());
          node.setIsSync(preNodeDefinition.getIsSync());
          node.setIsProtected(preNodeDefinition.getIsProtected());
          node.setMaxExeTime(preNodeDefinition.getMaxExeTime());

          NodeTypeEnum nodeTypeEnum =
              NodeTypeEnum.getNodeTypeEnumByType(preNodeDefinition.getNodeType());
          if (nodeTypeEnum == null) {
            throw new ProcessxException(ProcessxResultEnum.ILLEGAL_PROCESS_NODE_TYPE);
          }
          node.setNodeType(nodeTypeEnum);

          List<String> nextNodeIdList = node.getNextNodeIdList();
          if (nextNodeIdList == null) {
            nextNodeIdList = new ArrayList<>();
            node.setNextNodeIdList(nextNodeIdList);
          }
          nextNodeIdList.add(nodeDefinition.getName());

          Map<String, NodeInstance> nameNodeInstanceMap = process.getNameNodeInstanceMap();
          if (nameNodeInstanceMap == null) {
            nameNodeInstanceMap = new HashMap<>();
            process.setNameNodeInstanceMap(nameNodeInstanceMap);
          }
          nameNodeInstanceMap.put(node.getName(), node);
        }
      }

      /** 初始节点 */
      if (BeanCheckUtil.checkNullOrEmpty(nodeDefinition.getPreNodeIdList()) && node.isAutoNode()) {
        process.setStartNode(node);
      }

      /** 结束节点 */
      if (BeanCheckUtil.checkNullOrEmpty(node.getNextNodeIdList()) && node.isAutoNode()) {
        process.setEndNode(node);
      }
    }

    return process;
  }
}
