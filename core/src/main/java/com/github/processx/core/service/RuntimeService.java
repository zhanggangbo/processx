/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service;

import com.github.processx.core.service.model.ProcessNodeInstanceModel;
import java.util.Date;

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

  /**
   * 更新节点实例状态
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @param status 流程节点状态
   * @return 是否更新成功
   */
  boolean updateNodeInstanceStatus(
    Long processInstanceId, Long nodeId, String bizNo, Integer status);

  /**
   * 更新节点实例修改时间
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @param status 流程节点状态
   * @param modifiedTime 节点实例修改时间
   * @return 是否更新成功
   */
  boolean updateNodeInstance4ModifiedTime(
    Long processInstanceId, Long nodeId, String bizNo, Integer status, Date modifiedTime);
}
