/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.console.home.util;

import com.github.processx.common.util.LoggerUtil;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Http与Servlet工具类.
 */
public class ServletUtils {

    /**
     * Content Type 定义
     */
    public static final String TEXT_TYPE = "text/plain";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";

    /**
     * 日志记录
     */
    private static final Logger LOGGER = LogManager
        .getLogger(ServletUtils.class);

    /**
     * 设置客户端缓存过期时间 的Header.
     *
     * @param response
     * @param expiresSeconds
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        //Http 1.0 header
        response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
        //Http 1.1 header
        response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     *
     * @param response
     */
    public static void setDisableCacheHeader(HttpServletResponse response) {
        //Http 1.0 header
        response.setDateHeader("Expires", 1L);
        response.addHeader("Pragma", "no-cache");
        //Http 1.1 header
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
    }


    /**
     * 设置让浏览器弹出下载对话框的Header.
     *
     * @param fileName 下载后的文件名.
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName
                + "\"");
        } catch (UnsupportedEncodingException e) {
            LoggerUtil.error(LOGGER, e, "throw UnsupportedEncodingException");
            throw new RuntimeException(e);
        }
    }


}
