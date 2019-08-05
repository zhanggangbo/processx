/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.core.service.enums.NodeTypeEnum;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 节点实例
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/4 0:10
 */
@Getter
@Setter
public class NodeInstance {
  /** 节点ID */
  private Long nodeId;

  /** 流程ID */
  private Long processId;

  /** 流程节点名称 */
  private String name;

  /**
   * 流程节点类型；
   *
   * @see com.github.processx.core.service.enums.NodeTypeEnum
   */
  private NodeTypeEnum nodeType;

  /** 执行组件 */
  private String executeCompoment;

  /** 最大执行时间 */
  private Integer maxExeTime;

  /** 是否同步 */
  private Boolean isSync;

  /** 是否被保护 */
  private Boolean isProtected;

  /** 流程实例 */
  private ProcessInstance processInstance;

  /** 下一个节点标识 */
  private List<String> nextNodeIdList;

  /**
   * 自动节点判断
   *
   * @return
   */
  public boolean isAutoNode() {
    return this.nodeType == NodeTypeEnum.AUTO;
  }

  /**
   * 自动节点判断
   *
   * @return
   */
  public boolean isGatewayNode() {
    return this.nodeType == NodeTypeEnum.GATEWAY;
  }

  /**
   * 触发（被动）节点判断
   *
   * @return
   */
  public boolean isTriggerNode() {
    return this.nodeType == NodeTypeEnum.TRIGGER;
  }

  /**
   * 定时节点判断
   *
   * @return
   */
  public boolean isScheduleNode() {
    return this.nodeType == NodeTypeEnum.SCHEDULE;
  }
}
