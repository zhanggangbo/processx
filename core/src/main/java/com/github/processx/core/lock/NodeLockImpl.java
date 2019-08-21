/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.lock;

import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.model.ProcessFeature;
import com.github.processx.core.service.model.ProcessNodeInstanceModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 节点锁
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/18 17:11
 */
public class NodeLockImpl implements NodeLock {
  /** 业务运行时服务接口 */
  @Autowired
  private RuntimeService runtimeService;

  /**
   * 节点锁
   *
   * <p>1.判断流程特征是否记录节点实例 ，不记录则获取到执行锁，执行后面操作；
   *
   * <p>2.查询节点实例是否存在，
   *
   * <p>不存在：新增流程实例，新增成功则获取到执行锁，执行后面操作，否则拿锁失败；
   *
   * <p>存在：判断节点属性（isProtected）是否保护，
   *
   * <p>不保护则更新节点实例状态为开始执行（START）,更新成功则获取到执行锁，执行后面操作，否则拿锁失败；
   *
   * <p>判断节点状态：
   *
   * <p>（1）节点状态为START：判断节点执行时间是否超过最大执行时间，超过更新修改时间，更新成功则获取到执行锁，执行后面操作，否则拿锁失败，没有超时抛运行中异常；
   *
   * <p>（2）节点状态为END：抛节点完成异常
   *
   * <p>（3）节点状态为WAIT：更新状态为START，更新成功则获取到执行锁，执行后面操作，否则拿锁失败；
   *
   * <p>（4）节点状态为ERROR：更新状态为START，更新成功则获取到执行锁，执行后面操作，否则拿锁失败；
   *
   * @param processInstanceId 流程实例ID
   * @param nodeId 节点ID
   * @param bizNo 业务流水号
   * @param isProtected isProtected为false流程引擎执行到该节点无论节点什么状态都会重新执行
   * @param processFeature 流程特征
   * @return 是否拿到锁
   */
  @Override
  public boolean getLock(
    Long processInstanceId,
    Long nodeId,
    String bizNo,
    Boolean isProtected,
    ProcessFeature processFeature) {
    if (!processFeature.getRecordNodeInstance()) {
      return true;
    }

    ProcessNodeInstanceModel processNodeInstance =
      runtimeService.queryNodeInstance(processInstanceId, nodeId, bizNo);

    if (processNodeInstance == null) {
      return runtimeService.addNodeInstance(processInstanceId, nodeId, bizNo);
    }

    // 不保护更新状态为START
    if (!isProtected) {

      return true;
    }

    // 流程节点状态判断
    return false;
  }
}
