/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import com.github.processx.core.schedule.ScheduleResult;
import java.util.Map;

/**
 * 业务数据辅助类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/15 12:53
 */
public class DataHelper {
  protected static ThreadLocal<DataBus> dataHelper = new ThreadLocal<>();

  /**
   * 流程启动数据初始
   *
   * @param bizNo
   * @param processInput
   */
  protected static void init(String bizNo, Map<String, Object> processInput) {
    DataBus data = new DataBus();
    data.setBizNo(bizNo);
    data.setProcessInput(processInput);
    data.setTriggerInput(processInput);
    dataHelper.set(data);
  }

  /**
   * 流程触发启动数据初始
   *
   * @param bizNo
   */
  protected static void init(String bizNo) {
    DataBus data = new DataBus();
    data.setBizNo(bizNo);
    dataHelper.set(data);
  }

  /**
   * 设置定时任务执行结果
   *
   * @param scheduleResult
   */
  protected static void setScheduleResult(ScheduleResult scheduleResult) {
    DataBus dataBus = dataHelper.get();
    dataBus.setScheduleResult(scheduleResult);
  }

  /**
   * 获取定时任务执行结果
   *
   * @return
   */
  protected ScheduleResult getScheduleResult() {
    DataBus dataBus = dataHelper.get();
    return dataBus.getScheduleResult();
  }
}
