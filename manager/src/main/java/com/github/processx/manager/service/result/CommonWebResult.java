/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.manager.service.result;


import com.github.processx.common.exception.ProcessxResultEnum;
import lombok.Getter;
import lombok.Setter;


/**
 * Web通用返回结果集
 */
@Setter
@Getter
public class CommonWebResult<T> extends CommonResult {

    /**
     * 成功标识
     */
    private boolean success = false;

    /**
     * 结果码
     */
    private String resultCode;

    /**
     * 结果信息
     */
    private String resultMsg;

    /**
     * 跳转页面
     */
    private String resultUrl;

    /**
     * 业务数据
     */
    private T data = null;

    /**
     * 成功结果信息设置
     */
    @Override
    public void setSuccessMessage() {
        this.success = true;
        this.resultCode = ProcessxResultEnum.SUCCESS.getCode();
        this.resultMsg = ProcessxResultEnum.SUCCESS.getDescription();
    }

    /**
     * 异常结果信息设置
     *
     * @param resultCode 异常结果码
     * @param resultMsg 异常结果信息
     */
    @Override
    public void setErrorMessage(String resultCode, String resultMsg) {
        this.success = false;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
