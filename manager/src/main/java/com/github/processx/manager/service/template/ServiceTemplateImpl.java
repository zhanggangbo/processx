/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.manager.service.template;

import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.common.util.LoggerUtil;
import com.github.processx.manager.service.result.CommonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 接口服务模板服务类实现
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public class ServiceTemplateImpl implements ServiceTemplate {

  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(ServiceTemplateImpl.class);
  /** processx数据库事务模板 */
  @Autowired private TransactionTemplate processxTransactionTemplate;

  /**
   * 有事务模板
   *
   * @param result 返回结果集
   * @param mark 标识信息
   * @param param 入参信息 eg: {"key":"value","key1":"value1"}
   * @param action 回调函数
   * @param <T> 泛结果集
   */
  @Override
  public <T extends CommonResult> void execute(
      T result, String mark, String param, ServiceCallback action) {

    processxTransactionTemplate.execute(
        (status) -> {
          try {
            /** 获取业务执行锁，对于获取锁失败，异常驱动返回 */
            action.doLock();
            /** 入参校验，对于校验不通过，异常驱动返回 */
            action.check();
            /** 执行业务逻辑 */
            action.execute();
            /** 结果封装 */
            result.setSuccessMessage();
          } catch (ProcessxException e) {
            /** 业务异常 */
            LoggerUtil.error(
                LOGGER,
                "mark={0},param={1},resultCode= {2},resultMsg={3}",
                mark,
                param,
                e.getResultCode(),
                e.getResultMsg());
            result.setErrorMessage(e.getResultCode(), e.getResultMsg());

          } catch (Throwable e2) {
            /** 未知异常 */
            LoggerUtil.error(LOGGER, e2, "mark={0},param={1}", mark, param);
            status.setRollbackOnly();

            result.setErrorMessage(
                ProcessxResultEnum.SYSTEM_ERROR.getCode(),
                ProcessxResultEnum.SYSTEM_ERROR.getDescription());
          }

          return result;
        });
  }

  /**
   * 无事务模板
   *
   * @param result 返回结果集
   * @param mark 标识信息
   * @param param 入参信息 eg: {"key":"value","key1":"value1"}
   * @param action 回调函数
   * @param <T> 泛结果集
   */
  @Override
  public <T extends CommonResult> void executeWithoutTransaction(
      T result, String mark, String param, ServiceCallback action) {
    try {
      /** 获取业务执行锁，对于获取锁失败，异常驱动返回 */
      action.check();
      /** 入参校验，对于校验不通过，异常驱动返回 */
      action.doLock();
      /** 结果封装 */
      action.execute();
      /** 结果封装 */
      result.setSuccessMessage();
    } catch (ProcessxException e) {
      /** 业务异常 */
      LoggerUtil.error(
          LOGGER,
          "mark={0},param={1},resultCode= {2},resultMsg={3}",
          mark,
          param,
          e.getResultCode(),
          e.getResultMsg());
      result.setErrorMessage(e.getResultCode(), e.getResultMsg());
    } catch (Throwable e2) {
      /** 未知异常 */
      LoggerUtil.error(LOGGER, e2, "mark={0},param={1}", mark, param);
      result.setErrorMessage(
          ProcessxResultEnum.SYSTEM_ERROR.getCode(),
          ProcessxResultEnum.SYSTEM_ERROR.getDescription());
    }
  }
}
