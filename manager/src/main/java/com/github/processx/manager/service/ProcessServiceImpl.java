/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.dal.daointerface.ProcessDOMapper;
import com.github.processx.dal.daointerface.ProcessNodeDOMapper;
import com.github.processx.dal.dataobjects.ProcessDO;
import com.github.processx.dal.dataobjects.ProcessNodeDO;
import com.github.processx.manager.service.result.CommonPageResult;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程服务
 *
 * @author zhanggangbo
 * @version v 0.1 2019/10/3 17:15
 */
public class ProcessServiceImpl implements ProcessService {

  /** DAO接口 */
  @Autowired private ProcessDOMapper processDOMapper;

  @Autowired private ProcessNodeDOMapper processNodeDOMapper;

  /**
   * 分页获取流程信息
   *
   * @param queryData 查询入参
   * @param pageNum 第几页
   * @param pageSize 每页记录数
   * @return 分页数据
   */
  @Override
  public CommonPageResult<ProcessDO> findProcess(String queryData, int pageNum, int pageSize) {
    PageInfo<Object> pageInfo =
        Optional.ofNullable(
                PageHelper.startPage(pageNum, pageSize)
                    .doSelectPageInfo(() -> processDOMapper.selectByQueryData(queryData)))
            .orElseThrow(
                () -> new ProcessxException(ProcessxResultEnum.SYSTEM_ERROR, "获取流程分页数据失败"));

    return new CommonPageResult(pageInfo.getList(), pageInfo.getTotal());
  }

  /**
   * 根据流程ID获取流程节点信息
   *
   * @param processId 流程ID
   * @return 流程节点信息
   */
  @Override
  public List<ProcessNodeDO> findProcessNode(Long processId) {
    return processNodeDOMapper.selectProcessNodeByProcessId(processId);
  }
}
