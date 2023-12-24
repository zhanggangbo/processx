/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.schedule;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时节点执行结果集封装
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/24 11:57
 */
@Getter
@Setter
public class ScheduleResult {

  /** 是否执行成功 */
  private boolean execResult;

  /** 是否已经到底最大执行次数 */
  private boolean reachMaxCount;

  /** 执行周期 */
  private Integer period;
}
