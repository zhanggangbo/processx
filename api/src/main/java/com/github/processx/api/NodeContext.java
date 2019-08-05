/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 节点执行上下文
 *
 * @author zhanggangbo
 * @version v 0.1 2019/8/1 21:24
 */
@Getter
@Setter
public class NodeContext {
  /** 执行次数 */
  private Integer execCount;
  /** 失败次数 */
  private Integer failCount;
}
