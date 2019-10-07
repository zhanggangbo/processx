/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessNodeInstanceDO;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ProcessNodeInstanceDOMapperBase
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 22:30
 */
public interface ProcessNodeInstanceDOMapperBase {
  /**
   * 查询流程节点实例
   *
   * @param processInstanceId
   * @param nodeId
   * @param bizNo
   * @return
   */
  ProcessNodeInstanceDO selectNodeInstance(
    @Param("processInstanceId") Long processInstanceId,
    @Param("nodeId") Long nodeId,
    @Param("bizNo") String bizNo);

  /**
   * 查询指定业务流水号下所有流程节点
   *
   * @param bizNo
   * @return
   */
  List<ProcessNodeInstanceDO> selectExecNodeInstance(@Param("bizNo") String bizNo);

  /**
   * 插入流程节点实例
   *
   * @param record
   * @return
   */
  int insertNodeInstance(ProcessNodeInstanceDO record);

  /**
   * 更新流程节点实例状态
   *
   * @param processInstanceId
   * @param nodeId
   * @param bizNo
   * @param status
   * @return
   */
  int updateNodeInstanceStatus(
      @Param("processInstanceId") Long processInstanceId,
      @Param("nodeId") Long nodeId,
      @Param("bizNo") String bizNo,
      @Param("status") Integer status);

  /**
   * 更新流程节点实例状态
   *
   * @param processInstanceId
   * @param nodeId
   * @param bizNo
   * @param status
   * @param modifiedTime
   * @return
   */
  int updateNodeInstance4ModifiedTime(
      @Param("processInstanceId") Long processInstanceId,
      @Param("nodeId") Long nodeId,
      @Param("bizNo") String bizNo,
      @Param("status") Integer status,
      @Param("modifiedTime") Date modifiedTime);

  /**
   * 根据业务流水号获取流程节点实例信息
   */
  List<ProcessNodeInstanceDO> selectByBizNo(@Param("bizNo") String bizNo);
}
