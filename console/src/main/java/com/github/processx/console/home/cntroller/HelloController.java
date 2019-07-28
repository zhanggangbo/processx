/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.console.home.cntroller;


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


    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello, world!");
        return "index";
    }
}
