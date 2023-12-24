/** GitHub. Inc. Copyright (c) 2018-2019 All Rights Reserved. */
package com.github.processx.core.threadpool;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * ThreadFactory 构建器
 *
 * @author zhanggangbo
 * @version v 0.1 2019/9/15 0:04
 * @see com.google.common.util.concurrent.ThreadFactoryBuilder
 */
public final class ThreadFactoryBuilder {
  private String threadPoolName;
  private Boolean daemon = null;
  private Integer priority = null;
  private UncaughtExceptionHandler uncaughtExceptionHandler = null;
  private ThreadFactory backingThreadFactory = null;

  /** Creates a new {@link ThreadFactory} builder. */
  public ThreadFactoryBuilder(String threadPoolName) {
    this.threadPoolName = threadPoolName;
  }

  /**
   * Sets the naming format to use when naming threads ({@link Thread#setName}) which are created
   * with this ThreadFactory.
   *
   * @param threadPoolName
   * @return this for the builder pattern
   */
  public ThreadFactoryBuilder setThreadPoolName(String threadPoolName) {
    this.threadPoolName = threadPoolName;
    return this;
  }

  /**
   * Sets daemon or not for new threads created with this ThreadFactory.
   *
   * @param daemon whether or not new Threads created with this ThreadFactory will be daemon threads
   * @return this for the builder pattern
   */
  public ThreadFactoryBuilder setDaemon(boolean daemon) {
    this.daemon = daemon;
    return this;
  }

  /**
   * Sets the priority for new threads created with this ThreadFactory.
   *
   * @param priority the priority for new Threads created with this ThreadFactory
   * @return this for the builder pattern
   */
  public ThreadFactoryBuilder setPriority(int priority) {
    // Thread#setPriority() already checks for validity. These error messages
    // are nicer though and will fail-fast.
    checkArgument(
        priority >= Thread.MIN_PRIORITY,
        "Thread priority (%s) must be >= %s",
        priority,
        Thread.MIN_PRIORITY);
    checkArgument(
        priority <= Thread.MAX_PRIORITY,
        "Thread priority (%s) must be <= %s",
        priority,
        Thread.MAX_PRIORITY);
    this.priority = priority;
    return this;
  }

  /**
   * Sets the {@link UncaughtExceptionHandler} for new threads created with this ThreadFactory.
   *
   * @param uncaughtExceptionHandler the uncaught exception handler for new Threads created with
   *     this ThreadFactory
   * @return this for the builder pattern
   */
  public ThreadFactoryBuilder setUncaughtExceptionHandler(
      UncaughtExceptionHandler uncaughtExceptionHandler) {
    this.uncaughtExceptionHandler = checkNotNull(uncaughtExceptionHandler);
    return this;
  }

  /**
   * Sets the backing {@link ThreadFactory} for new threads created with this ThreadFactory. Threads
   * will be created by invoking #newThread(Runnable) on this backing {@link ThreadFactory}.
   *
   * @param backingThreadFactory the backing {@link ThreadFactory} which will be delegated to during
   *     thread creation.
   * @return this for the builder pattern
   * @see MoreExecutors
   */
  public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory) {
    this.backingThreadFactory = checkNotNull(backingThreadFactory);
    return this;
  }

  /**
   * Returns a new thread factory using the options supplied during the building process. After
   * building, it is still possible to change the options used to build the ThreadFactory and/or
   * build again. State is not shared amongst built instances.
   *
   * @return the fully constructed {@link ThreadFactory}
   */
  public ThreadFactory build() {
    return build(this);
  }

  private static ThreadFactory build(ThreadFactoryBuilder builder) {
    final String threadPoolName = builder.threadPoolName;
    final Boolean daemon = builder.daemon;
    final Integer priority = builder.priority;
    final UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
    final ThreadFactory backingThreadFactory =
        (builder.backingThreadFactory != null)
            ? builder.backingThreadFactory
            : Executors.defaultThreadFactory();

    return (runnable) -> {
      Thread thread = backingThreadFactory.newThread(runnable);
      if (threadPoolName != null) {
        thread.setName(threadPoolName);
      }
      if (daemon != null) {
        thread.setDaemon(daemon);
      }
      if (priority != null) {
        thread.setPriority(priority);
      }
      if (uncaughtExceptionHandler != null) {
        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
      }
      return thread;
    };
  }

  /**
   * 获取线程名称
   *
   * @return
   */
  public String getThreadPoolName() {
    return threadPoolName;
  }
}
