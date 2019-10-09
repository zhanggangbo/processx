/**
 * GitHub. Inc.
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhanggangbo
 * @version v 0.1 2019/9/22 16:29
 */
@Setter
@Getter
public class TriggerResult {
  private Long processInstanceId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_trigger_result.biz_no
   *
   * @mbggenerated
   */
  private String bizNo;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_trigger_result.node_id
   *
   * @mbggenerated
   */
  private Long nodeId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column process_trigger_result.value
   *
   * @mbggenerated
   */
  private String value;
}