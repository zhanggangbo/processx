/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.api.event.enums;

import lombok.Getter;

/**
 * 节点事件类型枚举
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/3 19:35
 */
@Getter
public enum NodeEventTypeEnum {
  /** 成功事件 */
  SUCCESS("SUCCESS", "成功事件"),
  /** 等待事件 */
  FAIL("FAIL", "失败事件"),
  /** */
  WAIT("WAIT", "等待事件"),
  /** 终止事件 */
  TERMINAL("TERMINAL", "终止事件"),
  /** 运行中事件 */
  RUNNING("RUNNING", "运行中事件"),
  /** 完成事件 */
  COMPLETE("COMPLETE", "完成事件"),
  ;
  /** 节点事件类型 */
  private String eventType;
  /** 节点事件类型描述 */
  private String desc;

  /**
   * 构造方法
   *
   * @param eventType
   * @param desc
   */
  NodeEventTypeEnum(String eventType, String desc) {
    this.eventType = eventType;
    this.desc = desc;
  }
}
