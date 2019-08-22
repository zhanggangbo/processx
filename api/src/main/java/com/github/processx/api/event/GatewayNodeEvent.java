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
 * 网关节点事件定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/3 20:23
 * @see com.github.processx.api.event.NodeEvent
 */
@Getter
public class GatewayNodeEvent extends NodeEvent {
  /** 下一个流程节点名称 */
  private String nextNodeName;
  /**
   * 构造方法
   *
   * @see NodeEventTypeEnum
   */
  public GatewayNodeEvent(NodeEventTypeEnum eventType) {
    super(eventType);
  }

  /**
   * 构造方法
   *
   * @see NodeEventTypeEnum
   */
  public GatewayNodeEvent(NodeEventTypeEnum eventType, Throwable exception) {
    super(eventType, exception);
  }

  /**
   * 构造方法
   *
   * @param eventType
   * @param nextNodeName
   * @see NodeEventTypeEnum
   */
  public GatewayNodeEvent(NodeEventTypeEnum eventType, String nextNodeName) {
    super(eventType);
    this.nextNodeName = nextNodeName;
  }

  /**
   * 创建一个成功事件
   *
   * @param nextNodeName
   * @return
   */
  public static GatewayNodeEvent createSuccessEvent(String nextNodeName) {
    return new GatewayNodeEvent(SUCCESS, nextNodeName);
  }

  /**
   * 创建一个完成事件
   */
  public static GatewayNodeEvent createCompleteEvent(String nextNodeName) {
    return new GatewayNodeEvent(COMPLETE, nextNodeName);
  }

  /**
   * 创建一个失败事件
   */
  public static GatewayNodeEvent createFailEvent() {
    return new GatewayNodeEvent(FAIL);
  }

  /**
   * 创建一个等待事件
   */
  public static GatewayNodeEvent createWaitEvent() {
    return new GatewayNodeEvent(WAIT);
  }

  /**
   * 创建一个等待事件
   */
  public static GatewayNodeEvent createWaitEvent(Throwable exception) {
    return new GatewayNodeEvent(WAIT, exception);
  }

  /**
   * 创建一个终止事件
   */
  public static GatewayNodeEvent createTerminalEvent() {
    return new GatewayNodeEvent(TERMINAL);
  }

  /**
   * 创建一个终止事件
   */
  public static GatewayNodeEvent createTerminalEvent(Throwable exception) {
    return new GatewayNodeEvent(TERMINAL, exception);
  }

  /**
   * 创建一个运行中事件
   */
  public static GatewayNodeEvent createRunningEvent() {
    return new GatewayNodeEvent(RUNNING, new RuntimeException("节点运行中..."));
  }
}
