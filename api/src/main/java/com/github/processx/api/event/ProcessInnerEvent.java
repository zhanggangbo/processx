/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.api.event;

import static com.github.processx.api.event.enums.ProcessInnerEventTypeEnum.RESUME;
import static com.github.processx.api.event.enums.ProcessInnerEventTypeEnum.SCHEDULE;
import static com.github.processx.api.event.enums.ProcessInnerEventTypeEnum.START;
import static com.github.processx.api.event.enums.ProcessInnerEventTypeEnum.TERMINAL;
import static com.github.processx.api.event.enums.ProcessInnerEventTypeEnum.TRIGGER;

import com.github.processx.api.event.enums.ProcessInnerEventTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 流程事件
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 15:13
 */
@Setter
@Getter
public class ProcessInnerEvent extends Event {
  /** 节点名称 */
  private String nodeName;
  /**
   * 事件类型
   *
   * @see com.github.processx.api.event.enums.ProcessInnerEventTypeEnum
   */
  private ProcessInnerEventTypeEnum eventType;
  /** 定时执行次数 */
  private int scheduleExecCounts;

  /**
   * 构造方法
   */
  public ProcessInnerEvent(ProcessInnerEventTypeEnum eventType) {
    this.eventType = eventType;
  }

  /**
   * 构造方法
   *
   * @param nodeName
   * @param eventType
   */
  public ProcessInnerEvent(String nodeName, ProcessInnerEventTypeEnum eventType) {
    this.nodeName = nodeName;
    this.eventType = eventType;
  }

  /**
   * 构造方法
   *
   * @param nodeName
   * @param eventType
   * @param scheduleExecCounts
   */
  public ProcessInnerEvent(
    String nodeName, ProcessInnerEventTypeEnum eventType, int scheduleExecCounts) {
    this.nodeName = nodeName;
    this.eventType = eventType;
    this.scheduleExecCounts = scheduleExecCounts;
  }

  /**
   * 创建一个开始事件
   *
   * @param nodeName
   * @return
   */
  public static ProcessInnerEvent createStartEvent(String nodeName) {
    return new ProcessInnerEvent(nodeName, START);
  }

  /**
   * 创建一个触发事件
   *
   * @param nodeName
   * @return
   */
  public static ProcessInnerEvent createTriggerEvent(String nodeName) {
    return new ProcessInnerEvent(nodeName, TRIGGER);
  }

  /**
   * 创建一个重试事件
   *
   * @param nodeName
   * @return
   */
  public static ProcessInnerEvent createResumeEvent(String nodeName) {
    return new ProcessInnerEvent(nodeName, RESUME);
  }

  /**
   * 创建一个定时事件
   *
   * @param nodeName
   * @param scheduleExecCounts
   * @return
   */
  public static ProcessInnerEvent createScheduleEvent(String nodeName, int scheduleExecCounts) {
    return new ProcessInnerEvent(nodeName, SCHEDULE, scheduleExecCounts);
  }

  /**
   * 创建一个终止事件
   *
   * @param nodeName
   * @return
   */
  public static ProcessInnerEvent createTerminalEvent(String nodeName) {
    return new ProcessInnerEvent(nodeName, TERMINAL);
  }
}
