/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api.demo;

import com.github.processx.api.NodeContext;
import com.github.processx.api.ScheduleExecution;
import com.github.processx.api.event.ScheduleNodeEvent;
import com.github.processx.common.util.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zhanggangbo
 * @version v 0.1 2019/8/4 14:12
 */
public class CreditQueryExecution implements ScheduleExecution {
  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(CreditQueryExecution.class);
  /**
   * 定时节点执行方法
   *
   * @param context 节点上下文
   */
  @Override
  public ScheduleNodeEvent execute(NodeContext context) {
    LoggerUtil.info(LOGGER, "CreditQueryExecution 6");
    return ScheduleNodeEvent.createSuccessEvent();
  }

  /**
   * 获取执行周期
   *
   * @return 执行周期（单位：秒）
   */
  @Override
  public Integer getPeriod() {
    return 30;
  }

  /**
   * 获取最大执行次数
   *
   * @return 最大执行次数
   */
  @Override
  public Integer getMaxExecCount() {
    return 3;
  }
}
