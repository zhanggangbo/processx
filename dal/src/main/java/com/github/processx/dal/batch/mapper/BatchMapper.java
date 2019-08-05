/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.batch.mapper;

import java.util.List;

/**
 * MyBatis批量操作
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 21:21
 */
public interface BatchMapper {
  /**
   * Mapper批量操作
   *
   * @param mClass
   * @param method
   * @param data
   * @param <C>
   * @param <T>
   * @return
   */
  <C, T> boolean batch(Class<C> mClass, String method, List<T> data);
}
