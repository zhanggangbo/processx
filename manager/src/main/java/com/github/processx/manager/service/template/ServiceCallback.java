/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.manager.service.template;

/**
 * 业务操作回调的接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public interface ServiceCallback {

  /** 对于获取锁失败，异常驱动返回 */
  default void doLock() {}

  /** 对于校验不通过，异常驱动返回 */
  default void check() {}

  /** 执行业务逻辑 */
  void execute();
}
