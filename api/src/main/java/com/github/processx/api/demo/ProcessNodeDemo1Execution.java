/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.api.demo;

import com.github.processx.api.AutoExecution;
import com.github.processx.api.NodeContext;
import com.github.processx.api.event.AutoNodeEvent;

/**
 * 示例流程节点1
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/12 23:43
 */
public class ProcessNodeDemo1Execution implements AutoExecution {
  /**
   * 自动节点执行方法
   *
   * @param context 节点上下文
   */
  @Override
  public AutoNodeEvent execute(NodeContext context) {
    System.out.println("示例流程节点1，开始执行");
    return AutoNodeEvent.createSuccessEvent();
  }
}
