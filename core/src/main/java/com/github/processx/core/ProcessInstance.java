/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.api.NodeContext;
import com.github.processx.api.ScheduleExecution;
import com.github.processx.api.event.AutoNodeEvent;
import com.github.processx.api.event.Event;
import com.github.processx.api.event.GatewayNodeEvent;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.api.event.ScheduleNodeEvent;
import com.github.processx.api.event.TriggerNodeEvent;
import com.github.processx.api.event.enums.NodeEventTypeEnum;
import com.github.processx.common.bean.BeanCheckUtil;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.exception.NodeCompleteException;
import com.github.processx.core.exception.NodeRunningException;
import com.github.processx.core.listener.EventListener;
import com.github.processx.core.lock.NodeLock;
import com.github.processx.core.schedule.ScheduleResult;
import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.enums.NodeTypeEnum;
import com.github.processx.core.service.model.ProcessFeature;
import com.github.processx.core.service.model.ProcessNodeInstanceModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 流程实例
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/4 0:09
 */
@Getter
@Setter
public class ProcessInstance {
  /**
   * 日志记录
   */
  private static final Logger LOGGER = LogManager.getLogger(ProcessInstance.class);
  /** 业务流水号 */
  private String bizNo;

  /** 流程ID */
  private Long processId;

  /** 流程实例ID */
  private Long processInstanceId = 0L;

  /** 流程名称 */
  private String name;

  /** 版本号 */
  private Integer version;

  /** 流程特征 */
  private ProcessFeature processFeature;

  /** 开始节点 or 初始节点 */
  private NodeInstance startNode;

  /** 当前节点 */
  private NodeInstance currentNode;

  /** 节点实例map */
  private Map<String, NodeInstance> nodeInstanceMapWithNodeName = new HashMap<>();

  /** 节点实例map */
  private Map<Long, NodeInstance> nodeInstanceMapWithNodeId = new HashMap<>();

  /** nodeEventListener */
  private static EventListener nodeEventListener;

  /** runtimeService */
  private static RuntimeService runtimeService;

  /** nodeLock */
  private static NodeLock nodeLock;

  /** 工具方法初始 */
  public void initTools() {
    if (nodeEventListener == null) {
      ProcessInstance.nodeEventListener = ProcessLoader.getEventListener();
    }

    if (runtimeService == null) {
      ProcessInstance.runtimeService = ProcessLoader.getRuntimeService();
    }

    if (nodeLock == null) {
      ProcessInstance.nodeLock = ProcessLoader.getNodeLock();
    }
  }

  /**
   * 添加节点实例
   *
   * @param nodeInstance
   */
  public void addNodeInstance(NodeInstance nodeInstance) {
    nodeInstanceMapWithNodeName.put(nodeInstance.getName(), nodeInstance);
    nodeInstanceMapWithNodeId.put(nodeInstance.getNodeId(), nodeInstance);
  }

  /** 根据节点名称获取节点实例 */
  public NodeInstance findNodeInstance(String nodeName) {
    return nodeInstanceMapWithNodeName.get(nodeName);
  }

  /**
   * 根据节点ID获取节点实例
   *
   * @param nodeId
   * @return
   */
  public NodeInstance findNodeInstance(Long nodeId) {
    return nodeInstanceMapWithNodeId.get(nodeId);
  }

  /** 事件通知 */
  public void notifyEvent(Event event) {
    nodeEventListener.handle(this, event);
  }

  /** 节点执行 */
  public void execNode(String nodeName) {
    currentNode = this.findNodeInstance(nodeName);
    switch (currentNode.getNodeType()) {
      case AUTO:
        execAutoNode(nodeName);
        break;
      case TRIGGER:
        execTriggerNode(nodeName);
        break;
      case GATEWAY:
        execGatewayNode(nodeName);
        break;
      case SCHEDULE:
        execScheduleNode(nodeName);
        break;
      default:
        break;
    }
  }

  /**
   * 自动节点执行
   *
   * @param nodeName
   */
  public void execAutoNode(String nodeName) {
    NodeEvent nodeEvent;
    currentNode = this.findNodeInstance(nodeName);
    try {
      if (!nodeLock.getLock(
          processInstanceId,
        currentNode.getNodeId(),
        bizNo,
        currentNode.getIsProtected(),
        processFeature)) {
        // TODO 日志记录
        nodeEvent = AutoNodeEvent.createRunningEvent();
      } else {
        NodeContext nodeContext =
            buildNodeContext(processInstanceId, currentNode.getNodeId(), bizNo, processFeature);
        nodeEvent = currentNode.execute(nodeContext);
      }

    } catch (NodeRunningException e) {
      // TODO 日志记录
      nodeEvent = AutoNodeEvent.createRunningEvent();
    } catch (NodeCompleteException e) {
      // TODO 日志记录
      nodeEvent = AutoNodeEvent.createCompleteEvent();
    }

    notifyEvent(nodeEvent);
  }

  /**
   * 触发节点执行
   *
   * @param nodeName
   */
  public void execTriggerNode(String nodeName) {
    NodeEvent nodeEvent;
    currentNode = this.findNodeInstance(nodeName);
    try {
      if (!nodeLock.getLock(
          processInstanceId,
        currentNode.getNodeId(),
        bizNo,
        currentNode.getIsProtected(),
        processFeature)) {
        // TODO 日志记录
        nodeEvent = TriggerNodeEvent.createRunningEvent();
      } else {
        NodeContext nodeContext =
            buildNodeContext(processInstanceId, currentNode.getNodeId(), bizNo, processFeature);
        nodeEvent = currentNode.execute(nodeContext);
      }

    } catch (NodeRunningException e) {
      // TODO 日志记录
      nodeEvent = TriggerNodeEvent.createRunningEvent();
    } catch (NodeCompleteException e) {
      // TODO 日志记录
      nodeEvent = TriggerNodeEvent.createCompleteEvent();
    }

    notifyEvent(nodeEvent);
  }

