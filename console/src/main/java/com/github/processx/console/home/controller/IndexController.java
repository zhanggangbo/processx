/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.console.home.controller;

import com.github.processx.console.home.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

  /**
   * 首页渲染
   *
   * @return
   */
  @RequestMapping(
      value = {"/index.htm"},
      method = RequestMethod.GET)
  public String index() {
    return "index";
  }
}
