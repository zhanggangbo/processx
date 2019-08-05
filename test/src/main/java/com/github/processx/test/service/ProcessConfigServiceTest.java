/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.test.service;

import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.service.ProcessConfigService;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.test.base.AbstractTestBase;
import java.util.Map;
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

  @Test
  public void testGetAllProcessDefinition() {
    Map<Long, ProcessDefinition> allProcessDefinition =
        processConfigService.getAllProcessDefinition();

    LoggerUtil.info(LOGGER, "allProcessDefinition={0}", allProcessDefinition);
  }
}
