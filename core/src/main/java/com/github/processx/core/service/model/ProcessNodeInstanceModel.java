/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service.model;

import com.github.processx.core.service.enums.NodeInstanceStatusEnum;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 流程节点实例定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 21:09
 */
@Getter
@Setter
public class ProcessNodeInstanceModel implements Serializable {
  /** 主键 */
  private Long id;

  /** 流程实例ID */
  private Long processInstanceId;

  /** 业务流水号 */
  private String bizNo;

  /** 节点ID */
  private Long nodeId;

  /**
   * 节点实例执行状态
   *
   * @see com.github.processx.core.service.enums.NodeInstanceStatusEnum
   */
  private NodeInstanceStatusEnum status;

  /** 失败执行次数 */
  private Integer failedCount;

  /** 已执行次数 */
  private Integer execCount;

  /** 下次执行时间 */
  private Date recoverTime;

  /** 创建时间 */
  private Date createTime;

  /** 修改时间 */
  private Date modifiedTime;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
