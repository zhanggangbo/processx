/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.manager.service.template;

import com.github.processx.manager.service.result.CommonResult;
/**
 * 服务模板类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public interface ServiceTemplate {

  /**
   * 无事务模板
   *
   * @param result 返回结果集
   * @param mark 标识信息
   * @param param 入参信息 eg: {"key":"value","key1":"value1"}
   * @param action 回调函数
   * @param <T>
   */
  <T extends CommonResult> void executeWithoutTransaction(
      T result, String mark, String param, ServiceCallback action);

  /**
   * 有事务模板
   *
   * @param result 返回结果集
   * @param mark 标识信息
   * @param param 入参信息 eg: {"key":"value","key1":"value1"}
   * @param action 回调函数
   * @param <T>
   */
  <T extends CommonResult> void execute(
      T result, String mark, String param, ServiceCallback action);
}
