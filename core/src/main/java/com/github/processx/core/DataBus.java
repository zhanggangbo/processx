/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core;

import com.github.processx.core.schedule.ScheduleResult;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务数据
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/15 13:33
 */
@Setter
@Getter
public class DataBus {
  protected static ThreadLocal<DataBus> threadLocal = new ThreadLocal<>();

  /** 业务流水号 */
  private String bizNo;
  /** 流程入参 */
  private Map<String, Object> processInput;
  /** 流程触发入参 */
  private Map<String, Object> triggerInput;
  /** 定时任务执行结果 */
  private ScheduleResult scheduleResult;
  /** 流程实例 */
  ProcessInstance processInstance;

  private Long onsetNodeId;

  private Map<String, Object> lastOutput;

  /** 构造方法 */
  private DataBus(String bizNo, ProcessInstance processInstance) {
    this.bizNo = bizNo;
    this.processInstance = processInstance;
  }

  /** 获取业务数据 */
  public static DataBus get() {
    return threadLocal.get();
  }

  /** 添加业务数据 */
  public static void put(DataBus dataBus) {
    if (threadLocal.get() != null) {
      threadLocal.remove();
    }
    threadLocal.set(dataBus);
  }

  /** 流程启动时，数据初始化 */
  public static DataBus init(
    String bizNo, Map<String, Object> processInput, ProcessInstance processInstance) {
    return init(bizNo, processInput, processInput, processInstance);
  }

  /** */
  public static DataBus init(String bizNo, ProcessInstance processInstance) {
    return init(bizNo, null, null, processInstance);
  }

  /**
   * 初始化
   *
   * @param bizNo 业务单号
   * @param processInput 流程输入
   * @param triggerInput 触发节点输入
   */
  private static DataBus init(
    String bizNo,
    Map<String, Object> processInput,
    Map<String, Object> triggerInput,
    ProcessInstance processInstance) {

    threadLocal.remove();

    DataBus dataBus = new DataBus(bizNo, processInstance);
    dataBus.setProcessInput(processInput);
    dataBus.setTriggerInput(triggerInput);

    threadLocal.set(dataBus);

    return dataBus;
  }
}
