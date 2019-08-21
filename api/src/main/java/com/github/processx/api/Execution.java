/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api;

import com.github.processx.api.event.NodeEvent;

/**
 * 节点执行基类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public interface Execution {
  /**
   * 节点执行方法
   *
   * @param context 节点上下文
   * @return 节点事件
   */
  NodeEvent execute(NodeContext context);
}
