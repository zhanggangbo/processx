/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.handle;

import com.github.processx.api.event.Event;
import com.github.processx.core.ProcessInstance;

/**
 * 事件处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 16:32
 */
public interface EventHandle {
  
  /**
   * 流程事件处理
   *
   * @param processInstance 流程实例
   * @param event 流程事件
   */
  void handle(ProcessInstance processInstance, Event event);
}
