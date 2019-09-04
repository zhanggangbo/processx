/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ProcessSchedulePlanDOMapperBase
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 17:30
 */
public interface ProcessSchedulePlanDOMapperBase {
  /**
   * 查询执行时间小于当前时间的定时任务
   */
  List<ProcessSchedulePlanDO> selectExectionSchedulePlan();

  /**
   * 插入定时任务
   */
  int insertSchedulePlan(ProcessSchedulePlanDO record);

  /**
   * 查询定时任务
   */
  ProcessSchedulePlanDO selectSchedulePlan(
    @Param("bizNo") String bizNo, @Param("nodeId") Long nodeId);

  /**
   * 删除定时任务
   */
  int deleteSchedulePlan(@Param("bizNo") String bizNo, @Param("nodeId") Long nodeId);

  /**
   * 修改定时任务状态
   */
  int updateStatus(
    @Param("bizNo") String bizNo, @Param("nodeId") Long nodeId, @Param("status") String status);
}
