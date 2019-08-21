/**
 * Lingxi.com Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.service.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * 节点实例执行状态枚举
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/21 21:15
 */
@Getter
public enum NodeInstanceStatusEnum {

  /** 开始 */
  START(1, "开始"),
  /** 完成 */
  END(2, "完成"),
  /** 异常 */
  ERROT(3, "异常"),
  /** 等待 */
  WAIT(4, "等待"),
  ;
  /** 节点类型 */
  private Integer status;
  /** 节点类型描述 */
  private String desc;

  /**
   * 构造方法
   *
   * @param status
   * @param desc
   */
  NodeInstanceStatusEnum(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }

  /** nodeInstanceStatusEnumMap */
  private static Map<Integer, NodeInstanceStatusEnum> nodeInstanceStatusEnumMap = new HashMap<>(4);

  static {
    for (NodeInstanceStatusEnum nodeTypeEnum : values()) {
      nodeInstanceStatusEnumMap.put(nodeTypeEnum.status, nodeTypeEnum);
    }
  }

  /**
   * 根据执行状态类型获取节点实例执行状态枚举
   *
   * @param status 节点类型
   * @return
   */
  public static NodeInstanceStatusEnum getNodeInstanceStatusEnumByStatus(Integer status) {
    return nodeInstanceStatusEnumMap.get(status);
  }
}
