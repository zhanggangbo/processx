/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.schedule.impl;

import com.github.processx.core.executor.SynProcessExecutor;
import com.github.processx.core.schedule.ProcessSchedulePlanHandle;
import com.github.processx.core.schedule.ProcessSchedulePlanService;
import com.github.processx.core.schedule.ScheduleResult;
import com.github.processx.core.schedule.lock.SchedulePlanLock;
import com.github.processx.core.service.ProcessTracker;
import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程定时任务处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 18:20
 */
public class ProcessSchedulePlanHandleImpl implements ProcessSchedulePlanHandle {
  @Autowired
  private ProcessTracker processTracker;

  @Autowired
  private ProcessSchedulePlanService processSchedulePlanService;

  @Autowired
  private SchedulePlanLock schedulePlanLock;

  @Autowired
  private SynProcessExecutor synProcessExecutor;

  /** 定时去DB中加载被更新的配置 判断条件 TIMESTAMPDIFF(MINUTE,gmt_modified,now())<5 */
  @Override
  public void doJob() {
    List<ProcessSchedulePlanDO> schedulePlanList = processSchedulePlanService.getSchedulePlan();
    for (ProcessSchedulePlanDO schedulePlan : schedulePlanList) {

      boolean lock = schedulePlanLock.getLock(schedulePlan);

      if (lock) {
        ScheduleResult result =
          synProcessExecutor.exectionSchedule(
            schedulePlan.getBizNo(), schedulePlan.getNodeId(), schedulePlan.getExecCounts());

        if (result == null) {
          // TODO 打印日志
          return;
        }

        // 执行成功
        if (result.isExecResult()) {
          processSchedulePlanService.deleteSchedulePlan(
            schedulePlan.getBizNo(), schedulePlan.getNodeId());
        } else if (result.isReachMaxCount()) {
          processSchedulePlanService.deleteSchedulePlan(
            schedulePlan.getBizNo(), schedulePlan.getNodeId());
        } else {
          // TODO 执行次数+1
        }
      }
    }
  }
}
