/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.dal.daointerface.ProcessTriggerResultDOMapper;
import com.github.processx.dal.dataobjects.ProcessTriggerResultDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhanggangbo
 * @version v 0.1 2019/9/22 16:29
 */
public class ProcessTriggerResultHandler {

  /** DAO接口注入 */
  @Autowired private ProcessTriggerResultDOMapper processTriggerResultDOMapper;

  /**
   * @param bizNo
   * @param onsetNodeId
   * @return
   */
  public TriggerResult selectTriggerResult(String bizNo, Long onsetNodeId) {
    ProcessTriggerResultDO processTriggerResultDO =
        processTriggerResultDOMapper.selectProcessTriggerResult(bizNo, onsetNodeId);
    if (processTriggerResultDO != null) {
      TriggerResult triggerResult = new TriggerResult();
      triggerResult.setBizNo(processTriggerResultDO.getBizNo());
      triggerResult.setNodeId(processTriggerResultDO.getNodeId());
      triggerResult.setProcessInstanceId(processTriggerResultDO.getProcessInstanceId());
      triggerResult.setValue(processTriggerResultDO.getValue());
      return triggerResult;
    }

    return null;
  }
}
