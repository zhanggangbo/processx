/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.common;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间处理工具类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/3 23:11
 */
public class DateUtil {

  /**
   * 获得当前时间
   *
   * @return
   */
  public static Date getCurrentTime() {
    Calendar rightNow = Calendar.getInstance();
    return rightNow.getTime();
  }

  /**
   * 给指定日期加间隔毫秒
   *
   * @param startDate
   * @param intervalSecond
   * @return
   */
  public static Date addIntervalSecond(Date startDate, long intervalSecond) {
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    calendar.setTimeInMillis(startDate.getTime() + intervalSecond);
    return calendar.getTime();
  }
}
