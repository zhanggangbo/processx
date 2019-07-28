/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.test.service;

import com.github.processx.core.BeanFactoryData;
import com.github.processx.core.service.impl.ProcessConfigServiceImpl;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.test.base.AbstractTestBase;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程配置服务接口测试
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 15:10
 */
public class ProcessConfigServiceTest extends AbstractTestBase {
//  @Autowired private ProcessConfigService processConfigService;

  @Autowired private  BeanFactoryData beanFactoryData;

  @Test
  public void testGetAllProcessDefinition() {
//    Map<Long, ProcessDefinition> allProcessDefinition =
//        processConfigService.getAllProcessDefinition();

    ProcessConfigServiceImpl processConfigService = beanFactoryData.getDataSource("processConfigService", ProcessConfigServiceImpl.class);

    Map<Long, ProcessDefinition> allProcessDefinition = processConfigService.getAllProcessDefinition();

  }
}
