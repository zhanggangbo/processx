/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.util;

import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.util.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.Assert;

/**
 * BeanFactory方法
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 15:43
 */
public class BeanFactoryUtil implements BeanFactoryAware {
  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(BeanFactoryUtil.class);

  /** @see org.springframework.beans.factory.BeanFactory */
  private BeanFactory beanFactory;

  /**
   * 获取指定名称bean实例
   *
   * @param name
   * @param requiredType
   * @param <T>
   * @return
   * @throws
   */
  public static <T> T getBean(String name, Class<T> requiredType) throws ProcessxException {
    try {
      return getInstance().getBeanFactory().getBean(name, requiredType);
    } catch (BeansException ex) {
      LoggerUtil.error(LOGGER, ex, "Failed to look up  bean with name {0}", name);
      throw ex;
    }
  }

  /**
   * 获取单例方法
   *
   * @return
   */
  private static BeanFactoryUtil getInstance() {
    return BeanFactoryUtilHolder.instance;
  }

  /** 单例方法 */
  private static class BeanFactoryUtilHolder {
    private static BeanFactoryUtil instance = new BeanFactoryUtil();
  }

  /**
   * Callback that supplies the owning factory to a bean instance.
   *
   * <p>Invoked after the population of normal bean properties but before an initialization callback
   * such as {@link InitializingBean#afterPropertiesSet()} or a custom init-method.
   *
   * @param beanFactory owning BeanFactory (never {@code null}). The bean can immediately call
   *     methods on the factory.
   * @throws BeansException in case of initialization errors
   * @see BeanInitializationException
   */
  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    Assert.notNull(beanFactory, "BeanFactory is required");
    this.beanFactory = beanFactory;
  }

  /**
   * getBeanFactory
   *
   * @return
   */
  public BeanFactory getBeanFactory() {
    return beanFactory;
  }
}
