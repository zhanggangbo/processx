/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessInstanceDO;
import org.apache.ibatis.annotations.Param;

/**
 * ProcessInstanceDOMapperBase
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/17 22:28
 */
public interface ProcessInstanceDOMapperBase {
  /**
   * 根据业务流水号查询流程实例
   */
  ProcessInstanceDO selectByBizNo(@Param("nodeId") String bizNo);

  /**
   * 新增流程实例
   */
  int insertProcessInstance(ProcessInstanceDO record);
}
