/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.schedule.impl;

import com.github.processx.common.util.DateUtil;
import com.github.processx.core.schedule.SchedulePlanService;
import com.github.processx.core.schedule.enums.StatusEnum;
import com.github.processx.dal.daointerface.ProcessSchedulePlanDOMapper;
import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务服务类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/2 23:30
 */
public class SchedulePlanServiceImpl implements SchedulePlanService {
  /** DAO接口 */
  @Autowired
  private ProcessSchedulePlanDOMapper processSchedulePlanDOMapper;

  /**
   * 捞取执行时间小于当前时间的定时任务
   *
   * @return
   */
  @Override
  public List<ProcessSchedulePlanDO> getSchedulePlan() {
    return processSchedulePlanDOMapper.selectExectionSchedulePlan();
  }

  /**
   * 新增or更新定时任务
   *
   * @param processInstanceId 流程实例ID
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param period 执行周期
   * @return 是否添加成功
   */
  @Override
  public boolean addOrUpdateSchedulePlan(
    Long processInstanceId, String bizNo, Long nodeId, int period) {
    ProcessSchedulePlanDO record = new ProcessSchedulePlanDO();
    record.setBizNo(bizNo);
    record.setNodeId(nodeId);
    record.setProcessInstanceId(processInstanceId);

    ProcessSchedulePlanDO schedulePlan =
      processSchedulePlanDOMapper.selectSchedulePlan(bizNo, nodeId);

    if (schedulePlan == null) {
      record.setExecCounts(0);
      record.setExecTime(DateUtil.addIntervalSecond(DateUtil.getCurrentTime(), period * 1000));
      record.setStatus(StatusEnum.READY.getStatus());
      return processSchedulePlanDOMapper.insertSchedulePlan(record) > 0;
    } else {
      schedulePlan.setExecCounts(schedulePlan.getExecCounts() + 1);
      return processSchedulePlanDOMapper.updateSchedulePlan(schedulePlan) > 0;
    }
  }

  /**
   * 将初始准备定时任务状态修改为运行中
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @return 是否修改成功
   */
  @Override
  public boolean moditySchedulePlanReday2Running4Lock(String bizNo, Long nodeId) {
    return processSchedulePlanDOMapper.updateReday2Running4Lock(bizNo, nodeId) > 0;
  }

  /**
   * 修改运行超时的定时任务修改时间
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param modifiedTime 修改时间
   * @return 是否修改成功
   */
  @Override
  public boolean moditySchedulePlanupdateRunningModifiedTime4Lock(
    String bizNo, Long nodeId, Date modifiedTime) {
    return processSchedulePlanDOMapper.updateRunningModifiedTime4Lock(bizNo, nodeId, modifiedTime)
      > 0;
  }

  /**
   * 删除定时任务
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @return 是否删除成功
   */
  @Override
  public boolean deleteSchedulePlan(String bizNo, Long nodeId) {
    return processSchedulePlanDOMapper.deleteSchedulePlan(bizNo, nodeId) > 0;
  }

  /**
   * 修改定时任务
   *
   * @param record 定时任务
   * @return 是否修改成功
   */
  @Override
  public boolean moditySchedulePlan(ProcessSchedulePlanDO record) {
    return processSchedulePlanDOMapper.updateSchedulePlan(record) > 0;
  }
}
