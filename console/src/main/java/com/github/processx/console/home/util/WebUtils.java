/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.console.home.util;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * WebUtils
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 12:20
 */
public class WebUtils {

  /** 日志记录 */
  private static final Log logger = LogFactory.getLog(WebUtils.class);

  /** header 常量定义 */
  private static final String HEADER_ENCODING = "encoding";

  private static final String HEADER_NOCACHE = "no-cache";
  private static final String DEFAULT_ENCODING = "UTF-8";
  private static final boolean DEFAULT_NOCACHE = true;

  /** */
  public WebUtils() {}

  /**
   * 直接输出内容的简便函数.
   *
   * <p>eg. render(response, "text/plain", "hello", "encoding:GBK"); render(response, "text/plain",
   * "hello", "no-cache:false"); render(response, "text/plain", "hello", "encoding:GBK",
   * "no-cache:false");
   *
   * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
   */
  public static void render(
      HttpServletResponse response,
      final String contentType,
      final String content,
      final String... headers) {
    initResponseHeader(response, contentType, headers);
    try {
      response.getWriter().write(content);
      response.getWriter().flush();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * 输出文本
   *
   * @param response HttpServletResponse
   * @param text 文本信息
   * @param headers 消息头信息
   */
  public static void renderText(
      HttpServletResponse response, final String text, final String... headers) {
    render(response, ServletUtils.TEXT_TYPE, text, headers);
  }

  /**
   * 输出HTML
   *
   * @param response HttpServletResponse
   * @param html html信息
   * @param headers 消息头信息
   */
  public static void renderHtml(
      HttpServletResponse response, final String html, final String... headers) {
    render(response, ServletUtils.HTML_TYPE, html, headers);
  }

  /**
   * 输出XML.
   *
   * @param response HttpServletResponse
   * @param xml xml信息
   * @param headers 消息头信息
   */
  public static void renderXml(
      HttpServletResponse response, final String xml, final String... headers) {
    render(response, ServletUtils.XML_TYPE, xml, headers);
  }

  /**
   * 输出JSON.
   *
   * @param response HttpServletResponse
   * @param jsonString json字符串
   * @param headers 消息头信息
   */
  public static void renderJson(
      HttpServletResponse response, final String jsonString, final String... headers) {
    render(response, ServletUtils.JSON_TYPE, jsonString, headers);
  }

  /**
   * 输出JSON
   *
   * @param response HttpServletResponse
   * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map键值对
   * @param headers 消息头信息
   */
  public static void renderJson(
      HttpServletResponse response, final Object data, final String... headers) {
    initResponseHeader(response, ServletUtils.JSON_TYPE, headers);
    render(response, ServletUtils.JSON_TYPE, JSONObject.toJSONString(data), headers);
  }

  /**
   * 分析并设置contentType与headers.
   *
   * @param response HttpServletResponse
   * @param contentType contentType
   * @param headers 消息头信息
   */
  private static HttpServletResponse initResponseHeader(
      HttpServletResponse response, final String contentType, final String... headers) {
    // 分析headers参数
    String encoding = DEFAULT_ENCODING;
    boolean noCache = DEFAULT_NOCACHE;
    for (String header : headers) {
      String headerName = StringUtils.substringBefore(header, ":");
      String headerValue = StringUtils.substringAfter(header, ":");

      if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
        encoding = headerValue;
      } else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
        noCache = Boolean.parseBoolean(headerValue);
      } else {
        throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
      }
      response.addHeader(headerName, headerValue);
    }

    // 设置headers参数
    String fullContentType = contentType + ";charset=" + encoding;
    response.setContentType(fullContentType);
    if (noCache) {
      ServletUtils.setDisableCacheHeader(response);
    }
    return response;
  }

  /** 文件下载方法 */
  public static void downloadFile(HttpServletResponse response, String fileName, byte[] data) {
    try {
      response.setContentType("application/octet-stream");
      response.addHeader(
          "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
      ServletOutputStream out = response.getOutputStream();
      out.write(data); // 动态生成需要下载的内容
      out.flush();
      out.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }
}
