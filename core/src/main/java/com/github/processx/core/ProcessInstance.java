/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.api.NodeContext;
import com.github.processx.api.event.Event;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.core.exception.CompleteException;
import com.github.processx.core.exception.RunningException;
import com.github.processx.core.listener.EventListener;
import com.github.processx.core.lock.NodeLock;
import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.model.ProcessFeature;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * 流程实例
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/4 0:09
 */
@Getter
@Setter
public class ProcessInstance {
  /** 业务流水号 */
  private String bizNo;

  /** 流程ID */
  private Long processId;

  /** 流程实例ID */
  private Long processInstanceId;

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

  private static EventListener nodeEventListener;

  private static RuntimeService runtimeService;

  private static NodeLock nodeLock;

  /**
   * 工具方法初始
   */
  public void initTools() {
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

  /**
   * 根据节点名称获取节点实例
   */
  private NodeInstance findNodeInstance(String nodeName) {
    return nodeInstanceMapWithNodeName.get(nodeName);
  }

  /**
   * 事件通知
   */
  public void notifyEvent(Event event) {
    nodeEventListener.handle(this, event);
  }

  /**
   * 节点执行
   */
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
   */
  public void execAutoNode(String nodeName) {

    currentNode = this.findNodeInstance(nodeName);

    if (!nodeLock.getLock(
      currentNode.getProcessInstance().getProcessInstanceId(),
      currentNode.getNodeId(),
      currentNode.getBizNo(),
      currentNode.getIsProtected(),
      currentNode.getProcessInstance().getProcessFeature())) {
      throw new RuntimeException();
    }

    NodeEvent nodeEvent = null;
    try {
      NodeContext context = new NodeContext();

      nodeEvent = currentNode.execute(context);
    } catch (RunningException e) {

    } catch (CompleteException e) {

    } catch (Exception e) {

    }

    notifyEvent(nodeEvent);
  }

  public void execTriggerNode(String nodeName) {
  }

  public void execGatewayNode(String nodeName) {
  }

  public void execScheduleNode(String nodeName) {}
}
