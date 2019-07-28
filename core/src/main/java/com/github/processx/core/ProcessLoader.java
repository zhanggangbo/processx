/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import com.github.processx.core.service.ProcessConfigService;
import com.github.processx.core.service.model.ProcessDefinition;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.BeansException;
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
  /** 流程配置服务接口 */
  @Autowired private ProcessConfigService processConfigService;

  protected ApplicationContext applicationContext;

  public static Map<String, ProcessDefinition> PROCESS_DEFINITION_MAP = new ConcurrentHashMap();

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
    Map<Long, ProcessDefinition> processDefinitionMap =
        processConfigService.getAllProcessDefinition();

    for (Entry<Long, ProcessDefinition> entry : processDefinitionMap.entrySet()) {
      ProcessDefinition processDefinition = entry.getValue();
      PROCESS_DEFINITION_MAP.put(processDefinition.getName(), processDefinition);
    }
  }

  /**
   * 根据流程名称获取流程信息
   *
   * @param name
   * @return
   */
  public ProcessDefinition getProcessDefinition(String name) {
    return PROCESS_DEFINITION_MAP.get(name);
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
