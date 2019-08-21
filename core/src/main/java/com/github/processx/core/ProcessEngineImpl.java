/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import com.github.processx.api.event.ProcessInnerEvent;
import java.util.Map;

/**
 * 流程引擎实现
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 15:49
 */
public class ProcessEngineImpl implements ProcessEngine {

  /**
   * 流程启动
   *
   * @param processName
   * @param bizNo
   * @param inputParam
   * @param listener
   * @return
   */
  @Override
  public ProcessResult start(
    String processName, String bizNo, Map<String, Object> inputParam, ProsessListener listener) {
    try {
      // 创建流程实例
      ProcessInstance processInstance = ProcessInstanceFactory.create(processName, bizNo);

      // 从头节点触发流程启动
      processInstance.notifyEvent(
        ProcessInnerEvent.createStartEvent(processInstance.getStartNode().getName()));

      // 日志记录
      ProcessResult result = new ProcessResult();
      result.setBizNo(bizNo);

      return result;
    } catch (Exception e) {

      e.printStackTrace();
    }
    return null;
  }
}
