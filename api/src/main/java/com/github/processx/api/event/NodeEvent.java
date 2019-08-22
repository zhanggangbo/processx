/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api.event;

import static com.github.processx.api.event.enums.NodeEventTypeEnum.FAIL;

import com.github.processx.api.event.enums.NodeEventTypeEnum;
import lombok.Getter;

/**
 * 节点事件定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/31 23:18
 */
@Getter
public class NodeEvent extends Event {
  /**
   * 事件类型
   *
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  private NodeEventTypeEnum eventType;
  /** 异常抛出 */
  private Throwable exception;

  /**
   * 构造方法
   *
   * @param eventType
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public NodeEvent(NodeEventTypeEnum eventType) {
    this.eventType = eventType;
  }

  /**
   * 构造方法
   *
   * @param eventType
   * @param exception
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public NodeEvent(NodeEventTypeEnum eventType, Throwable exception) {
    this.eventType = eventType;
    this.exception = exception;
  }

  /**
   * 创建一个失败事件
   */
  public static NodeEvent createFailEvent() {
    return new NodeEvent(FAIL);
  }
}
