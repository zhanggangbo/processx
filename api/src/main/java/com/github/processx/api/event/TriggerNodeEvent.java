/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api.event;

import static com.github.processx.api.event.enums.NodeEventTypeEnum.COMPLETE;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.RUNNING;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.SUCCESS;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.WAIT;

import com.github.processx.api.event.enums.NodeEventTypeEnum;

/**
 * 触发（被动）节点事件定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/3 20:51
 * @see com.github.processx.api.event.NodeEvent
 */
public class TriggerNodeEvent extends NodeEvent {
  /**
   * 构造方法
   *
   * @see NodeEventTypeEnum
   */
  public TriggerNodeEvent(NodeEventTypeEnum eventType) {
    super(eventType);
  }

  /**
   * 构造方法
   *
   * @see NodeEventTypeEnum
   */
  public TriggerNodeEvent(NodeEventTypeEnum eventType, Throwable exception) {
    super(eventType, exception);
  }

  /**
   * 创建一个成功事件
   *
   * @return
   */
  public static TriggerNodeEvent createSuccessEvent() {
    return new TriggerNodeEvent(SUCCESS);
  }

  /**
   * 创建一个等待事件
   */
  public static TriggerNodeEvent createWaitEvent() {
    return new TriggerNodeEvent(WAIT);
  }

  /**
   * 创建一个等待事件
   */
  public static TriggerNodeEvent createWaitEvent(Throwable exception) {
    return new TriggerNodeEvent(WAIT, exception);
  }

  /**
   * 创建一个运行中事件
   */
  public static TriggerNodeEvent createRunningEvent() {
    return new TriggerNodeEvent(RUNNING, new RuntimeException("节点运行中..."));
  }

  /**
   * 创建一个完成事件
   */
  public static TriggerNodeEvent createCompleteEvent() {
    return new TriggerNodeEvent(COMPLETE);
  }
}
