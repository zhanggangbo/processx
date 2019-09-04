/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessSchedulePlanDO;
import java.util.Date;
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
   *
   * @return
   */
  List<ProcessSchedulePlanDO> selectExectionSchedulePlan();

  /**
   * 插入定时任务
   *
   * @param record
   * @return
   */
  int insertSchedulePlan(ProcessSchedulePlanDO record);

  /**
   * 查询定时任务
   *
   * @param bizNo
   * @param nodeId
   * @return
   */
  ProcessSchedulePlanDO selectSchedulePlan(
    @Param("bizNo") String bizNo, @Param("nodeId") Long nodeId);

  /**
   * 删除定时任务
   *
   * @param bizNo
   * @param nodeId
   * @return
   */
  int deleteSchedulePlan(@Param("bizNo") String bizNo, @Param("nodeId") Long nodeId);

  /**
   * 将初始准备定时任务状态更新为运行中
   */
  int updateReday2Running4Lock(@Param("bizNo") String bizNo, @Param("nodeId") Long nodeId);

  /**
   * 更新运行超时的定时任务修改时间
   *
   * @param bizNo
   * @param nodeId
   * @param modifiedTime
   * @return
   */
  int updateRunningModifiedTime4Lock(
      @Param("bizNo") String bizNo,
      @Param("nodeId") Long nodeId,
      @Param("modifiedTime") Date modifiedTime);

  /**
   * 修改定时任务
   */
  int updateSchedulePlan(ProcessSchedulePlanDO record);
}
