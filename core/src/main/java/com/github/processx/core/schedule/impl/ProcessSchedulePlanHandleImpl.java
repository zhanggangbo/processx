/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.schedule.impl;

import com.github.processx.core.schedule.ProcessSchedulePlanHandle;

/**
 * 流程定时任务处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 18:20
 */
public class ProcessSchedulePlanHandleImpl implements ProcessSchedulePlanHandle {
  /** 定时去DB中加载被更新的配置 判断条件 TIMESTAMPDIFF(MINUTE,gmt_modified,now())<5 */
  @Override
  public void doJob() {
  }
}
