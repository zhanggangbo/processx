/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.manager.service.result;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询结果集
 *
 * @author zhanggangbo
 * @version v 0.1 2019/10/3 17:19
 */
@Getter
@Setter
public class CommonPageResult<T> {
  /** 分页数据 */
  private List<T> pageData;
  /** 总页数 */
  private long total;

  /**
   * 构造方法
   *
   * @param pageData
   * @param total
   */
  public CommonPageResult(List<T> pageData, long total) {
    this.pageData = pageData;
    this.total = total;
  }
}
