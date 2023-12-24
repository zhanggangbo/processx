/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
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

  /** 流程信息不存在 */
  PROCESS_DADA_NO_EXIST("PROCESS_DADA_NO_EXIST", "流程信息不存在"),

  /** 获取指定名称bean实例失败 */
  GET_BEAN_INSTANCE_FAIL("GET_BEAN_INSTANCE_FAIL", "获取指定名称bean实例失败"),

  /** 流程节点类型不正确 */
  ILLEGAL_PROCESS_NODE_TYPE("ILLEGAL_PROCESS_NODE_TYPE", "流程节点类型不正确"),

  /** 执行组件不存在 */
  EXECUTE_COMPOMENT_NO_EXIST("EXECUTE_COMPOMENT_NO_EXIST", "执行组件不存在"),

  /** 下一个执行节点不存在 */
  NEXT_EXECUTE_NODE_NO_EXIST("NEXT_EXECUTE_NODE_NO_EXIST", "下一个执行节点不存在"),

  /** 业务内部异常 */
  BIZ_INNER_EXCEPTION("BIZ_INNER_EXCEPTION", "业务内部异常"),

  /** 流程节点运行中 */
  NODE_RUNNING("NODE_RUNNING", "流程节点运行中"),
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
