/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.service.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程节点类型枚举
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public enum NodeTypeEnum {

  /** 自动节点 */
  AUTO(1, "自动节点"),
  /** 触发节点 */
  TRIGGER(2, "触发节点"),
  /** 网关节点 */
  GATEWAY(3, "网关节点"),
  /** 定时节点 */
  SCHEDULE(4, "定时节点"),
  ;

  /** 节点类型 */
  private Integer type;

  /** 节点类型描述 */
  private String desc;

  /**
   * 构造方法
   *
   * @param type 节点类型
   * @param desc 节点类型描述
   */
  NodeTypeEnum(Integer type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  /** nodeTypeEnumMap */
  private static Map<Integer, NodeTypeEnum> nodeTypeEnumMap = new HashMap<>(4);

  static {
    for (NodeTypeEnum nodeTypeEnum : values()) {
      nodeTypeEnumMap.put(nodeTypeEnum.type, nodeTypeEnum);
    }
  }

  /**
   * 根据节点类型获取流程节点类型枚举
   *
   * @param type 节点类型
   * @return
   */
  public static NodeTypeEnum getNodeTypeEnumByType(Integer type) {
    return nodeTypeEnumMap.get(type);
  }
}
