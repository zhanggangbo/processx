/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.executor;

import com.github.processx.api.NodeContext;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.core.NodeInstance;

/**
 * 节点执行器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 22:50
 */
public interface NodeExecutor {
  /**
   * 节点执行
   *
   * @param nodeInstance 节点实例
   * @param nodeContext 节点执行上下文
   * @return
   */
  NodeEvent execute(NodeInstance nodeInstance, NodeContext nodeContext);
}
