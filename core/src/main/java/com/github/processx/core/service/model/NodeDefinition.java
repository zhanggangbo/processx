/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.service.model;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 流程节点定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/25 22:30
 */
@Getter
@Setter
public class NodeDefinition implements Serializable {

  /** 节点ID */
  private Long nodeId;

  /** 流程ID */
  private Long processId;

  /** 流程节点名称 */
  private String name;

  /** 流程节点类型 */
  private Integer nodeType;

  /** 执行组件 */
  private String executeCompoment;

  /** 最大执行时间 */
  private Integer maxExeTime;

  /** 是否同步 */
  private Boolean isSync;

  /** 是否被保护 */
  private Boolean isProtected;

  /** 模块名称 */
  private String stage;

  /** 上一个节点标识 */
  private List<Long> preNodeIdList;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
