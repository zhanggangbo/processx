/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.test.service;

import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.ProcessEngine;
import com.github.processx.core.ProcessResult;
import com.github.processx.core.service.ProcessConfigService;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.test.base.AbstractTestBase;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程配置服务接口测试
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 15:10
 */
public class ProcessConfigServiceTest extends AbstractTestBase {
  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(ProcessConfigServiceTest.class);

  @Autowired private ProcessConfigService processConfigService;

  @Autowired
  private ProcessEngine processEngine;

  @Test
  public void testGetAllProcessDefinition() {
    Map<Long, ProcessDefinition> allProcessDefinition =
        processConfigService.getAllProcessDefinition();

    LoggerUtil.info(LOGGER, "allProcessDefinition={0}", allProcessDefinition);

    String bizNo = StringUtils.replace(UUID.randomUUID().toString(), "_", "");
    Map<String, Object> inputParam = new HashMap<>(2);
    inputParam.put("key1", "value1");
    inputParam.put("key2", "value1");

    ProcessResult result = processEngine.start("credit", bizNo, inputParam, null);
    LoggerUtil.info(LOGGER, "result============={0}", result);

    ProcessResult result1 = processEngine.call("credit", bizNo, "binding_card", inputParam, null);

    LoggerUtil.info(LOGGER, "result1=================={0}", result1);
  }
}
