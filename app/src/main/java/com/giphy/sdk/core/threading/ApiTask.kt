/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.threading

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.giphy.sdk.core.network.api.CompletionHandler
import java.io.InterruptedIOException
import java.util.concurrent.*

/**
 * Represents a top level task returned by API calls that can be ran async or immediately
 * with an ExecutorService.
 *
 * @see Future
 *
 * @see ExecutorService
 */
class ApiTask<V> {

    private val callable: Callable<V>
    private val networkRequestExecutor: ExecutorService
    private val completionExecutor: Executor

    constructor(callable: Callable<V>) {
        this.callable = callable
        this.networkRequestExecutor = getNetworkRequestExecutor()
        this.completionExecutor = getCompletionExecutor()
    }

    constructor(callable: Callable<V>, networkRequestExecutor: ExecutorService, completionExecutor: Executor) {
        this.callable = callable
        this.networkRequestExecutor = networkRequestExecutor
        this.completionExecutor = completionExecutor
    }

    /**
     * Resolves the task on a shared thread pool executor service and returns the result using the
     * completionHandler
     *
     * @param completionHandler
     * @return
     */
    fun executeAsyncTask(completionHandler: CompletionHandler<V>): Future<*> {
        return networkRequestExecutor.submit {
            try {
                val value = callable.call()

                // If thread was interrupted, throw error
                if (Thread.currentThread().isInterrupted) {
                    throw InterruptedException()
                }

                completionExecutor.execute { completionHandler.onComplete(value, null) }
            } catch (e: ExecutionException) {
                Log.e(ApiTask::class.java.name, "Unable to perform async task, cancelling…", e)

                completionExecutor.execute { completionHandler.onComplete(null, e) }
            } catch (e: InterruptedIOException) { // interrupts will naturally occur from cancelling
            } catch (e: InterruptedException) {
            } catch (e: Throwable) {
                completionExecutor.execute { completionHandler.onComplete(null, e) }
            }
        }
    }

    /**
     * Immediately resolves the task on the same thread.
     *
     * @return The resolved value from running the task.
     * @throws Exception
     */
    @Throws(Exception::class)
    fun executeImmediately(): V {
        return callable.call()
    }

    companion object {

        /**
         * HTTP Executor settings borrowed from Bolts-Android's AndroidExecutors.newCachedThreadPool
         * https://github.com/BoltsFramework/Bolts-Android/blob/master/bolts-tasks/src/main/java/bolts/AndroidExecutors.java
         */
        val CPU_COUNT = Runtime.getRuntime().availableProcessors()
        val THREAD_POOL_CORE_SIZE = CPU_COUNT + 2
        val THREAD_POOL_MAX_SIZE = CPU_COUNT * 2 + 2
        val THREAD_POOL_KEEP_ALIVE_TIME = 1L

        private var NETWORK_REQUEST_EXECUTOR: ExecutorService? = null

        fun getNetworkRequestExecutor(): ExecutorService {
            if (NETWORK_REQUEST_EXECUTOR == null) {
                NETWORK_REQUEST_EXECUTOR = ThreadPoolExecutor(
                        THREAD_POOL_CORE_SIZE,
                        THREAD_POOL_MAX_SIZE,
                        THREAD_POOL_KEEP_ALIVE_TIME,
                        TimeUnit.SECONDS,
                        LinkedBlockingQueue()
                )
            }
            return NETWORK_REQUEST_EXECUTOR!!
        }

        private var COMPLETION_EXECUTOR: Executor? = null

        fun getCompletionExecutor(): Executor {
            if (COMPLETION_EXECUTOR == null) {
                COMPLETION_EXECUTOR = HandlerExecutor(Handler(Looper.getMainLooper()))
            }
            return COMPLETION_EXECUTOR!!
        }
    }
}