/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service.impl;

import com.github.processx.core.service.ProcessConfigService;
import com.github.processx.core.service.enums.NodeTypeEnum;
import com.github.processx.core.service.model.NodeDefinition;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.core.service.model.ProcessFeature;
import com.github.processx.core.util.BeanCheckUtil;
import com.github.processx.dal.daointerface.ProcessDOMapper;
import com.github.processx.dal.daointerface.ProcessNodeDOMapper;
import com.github.processx.dal.dataobjects.ProcessDO;
import com.github.processx.dal.dataobjects.ProcessNodeDO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程配置服务接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 14:23
 */
public class ProcessConfigServiceImpl implements ProcessConfigService {
  /** 流程信息DAO接口 */
  @Autowired private ProcessDOMapper processDOMapper;
  /** 流程节点信息DAO接口 */
  @Autowired private ProcessNodeDOMapper processNodeDOMapper;

  /** 获取所有流程配置信息 */
  @Override
  public Map<Long, ProcessDefinition> getAllProcessDefinition() {
    /** 获取所有流程信息 */
    List<ProcessDO> processList = processDOMapper.selectAll();
    /** 获取所有流程节点信息 */
    List<ProcessNodeDO> processNodeList = processNodeDOMapper.selectAll();

    Map<Long, ProcessDefinition> processDefinitionMap = new HashMap<>(processList.size());
    for (ProcessDO process : processList) {
      ProcessDefinition processDefinition = new ProcessDefinition();
      processDefinition.setProcessId(process.getId());
      processDefinition.setName(process.getName());
      processDefinition.setVersion(process.getVersion());

      ProcessFeature processFeature = new ProcessFeature();
      processFeature.setRecordGlobalParam(process.getRecordGlobalParam());
      processFeature.setRecordNodeInstance(process.getRecordNodeInstance());
      processFeature.setRecordProcessInput(process.getRecordProcessInput());
      processFeature.setRecordProcessInstance(process.getRecordProcessInstance());
      processFeature.setRecordTriggerInput(process.getRecordTriggerInput());
      processFeature.setRecordTriggerResult(process.getRecordTriggerResult());
      processDefinition.setProcessFeature(processFeature);

      processDefinitionMap.put(processDefinition.getProcessId(), processDefinition);
    }

    for (ProcessNodeDO processNod : processNodeList) {
      NodeDefinition nodeDefinition = new NodeDefinition();
      nodeDefinition.setProcessNodeId(processNod.getId());
      nodeDefinition.setProcessId(processNod.getProcessId());
      nodeDefinition.setName(processNod.getName());
      nodeDefinition.setNodeType(NodeTypeEnum.getNodeTypeEnumByType(processNod.getNodeType()));
      nodeDefinition.setExecuteCompoment(processNod.getExecuteCompoment());
      nodeDefinition.setIsSync(processNod.getIsSync());
      nodeDefinition.setIsProtected(processNod.getIsProtected());
      nodeDefinition.setMaxExeTime(processNod.getMaxExeTime());
      nodeDefinition.setStage(processNod.getStage());

      String[] preNodeIds = StringUtils.split(processNod.getPreNodeId(), ",");
      List<Long> preNodeIdList = new ArrayList<>(preNodeIds.length);
      for (int i = 0; i < preNodeIds.length; i++) {
        preNodeIdList.add(Long.valueOf(preNodeIds[i]));
      }
      nodeDefinition.setPreNodeIdList(preNodeIdList);

      ProcessDefinition processDefinition = processDefinitionMap.get(processNod.getProcessId());
      List<NodeDefinition> nodeDefinitionList = processDefinition.getNodeDefinitionList();
      if (BeanCheckUtil.checkNullOrEmpty(nodeDefinitionList)) {
        nodeDefinitionList = new ArrayList<>();
      }
      nodeDefinitionList.add(nodeDefinition);
    }

    return processDefinitionMap;
  }
}
