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
 * 定时节点事件定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/3 20:51
 * @see com.github.processx.api.event.NodeEvent
 */
public class ScheduleNodeEvent extends NodeEvent {
  /**
   * 构造方法
   *
   * @see NodeEventTypeEnum
   */
  public ScheduleNodeEvent(NodeEventTypeEnum eventType) {
    super(eventType);
  }

  /**
   * 构造方法
   *
   * @see NodeEventTypeEnum
   */
  public ScheduleNodeEvent(NodeEventTypeEnum eventType, Throwable exception) {
    super(eventType, exception);
  }

  /**
   * 创建一个成功事件
   *
   * @return
   */
  public static ScheduleNodeEvent createSuccessEvent() {
    return new ScheduleNodeEvent(SUCCESS);
  }

  /**
   * 创建一个失败事件
   */
  public static ScheduleNodeEvent createFailEvent() {
    return new ScheduleNodeEvent(FAIL);
  }

  /** 创建一个等待事件 */
  public static ScheduleNodeEvent createWaitEvent() {
    return new ScheduleNodeEvent(WAIT);
  }

  /** 创建一个等待事件 */
  public static ScheduleNodeEvent createWaitEvent(Throwable exception) {
    return new ScheduleNodeEvent(WAIT, exception);
  }

  /** 创建一个终止事件 */
  public static ScheduleNodeEvent createTerminalEvent() {
    return new ScheduleNodeEvent(TERMINAL);
  }

  /** 创建一个终止事件 */
  public static ScheduleNodeEvent createTerminalEvent(Throwable exception) {
    return new ScheduleNodeEvent(TERMINAL, exception);
  }

  /** 创建一个运行中事件 */
  public static ScheduleNodeEvent createRunningEvent() {
    return new ScheduleNodeEvent(RUNNING, new RuntimeException("节点运行中..."));
  }

  /** 创建一个完成事件 */
  public static ScheduleNodeEvent createCompleteEvent() {
    return new ScheduleNodeEvent(COMPLETE);
  }
}
