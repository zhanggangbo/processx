/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.handle;

import com.github.processx.api.event.Event;
import com.github.processx.api.event.ProcessInnerEvent;
import com.github.processx.core.ProcessInstance;

/**
 * 流程事件处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 16:34
 */
public class ProcessInnerEventHandle implements EventHandle {
  /**
   * 流程事件处理
   *
   * @param processInstance 流程实例
   * @param event 流程事件
   */
  @Override
  public void handle(ProcessInstance processInstance, Event event) {
    ProcessInnerEvent processInnerEvent = (ProcessInnerEvent) event;

    switch (processInnerEvent.getEventType()) {
      case TRIGGER:
        processInstance.execTriggerNode(processInnerEvent.getNodeName());
        break;
      case RESUME:
        processInstance.execNode(processInnerEvent.getNodeName());
        break;
      case SCHEDULE:
        processInstance.execScheduleNode(processInnerEvent.getNodeName());
        break;
      // START or TERMINAL
      default:
        processInstance.execAutoNode(processInnerEvent.getNodeName());
        break;
    }
  }
}
