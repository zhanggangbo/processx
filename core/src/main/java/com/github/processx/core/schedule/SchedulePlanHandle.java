/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.schedule;

/**
 * 流程定时任务处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 18:15
 */
public interface SchedulePlanHandle {

  /** 定时处理 */
  void doJob();
}
