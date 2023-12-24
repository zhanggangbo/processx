/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.executor;

import com.github.processx.api.NodeContext;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.api.event.ProcessInnerEvent;
import com.github.processx.core.NodeInstance;
import com.github.processx.core.ProcessInstance;
import com.github.processx.core.threadpool.MonitorThreadPoolExecutor;
import com.github.processx.core.threadpool.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;

/**
 * 节点异步执行器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/24 20:55
 */
public class AynNodeExecutor implements NodeExecutor {

  private static ExecutorService aynNodeExecutorThreadPool =
      new MonitorThreadPoolExecutor(60, new ThreadFactoryBuilder("NodeExecutorThreadPool"));

  /**
   * 节点执行
   *
   * @param nodeInstance 节点实例
   * @param nodeContext 节点执行上下文
   */
  @Override
  public NodeEvent execute(NodeInstance nodeInstance, NodeContext nodeContext) {

    aynNodeExecutorThreadPool.submit(
        () -> {
          ProcessInstance processInstance = nodeInstance.getProcessInstance();

          processInstance.notifyEvent(ProcessInnerEvent.createResumeEvent(nodeInstance.getName()));
        });

    return null;
  }
}
