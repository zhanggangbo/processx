/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api;

import com.github.processx.api.event.TriggerNodeEvent;

/**
 * 触发(被动)节点执行接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/31 23:00
 */
public interface TriggerExecution extends Execution {
  /**
   * 触发(节点执行方法
   *
   * @param context 节点上下文
   * @return
   */
  TriggerNodeEvent execute(NodeContext context);
}
