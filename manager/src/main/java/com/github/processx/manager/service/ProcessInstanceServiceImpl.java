/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.dal.daointerface.ProcessInstanceDOMapper;
import com.github.processx.dal.daointerface.ProcessNodeInstanceDOMapper;
import com.github.processx.dal.dataobjects.ProcessInstanceDO;
import com.github.processx.dal.dataobjects.ProcessNodeInstanceDO;
import com.github.processx.manager.service.result.CommonPageResult;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhanggangbo
 * @version v 0.1 2019/10/7 9:47
 */
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

  /** DAO接口 */
  @Autowired private ProcessInstanceDOMapper processInstanceDOMapper;

  @Autowired private ProcessNodeInstanceDOMapper processNodeInstanceDOMapper;

  /**
   * 根据业务流水号获取流程实例信息
   *
   * @param queryData 查询入参
   * @param pageNum 第几页
   * @param pageSize 每页记录数
   * @return 流程实例信息
   */
  @Override
  public CommonPageResult<ProcessInstanceDO> findProcessInstance(
      String queryData, int pageNum, int pageSize) {
    PageInfo<Object> pageInfo =
        Optional.ofNullable(
                PageHelper.startPage(pageNum, pageSize)
                    .doSelectPageInfo(() -> processInstanceDOMapper.selectByQueryData(queryData)))
            .orElseThrow(
                () -> new ProcessxException(ProcessxResultEnum.SYSTEM_ERROR, "获取流程实例分页数据失败"));

    return new CommonPageResult(pageInfo.getList(), pageInfo.getTotal());
  }

  /**
   * 根据业务流水号获取流程节点实例信息
   *
   * @param bizNo 业务流水号
   * @return 流程节点实例信息
   */
  @Override
  public List<ProcessNodeInstanceDO> findProcessNodeInstance(String bizNo) {
    return processNodeInstanceDOMapper.selectByBizNo(bizNo);
  }
}
