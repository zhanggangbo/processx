/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.console.home.controller;

import com.github.processx.manager.service.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public class BaseController {
  /**
   * 服务模板类
   *
   * <p>1. 响应端响应端统一数据输出格式
   *
   * <p>2. 日志统一打印
   *
   * <p>3. 异常统一处理
   *
   * <p>4. 数据库事务统一管理
   */
  @Autowired public ServiceTemplate serviceTemplate;
}
