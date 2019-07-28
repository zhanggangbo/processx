/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.util;

import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 * 防空判断工具类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/25 21:57
 */
public class BeanCheckUtil {
  /**
   * 判断对象是否为NUll。 集合或者Map为Null或者空返回true否则返回false 字符串对象为Null或者空字符返回true
   *
   * @param source
   * @return boolean
   */
  public static boolean checkNullOrEmpty(Object source) {
    if (source instanceof String) {
      return StringUtils.isBlank((String) source);
    } else if (source instanceof Collection) {
      return source == null || ((Collection<?>) source).isEmpty();
    } else if (source instanceof Map) {
      return source == null || ((Map<?, ?>) source).isEmpty();
    }
    return source == null;
  }
}
