/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.manager.service;

import com.github.processx.dal.dataobjects.ProcessInstanceDO;
import com.github.processx.dal.dataobjects.ProcessNodeInstanceDO;
import com.github.processx.manager.service.result.CommonPageResult;
import java.util.List;

/**
 * 流程服务服务
 *
 * @author zhanggangbo
 * @version v 0.1 2019/10/7 9:46
 */
public interface ProcessInstanceService {
  /**
   * 根据业务流水号获取流程实例信息
   *
   * @param queryData 查询入参
   * @param pageNum 第几页
   * @param pageSize 每页记录数
   * @return 流程实例信息
   */
  CommonPageResult<ProcessInstanceDO> findProcessInstance(
    String queryData, int pageNum, int pageSize);

  /**
   * 根据业务流水号获取流程节点实例信息
   *
   * @param bizNo 业务流水号
   * @return 流程节点实例信息
   */
  List<ProcessNodeInstanceDO> findProcessNodeInstance(String bizNo);
}
