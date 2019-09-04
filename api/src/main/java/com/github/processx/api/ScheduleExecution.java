/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api;

import com.github.processx.api.event.ScheduleNodeEvent;

/**
 * 定时节点执行接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/31 23:01
 */
public interface ScheduleExecution extends Execution {
  /**
   * 定时节点执行方法
   *
   * @param context 节点上下文
   * @return
   */
  ScheduleNodeEvent execute(NodeContext context);

  /**
   * 获取执行周期
   *
   * @return 执行周期（单位：秒）
   */
  Integer getPeriod();

  /**
   * 获取最大执行次数
   *
   * @return 最大执行次数
   */
  Integer getMaxExecCount();
}
