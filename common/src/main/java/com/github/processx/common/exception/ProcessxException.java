/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 异常处理基类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
@Getter
@Setter
public class ProcessxException extends RuntimeException {

  /** 结果码 */
  private String resultCode = "UN_KNOWN_EXCEPTION";

  /** 结果信息 */
  private String resultMsg = "未知异常";

  /** 创建一个ProcessxException对象 */
  public ProcessxException() {
    super();
  }

  /**
   * 创建一个ProcessxException
   *
   * @param resultCode 异常结果码
   */
  public ProcessxException(String resultCode) {
    super(resultCode);
    this.resultCode = resultCode;
  }

  /**
   * 创建一个ProcessxException
   *
   * @param processxResultEnum 业务结果枚举
   */
  public ProcessxException(ProcessxResultEnum processxResultEnum) {
    super(processxResultEnum.getCode());
    this.resultCode = processxResultEnum.getCode();
    this.resultMsg = processxResultEnum.getDescription();
  }

  /**
   * 创建一个ProcessxException
   *
   * @param processxResultEnum 业务结果枚举
   * @param expandMsg 异常拓展信息
   */
  public ProcessxException(ProcessxResultEnum processxResultEnum, String expandMsg) {
    super(processxResultEnum.getCode());
    this.resultCode = processxResultEnum.getCode();
    this.resultMsg = processxResultEnum.getDescription() + "," + expandMsg;
  }

  /**
   * 创建一个ProcessxException
   *
   * @param resultCode 异常结果码
   * @param resultMsg 异常结果信息
   */
  public ProcessxException(String resultCode, String resultMsg) {
    super(resultCode);
    this.resultCode = resultCode;
    this.resultMsg = resultMsg;
  }

  /**
   * 创建一个ProcessxException
   *
   * @param cause 异常原因
   */
  public ProcessxException(Throwable cause) {
    super(cause);
  }

  /**
   * 创建一个ProcessxException
   *
   * @param resultCode 异常结果码
   * @param cause 异常原因
   */
  public ProcessxException(String resultCode, Throwable cause) {
    super(resultCode, cause);
    this.resultCode = resultCode;
  }

  /**
   * 创建一个ProcessxException
   *
   * @param processxResultEnum
   * @param expandMsg
   * @param cause
   */
  public ProcessxException(
      ProcessxResultEnum processxResultEnum, String expandMsg, Throwable cause) {
    super(processxResultEnum.getCode(), cause);
    this.resultCode = processxResultEnum.getCode();
    this.resultMsg = processxResultEnum.getDescription() + "," + expandMsg;
  }

  /**
   * 创建一个ProcessxException
   *
   * @param processxResultEnum 业务结果枚举
   * @param cause 异常原因
   */
  public ProcessxException(ProcessxResultEnum processxResultEnum, Throwable cause) {
    super(processxResultEnum.getCode(), cause);
    this.resultCode = processxResultEnum.getCode();
    this.resultMsg = processxResultEnum.getDescription();
  }
}
