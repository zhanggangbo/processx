/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessNodeInstanceDO;
import java.util.Date;
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
   * 插入流程节点实例
   *
   * @param record
   * @return
   */
  int insertNodeInstance(ProcessNodeInstanceDO record);

  /**
   * 更新流程节点实例状态
   */
  int updateNodeInstanceStatus(
    @Param("processInstanceId") Long processInstanceId,
    @Param("nodeId") Long nodeId,
    @Param("bizNo") String bizNo,
    @Param("status") Integer status);

  /**
   *
   */
  int updateNodeInstance4ModifiedTime(
    @Param("processInstanceId") Long processInstanceId,
    @Param("nodeId") Long nodeId,
    @Param("bizNo") String bizNo,
    @Param("status") Integer status,
    @Param("modifiedTime") Date modifiedTime);
}
