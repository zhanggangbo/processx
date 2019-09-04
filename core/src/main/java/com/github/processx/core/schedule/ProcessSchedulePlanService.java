/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.schedule;

import com.github.processx.core.schedule.enums.StatusEnum;
import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.List;

/**
 * 定时任务服务类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 17:31
 */
public interface ProcessSchedulePlanService {
  /**
   * 捞取执行时间小于当前时间的定时任务
   */
  List<ProcessSchedulePlanDO> getSchedulePlan();

  /**
   * 新增定时任务
   *
   * @param processInstanceId 流程实例ID
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param period 执行周期
   * @return 是否添加成功
   */
  boolean addSchedulePlan(Long processInstanceId, String bizNo, Long nodeId, int period);

  /**
   * 修改定时任务状态
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param status 定时任务状态
   * @return 是否修改成功
   */
  boolean moditySchedulePlanStatus(String bizNo, Long nodeId, StatusEnum status);

  /**
   * 删除定时任务
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @return 是否删除成功
   */
  boolean deleteSchedulePlan(String bizNo, Long nodeId);
}
