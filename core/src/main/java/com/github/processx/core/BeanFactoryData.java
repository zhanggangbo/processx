/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.util.Assert;

/**
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 15:43
 */
public class BeanFactoryData implements BeanFactoryAware {
  /** @see org.springframework.beans.factory.BeanFactory */
  private BeanFactory beanFactory;

  public <T> T getDataSource(String name, Class<T> requiredType)
      throws DataSourceLookupFailureException {
    Assert.state(this.beanFactory != null, "BeanFactory is required");
    try {
      return this.beanFactory.getBean(name, requiredType);
    } catch (BeansException ex) {
      throw new DataSourceLookupFailureException(
          "Failed to look up DataSource bean with name '" + name + "'", ex);
    }
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
}