  /**
   * 网关节点执行
   *
   * @param nodeName
   */
  public void execGatewayNode(String nodeName) {
    NodeEvent nodeEvent;
    currentNode = this.findNodeInstance(nodeName);
    try {
      if (!nodeLock.getLock(
          processInstanceId,
        currentNode.getNodeId(),
        bizNo,
        currentNode.getIsProtected(),
        processFeature)) {
        // TODO 日志记录
        nodeEvent = GatewayNodeEvent.createRunningEvent();
      } else {
        NodeContext nodeContext =
            buildNodeContext(processInstanceId, currentNode.getNodeId(), bizNo, processFeature);
        nodeEvent = currentNode.execute(nodeContext);
      }

    } catch (NodeRunningException e) {
      // TODO 日志记录
      nodeEvent = GatewayNodeEvent.createRunningEvent();
    } catch (NodeCompleteException e) {
      // 当节点执行状态为完成时，必然可以找到下一个节点，查找不到抛出异常
      List<ProcessNodeInstanceModel> nodeInstanceList = runtimeService.queryExecNodeInstance(bizNo);
      if (BeanCheckUtil.checkNullOrEmpty(nodeInstanceList)) {
        throw new ProcessxException(ProcessxResultEnum.NEXT_EXECUTE_NODE_NO_EXIST);
      }

      NodeInstance nextNodeInstance = null;

      for (int i = 0; i < nodeInstanceList.size(); i++) {
        NodeInstance nodeInstance = this.findNodeInstance(nodeInstanceList.get(i).getNodeId());
        if (StringUtils.equals(nodeName, nodeInstance.getName())) {
          // 获取下一个节点实例
          int next = i + 1;
          if (next < nodeInstanceList.size()) {
            nextNodeInstance = this.findNodeInstance(nodeInstanceList.get(next).getNodeId());
          }
        }
      }

      if (nextNodeInstance == null) {
        throw new ProcessxException(ProcessxResultEnum.NEXT_EXECUTE_NODE_NO_EXIST);
      }

      // TODO 日志记录
      nodeEvent = GatewayNodeEvent.createCompleteEvent(nextNodeInstance.getName());
    }

    notifyEvent(nodeEvent);
  }

  /**
   * 定时节点执行
   *
   * @param nodeName
   */
  public void execScheduleNode(String nodeName) {
    NodeEvent nodeEvent;
    currentNode = this.findNodeInstance(nodeName);

    if (NodeTypeEnum.SCHEDULE != currentNode.getNodeType()) {
      throw new ProcessxException(
        ProcessxResultEnum.ILLEGAL_PROCESS_NODE_TYPE, "此节点非定时节点，nodeName=" + nodeName);
    }

    ScheduleExecution executeCompoment = (ScheduleExecution) currentNode.getExecuteCompoment();

    ScheduleResult result = new ScheduleResult();
    result.setPeriod(executeCompoment.getPeriod());

    NodeContext nodeContext = null;

    try {
      if (!nodeLock.getLock(
        processInstanceId,
        currentNode.getNodeId(),
        bizNo,
        currentNode.getIsProtected(),
        processFeature)) {
        // TODO 日志记录
        nodeEvent = ScheduleNodeEvent.createRunningEvent();
      } else {
        nodeContext =
          buildNodeContext(processInstanceId, currentNode.getNodeId(), bizNo, processFeature);

        LoggerUtil.info(LOGGER, "nodeContext============={0}", nodeContext);
        nodeEvent = currentNode.execute(nodeContext);

        LoggerUtil.info(LOGGER, "nodeEvent============={0}", nodeEvent.getEventType());

        if (nodeEvent.getEventType() == NodeEventTypeEnum.SUCCESS
          || nodeEvent.getEventType() == NodeEventTypeEnum.TERMINAL) {

          result.setExecResult(true);
          result.setReachMaxCount(
            nodeContext.getExecCount() + 1 > executeCompoment.getMaxExecCount());
        }
      }

    } catch (NodeRunningException e) {
      result.setExecResult(false);
      result.setReachMaxCount(nodeContext.getExecCount() + 1 > executeCompoment.getMaxExecCount());
      // TODO 日志记录
      nodeEvent = ScheduleNodeEvent.createRunningEvent();
    } catch (NodeCompleteException e) {
      result.setExecResult(false);
      result.setReachMaxCount(nodeContext.getExecCount() + 1 > executeCompoment.getMaxExecCount());

      // TODO 日志记录
      nodeEvent = ScheduleNodeEvent.createCompleteEvent();
    }

    notifyEvent(nodeEvent);

    //  保存结果到ThreadLocal
    DataHelper.setScheduleResult(result);
  }

  /**
   * 构建节点执行上下文
   *
   * @param processInstanceId
   * @param nodeId
   * @param bizNo
   * @return
   */
  private NodeContext buildNodeContext(
    Long processInstanceId, Long nodeId, String bizNo, ProcessFeature processFeature) {
    NodeContext context = new NodeContext();
    if (!processFeature.getRecordNodeInstance()) {
      return context;
    }

    ProcessNodeInstanceModel nodeInstance =
        runtimeService.queryNodeInstance(processInstanceId, nodeId, bizNo);

    context.setExecCount(nodeInstance.getExecCount());
    context.setFailCount(nodeInstance.getFailedCount());
    return context;
  }
}
