/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api;

import com.github.processx.api.event.AutoNodeEvent;

/**
 * 自动节点执行接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/31 22:58
 * @see com.github.processx.api.Execution
 */
public interface AutoExecution extends Execution {
  /**
   * 自动节点执行方法
   *
   * @param context 节点上下文
   * @return
   */
  @Override
  AutoNodeEvent execute(NodeContext context);
}
