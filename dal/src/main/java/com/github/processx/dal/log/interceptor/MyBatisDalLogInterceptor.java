/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.log.interceptor;

import com.github.processx.common.util.LoggerUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StopWatch;

/**
 * MyBatis数据库操作日志拦截输出
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 21:29
 */
public class MyBatisDalLogInterceptor implements MethodInterceptor {

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
    StopWatch clock = new StopWatch();
    try {
      // 计时开始
      clock.start();
      result = invocation.proceed();
      String method =
          invocation.getMethod().getDeclaringClass().getSimpleName()
              + "."
              + invocation.getMethod().getName();

      LoggerUtil.info(
          LOGGER,
          "方法名:%s,执行时间:%s ms,参数:%s",
          method,
          clock.getTotalTimeMillis(),
          getString(invocation.getArguments()));
    } finally {
      // 计时结束
      clock.stop();
    }

    return result;
  }

  /**
   * 方法的参数输出
   *
   * @param objs 方法的参数数组
   * @return 方法的参数字符串, 逗号隔开
   */
  private String getString(Object[] objs) {
    String args = "";

    if (objs != null && objs.length > 0) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < objs.length; i++) {
        sb.append(objs[i]).append(",");
      }
      args = sb.substring(0, sb.length() - 1);
    }

    return args;
  }
}
