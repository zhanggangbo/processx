/**
 * GitHub. Inc.
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.api.demo;

import com.github.processx.api.NodeContext;
import com.github.processx.api.TriggerExecution;
import com.github.processx.api.event.TriggerNodeEvent;
import com.github.processx.common.util.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/4 14:12
 */
public class BindingCardExecution implements TriggerExecution {
  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(BindingCardExecution.class);
  /**
   * 触发(节点执行方法
   *
   * @param context 节点上下文
   */
  @Override
  public TriggerNodeEvent execute(NodeContext context) {
    LoggerUtil.info(LOGGER, "BindingCardExecution 7");
    return TriggerNodeEvent.createSuccessEvent();
  }
}
