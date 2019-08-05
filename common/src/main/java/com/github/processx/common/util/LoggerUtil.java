/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.common.util;

import java.text.MessageFormat;
import org.apache.logging.log4j.Logger;

/**
 * 日志统一输出工具
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public class LoggerUtil {

  /**
   * info
   *
   * @param logger
   * @param format
   * @param args
   */
  public static void info(Logger logger, String format, Object... args) {
    if (logger.isInfoEnabled()) {
      if (null == args || args.length == 0) {
        logger.info(format);
      } else {
        logger.info(MessageFormat.format(format, args));
      }
    }
  }

  /**
   * info
   *
   * @param logger
   * @param e
   * @param format
   * @param args
   */
  public static void info(Logger logger, Throwable e, String format, Object... args) {
    if (logger.isInfoEnabled()) {
      if (null == args || args.length == 0) {
        logger.info(format, e);
      } else {
        logger.info(MessageFormat.format(format, args), e);
      }
    }
  }

  /**
   * warn
   *
   * @param logger
   * @param e
   * @param format
   * @param args
   */
  public static void warn(Logger logger, Throwable e, String format, Object... args) {
    if (null == args || args.length == 0) {
      logger.warn(format, e);
    } else {
      logger.warn(MessageFormat.format(format, args), e);
    }
  }

  /**
   * warn
   *
   * @param logger
   * @param format
   * @param args
   */
  public static void warn(Logger logger, String format, Object... args) {
    if (null == args || args.length == 0) {
      logger.warn(format);
    } else {
      logger.warn(MessageFormat.format(format, args));
    }
  }

  /**
   * error
   *
   * @param logger
   * @param format
   * @param args
   */
  public static void error(Logger logger, String format, Object... args) {
    if (null == args || args.length == 0) {
      logger.error(format);
    } else {
      logger.error(MessageFormat.format(format, args));
    }
  }

  /**
   * error
   *
   * @param logger
   * @param e
   * @param format
   * @param args
   */
  public static void error(Logger logger, Throwable e, String format, Object... args) {
    if (null == args || args.length == 0) {
      logger.error(format, e);
    } else {
      logger.error(MessageFormat.format(format, args), e);
    }
  }

  /**
   * debug
   *
   * @param logger
   * @param format
   * @param args
   */
  public static void debug(Logger logger, String format, Object... args) {
    if (logger.isDebugEnabled()) {
      if (null == args || args.length == 0) {
        logger.debug(format);
      } else {
        logger.debug(MessageFormat.format(format, args));
      }
    }
  }

  /**
   * debug
   *
   * @param logger
   * @param e
   * @param format
   * @param args
   */
  public static void debug(Logger logger, Throwable e, String format, Object... args) {
    if (logger.isDebugEnabled()) {
      if (null == args || args.length == 0) {
        logger.debug(format, e);
      } else {
        logger.debug(MessageFormat.format(format, args), e);
      }
    }
  }
}
