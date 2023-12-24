/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.console.home.controller;

import com.github.processx.common.exception.ProcessxException;
import com.github.processx.common.exception.ProcessxResultEnum;
import com.github.processx.console.home.controller.base.BaseController;
import com.github.processx.manager.service.ProcessInstanceService;
import com.github.processx.manager.service.result.CommonWebResult;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 流程实例web
 *
 * @author zhanggangbo
 * @version v 0.1 2019/10/7 9:50
 */
@Controller
@RequestMapping("/process_instance")
public class ProcessNodeInstanceController extends BaseController {

  /** 流程实例服务 */
  @Autowired private ProcessInstanceService processInstanceService;

  /**
   * 分页获取流程实例信息
   *
   * @param response
   * @param queryData
   * @param pageNum
   * @param pageSize
   */
  @RequestMapping(
      value = {"/page.json"},
      method = RequestMethod.GET)
  public CommonWebResult findProcessInstance(
      HttpServletResponse response,
      @RequestParam(value = "queryData") String queryData,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    CommonWebResult result = new CommonWebResult();
    serviceTemplate.executeWithoutTransaction(
        result,
        "分页获取流程实例信息",
        MessageFormat.format(
            "queryData={0},pageNum={1},pageSize={2}", queryData, pageNum, pageSize),
        () ->
            result.setData(
                processInstanceService.findProcessInstance(queryData, pageNum, pageSize)));
    return result;
  }

  /**
   * 根据业务流水号获取流程节点实例信息
   *
   * @param response
   * @param bizNo
   */
  @RequestMapping(
      value = {"/node.json"},
      method = RequestMethod.GET)
  public CommonWebResult findProcessNodeInstance(
      HttpServletResponse response, @RequestParam(value = "bizNo") String bizNo) {
    CommonWebResult result = new CommonWebResult();
    serviceTemplate.executeWithoutTransaction(
        result,
        "根据业务流水号获取流程节点实例信息",
        MessageFormat.format("bizNo={0}", bizNo),
        () -> {
          if (StringUtils.isBlank(bizNo)) {
            throw new ProcessxException(ProcessxResultEnum.NULL_ARGUMENT, "业务流水号不能为空");
          }

          result.setData(processInstanceService.findProcessNodeInstance(bizNo));
        });

    return result;
  }
}
