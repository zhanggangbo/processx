/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.console.home.cntroller;


import com.github.processx.console.home.util.WebUtils;
import com.github.processx.core.service.ProcessConfigService;
import com.github.processx.manager.service.result.CommonWebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A sample controller.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private ProcessConfigService processConfigService;


    /**
     *
     */
    @RequestMapping(value = {"/index.htm"}, method = RequestMethod.GET)
    public void index(Model model, HttpServletRequest request, HttpServletResponse response) {
        CommonWebResult result = new CommonWebResult();

        serviceTemplate.executeWithoutTransaction(result, "", "", () ->
            result.setData(processConfigService.getAllProcessDefinition())
        );

        WebUtils.renderJson(response, result);

    }


}
