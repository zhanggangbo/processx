/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import com.github.processx.common.exception.ProcessxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程引擎启动示例
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/13 0:00
 */
public class ProcessEngineDemo {
  /** 流程引擎API */
  @Autowired
  private ProcessEngine processEngine;

  /** 启动流程引擎 */
  private void startProcessEngine() {
    String bizNo = StringUtils.replace(UUID.randomUUID().toString(), "_", "");
    Map<String, Object> inputParam = new HashMap<>(2);
    inputParam.put("key1", "value1");
    inputParam.put("key2", "value1");

    try {
      ProcessResult result = processEngine.start("credit", bizNo, inputParam, null);
      System.out.println(result);
    } catch (ProcessxException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
