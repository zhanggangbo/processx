/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.executor;

import com.github.processx.api.Execution;
import com.github.processx.api.NodeContext;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.core.NodeInstance;
import com.github.processx.core.ProcessInstance;
import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.enums.NodeInstanceStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 节点同步执行器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 22:54
 */
public class SynNodeExecutor implements NodeExecutor {
  @Autowired
  private RuntimeService runtimeService;
  /**
   * 节点执行
   *
   * @param nodeInstance 节点实例
   * @param nodeContext 节点执行上下文
   */
  @Override
  public NodeEvent execute(NodeInstance nodeInstance, NodeContext nodeContext) {

    ProcessInstance processInstance = nodeInstance.getProcessInstance();

    Execution executeCompoment = nodeInstance.getExecuteCompoment();

    NodeEvent nodeEvent;
    try {
      nodeEvent = executeCompoment.execute(nodeContext);

      switch (nodeEvent.getEventType()) {
        case SUCCESS:
          // TODO 更新节点状态
          runtimeService.updateNodeInstanceStatus(
            processInstance.getProcessInstanceId(),
            nodeInstance.getNodeId(),
            nodeInstance.getBizNo(),
            NodeInstanceStatusEnum.END.getStatus());
          break;
        case WAIT:
          runtimeService.updateNodeInstanceStatus(
            processInstance.getProcessInstanceId(),
            nodeInstance.getNodeId(),
            nodeInstance.getBizNo(),
            NodeInstanceStatusEnum.WAIT.getStatus());
          break;
        case TERMINAL:
          break;
        case RUNNING:
          break;
        case COMPLETE:
          break;
        case FAIL:
          runtimeService.updateNodeInstanceStatus(
            processInstance.getProcessInstanceId(),
            nodeInstance.getNodeId(),
            nodeInstance.getBizNo(),
            NodeInstanceStatusEnum.ERROT.getStatus());
          break;
        default:
          break;
      }

    } catch (Exception e) {
      runtimeService.updateNodeInstanceStatus(
        processInstance.getProcessInstanceId(),
        nodeInstance.getNodeId(),
        nodeInstance.getBizNo(),
        NodeInstanceStatusEnum.ERROT.getStatus());

      nodeEvent = NodeEvent.createFailEvent();
    }

    return nodeEvent;
  }
}
