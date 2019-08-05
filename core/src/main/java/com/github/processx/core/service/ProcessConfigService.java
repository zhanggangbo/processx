/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.service;

import com.github.processx.core.service.model.ProcessDefinition;
import java.util.Map;

/**
 * 流程配置服务接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/25 21:57
 */
public interface ProcessConfigService {
  /**
   * 获取所有流程配置信息
   *
   * @return
   */
  Map<Long, ProcessDefinition> getAllProcessDefinition();
}
