/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.schedule.lock;

import com.github.processx.core.schedule.SchedulePlanService;
import com.github.processx.core.schedule.enums.StatusEnum;
import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务锁
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/3 23:33
 */
public class SchedulePlanLock {

  /** 定时任务服务类 */
  @Autowired private SchedulePlanService processSchedulePlanService;

  /**
   * 获取定时任务锁
   *
   * @param schedulePlan
   * @return
   */
  public boolean getLock(ProcessSchedulePlanDO schedulePlan) {
    // 默认超时时间10秒
    if (StringUtils.equals(schedulePlan.getStatus(), StatusEnum.RUNNING.getStatus())
        && System.currentTimeMillis() - schedulePlan.getModifiedTime().getTime() > 10000) {
      return processSchedulePlanService.moditySchedulePlanupdateRunningModifiedTime4Lock(
          schedulePlan.getBizNo(), schedulePlan.getNodeId(), schedulePlan.getModifiedTime());
    } else if (StringUtils.equals(schedulePlan.getStatus(), StatusEnum.RUNNING.getStatus())) {
      return false;
    } else {
      return processSchedulePlanService.moditySchedulePlanReday2Running4Lock(
          schedulePlan.getBizNo(), schedulePlan.getNodeId());
    }
  }
}
