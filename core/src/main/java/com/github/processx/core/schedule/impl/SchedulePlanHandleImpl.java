/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.schedule.impl;

import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.executor.SynProcessExecutor;
import com.github.processx.core.schedule.SchedulePlanHandle;
import com.github.processx.core.schedule.SchedulePlanService;
import com.github.processx.core.schedule.ScheduleResult;
import com.github.processx.core.schedule.lock.SchedulePlanLock;
import com.github.processx.core.service.ProcessTracker;
import com.github.processx.core.threadpool.MonitorThreadPoolExecutor;
import com.github.processx.core.threadpool.ThreadFactoryBuilder;
import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程定时任务处理器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 18:20
 */
public class SchedulePlanHandleImpl implements SchedulePlanHandle {

  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(SchedulePlanHandleImpl.class);

  private static ExecutorService schedulePlanExecutorThreadPool =
      new MonitorThreadPoolExecutor(60, new ThreadFactoryBuilder("SchedulePlanExecutorThreadPool"));

  @Autowired private ProcessTracker processTracker;

  @Autowired private SchedulePlanService schedulePlanService;

  @Autowired private SchedulePlanLock schedulePlanLock;

  @Autowired private SynProcessExecutor synProcessExecutor;

  /** 定时处理 */
  @Override
  public void doJob() {
    List<ProcessSchedulePlanDO> schedulePlanList = schedulePlanService.getSchedulePlan();
    for (ProcessSchedulePlanDO schedulePlan : schedulePlanList) {
      schedulePlanExecutorThreadPool.submit(
          () -> {
            boolean lock = schedulePlanLock.getLock(schedulePlan);

            if (lock) {
              ScheduleResult result =
                  synProcessExecutor.exectionSchedule(
                      schedulePlan.getBizNo(),
                      schedulePlan.getNodeId(),
                      schedulePlan.getExecCounts());

              LoggerUtil.info(LOGGER, "ScheduleResult============={0}", result);

              if (result == null) {
                // TODO 打印日志
                return;
              }

              // 执行成功
              if (result.isExecResult()) {
                schedulePlanService.deleteSchedulePlan(
                    schedulePlan.getBizNo(), schedulePlan.getNodeId());
              } else if (result.isReachMaxCount()) {
                schedulePlanService.deleteSchedulePlan(
                    schedulePlan.getBizNo(), schedulePlan.getNodeId());
              } else {
                // TODO 执行次数+1
                schedulePlan.setExecCounts(schedulePlan.getExecCounts() + 1);
                schedulePlanService.moditySchedulePlan(schedulePlan);
              }
            }
          });
    }
  }
}
