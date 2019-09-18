/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.executor;

import com.github.processx.api.event.ProcessInnerEvent;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.DataBus;
import com.github.processx.core.ProcessInstance;
import com.github.processx.core.ProcessInstanceFactory;
import com.github.processx.core.ProcessLoader;
import com.github.processx.core.schedule.ScheduleResult;
import com.github.processx.core.schedule.impl.SchedulePlanHandleImpl;
import com.github.processx.core.service.ProcessTracker;
import com.github.processx.core.service.model.NodeDefinition;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.core.service.model.ProcessFeature;
import com.github.processx.dal.dataobjects.ProcessInstanceDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhanggangbo
 * @version v 0.1 2019/9/4 0:07
 */
public class SynProcessExecutor {
  /**
   * 日志记录
   */
  private static final Logger LOGGER = LogManager.getLogger(SchedulePlanHandleImpl.class);

  /**
   * 流程追踪器
   */
  @Autowired
  private ProcessTracker processTracker;
  /**
   * 定时节点执行
   *
   * @param bizNo
   * @param nodeId
   * @param execCounts
   * @return
   */
  public ScheduleResult exectionSchedule(String bizNo, Long nodeId, int execCounts) {

    try {
      // 根据节点ID获取流程实例
      ProcessDefinition processDefinition = ProcessLoader.getProcessDefinition(nodeId);

      // 创建流程实例
      ProcessInstance processInstance = ProcessInstanceFactory.create(processDefinition, bizNo);

      ProcessFeature processFeature = processDefinition.getProcessFeature();
      if (processFeature.getRecordProcessInstance()) {
        ProcessInstanceDO processInstanceDO = processTracker.getProcessInstanceByBizNo(bizNo);
        if (processInstanceDO != null) {
          processInstance.setProcessInstanceId(processInstanceDO.getId());
        }
      }

      NodeDefinition nodeDefinition = ProcessLoader.getNodeDefinition(nodeId);

      processInstance.notifyEvent(
        ProcessInnerEvent.createScheduleEvent(nodeDefinition.getName(), execCounts));

      ScheduleResult scheduleResult = DataBus.get().getScheduleResult();
      LoggerUtil.info(LOGGER, "ScheduleResult====exectionScheduleexectionScheduleexectionSchedule========={0}", scheduleResult);
      return scheduleResult;
    } catch (Exception e) {
      // TODO 细化异常并打印日志
      throw new ProcessxException(ProcessxResultEnum.SYSTEM_ERROR);
    }
  }
}
