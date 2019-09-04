/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.handle;

import com.github.processx.api.ScheduleExecution;
import com.github.processx.api.event.Event;
import com.github.processx.api.event.GatewayNodeEvent;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.common.bean.BeanCheckUtil;
import com.github.processx.core.NodeInstance;
import com.github.processx.core.ProcessInstance;
import com.github.processx.core.executor.AynNodeExecutor;
import com.github.processx.core.schedule.ProcessSchedulePlanService;
import com.github.processx.core.service.enums.NodeTypeEnum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程节点事件处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 16:48
 */
public class NodeEventHandle implements EventHandle {
  /**
   * 节点异步执行器
   */
  @Autowired
  private AynNodeExecutor aynNodeExecutor;

  @Autowired
  ProcessSchedulePlanService processSchedulePlanService;

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
        handleSuccessEvent(processInstance, event);
        break;
      case FAIL:
        break;
      case WAIT:
        break;
      case TERMINAL:
        break;
      case RUNNING:
        break;
      case COMPLETE:
        break;

      default:
        break;
    }
  }

  /**
   * 成功事件处理
   */
  private void handleSuccessEvent(ProcessInstance processInstance, Event event) {
    NodeInstance nextNodeInstance = getNextNodeInstance(processInstance, event);
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
      processSchedulePlanService.addSchedulePlan(
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
   * 获取下一个流程节点
   */
  private NodeInstance getNextNodeInstance(ProcessInstance processInstance, Event event) {
    NodeInstance nextNode = null;
    NodeInstance currentNode = processInstance.getCurrentNode();

    if (currentNode.getNodeType() == NodeTypeEnum.GATEWAY) {
      GatewayNodeEvent nodeEvent = (GatewayNodeEvent) event;
      nextNode = processInstance.findNodeInstance(nodeEvent.getNextNodeName());
    } else {
      List<NodeInstance> nextNodeInstanceList = currentNode.getNextNodeInstanceList();
      if (!BeanCheckUtil.checkNullOrEmpty(nextNodeInstanceList)) {
        nextNode = currentNode.getNextNodeInstanceList().get(0);
      }
    }

    return nextNode;
  }
}
