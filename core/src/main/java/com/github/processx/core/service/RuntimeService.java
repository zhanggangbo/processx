/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service;

import com.github.processx.core.service.model.ProcessNodeInstanceModel;

/**
 * 业务运行时服务接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 21:07
 */
public interface RuntimeService {
  /**
   * 查询流程节点实例
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @return 流程节点实例
   */
  ProcessNodeInstanceModel queryNodeInstance(Long processInstanceId, Long nodeId, String bizNo);

  /**
   * 新增流程节点实例
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @return 是否添加成功
   */
  boolean addNodeInstance(Long processInstanceId, Long nodeId, String bizNo);
}
