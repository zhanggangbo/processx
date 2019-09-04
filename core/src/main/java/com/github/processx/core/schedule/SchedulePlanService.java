/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.schedule;

import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.Date;
import java.util.List;

/**
 * 定时任务服务类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 17:31
 */
public interface SchedulePlanService {
  /**
   * 捞取执行时间小于当前时间的定时任务
   *
   * @return
   */
  List<ProcessSchedulePlanDO> getSchedulePlan();

  /**
   * 新增or更新定时任务
   *
   * @param processInstanceId 流程实例ID
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param period 执行周期
   * @return 是否添加成功
   */
  boolean addOrUpdateSchedulePlan(Long processInstanceId, String bizNo, Long nodeId, int period);

  /**
   * 初始准备定时任务状态修改为运行中
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @return 是否修改成功
   */
  boolean moditySchedulePlanReday2Running4Lock(String bizNo, Long nodeId);

  /**
   * 修改运行超时的定时任务修改时间
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param modifiedTime 修改时间
   * @return 是否修改成功
   */
  boolean moditySchedulePlanupdateRunningModifiedTime4Lock(
    String bizNo, Long nodeId, Date modifiedTime);

  /**
   * 删除定时任务
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @return 是否删除成功
   */
  boolean deleteSchedulePlan(String bizNo, Long nodeId);

  /**
   * 修改定时任务
   *
   * @param record 定时任务
   * @return 是否修改成功
   */
  boolean moditySchedulePlan(ProcessSchedulePlanDO record);
}
