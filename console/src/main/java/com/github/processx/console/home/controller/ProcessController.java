/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.console.home.controller;

import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.console.home.controller.base.BaseController;
import com.github.processx.manager.service.ProcessService;
import com.github.processx.manager.service.result.CommonWebResult;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 流程web
 *
 * @author zhanggangbo
 * @version v 0.1 2019/10/3 17:07
 */
@Controller
@RequestMapping("/process")
public class ProcessController extends BaseController {
  /** 流程服务 */
  @Autowired private ProcessService processService;

  /**
   * 分页获取流程信息
   *
   * @param response
   * @param queryData
   * @param pageNum
   * @param pageSize
   */
  @RequestMapping(
      value = {"/page.json"},
      method = RequestMethod.GET)
  public CommonWebResult findProcess(
      HttpServletResponse response,
      @RequestParam(value = "queryData") String queryData,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    CommonWebResult result = new CommonWebResult();
    serviceTemplate.executeWithoutTransaction(
        result,
        "分页获取流程信息",
        MessageFormat.format(
            "queryData={0},pageNum={1},pageSize={2}", queryData, pageNum, pageSize),
        () -> result.setData(processService.findProcess(queryData, pageNum, pageSize)));

    return result;
  }

  /**
   * 根据流程ID获取流程节点信息
   *
   * @param response
   * @param processId
   */
  @RequestMapping(
      value = {"/node.json"},
      method = RequestMethod.GET)
  public CommonWebResult findNode(
      HttpServletResponse response, @RequestParam(value = "processId") Long processId) {
    CommonWebResult result = new CommonWebResult();
    serviceTemplate.executeWithoutTransaction(
        result,
        "根据流程ID获取流程节点信息",
        MessageFormat.format("processId={0}", processId),
        () -> {
          if (processId == null) {
            throw new ProcessxException(ProcessxResultEnum.NULL_ARGUMENT, "流程ID不能为空");
          }

          result.setData(processService.findProcessNode(processId));
        });

    return result;
  }
}
