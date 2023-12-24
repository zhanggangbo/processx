/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.api.Execution;
import com.github.processx.common.bean.BeanCheckUtil;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.core.service.enums.NodeTypeEnum;
import com.github.processx.core.service.model.NodeDefinition;
import com.github.processx.core.service.model.ProcessDefinition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
   * @param processDefinition 流程定义
   * @param bizNo 业务流水号
   * @return 流程实例
   */
  public static ProcessInstance create(ProcessDefinition processDefinition, String bizNo) {

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

    /** 获取流程节点 */
    List<NodeDefinition> nodeDefinitionList = processDefinition.getNodeDefinitionList();
    if (!BeanCheckUtil.checkNullOrEmpty(nodeDefinitionList)) {
      Map<Long, NodeInstance> nodeInstanceMap = new HashMap<>(nodeDefinitionList.size());
      for (NodeDefinition nodeDefinition : nodeDefinitionList) {
        NodeInstance nodeInstance = createNodeInstance(bizNo, nodeDefinition, process);
        nodeInstanceMap.put(nodeInstance.getNodeId(), nodeInstance);
      }

      for (NodeDefinition nodeDefinition : nodeDefinitionList) {
        NodeInstance nodeInstance = nodeInstanceMap.get(nodeDefinition.getNodeId());
        // 获取上一个节点信息
        List<Long> preNodeIdList = nodeDefinition.getPreNodeIdList();
        if (!BeanCheckUtil.checkNullOrEmpty(preNodeIdList)) {
          for (Long processId : preNodeIdList) {
            NodeInstance preNodeInstance = nodeInstanceMap.get(processId);
            preNodeInstance.addNextNodeInstance(nodeInstance);
            nodeInstance.addPreNodeInstance(preNodeInstance);
          }
        }
        process.addNodeInstance(nodeInstance);
      }

      for (Entry<Long, NodeInstance> entry : nodeInstanceMap.entrySet()) {
        NodeInstance nodeInstance = entry.getValue();
        List<NodeInstance> nextNodeInstanceList = nodeInstance.getNextNodeInstanceList();
        if (BeanCheckUtil.checkNullOrEmpty(nextNodeInstanceList)
            && nodeInstance.getNodeType() == NodeTypeEnum.AUTO) {
          nodeInstance.setEnd(true);
          process.setEndNode(nodeInstance);
        }

        List<NodeInstance> preNodeInstanceList = nodeInstance.getPreNodeInstanceList();
        if (BeanCheckUtil.checkNullOrEmpty(preNodeInstanceList)
            && nodeInstance.getNodeType() == NodeTypeEnum.AUTO) {
          nodeInstance.setStart(true);
          process.setStartNode(nodeInstance);
        }
      }
    }

    process.initTools();
    return process;
  }

  /**
   * 创建节点实例
   *
   * @param bizNo
   * @param nodeDefinition
   * @param process
   * @return
   */
  private static NodeInstance createNodeInstance(
      String bizNo, NodeDefinition nodeDefinition, ProcessInstance process) {
    NodeInstance node = new NodeInstance();
    node.setBizNo(bizNo);
    node.setNodeId(nodeDefinition.getNodeId());
    node.setProcessInstance(process);
    node.setName(nodeDefinition.getName());
    node.setStage(nodeDefinition.getStage());

    NodeTypeEnum nodeTypeEnum = NodeTypeEnum.getNodeTypeEnumByType(nodeDefinition.getNodeType());
    if (nodeTypeEnum == null) {
      throw new ProcessxException(ProcessxResultEnum.ILLEGAL_PROCESS_NODE_TYPE);
    }
    node.setNodeType(nodeTypeEnum);

    Execution execution =
        ProcessLoader.getExecution(nodeTypeEnum, nodeDefinition.getExecuteCompoment());
    if (execution == null) {
      throw new ProcessxException(ProcessxResultEnum.EXECUTE_COMPOMENT_NO_EXIST);
    }
    node.setExecuteCompoment(execution);

    node.setIsSync(nodeDefinition.getIsSync());
    node.setIsProtected(nodeDefinition.getIsProtected());
    node.setMaxExeTime(nodeDefinition.getMaxExeTime());
    return node;
  }
}
