/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.executor;

import com.github.processx.api.Execution;
import com.github.processx.api.NodeContext;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.common.enums.LoggerEnum;
import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.NodeInstance;
import com.github.processx.core.ProcessInstance;
import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.enums.NodeInstanceStatusEnum;
import com.github.processx.core.service.model.ProcessFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 节点同步执行器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 22:54
 */
public class SynNodeExecutor implements NodeExecutor {

  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(LoggerEnum.NODE_DIGEST.getLogger());

  @Autowired private RuntimeService runtimeService;
  /**
   * 节点执行
   *
   * @param nodeInstance 节点实例
   * @param nodeContext 节点执行上下文
   */
  @Override
  public NodeEvent execute(NodeInstance nodeInstance, NodeContext nodeContext) {
    ProcessInstance processInstance = nodeInstance.getProcessInstance();
    long end;
    long begin = System.currentTimeMillis();

    ProcessFeature processFeature = processInstance.getProcessFeature();
    NodeEvent nodeEvent;

    try {
      Execution executeCompoment = nodeInstance.getExecuteCompoment();
      nodeEvent = executeCompoment.execute(nodeContext);

      end = System.currentTimeMillis() - begin;

      // TODO 保存输出结果

      switch (nodeEvent.getEventType()) {
        case SUCCESS:
          LoggerUtil.info(
              LOGGER,
              "{0},{1},{2},{3}",
              processInstance.getName(),
              nodeInstance.getName(),
              "SUCCESS",
              end);
          // TODO 此方法需要优化
          runtimeService.updateNodeInstanceStatus(
              processInstance.getProcessInstanceId(),
              nodeInstance.getNodeId(),
              nodeInstance.getBizNo(),
              NodeInstanceStatusEnum.END.getStatus());
          break;
        case WAIT:
          LoggerUtil.info(
              LOGGER,
              "{0},{1},{2},{3}",
              processInstance.getName(),
              nodeInstance.getName(),
              "WAIT",
              end);
          runtimeService.updateNodeInstanceStatus(
              processInstance.getProcessInstanceId(),
              nodeInstance.getNodeId(),
              nodeInstance.getBizNo(),
              NodeInstanceStatusEnum.WAIT.getStatus());
          break;
        case TERMINAL:
          LoggerUtil.info(
              LOGGER,
              "{0},{1},{2},{3}",
              processInstance.getName(),
              nodeInstance.getName(),
              "TERMINAL",
              end);
          runtimeService.updateNodeInstanceStatus(
              processInstance.getProcessInstanceId(),
              nodeInstance.getNodeId(),
              nodeInstance.getBizNo(),
              NodeInstanceStatusEnum.END.getStatus());
          break;

        default:
          LoggerUtil.error(
              LOGGER,
              "{0},{1},{2},{3}",
              processInstance.getName(),
              nodeInstance.getName(),
              "ERROR",
              end);
          runtimeService.updateNodeInstanceStatus(
              processInstance.getProcessInstanceId(),
              nodeInstance.getNodeId(),
              nodeInstance.getBizNo(),
              NodeInstanceStatusEnum.ERROR.getStatus());
          break;
      }

    } catch (Exception e) {
      end = System.currentTimeMillis() - begin;
      LoggerUtil.error(
          LOGGER,
          "{0},{1},{2},{3}",
          processInstance.getName(),
          nodeInstance.getName(),
          "ERROR",
          end);

      runtimeService.updateNodeInstanceStatus(
          processInstance.getProcessInstanceId(),
          nodeInstance.getNodeId(),
          nodeInstance.getBizNo(),
          NodeInstanceStatusEnum.ERROR.getStatus());

      nodeEvent = NodeEvent.createFailEvent();
    }

    return nodeEvent;
  }
}
