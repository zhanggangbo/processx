/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessNodeDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ProcessNodeDOMapperBase
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/24 23:23
 */
public interface ProcessNodeDOMapperBase {
  /**
   * 根据流程ID查询流程节点信息
   */
  List<ProcessNodeDO> selectProcessNodeByProcessId(@Param("processId") Long processId);
}
