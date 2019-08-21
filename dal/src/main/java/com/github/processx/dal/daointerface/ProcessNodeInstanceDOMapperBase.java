/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessNodeInstanceDO;

/**
 * ProcessNodeInstanceDOMapperBase
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 22:30
 */
public interface ProcessNodeInstanceDOMapperBase {
  /**
   * @param processInstanceId
   * @param nodeId
   * @param bizNo
   * @return
   */
  ProcessNodeInstanceDO selectNodeInstance(Long processInstanceId, Long nodeId, String bizNo);

  /**
   * 插入流程节点实例
   *
   * @param record
   * @return
   */
  int insertNodeInstance(ProcessNodeInstanceDO record);
}
