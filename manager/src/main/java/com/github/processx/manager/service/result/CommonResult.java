/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.manager.service.result;

/**
 * 通用返回结果基类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public abstract class CommonResult {

  /** 成功结果信息设置 */
  public abstract void setSuccessMessage();

  /**
   * 异常结果信息设置
   *
   * @param resultCode 异常结果码
   * @param resultMsg 异常结果信息
   */
  public abstract void setErrorMessage(String resultCode, String resultMsg);
}
