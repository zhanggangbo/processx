/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.manager.service;

import com.github.processx.dal.dataobjects.ProcessDO;
import com.github.processx.dal.dataobjects.ProcessNodeDO;
import com.github.processx.manager.service.result.CommonPageResult;
import java.util.List;

/**
 * 流程服务
 *
 * @author zhanggangbo
 * @version v 0.1 2019/10/3 17:09
 */
public interface ProcessService {

  /**
   * 分页获取流程信息
   *
   * @param queryData 查询入参
   * @param pageNum 第几页
   * @param pageSize 每页记录数
   * @return 分页数据
   */
  CommonPageResult<ProcessDO> findProcess(String queryData, int pageNum, int pageSize);

  /**
   * 根据流程ID获取流程节点信息
   *
   * @param processId 流程ID
   * @return 流程节点信息
   */
  List<ProcessNodeDO> findProcessNode(Long processId);
}
