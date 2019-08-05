/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api.event;

import static com.github.processx.api.event.enums.NodeEventTypeEnum.SUCCESS;

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
}
