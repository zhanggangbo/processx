/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.common.enums;

import lombok.Getter;

/**
 * 日志枚举
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/14 22:21
 */
@Getter
public enum LoggerEnum {
  /** DAL层操作明细 */
  COMMON_DAL_DIGEST("COMMON-DAL-DIGEST", "DAL层操作明细"),
  /** 核心层操作明细 */
  COMMON_CORE_DIGEST("COMMON-CORE-DIGEST", "核心层操作明细"),
  /** 节点操作明细 */
  NODE_DIGEST("NODE-DIGEST", "节点操作明细"),
  /** 流程操作明细 */
  PROCESS_DIGEST("PROCESS-DIGEST", "流程操作明细"),
  /** 定时任务操作明细 */
  SCHEDULE_DIGEST("SCHEDULE-DIGEST", "定时任务操作明细"),
  /** 线程池操作明细 */
  THREAD_POOL_DIGEST("THREAD-POOL-DIGEST", "线程池操作明细"),
  ;

  /** 节点类型 */
  private String logger;
  /** 节点类型描述 */
  private String desc;

  /**
   * 构造方法
   *
   * @param logger
   * @param desc
   */
  LoggerEnum(String logger, String desc) {
    this.logger = logger;
    this.desc = desc;
  }
}
