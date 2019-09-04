/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.schedule.impl;

import com.github.processx.common.DateUtil;
import com.github.processx.core.schedule.ProcessSchedulePlanService;
import com.github.processx.core.schedule.enums.StatusEnum;
import com.github.processx.dal.daointerface.ProcessSchedulePlanDOMapper;
import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务服务类
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/2 23:30
 */
public class ProcessSchedulePlanServiceImpl implements ProcessSchedulePlanService {
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
   * 新增定时任务
   *
   * @param processInstanceId 流程实例ID
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param period 执行周期
   * @return 是否添加成功
   */
  @Override
  public boolean addSchedulePlan(Long processInstanceId, String bizNo, Long nodeId, int period) {
    ProcessSchedulePlanDO record = new ProcessSchedulePlanDO();
    record.setBizNo(bizNo);
    record.setNodeId(nodeId);
    record.setProcessInstanceId(processInstanceId);
    record.setExecCounts(0);
    record.setExecTime(DateUtil.addIntervalSecond(DateUtil.getCurrentTime(), period * 1000));
    record.setStatus(StatusEnum.READY.getStatus());

    return processSchedulePlanDOMapper.insertSchedulePlan(record) > 0;
  }

  /**
   * 修改定时任务状态
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   * @param status 定时任务状态
   * @return 是否修改成功
   */
  @Override
  public boolean moditySchedulePlanStatus(String bizNo, Long nodeId, StatusEnum status) {
    return processSchedulePlanDOMapper.updateStatus(bizNo, nodeId, status.getStatus()) > 0;
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
}
