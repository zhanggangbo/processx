/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service.impl;

import com.github.processx.core.service.ProcessTracker;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.core.service.model.ProcessFeature;
import com.github.processx.dal.daointerface.ProcessInstanceDOMapper;
import com.github.processx.dal.dataobjects.ProcessInstanceDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程追踪器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/2 21:22
 */
public class ProcessTrackerImpl implements ProcessTracker {

  /** 流程实例DAO接口 */
  @Autowired
  private ProcessInstanceDOMapper processInstanceDOMapper;

  /**
   * 流程开始
   *
   * @param processDefinition 流程定义
   * @param bizNo 业务流水号
   * @return 流程实例
   */
  @Override
  public ProcessInstanceDO processBegin(ProcessDefinition processDefinition, String bizNo) {
    ProcessFeature processFeature = processDefinition.getProcessFeature();
    if (!processFeature.getRecordProcessInstance()) {
      return null;
    }

    ProcessInstanceDO processInstanceDO = processInstanceDOMapper.selectByBizNo(bizNo);
    if (processInstanceDO != null) {
      return processInstanceDO;
    }

    ProcessInstanceDO record = new ProcessInstanceDO();
    record.setBizNo(bizNo);
    record.setProcessId(processDefinition.getProcessId());
    int count = processInstanceDOMapper.insertProcessInstance(record);

    if (count > 0) {
      return processInstanceDOMapper.selectByBizNo(bizNo);
    }

    return null;
  }

  /**
   * 根据业务流水号获取流程实例
   *
   * @param bizNo 业务流水号
   * @return 流程实例
   */
  @Override
  public ProcessInstanceDO getProcessInstanceByBizNo(String bizNo) {
    return processInstanceDOMapper.selectByBizNo(bizNo);
  }
}
