/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api.event;

import static com.github.processx.api.event.enums.NodeEventTypeEnum.COMPLETE;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.FAIL;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.RUNNING;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.SUCCESS;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.TERMINAL;
import static com.github.processx.api.event.enums.NodeEventTypeEnum.WAIT;

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
   * 判断是否为成功事件
   *
   * @param eventType
   * @return
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public static boolean isSuccessEvent(NodeEventTypeEnum eventType) {
    return SUCCESS == eventType;
  }

  /**
   * 判断是否为失败事件
   *
   * @param eventType
   * @return
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public static boolean isFailEvent(NodeEventTypeEnum eventType) {
    return FAIL == eventType;
  }

  /**
   * 判断是否为等待事件
   *
   * @param eventType
   * @return
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public static boolean isWaitEvent(NodeEventTypeEnum eventType) {
    return WAIT == eventType;
  }

  /**
   * 判断是否为终止事件
   *
   * @param eventType
   * @return
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public static boolean isTerminalEvent(NodeEventTypeEnum eventType) {
    return TERMINAL == eventType;
  }

  /**
   * 判断是否为运行中事件
   *
   * @param eventType
   * @return
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public static boolean isRunningEvent(NodeEventTypeEnum eventType) {
    return RUNNING == eventType;
  }

  /**
   * 判断是否为完成事件
   *
   * @param eventType
   * @return
   * @see com.github.processx.api.event.enums.NodeEventTypeEnum
   */
  public static boolean isCompleteEvent(NodeEventTypeEnum eventType) {
    return COMPLETE == eventType;
  }
}
