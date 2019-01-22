/*
 * Created by Bogdan Tirca on 10/26/17.
 * Copyright © 2017 Giphy. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.threading


import android.os.Handler

import java.util.concurrent.Executor

/**
 * Adapts an Android [Handler][android.os.Handler] into a JRE [Executor][java.util.concurrent.Executor].
 * Runnables will be posted asynchronously.
 */
class HandlerExecutor
/**
 * Construct a new executor wrapping the specified handler.
 *
 * @param handler Handler to wrap.
 */
(
        /** Handler wrapped by this executor.  */
        private val handler: Handler) : Executor {

    /**
     * Execute a command, by posting it to the underlying handler.
     *
     * @param command Command to execute.
     */
    override fun execute(command: Runnable) {
        handler.post(command)
    }
}