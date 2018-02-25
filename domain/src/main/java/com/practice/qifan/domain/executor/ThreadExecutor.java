package com.practice.qifan.domain.executor;

/**
 * Created by qifan on 2018/2/20.
 */

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the out of the UI thread.
 */
public interface ThreadExecutor extends Executor {}