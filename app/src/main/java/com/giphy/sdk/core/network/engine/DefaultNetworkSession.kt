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
import android.util.Log
import com.giphy.sdk.core.models.json.*
import com.giphy.sdk.core.network.api.GPHApiClient
import com.giphy.sdk.core.network.response.ErrorResponse
import com.giphy.sdk.core.network.response.GenericResponse
import com.giphy.sdk.core.threading.ApiTask
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

/**
 * Does the low level GET requests.
 */
class DefaultNetworkSession : NetworkSession {

    private var networkRequestExecutor: ExecutorService? = null
    private var completionExecutor: Executor? = null

    constructor() {
        networkRequestExecutor = ApiTask.getNetworkRequestExecutor()
        completionExecutor = ApiTask.getCompletionExecutor()
    }

    constructor(networkRequestExecutor: ExecutorService, completionExecutor: Executor) {
        this.networkRequestExecutor = networkRequestExecutor
        this.completionExecutor = completionExecutor
    }


    override fun <T : GenericResponse> queryStringConnection(serverUrl: Uri, path: String,
                                                             method: String, responseClass: Class<T>, queryStrings: Map<String, String>?,
                                                             headers: Map<String, String>?): ApiTask<T> {
        return postStringConnection(serverUrl, path, method, responseClass, queryStrings, headers, null)
    }

    override fun <T : GenericResponse> postStringConnection(serverUrl: Uri, path: String,
                                                            method: String, responseClass: Class<T>, queryStrings: Map<String, String>?,
                                                            headers: Map<String, String>?,
                                                            requestBody: Any?): ApiTask<T> {
        return ApiTask(Callable {
            var connection: HttpURLConnection? = null
            var url: URL? = null
            try {
                val uriBuilder = serverUrl.buildUpon().appendEncodedPath(path)

                if (queryStrings != null) {
                    for ((key, value) in queryStrings) {
                        uriBuilder.appendQueryParameter(key, value)
                    }
                }

                url = URL(uriBuilder.build().toString())
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = method

                if (headers != null) {
                    for ((key, value) in headers) {
                        connection.setRequestProperty(key, value)
                    }
                }


                if (method == GPHApiClient.HTTP_POST) {
                    connection.doOutput = true
                    connection.connect()
                    if (requestBody != null) {
                        val postDataBytes = GSON_INSTANCE.toJson(requestBody).toByteArray(charset("UTF-8"))
                        connection.outputStream.write(postDataBytes)
                    }
                } else {
                    connection.connect()
                }

                return@Callable readJsonResponse(url, connection, responseClass)
            } catch (t: Throwable) {
                Log.e(NetworkSession::class.java.name, "Unable to perform network request for url=" + url!!, t)
                throw t
            } finally {
                connection?.disconnect()
            }
        }, networkRequestExecutor, completionExecutor)
    }

    @Throws(IOException::class, ApiException::class)
    private fun <T : GenericResponse> readJsonResponse(url: URL?, connection: HttpURLConnection, responseClass: Class<T>): T {

        val responseCode = connection.responseCode
        val succeeded = (responseCode == HttpURLConnection.HTTP_OK
                || responseCode == HttpURLConnection.HTTP_CREATED
                || responseCode == HttpURLConnection.HTTP_ACCEPTED)
        var contents = ""
        if (succeeded) {
             contents = connection.inputStream.bufferedReader().readText()

        } else {
            contents = connection.errorStream.bufferedReader().readText()
        }

        return if (succeeded) {
            GSON_INSTANCE.fromJson(contents, responseClass)
        } else {
            when (responseCode) {
                HttpURLConnection.HTTP_UNAVAILABLE -> throw ApiException("503 Exception : URL : $url: Response Code :$responseCode", ErrorResponse(responseCode, null))
                HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    // Report if an invalid api key is used
                    Log.e(javaClass.toString(), "Api key invalid!")
                    try {
                        throw ApiException(GSON_INSTANCE.fromJson(contents, ErrorResponse::class.java))
                    } catch (e: JsonParseException) {
                        throw ApiException("Unable to parse server error response : " + url + " : " + contents + " : " + e.message,
                                ErrorResponse(responseCode, contents))
                    }

                }
                else -> try {
                    throw ApiException(GSON_INSTANCE.fromJson(contents, ErrorResponse::class.java))
                } catch (e: JsonParseException) {
                    throw ApiException("Unable to parse server error response : " + url + " : " + contents + " : " + e.message, ErrorResponse(responseCode, contents))
                }

            }
        }
    }

    companion object {
        val GSON_INSTANCE = GsonBuilder().registerTypeHierarchyAdapter(Date::class.java, DateDeserializer())
                .registerTypeHierarchyAdapter(Date::class.java, DateSerializer())
                .registerTypeHierarchyAdapter(Boolean::class.javaPrimitiveType, BooleanDeserializer())
                .registerTypeHierarchyAdapter(Int::class.javaPrimitiveType, IntDeserializer())
                .registerTypeAdapterFactory(MainAdapterFactory())
                .create()
    }
}
