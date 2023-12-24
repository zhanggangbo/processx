/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.service;

import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.dal.dataobjects.ProcessInstanceDO;

/**
 * 流程追踪器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/2 21:17
 */
public interface ProcessTracker {
  /**
   * 流程开始
   *
   * @param processDefinition 流程定义
   * @param bizNo 业务流水号
   * @return 流程实例
   */
  ProcessInstanceDO processBegin(ProcessDefinition processDefinition, String bizNo);

  /**
   * 根据业务流水号获取流程实例
   *
   * @param bizNo 业务流水号
   * @return 流程实例
   */
  ProcessInstanceDO getProcessInstanceByBizNo(String bizNo);
}
