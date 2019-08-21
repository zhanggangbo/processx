/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import java.util.Map;

/**
 * 流程引擎
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/12 23:55
 */
public interface ProcessEngine {
  /**
   * 流程启动
   *
   * @param processName
   * @param bizNo
   * @param inputParam
   * @param listener
   * @return
   */
  ProcessResult start(
    String processName, String bizNo, Map<String, Object> inputParam, ProsessListener listener);
}
