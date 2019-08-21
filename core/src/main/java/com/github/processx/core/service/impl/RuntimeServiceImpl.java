/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service.impl;

import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.enums.NodeInstanceStatusEnum;
import com.github.processx.core.service.model.ProcessNodeInstanceModel;
import com.github.processx.dal.daointerface.ProcessNodeInstanceDOMapper;
import com.github.processx.dal.dataobjects.ProcessNodeInstanceDO;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 业务运行时服务接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 21:23
 */
public class RuntimeServiceImpl implements RuntimeService {
  /** 节点实例DAO接口 */
  @Autowired
  private ProcessNodeInstanceDOMapper processNodeInstanceDOMapper;

  /**
   * 查询流程节点实例
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @return 流程节点实例
   */
  @Override
  public ProcessNodeInstanceModel queryNodeInstance(
    Long processInstanceId, Long nodeId, String bizNo) {
    ProcessNodeInstanceDO record =
      processNodeInstanceDOMapper.selectNodeInstance(processInstanceId, nodeId, bizNo);

    if (record == null) {
      return null;
    }

    ProcessNodeInstanceModel model = new ProcessNodeInstanceModel();
    model.setId(record.getId());
    model.setProcessInstanceId(record.getProcessInstanceId());
    model.setNodeId(record.getNodeId());
    model.setBizNo(record.getBizNo());
    NodeInstanceStatusEnum nodeInstanceStatusEnum =
      NodeInstanceStatusEnum.getNodeInstanceStatusEnumByStatus(record.getStatus());
    if (nodeInstanceStatusEnum == null) {
      throw new ProcessxException(ProcessxResultEnum.ILLEGAL_ARGUMENT, "非法的节点实例执行状态");
    }

    model.setStatus(nodeInstanceStatusEnum);
    model.setExecCount(record.getExecCount());
    model.setFailedCount(record.getFailedCount());
    model.setRecoverTime(record.getRecoverTime());
    model.setCreateTime(record.getCreateTime());
    model.setModifiedTime(record.getModifiedTime());
    return model;
  }

  /**
   * 新增流程节点实例
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @return 是否添加成功
   */
  @Override
  public boolean addNodeInstance(Long processInstanceId, Long nodeId, String bizNo) {
    ProcessNodeInstanceDO record = new ProcessNodeInstanceDO();
    record.setProcessInstanceId(processInstanceId);
    record.setNodeId(nodeId);
    record.setBizNo(bizNo);

    record.setStatus(NodeInstanceStatusEnum.START.getStatus());
    record.setExecCount(0);
    record.setFailedCount(0);
    record.setRecoverTime(new Date());
    return processNodeInstanceDOMapper.insertNodeInstance(record) > 0;
  }
}
