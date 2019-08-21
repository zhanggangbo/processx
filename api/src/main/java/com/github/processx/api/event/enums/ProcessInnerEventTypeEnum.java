/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.api.event.enums;

/**
 * 流程事件类型枚举
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/6 21:08
 */
public enum ProcessInnerEventTypeEnum {

  /** 开始事件 */
  START("START", "开始事件"),
  /** 触发事件 */
  TRIGGER("TRIGGER", "触发事件"),
  /** 重试事件 */
  RESUME("RESUME", "重试事件"),
  /** 定时事件 */
  SCHEDULE("SCHEDULE", "定时事件"),
  /** 终止事件 */
  TERMINAL("TERMINAL", "终止事件"),
  ;
  /** 流程事件类型 */
  private String eventType;
  /** 流程事件类型描述 */
  private String desc;

  /**
   * 构造方法
   *
   * @param eventType
   * @param desc
   */
  ProcessInnerEventTypeEnum(String eventType, String desc) {
    this.eventType = eventType;
    this.desc = desc;
  }
}
