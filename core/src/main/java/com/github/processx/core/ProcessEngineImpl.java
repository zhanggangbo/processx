/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import com.github.processx.api.event.ProcessInnerEvent;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.core.schedule.ScheduleResult;
import com.github.processx.core.service.ProcessTracker;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.core.service.model.ProcessFeature;
import com.github.processx.dal.dataobjects.ProcessInstanceDO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程引擎实现
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 15:49
 */
public class ProcessEngineImpl implements ProcessEngine {
  /**
   * 流程追踪器
   */
  @Autowired
  private ProcessTracker processTracker;

  /**
   * 流程启动
   *
   * @param processName
   * @param bizNo
   * @param inputParam
   * @param listener
   * @return
   */
  @Override
  public ProcessResult start(
    String processName, String bizNo, Map<String, Object> inputParam, ProsessListener listener) {
    try {
      // 根据流程名称获取流程实例
      ProcessDefinition processDefinition = ProcessLoader.getLastProcessDefinition(processName);

      // 创建流程实例
      ProcessInstance processInstance = ProcessInstanceFactory.create(processDefinition, bizNo);

      // 流程开始
      ProcessInstanceDO processInstanceDO = processTracker.processBegin(processDefinition, bizNo);
      if (processInstanceDO != null) {
        processInstance.setProcessInstanceId(processInstanceDO.getId());
      }

      // 从头节点触发流程启动
      processInstance.notifyEvent(
        ProcessInnerEvent.createStartEvent(processInstance.getStartNode().getName()));

      // 日志记录
      ProcessResult result = new ProcessResult();
      result.setBizNo(bizNo);

      return result;
    } catch (Exception e) {
      // TODO 细化异常并打印日志
      throw new ProcessxException(ProcessxResultEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 触发执行某个节点
   */
  @Override
  public ProcessResult call(
    String processName,
    String bizNo,
    String nodeName,
    Map<String, Object> triggerParam,
    ProsessListener listener) {
    try {
      // 根据流程名称获取流程实例
      ProcessDefinition processDefinition = ProcessLoader.getLastProcessDefinition(processName);

      // 创建流程实例
      ProcessInstance processInstance = ProcessInstanceFactory.create(processDefinition, bizNo);

      ProcessFeature processFeature = processDefinition.getProcessFeature();
      if (processFeature.getRecordProcessInstance()) {
        ProcessInstanceDO processInstanceDO = processTracker.getProcessInstanceByBizNo(bizNo);
        if (processInstanceDO != null) {
          processInstance.setProcessInstanceId(processInstanceDO.getId());
        }
      }

      processInstance.notifyEvent(ProcessInnerEvent.createTriggerEvent(nodeName));

      ProcessResult result = new ProcessResult();
      result.setBizNo(bizNo);

      return result;
    } catch (Exception e) {
      // TODO 细化异常并打印日志
      throw new ProcessxException(ProcessxResultEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 重新执行某个节点
   */
  @Override
  public ProcessResult resume(
    String processName,
    String bizNo,
    String nodeName,
    Map<String, Object> inputParam,
    ProsessListener listener) {
    return null;
  }

  public ScheduleResult scheduleExection(
    String processName, String bizNo, String nodeName, int scheduleExecCounts) {
    try {
      // 根据流程名称获取流程实例
      ProcessDefinition processDefinition = ProcessLoader.getLastProcessDefinition(processName);

      // 创建流程实例
      ProcessInstance processInstance = ProcessInstanceFactory.create(processDefinition, bizNo);

      ProcessFeature processFeature = processDefinition.getProcessFeature();
      if (processFeature.getRecordProcessInstance()) {
        ProcessInstanceDO processInstanceDO = processTracker.getProcessInstanceByBizNo(bizNo);
        if (processInstanceDO != null) {
          processInstance.setProcessInstanceId(processInstanceDO.getId());
        }
      }

      processInstance.notifyEvent(
        ProcessInnerEvent.createScheduleEvent(nodeName, scheduleExecCounts));

      ScheduleResult result = new ScheduleResult();

      return result;
    } catch (Exception e) {
      // TODO 细化异常并打印日志
      throw new ProcessxException(ProcessxResultEnum.SYSTEM_ERROR);
    }
  }
}
