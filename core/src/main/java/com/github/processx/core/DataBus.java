/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import com.github.processx.core.schedule.ScheduleResult;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务数据
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/15 13:33
 */
@Setter
@Getter
public class DataBus {
  /** 业务流水号 */
  private String bizNo;
  /** 流程入参 */
  private Map<String, Object> processInput;
  /** 流程触发入参 */
  private Map<String, Object> triggerInput;
  /** 定时任务执行结果 */
  private ScheduleResult scheduleResult;
}
