/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core;

import com.github.processx.api.AutoExecution;
import com.github.processx.api.Execution;
import com.github.processx.api.GatewayExecution;
import com.github.processx.api.ScheduleExecution;
import com.github.processx.api.TriggerExecution;
import com.github.processx.common.util.LoggerUtil;
import com.github.processx.core.executor.SynNodeExecutor;
import com.github.processx.core.listener.EventListener;
import com.github.processx.core.lock.NodeLock;
import com.github.processx.core.service.ProcessConfigService;
import com.github.processx.core.service.RuntimeService;
import com.github.processx.core.service.enums.NodeTypeEnum;
import com.github.processx.core.service.model.NodeDefinition;
import com.github.processx.core.service.model.ProcessDefinition;
import com.github.processx.core.util.BeanFactoryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
  private static ApplicationContext applicationContext;

  /** 流程配置服务接口 */
  @Autowired private ProcessConfigService processConfigService;

  /** 流程缓存 key：id */
  private static Map<Long, ProcessDefinition> ALL_PROCESS_DEFINITION_MAP = new ConcurrentHashMap();

  /** 流程缓存 key：name_version */
  private static Map<String, ProcessDefinition> PROCESS_DEFINITION_MAP = new ConcurrentHashMap();

  /**
   * 流程缓存 key：最终版本流程
   */
  private static Map<String, ProcessDefinition> LAST_PROCESS_DEFINITION_MAP =
    new ConcurrentHashMap();

  /**
   * 节点缓存
   */
  private static Map<Long, NodeDefinition> NODE_DEFINITION_MAP = new ConcurrentHashMap();

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
    LoggerUtil.info(LOGGER, "*******************************************************************");
    LoggerUtil.info(LOGGER, "*******************process config load start*******************");

    Map<Long, ProcessDefinition> processDefinitionMap =
        processConfigService.getAllProcessDefinition();

    List<ProcessDefinition> processDefinitionList = new ArrayList<>(processDefinitionMap.size());

    for (Entry<Long, ProcessDefinition> entry : processDefinitionMap.entrySet()) {
      processDefinitionList.add(entry.getValue());
    }

    // 根据版本号倒序
    Collections.sort(processDefinitionList, Comparator.comparing(ProcessDefinition::getVersion));

    for (ProcessDefinition process : processDefinitionList) {
      if (!LAST_PROCESS_DEFINITION_MAP.containsKey(process.getName())) {
        LAST_PROCESS_DEFINITION_MAP.put(process.getName(), process);
      }

      ALL_PROCESS_DEFINITION_MAP.put(process.getProcessId(), process);

      PROCESS_DEFINITION_MAP.put(process.getName() + "_" + process.getVersion(), process);

      List<NodeDefinition> nodeDefinitionList = process.getNodeDefinitionList();
      for (NodeDefinition nodeDefinition : nodeDefinitionList) {
        NODE_DEFINITION_MAP.put(nodeDefinition.getNodeId(), nodeDefinition);
      }
    }

    LoggerUtil.info(LOGGER, "*******************process config load end*******************");
    LoggerUtil.info(LOGGER, "*******************************************************************");
  }

  /**
   * 根据流程名称获取流程信息
   *
   * @param processName
   * @param version
   * @return
   */
  public static ProcessDefinition getProcessDefinition(String processName, String version) {
    return PROCESS_DEFINITION_MAP.get(processName + "_" + version);
  }

  /** 根据流程名称获取最高版本流程信息 */
  public static ProcessDefinition getLastProcessDefinition(String processName) {
    return LAST_PROCESS_DEFINITION_MAP.get(processName);
  }

  /**
   * 根据流程id获取流程信息
   *
   * @param processId
   * @return
   */
  public static ProcessDefinition getProcessDefinition(Long processId) {
    return ALL_PROCESS_DEFINITION_MAP.get(processId);
  }

  /**
   * 根据节点id获取节点信息
   *
   * @param nodeId
   * @return
   */
  public static NodeDefinition getNodeDefinition(Long nodeId) {
    return NODE_DEFINITION_MAP.get(nodeId);
  }

  /**
   * 获取节点执行接口
   *
   * @param nodeType
   * @param executeCompoment
   * @return
   */
  public static Execution getExecution(NodeTypeEnum nodeType, String executeCompoment) {
    Execution execution = null;
    switch (nodeType) {
      case AUTO:
        execution = BeanFactoryUtil.getBean(executeCompoment, AutoExecution.class);
        break;
      case TRIGGER:
        execution = BeanFactoryUtil.getBean(executeCompoment, TriggerExecution.class);
        break;
      case GATEWAY:
        execution = BeanFactoryUtil.getBean(executeCompoment, GatewayExecution.class);
        break;
      case SCHEDULE:
        execution = BeanFactoryUtil.getBean(executeCompoment, ScheduleExecution.class);
        break;
      default:
        break;
    }
    return execution;
  }

  /**
   * 获取业务运行时服务接口
   */
  public static RuntimeService getRuntimeService() {
    return applicationContext.getBean(RuntimeService.class);
  }

  /**
   * 获取流程事件监听器
   */
  public static EventListener getEventListener() {
    return applicationContext.getBean(EventListener.class);
  }

  /**
   * 获取流程节点锁
   */
  public static NodeLock getNodeLock() {
    return applicationContext.getBean(NodeLock.class);
  }

  /**
   * 获取节点同步执行器
   */
  public static SynNodeExecutor getSynNodeExecutor() {
    return applicationContext.getBean(SynNodeExecutor.class);
  }

  /**
   * Invoked by the containing {@code BeanFactory} on destruction of a bean.
   *
   * @throws Exception in case of shutdown errors. Exceptions will get logged but not rethrown to
   *     allow other beans to release their resources as well.
   */
  @Override
  public void destroy() throws Exception {}

  /**
   * setApplicationContext
   *
   * @param applicationContext
   * @throws BeansException
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ProcessLoader.applicationContext = applicationContext;
  }

  /**
   * getApplicationContext
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}
