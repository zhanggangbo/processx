/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.listener;

import com.github.processx.api.event.Event;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.api.event.ProcessInnerEvent;
import com.github.processx.core.ProcessInstance;
import com.github.processx.core.handle.NodeEventHandle;
import com.github.processx.core.handle.ProcessInnerEventHandle;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程事件监听器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 16:08
 */
public class EventListenerImpl implements EventListener {

  @Autowired private NodeEventHandle nodeEventHandle;
  @Autowired private ProcessInnerEventHandle processInnerEventHandle;

  /**
   * 流程事件监听处理
   *
   * @param processInstance
   * @param event
   */
  @Override
  public void handle(ProcessInstance processInstance, Event event) {
    if (event instanceof ProcessInnerEvent) {
      processInnerEventHandle.handle(processInstance, event);
    } else if (event instanceof NodeEvent) {
      nodeEventHandle.handle(processInstance, event);
    }
  }
}
