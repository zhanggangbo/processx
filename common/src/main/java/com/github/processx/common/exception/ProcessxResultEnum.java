/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.common.exception;

import lombok.Getter;

/**
 * 业务结果枚举
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
@Getter
public enum ProcessxResultEnum {

  /** 处理成功 */
  SUCCESS("SUCCESS", "处理成功"),

  /** 系统异常 */
  SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),

  /** 程序内部逻辑错误 */
  LOGIC_ERROR("LOGIC_ERROR", "程序内部逻辑错误"),

  /** 参数为空 */
  NULL_ARGUMENT("NULL_ARGUMENT", "参数为空"),

  /** 参数不正确 */
  ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", "参数不正确"),
  ;

  /** 枚举编号 */
  private String code;

  /** 枚举详情 */
  private String description;

  /**
   * 构造方法
   *
   * @param code 枚举编号
   * @param description 枚举详情
   */
  ProcessxResultEnum(String code, String description) {
    this.code = code;
    this.description = description;
  }
}
