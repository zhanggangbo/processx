/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

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
  ProcessFeature processFeature;

  /** 开始节点 or 初始节点 */
  private NodeInstance startNode;

  /** 当前节点 */
  private NodeInstance currentNode;

  /** 节点实例map */
  private Map<String, NodeInstance> nodeInstanceMapWithNodeName = new HashMap<>();

  /** 节点实例map */
  private Map<Long, NodeInstance> nodeInstanceMapWithNodeId = new HashMap<>();

  /**
   * 添加节点实例
   *
   * @param nodeInstance
   */
  public void addNodeInstance(NodeInstance nodeInstance) {
    nodeInstanceMapWithNodeName.put(nodeInstance.getName(), nodeInstance);
    nodeInstanceMapWithNodeId.put(nodeInstance.getNodeId(), nodeInstance);
  }
}
