/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.listener;

import com.github.processx.api.event.Event;
import com.github.processx.core.ProcessInstance;

/**
 * 流程事件监听器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 16:04
 */
public interface EventListener {

  /**
   * 流程事件监听处理
   *
   * @param processInstance
   * @param event
   */
  void handle(ProcessInstance processInstance, Event event);
}
