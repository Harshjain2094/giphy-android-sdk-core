/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.network.engine

import android.net.Uri

import com.giphy.sdk.core.network.response.GenericResponse
import com.giphy.sdk.core.threading.ApiTask

/**
 * A generic interface that describes all the params of a low level GET request.
 */
interface NetworkSession {
    fun <T : GenericResponse> queryStringConnection(serverUrl: Uri,
                                                    path: String,
                                                    method: String,
                                                    responseClass: Class<T>,
                                                    queryStrings: Map<String, String>?,
                                                    headers: Map<String, String>?): ApiTask<T>

    fun <T : GenericResponse> postStringConnection(serverUrl: Uri,
                                                   path: String,
                                                   method: String,
                                                   responseClass: Class<T>,
                                                   queryStrings: Map<String, String>?,
                                                   headers: Map<String, String>?,
                                                   requestBody: Any?): ApiTask<T>
}
