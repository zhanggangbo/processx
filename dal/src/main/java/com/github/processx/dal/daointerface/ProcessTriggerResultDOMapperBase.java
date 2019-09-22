/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessTriggerResultDO;
import org.apache.ibatis.annotations.Param;

/**
 * ProcessTriggerResultDOMapperBase
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 22:31
 */
public interface ProcessTriggerResultDOMapperBase {
  /**
   * 根据业务流水号和节点ID查询流程执行结果
   *
   * @param bizNo 业务流水号
   * @param nodeId 节点ID
   */
  ProcessTriggerResultDO selectProcessTriggerResult(
    @Param("bizNo") String bizNo, @Param("nodeId") Long nodeId);
}
