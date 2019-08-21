/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.handle;

import com.github.processx.api.event.Event;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.core.ProcessInstance;

/**
 * 流程节点事件处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 16:48
 */
public class NodeEventHandle implements EventHandle {
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
        handleSuccessEvent();
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

  private void handleSuccessEvent() {
  }
}
