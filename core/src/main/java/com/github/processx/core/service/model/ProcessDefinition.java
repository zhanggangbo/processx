/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service.model;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 流程定义
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/25 22:02
 */
@Getter
@Setter
public class ProcessDefinition implements Serializable {
  /** 流程ID */
  private Long processId;

  /** 流程名称 */
  private String name;

  /** 版本号 */
  private Integer version;

  /** 流程节点 */
  List<NodeDefinition> nodeDefinitionList;

  /** 流程特征 */
  ProcessFeature processFeature;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
