/** Lingxi.com Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.schedule.enums;

import lombok.Getter;

/**
 * 定时任务执行状态枚举
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/3 23:18
 */
@Getter
public enum StatusEnum {

  /** 定时任务初始准备 */
  READY("READY", "定时任务初始准备"),

  /** 定时任务运行中 */
  RUNNING("RUNNING", "定时任务运行中"),
  ;
  /** 定时任务执行状态 */
  private String status;

  /** 定时任务执行状态描述 */
  private String desc;

  /**
   * 构造方法
   *
   * @param status
   * @param desc
   */
  StatusEnum(String status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
