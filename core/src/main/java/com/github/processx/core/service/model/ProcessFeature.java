/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 流程特征
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/25 22:34
 */
@Getter
@Setter
public class ProcessFeature {

  /** 是否记录流程实例 */
  private Boolean recordProcessInstance;

  /** 是否记录节点实例 */
  private Boolean recordNodeInstance;

  /** 是否记录全局变量 */
  private Boolean recordGlobalParam;

  /** 是否记录流程输入 */
  private Boolean recordProcessInput;

  /** 是否记录触发节点输入 */
  private Boolean recordTriggerInput;

  /** 是否记录触发节点执行节点 */
  private Boolean recordTriggerResult;
}
