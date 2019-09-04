/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.schedule.lock;

import com.github.processx.common.DateUtil;
import com.github.processx.core.schedule.ProcessSchedulePlanService;
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
  @Autowired
  private ProcessSchedulePlanService processSchedulePlanService;

  /**
   * 获取定时任务锁
   *
   * @param schedulePlan
   * @return
   */
  public boolean getLock(ProcessSchedulePlanDO schedulePlan) {
    // 默认超时时间10秒
    if (StringUtils.equals(schedulePlan.getStatus(), StatusEnum.RUNNING.getStatus())
      && schedulePlan.getModifiedTime().getTime() - DateUtil.getCurrentTime().getTime() > 10000) {
      return processSchedulePlanService.moditySchedulePlanStatus(
        schedulePlan.getBizNo(), schedulePlan.getNodeId(), StatusEnum.READY);
    } else if (StringUtils.equals(schedulePlan.getStatus(), StatusEnum.RUNNING.getStatus())) {
      return false;
    } else {
      return false;
    }
  }
}
