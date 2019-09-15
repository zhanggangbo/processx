/**
 * GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.core.threadpool;

import com.github.processx.common.enums.LoggerEnum;
import com.github.processx.common.util.LoggerUtil;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 监控线程执池行器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/14 23:02
 */
public class MonitorThreadPoolExecutor extends ThreadPoolExecutor {
  /** 日志记录 */
  private static final Logger LOGGER =
    LogManager.getLogger(LoggerEnum.THREAD_POOL_DIGEST.getLogger());

  /** 线程池名称 */
  private String threadPoolName;

  private ScheduledExecutorService scheduledExecutorService;

  /**
   * 监控线程池
   *
   * @param nThreads
   * @param threadFactoryBuilder
   */
  public MonitorThreadPoolExecutor(int nThreads, ThreadFactoryBuilder threadFactoryBuilder) {
    this(
      nThreads,
      nThreads,
      0L,
      TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<>(),
      threadFactoryBuilder.build(),
      new AbortPolicy());

    scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    scheduledExecutorService.scheduleWithFixedDelay(
      new ThreadPoolStatistics(), 5, 10, TimeUnit.SECONDS);

    threadPoolName = threadFactoryBuilder.getThreadPoolName();
  }

  /**
   * Creates a new {@code ThreadPoolExecutor} with the given initial parameters.
   *
   * @param corePoolSize the number of threads to keep in the pool, even if they are idle, unless
   *     {@code allowCoreThreadTimeOut} is set
   * @param maximumPoolSize the maximum number of threads to allow in the pool
   * @param keepAliveTime when the number of threads is greater than the core, this is the maximum
   *     time that excess idle threads will wait for new tasks before terminating.
   * @param unit the time unit for the {@code keepAliveTime} argument
   * @param workQueue the queue to use for holding tasks before they are executed. This queue will
   *     hold only the {@code Runnable} tasks submitted by the {@code execute} method.
   * @param threadFactory the factory to use when the executor creates a new thread
   * @param handler the handler to use when execution is blocked because the thread bounds and queue
   *     capacities are reached
   * @throws IllegalArgumentException if one of the following holds:<br>
   *     {@code corePoolSize < 0}<br>
   *     {@code keepAliveTime < 0}<br>
   *     {@code maximumPoolSize <= 0}<br>
   *     {@code maximumPoolSize < corePoolSize}
   * @throws NullPointerException if {@code workQueue} or {@code threadFactory} or {@code handler}
   *     is null
   */
  public MonitorThreadPoolExecutor(
    int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
  }

  /** 线程池监控定时统计 */
  private class ThreadPoolStatistics implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread,
     * starting the thread causes the object's <code>run</code> method to be called in that
     * separately executing thread.
     *
     * <p>The general contract of the method <code>run</code> is that it may take any action
     * whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
      /**
       * 线程池监控日志输出
       *
       * <p>线程池名称, 线程池主线程数, 线程池最大线程数, 当前线程池数, 线程池中当前正在执行的任务的工作线程数, 当前工作队列实例数, 当前线程到到目前为止已经处理完毕的任务数
       */
      LoggerUtil.info(
        LOGGER,
        "{0},{1},{2},{3},{4},{5},{6}",
        threadPoolName,
        getCorePoolSize(),
        getMaximumPoolSize(),
        getPoolSize(),
        getActiveCount(),
        getQueue().size(),
        getCompletedTaskCount());
    }
  }
}
