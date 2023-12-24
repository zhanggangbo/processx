/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.handle;

import com.alibaba.fastjson.JSONObject;
import com.github.processx.api.ScheduleExecution;
import com.github.processx.api.event.Event;
import com.github.processx.api.event.GatewayNodeEvent;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.api.event.ProcessInnerEvent;
import com.github.processx.common.bean.BeanCheckUtil;
import com.github.processx.common.enums.LoggerEnum;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.DataBus;
import com.github.processx.core.NodeInstance;
import com.github.processx.core.ProcessInstance;
import com.github.processx.core.ProcessTriggerResultHandler;
import com.github.processx.core.TriggerResult;
import com.github.processx.core.executor.AynNodeExecutor;
import com.github.processx.core.schedule.SchedulePlanService;
import com.github.processx.core.service.enums.NodeTypeEnum;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程节点事件处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 16:48
 */
public class NodeEventHandle implements EventHandle {

  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(LoggerEnum.NODE_DIGEST.getLogger());

  /** 节点异步执行器 */
  @Autowired private AynNodeExecutor aynNodeExecutor;

  @Autowired SchedulePlanService processSchedulePlanService;

  @Autowired ProcessTriggerResultHandler processTriggerResultHandler;

  @Autowired ProcessInnerEventHandle processInnerEventHandle;

  /**
   * 流程节点事件处理
   *
   * @param processInstance 流程实例
   * @param event 流程事件
   */
  @Override
  public void handle(ProcessInstance processInstance, Event event) {
    NodeEvent nodeEvent = (NodeEvent) event;

    switch (nodeEvent.getEventType()) {
      case SUCCESS:
        handleSuccessEvent(processInstance, nodeEvent);
        break;
      case FAIL:
        handleFailEvent(processInstance, nodeEvent);
        break;
      case WAIT:
        handleWaitEvent(processInstance, nodeEvent);
        break;
      case TERMINAL:
        handleTerminalEvent(processInstance);
        break;
      case RUNNING:
        handleRunningEvent();
        break;
      case COMPLETE:
        handleCompleteEvent(processInstance, nodeEvent);
        break;

      default:
        break;
    }
  }

  /**
   * 成功事件处理
   *
   * <p>寻找下一个节点
   */
  private void handleSuccessEvent(ProcessInstance processInstance, NodeEvent nodeEvent) {
    NodeInstance nextNodeInstance = getNextNodeInstance(processInstance, nodeEvent);
    if (nextNodeInstance == null) {
      return;
    }

    // 异步执行节点
    if (!nextNodeInstance.getIsSync()) {
      aynNodeExecutor.execute(nextNodeInstance, null);
      return;
    } else if (nextNodeInstance.getNodeType() == NodeTypeEnum.TRIGGER) {
      // 触发节点
      return;
    } else if (nextNodeInstance.getNodeType() == NodeTypeEnum.SCHEDULE) {
      ScheduleExecution execute = (ScheduleExecution) nextNodeInstance.getExecuteCompoment();
      // 定时节点，写入定时任务表等待定时调度
      processSchedulePlanService.addOrUpdateSchedulePlan(
          nextNodeInstance.getProcessInstance().getProcessInstanceId(),
          nextNodeInstance.getBizNo(),
          nextNodeInstance.getNodeId(),
          execute.getPeriod());
      return;
    } else if (nextNodeInstance.getNodeType() == NodeTypeEnum.AUTO
        || nextNodeInstance.getNodeType() == NodeTypeEnum.GATEWAY) {
      processInstance.execNode(nextNodeInstance.getName());
      return;
    }
  }

  /**
   * 完成事件处理
   *
   * @param processInstance
   * @param event
   */
  private void handleCompleteEvent(ProcessInstance processInstance, NodeEvent event) {
    NodeInstance nextInstance = this.getNextNodeInstance(processInstance, event);

    if (nextInstance == null || nextInstance.executeByNewThread()) {
      String bizNo = processInstance.getBizNo();
      Long onsetNodeId = DataBus.get().getOnsetNodeId();
      if (onsetNodeId != null && processInstance.getProcessFeature().getRecordTriggerResult()) {
        TriggerResult triggerResult =
            processTriggerResultHandler.selectTriggerResult(bizNo, onsetNodeId);
        if (triggerResult != null) {
          String value = triggerResult.getValue();
          DataBus.get().setLastOutput(JSONObject.parseObject(value, Map.class));
        }
      }
    }

    handleSuccessEvent(processInstance, event);
  }

  /** 等待事件处理 */
  private void handleWaitEvent(ProcessInstance processInstance, NodeEvent event) {
    if (event.getException() != null) {
      loggerNodeError(processInstance, event);
      throw new ProcessxException(
          ProcessxResultEnum.BIZ_INNER_EXCEPTION, "流程节点执行等待中...", event.getException());
    }
  }

  /** 失败事件处理 */
  private void handleFailEvent(ProcessInstance processInstance, NodeEvent event) {
    if (event.getException() != null) {
      loggerNodeError(processInstance, event);
      throw new ProcessxException(
          ProcessxResultEnum.BIZ_INNER_EXCEPTION, "流程节点执行失败", event.getException());
    }
  }

  /**
   * 终止事件处理
   *
   * <p>执行end节点
   */
  private void handleTerminalEvent(ProcessInstance processInstance) {
    processInnerEventHandle.handle(
        processInstance,
        ProcessInnerEvent.createTerminalEvent(processInstance.getEndNode().getName()));
  }

  /** 运行中事件处理 */
  private void handleRunningEvent() {
    throw new ProcessxException(ProcessxResultEnum.NODE_RUNNING);
  }

  /** 日志记录 */
  private void loggerNodeError(ProcessInstance processInstance, NodeEvent event) {
    String processName = processInstance.getName();
    String bizNo = processInstance.getBizNo();
    String nodeName = "";
    if (processInstance.getCurrentNode() != null) {
      nodeName = processInstance.getCurrentNode().getName();
    }

    LoggerUtil.error(
        LOGGER,
        event.getException(),
        "processName:{0},bizNo:{1},nodeName:{2}",
        processName,
        bizNo,
        nodeName);
  }

  /** 获取下一个流程节点 */
  private NodeInstance getNextNodeInstance(ProcessInstance processInstance, NodeEvent nodeEvent) {
    NodeInstance nextNode = null;
    NodeInstance currentNode = processInstance.getCurrentNode();

    if (currentNode.getNodeType() == NodeTypeEnum.GATEWAY) {
      GatewayNodeEvent gatewayNodeEvent = (GatewayNodeEvent) nodeEvent;
      nextNode = processInstance.findNodeInstance(gatewayNodeEvent.getNextNodeName());
    } else {
      List<NodeInstance> nextNodeInstanceList = currentNode.getNextNodeInstanceList();
      if (!BeanCheckUtil.checkNullOrEmpty(nextNodeInstanceList)) {
        nextNode = currentNode.getNextNodeInstanceList().get(0);
      }
    }

    return nextNode;
  }
}
