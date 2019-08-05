/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api.event;

import static com.github.processx.api.event.enums.NodeEventTypeEnum.SUCCESS;

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
   * @return
   */
  public static GatewayNodeEvent createSuccessEvent() {
    return new GatewayNodeEvent(SUCCESS);
  }
}
