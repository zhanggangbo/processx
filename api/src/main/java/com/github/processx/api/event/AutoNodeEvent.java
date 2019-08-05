/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api.event;


import static com.github.processx.api.event.enums.NodeEventTypeEnum.COMPLETE;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.FAIL;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.RUNNING;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.SUCCESS;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.TERMINAL;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.WAIT;

import com.github.processx.api.event.enums.NodeEventTypeEnum;

/**
 * 自动节点事件定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/1 21:36
 * @see com.github.processx.api.event.NodeEvent
 */
public class AutoNodeEvent extends NodeEvent {

  /** 构造方法 */
  public AutoNodeEvent(NodeEventTypeEnum eventType) {
    super(eventType);
  }

  /** 构造方法 */
  public AutoNodeEvent(NodeEventTypeEnum eventType, Throwable exception) {
    super(eventType, exception);
  }

  /**
   * 创建一个成功事件
   *
   * @return
   */
  public static AutoNodeEvent createSuccessEvent() {
    return new AutoNodeEvent(SUCCESS);
  }

  /**
   * 创建一个失败事件
   *
   * @return
   */
  public static AutoNodeEvent createFailEvent() {
    return new AutoNodeEvent(FAIL);
  }

  /**
   * 创建一个等待事件
   *
   * @return
   */
  public static AutoNodeEvent createWaitEvent() {
    return new AutoNodeEvent(WAIT);
  }

  /**
   * 创建一个终止事件
   *
   * @param exception
   * @return
   */
  public static AutoNodeEvent createTerminalEvent(Throwable exception) {
    return new AutoNodeEvent(TERMINAL, exception);
  }

  /**
   * 创建一个运行中事件
   *
   * @return
   */
  public static AutoNodeEvent createRunningEvent() {
    return new AutoNodeEvent(RUNNING, new RuntimeException("节点运行中..."));
  }

  /**
   * 创建一个完成事件
   *
   * @return
   */
  public static AutoNodeEvent createCompleteEvent() {
    return new AutoNodeEvent(COMPLETE);
  }
}
