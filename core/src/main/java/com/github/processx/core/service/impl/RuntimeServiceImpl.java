/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.service.impl;

import com.github.processx.common.bean.BeanCheckUtil;
import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.enums.NodeInstanceStatusEnum;
import com.github.processx.core.service.model.ProcessNodeInstanceModel;
import com.github.processx.dal.daointerface.ProcessInstanceDOMapper;
import com.github.processx.dal.daointerface.ProcessNodeInstanceDOMapper;
import com.github.processx.dal.dataobjects.ProcessNodeInstanceDO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 业务运行时服务接口
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 21:23
 */
public class RuntimeServiceImpl implements RuntimeService {

  /** 节点实例DAO接口 */
  @Autowired private ProcessNodeInstanceDOMapper processNodeInstanceDOMapper;

  /** 流程实例DAO接口 */
  @Autowired private ProcessInstanceDOMapper processInstanceDOMapper;

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

    return getNodeInstanceModel(record);
  }

  /**
   * 查询指定业务流水号下所有流程节点
   *
   * @param bizNo 业务流水
   * @return 流程节点实例
   */
  @Override
  public List<ProcessNodeInstanceModel> queryExecNodeInstance(String bizNo) {
    List<ProcessNodeInstanceDO> nodeInstanceList =
        processNodeInstanceDOMapper.selectExecNodeInstance(bizNo);

    if (BeanCheckUtil.checkNullOrEmpty(nodeInstanceList)) {
      return null;
    }

    List<ProcessNodeInstanceModel> modelList = new ArrayList<>(nodeInstanceList.size());
    nodeInstanceList.forEach(record -> modelList.add(getNodeInstanceModel(record)));
    return modelList;
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

  /**
   * 更新节点实例状态
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @param status 流程节点状态
   * @return 是否更新成功
   */
  @Override
  public boolean updateNodeInstanceStatus(
      Long processInstanceId, Long nodeId, String bizNo, Integer status) {
    return processNodeInstanceDOMapper.updateNodeInstanceStatus(
            processInstanceId, nodeId, bizNo, status)
        > 0;
  }

  /**
   * 更新节点实例修改时间
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 流程节点ID
   * @param bizNo 业务流水
   * @param status 流程节点状态
   * @param modifiedTime 节点实例修改时间
   * @return 是否更新成功
   */
  @Override
  public boolean updateNodeInstance4ModifiedTime(
      Long processInstanceId, Long nodeId, String bizNo, Integer status, Date modifiedTime) {
    return processNodeInstanceDOMapper.updateNodeInstance4ModifiedTime(
            processInstanceId, nodeId, bizNo, status, modifiedTime)
        > 0;
  }

  /** getNodeInstanceModel */
  private ProcessNodeInstanceModel getNodeInstanceModel(ProcessNodeInstanceDO record) {
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
}
