/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.log.interceptor;

import com.github.processx.common.util.LoggerUtil;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 数据库操作日志拦截输出
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 21:29
 */
public class DalLogInterceptor implements MethodInterceptor {

  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger("common-dal-digest");

  /**
   * Implement this method to perform extra treatments before and after the invocation. Polite
   * implementations would certainly like to invoke {@link Joinpoint#proceed()}.
   *
   * @param invocation the method invocation joinpoint
   * @return the result of the call to {@link Joinpoint#proceed()}; might be intercepted by the
   *     interceptor
   * @throws Throwable if the interceptors or the target object throws an exception
   */
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Object result;

    Method method = invocation.getMethod();
    String interfaceName = method.getDeclaringClass().getName();
    String methodName = method.getName();

    long start = System.currentTimeMillis();

    try {
      result = invocation.proceed();
      long end = System.currentTimeMillis();
      LoggerUtil.info(
          LOGGER, "{0},{1},{2},{3},{4}", "processx", interfaceName, methodName, "S", end - start);
    } catch (Throwable throwable) {
      long end = System.currentTimeMillis();
      LoggerUtil.error(
          LOGGER, "{0},{1},{2},{3},{4}", "processx", interfaceName, methodName, "F", end - start);
      throw throwable;
    }
    return result;
  }
}
