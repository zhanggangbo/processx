/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.api.demo;

import com.github.processx.api.NodeContext;
import com.github.processx.api.TriggerExecution;
import com.github.processx.api.event.TriggerNodeEvent;

/**
 * 示例流程节点2
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/12 23:45
 */
public class ProcessNodeDemo2Execution implements TriggerExecution {
  /**
   * 触发节点执行方法
   *
   * @param context 节点上下文
   */
  @Override
  public TriggerNodeEvent execute(NodeContext context) {
    System.out.println("示例流程节点2，开始执行");
    return TriggerNodeEvent.createSuccessEvent();
  }
}
