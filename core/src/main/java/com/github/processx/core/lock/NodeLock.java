/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.lock;

import com.github.processx.core.service.model.ProcessFeature;

/**
 * 流程节点锁
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/18 17:10
 */
public interface NodeLock {
  
  /**
   * 节点锁
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 节点ID
   * @param bizNo 业务流水号
   * @param isProtected isProtected为false流程引擎执行到该节点无论节点什么状态都会重新执行
   * @param processFeature 流程特征
   * @return 是否拿到锁
   */
  boolean getLock(
    Long processInstanceId,
    Long nodeId,
    String bizNo,
    Boolean isProtected,
    ProcessFeature processFeature);
}
