/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.api.AutoExecution;
import com.github.processx.api.Execution;
import com.github.processx.api.GatewayExecution;
import com.github.processx.api.ScheduleExecution;
import com.github.processx.api.TriggerExecution;
import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.service.ProcessConfigService;
import com.github.processx.core.service.enums.NodeTypeEnum;
import com.github.processx.core.service.model.NodeDefinition;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.core.util.BeanFactoryUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 流程加载器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 16:08
 */
public class ProcessLoader implements InitializingBean, DisposableBean, ApplicationContextAware {

  /** 日志记录 */
  private static final Logger LOGGER = LogManager.getLogger(ProcessLoader.class);

  /** @see org.springframework.context.ApplicationContext */
  protected ApplicationContext applicationContext;

  /** 流程配置服务接口 */
  @Autowired private ProcessConfigService processConfigService;

  /** 流程缓存 */
  public static Map<String, ProcessDefinition> PROCESS_DEFINITION_MAP = new ConcurrentHashMap();

  /** 节点缓存 */
  public static Map<String, NodeDefinition> NODE_DEFINITION_MAP = new ConcurrentHashMap();

  /** 根据节点id节点缓存 */
  public static Map<Long, NodeDefinition> NODE_DEFINITION_4_ID_MAP = new ConcurrentHashMap();

  /**
   * Invoked by the containing {@code BeanFactory} after it has set all bean properties and
   * satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
   *
   * <p>This method allows the bean instance to perform validation of its overall configuration and
   * final initialization when all bean properties have been set.
   *
   * @throws Exception in the event of misconfiguration (such as failure to set an essential
   *     property) or if initialization fails for any other reason
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    LoggerUtil.info(LOGGER, "process config load start");
    Map<Long, ProcessDefinition> processDefinitionMap =
        processConfigService.getAllProcessDefinition();

    for (Entry<Long, ProcessDefinition> entry : processDefinitionMap.entrySet()) {
      ProcessDefinition processDefinition = entry.getValue();
      PROCESS_DEFINITION_MAP.put(processDefinition.getName(), processDefinition);

      List<NodeDefinition> nodeDefinitionList = processDefinition.getNodeDefinitionList();
      for (NodeDefinition nodeDefinition : nodeDefinitionList) {
        NODE_DEFINITION_MAP.put(nodeDefinition.getName(), nodeDefinition);
        NODE_DEFINITION_4_ID_MAP.put(nodeDefinition.getNodeId(), nodeDefinition);
      }
    }

    LoggerUtil.info(LOGGER, "process config load end");
  }

  /**
   * 根据流程名称获取流程信息
   *
   * @param processName
   * @return
   */
  public static ProcessDefinition getProcessDefinition(String processName) {
    return PROCESS_DEFINITION_MAP.get(processName);
  }

  /**
   * 根据节点名称获取节点信息
   *
   * @param nodeName
   * @return
   */
  public static NodeDefinition getNodeDefinition(String nodeName) {
    return NODE_DEFINITION_MAP.get(nodeName);
  }

  /**
   * 根据节点id获取节点信息
   *
   * @param nodeId
   * @return
   */
  public static NodeDefinition getNodeDefinition(Long nodeId) {
    return NODE_DEFINITION_4_ID_MAP.get(nodeId);
  }

  /**
   * 获取节点执行接口
   *
   * @param nodeType
   * @param executeCompoment
   * @return
   */
  public static Execution getExecution(NodeTypeEnum nodeType, String executeCompoment) {
    switch (nodeType) {
      case AUTO:
        BeanFactoryUtil.getBean(executeCompoment, AutoExecution.class);
        break;
      case TRIGGER:
        BeanFactoryUtil.getBean(executeCompoment, TriggerExecution.class);
        break;
      case GATEWAY:
        BeanFactoryUtil.getBean(executeCompoment, GatewayExecution.class);
        break;
      case SCHEDULE:
        BeanFactoryUtil.getBean(executeCompoment, ScheduleExecution.class);
        break;
      default:
        break;
    }

    return null;
  }

  /**
   * Invoked by the containing {@code BeanFactory} on destruction of a bean.
   *
   * @throws Exception in case of shutdown errors. Exceptions will get logged but not rethrown to
   *     allow other beans to release their resources as well.
   */
  @Override
  public void destroy() throws Exception {}

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {}
}
