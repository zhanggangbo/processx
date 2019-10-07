/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ProcessDOMapperBase
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/24 23:23
 */
public interface ProcessDOMapperBase {
  /**
   * 查询流程信息
   */
  List<ProcessDO> selectByQueryData(@Param("queryData") String queryData);
}
