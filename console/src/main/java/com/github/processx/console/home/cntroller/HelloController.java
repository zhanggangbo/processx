/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.console.home.cntroller;

import com.github.processx.core.ProcessInstance;
import com.github.processx.core.ProcessInstanceFactory;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
@Controller
@RequestMapping("/")
public class HelloController {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String printWelcome(ModelMap model) {

    String bizNo = "bizno20190804";
    String processName = "credit";
    Map<String, String> param = new HashMap<>();

    ProcessInstance processInstance = ProcessInstanceFactory.create(bizNo, processName, param);
    model.addAttribute("message", processInstance);
    return "index";
  }
}
