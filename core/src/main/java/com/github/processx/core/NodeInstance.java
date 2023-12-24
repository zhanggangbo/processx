/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.api.Execution;
import com.github.processx.api.NodeContext;
import com.github.processx.api.event.NodeEvent;
import com.github.processx.core.executor.SynNodeExecutor;
import com.github.processx.core.service.enums.NodeTypeEnum;
import java.util.ArrayList;
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

  /** 业务流水号 */
  private String bizNo;

  /** 节点ID */
  private Long nodeId;

  /** 流程实例 */
  private ProcessInstance processInstance;

  /** 流程节点名称 */
  private String name;

  /** 模块名称（别名） */
  private String stage;

  /**
   * 流程节点类型；
   *
   * @see com.github.processx.core.service.enums.NodeTypeEnum
   */
  private NodeTypeEnum nodeType;

  /** 执行组件 */
  private Execution executeCompoment;

  /** 最大执行时间 */
  private Integer maxExeTime;

  /** 是否同步 isSync为false流程引擎执行到该节点的时候，会另起一个线程执行后续节点，当前线程返回 */
  private Boolean isSync;

  /** 是否被保护 isProtected为false流程引擎执行到该节点无论节点什么状态都会重新执行 */
  private Boolean isProtected;

  /** 下一个节点标识 */
  private List<NodeInstance> nextNodeInstanceList = new ArrayList<>();

  /** 上一个节点标识 */
  private List<NodeInstance> preNodeInstanceList = new ArrayList<>();

  /** 是否为开始节点 */
  private boolean isStart;

  /** 是否为结束节点 */
  private boolean isEnd;

  /** 节点同步执行器 */
  private static SynNodeExecutor synNodeExecutor;

  /**
   * 节点执行器
   *
   * @param context
   * @return
   */
  public NodeEvent execute(NodeContext context) {
    if (synNodeExecutor == null) {
      synNodeExecutor = ProcessLoader.getSynNodeExecutor();
    }

    return synNodeExecutor.execute(this, context);
  }

  /**
   * 添加下一个节点标识
   *
   * @param NodeInstance
   */
  public void addNextNodeInstance(NodeInstance NodeInstance) {
    this.nextNodeInstanceList.add(NodeInstance);
  }

  /**
   * 添加上一个节点标识
   *
   * @param NodeInstance
   */
  public void addPreNodeInstance(NodeInstance NodeInstance) {
    this.preNodeInstanceList.add(NodeInstance);
  }

  /** 节点是否另起一个线程执行 */
  public boolean executeByNewThread() {
    if (!isSync || nodeType == NodeTypeEnum.TRIGGER || nodeType == NodeTypeEnum.SCHEDULE) {
      return true;
    }

    return false;
  }
}
