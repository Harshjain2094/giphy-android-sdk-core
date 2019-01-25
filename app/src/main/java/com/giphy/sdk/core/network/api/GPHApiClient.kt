/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.network.api

import com.giphy.sdk.core.models.enums.LangType
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.models.enums.RatingType
import com.giphy.sdk.core.network.engine.DefaultNetworkSession
import com.giphy.sdk.core.network.engine.NetworkSession
import com.giphy.sdk.core.network.response.*
import java.util.*
import java.util.concurrent.Future

/**
 * Main class that implements all endpoints supported by the sdk.
 */
class GPHApiClient @JvmOverloads constructor(val apiKey: String,
                                             val networkSession: NetworkSession = DefaultNetworkSession()) : GPHApi {

    override fun search(searchQuery: String, type: MediaType?, limit: Int?,
                        offset: Int?, rating: RatingType?,
                        lang: LangType?,
                        pingbackId: String?,
                        completionHandler: CompletionHandler<ListMediaResponse>): Future<*> {

        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        params["q"] = searchQuery
        limit?.let {
            params["limit"] = it.toString()
        }
        offset?.let {
            params["offset"] = it.toString()
        }
        rating?.let {
            params["rating"] = it.toString()
        }
        lang?.let {
            params["lang"] = it.toString()
        }
        pingbackId?.let {
            params["pingback_id"] = it
        }

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.SEARCH, mediaTypeToEndpoint(type)), HTTP_GET,
                ListMediaResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    override fun trending(type: MediaType?, limit: Int?,
                          offset: Int?, rating: RatingType?,
                          completionHandler: CompletionHandler<ListMediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        limit?.let {
            params["limit"] = it.toString()
        }
        offset?.let {
            params["offset"] = it.toString()
        }
        rating?.let {
            params["rating"] = it.toString()
        }
        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.TRENDING, mediaTypeToEndpoint(type)), HTTP_GET,
                ListMediaResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    override fun translate(term: String, type: MediaType?,
                           rating: RatingType?, lang: LangType?,
                           completionHandler: CompletionHandler<MediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        params["s"] = term
        rating?.let {
            params["rating"] = it.toString()
        }
        lang?.let {
            params["lang"] = it.toString()
        }
        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.TRANSLATE, mediaTypeToEndpoint(type)), HTTP_GET,
                MediaResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    override fun random(tag: String, type: MediaType?, rating: RatingType?,
                        completionHandler: CompletionHandler<MediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        params["tag"] = tag
        rating?.let {
            params["rating"] = it.toString()
        }

        val completionHandlerWrapper = object : CompletionHandler<RandomGifResponse> {
            override fun onComplete(result: RandomGifResponse?, e: Throwable?) {
                if (result != null) {
                    completionHandler.onComplete(result.toGifResponse(), null)
                } else {
                    completionHandler.onComplete(null, e)
                }
            }
        }

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.RANDOM, mediaTypeToEndpoint(type)), HTTP_GET,
                RandomGifResponse::class.java, params, null).executeAsyncTask(completionHandlerWrapper)
    }

    override fun categoriesForGifs(limit: Int?, offset: Int?,
                                   sort: String?,
                                   completionHandler: CompletionHandler<ListCategoryResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        limit?.let {
            params["limit"] = it.toString()
        }
        offset?.let {
            params["offset"] = it.toString()
        }
        sort?.let {
            params["sort"] = it
        }
        return networkSession.queryStringConnection(Constants.SERVER_URL,
                Constants.Paths.CATEGORIES, HTTP_GET, ListCategoryResponse::class.java, params, null)
                .executeAsyncTask(completionHandler)
    }

    override fun subCategoriesForGifs(categoryEncodedName: String,
                                      limit: Int?, offset: Int?,
                                      sort: String?,
                                      completionHandler: CompletionHandler<ListCategoryResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        limit?.let {
            params["limit"] = it.toString()
        }
        offset?.let {
            params["offset"] = it.toString()
        }
        sort?.let {
            params["sort"] = it
        }
        val completionHandlerWrapper = object : CompletionHandler<ListCategoryResponse> {
            override fun onComplete(result: ListCategoryResponse?, e: Throwable?) {
                result?.also {
                    result.data?.let {
                        for (subCategory in it) {
                            subCategory.encodedPath = categoryEncodedName + "/" + subCategory.nameEncoded
                        }
                    }
                    completionHandler.onComplete(result, null)
                } ?: run {
                    completionHandler.onComplete(null, e)
                }
            }
        }

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.SUBCATEGORIES, categoryEncodedName), HTTP_GET,
                ListCategoryResponse::class.java, params, null)
                .executeAsyncTask(completionHandlerWrapper)
    }

    override fun gifsByCategory(categoryEncodedName: String,
                                subCategoryEncodedName: String,
                                limit: Int?, offset: Int?,
                                ratingType: RatingType?, langType: LangType?, completionHandler: CompletionHandler<ListMediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        limit?.let {
            params["limit"] = it.toString()
        }
        offset?.let {
            params["offset"] = it.toString()
        }
        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.GIFS_BY_CATEGORY, categoryEncodedName,
                        subCategoryEncodedName), HTTP_GET, ListMediaResponse::class.java, params, null)
                .executeAsyncTask(completionHandler)
    }

    override fun gifById(gifId: String,
                         completionHandler: CompletionHandler<MediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.GIF_BY_ID, gifId), HTTP_GET, MediaResponse::class.java,
                params, null).executeAsyncTask(completionHandler)
    }

    override fun gifsByIds(gifIds: List<String>,
                           completionHandler: CompletionHandler<ListMediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey

        val str = StringBuilder()
        for (i in gifIds.indices) {
            str.append(gifIds[i])
            if (i < gifIds.size - 1) {
                str.append(",")
            }
        }
        params["ids"] = str.toString()

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                Constants.Paths.GIF_BY_IDS, HTTP_GET, ListMediaResponse::class.java, params, null)
                .executeAsyncTask(completionHandler)
    }

    override fun termSuggestions(term: String,
                                 completionHandler: CompletionHandler<ListTermSuggestionResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.TERM_SUGGESTIONS, term), HTTP_GET,
                ListTermSuggestionResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    override fun stickerPacks(completionHandler: CompletionHandler<ListStickerPacksResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                Constants.Paths.STICKER_PACKS, HTTP_GET,
                ListStickerPacksResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }


    override fun stickerPackChildren(packId: String,
                                     completionHandler: CompletionHandler<ListStickerPacksResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.STICKER_PACK_CHILDREN, packId), HTTP_GET,
                ListStickerPacksResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    override fun stickerPackById(packId: String,
                                 completionHandler: CompletionHandler<StickerPackResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.STICKER_PACK_BY_ID, packId), HTTP_GET,
                StickerPackResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    override fun stickersByPackId(packId: String,
                                  limit: Int?, offset: Int?,
                                  completionHandler: CompletionHandler<ListMediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        limit?.let {
            params["limit"] = it.toString()
        }
        offset?.let {
            params["offset"] = it.toString()
        }

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.STICKERS_BY_PACK_ID, packId), HTTP_GET,
                ListMediaResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    override fun channelContent(channelId: String, mediaType: MediaType?,
                                limit: Int?, offset: Int?,
                                completionHandler: CompletionHandler<ListMediaResponse>): Future<*> {
        val params = HashMap<String, String>()
        params[API_KEY] = apiKey
        limit?.let {
            params["limit"] = it.toString()
        }
        offset?.let {
            params["offset"] = it.toString()
        }

        return networkSession.queryStringConnection(Constants.SERVER_URL,
                String.format(Constants.Paths.GIFS_BY_CHANNEL_ID, channelId, mediaTypeToEndpoint(mediaType)), HTTP_GET,
                ListMediaResponse::class.java, params, null).executeAsyncTask(completionHandler)
    }

    private fun mediaTypeToEndpoint(type: MediaType?): String {
        return if (type == MediaType.sticker) {
            "stickers"
        } else {
            "gifs"
        }
    }

    companion object {
        const val HTTP_GET = "GET"
        const val HTTP_POST = "POST"
        const val HTTP_DELETE = "DELETE"
        const val API_KEY = "api_key"
    }
}
