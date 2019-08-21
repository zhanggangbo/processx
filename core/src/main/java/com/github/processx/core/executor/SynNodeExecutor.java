/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.executor;

import com.github.processx.api.Execution;
import com.github.processx.api.NodeContext;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.core.NodeInstance;

/**
 * 节点同步执行器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 22:54
 */
public class SynNodeExecutor implements NodeExecutor {
  /**
   * 节点执行
   *
   * @param nodeInstance 节点实例
   * @param nodeContext 节点执行上下文
   */
  @Override
  public NodeEvent execute(NodeInstance nodeInstance, NodeContext nodeContext) {

    Execution executeCompoment = nodeInstance.getExecuteCompoment();

    NodeEvent nodeEvent = null;
    try {
      nodeEvent = executeCompoment.execute(nodeContext);

      switch (nodeEvent.getEventType()) {
        case SUCCESS:
          // TODO 更新节点状态
          break;
        case FAIL:
          break;
        case WAIT:
          break;
        case TERMINAL:
          break;
        case RUNNING:
          break;
        case COMPLETE:
          break;
        default:
          break;
      }

    } catch (Exception e) {

    }

    return nodeEvent;
  }
}
