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
import com.giphy.sdk.core.network.response.*
import java.util.concurrent.Future

/**
 * The interface describing all the endpoints supported by the sdk.
 */
interface GPHApi {
    /**
     * Search for gifs or stickers
     * @param searchQuery search query term or phrase
     * @param type can be sticker or gif
     * @param limit (optional) number of results to return, maximum 100. Default 25.
     * @param offset (optional) results offset, defaults to 0.
     * @param rating (optional) limit results to those rated (y,g, pg, pg-13 or r).
     * @param lang  (optional) specify default country for regional content; format is 2-letter ISO 639-1 country code.
     * @param pingbackId  (optional) specify pingback user id
     * @param completionHandler
     * @return
     */
    fun search(searchQuery: String, type: MediaType?, limit: Int?,
               offset: Int?, rating: RatingType?,
               lang: LangType?,
               pingbackId: String?,
               completionHandler: CompletionHandler<ListMediaResponse>): Future<*>

    /**
     * Get the trending gifs or stickers
     * @param type can be sticker or gif
     * @param limit  (optional) limits the number of results returned. By default returns 25 results.
     * @param offset  (optional) results offset, defaults to 0);
     * @param rating  (optional) limit results to those rated (y,g, pg, pg-13 or r).
     * @param completionHandler
     * @return
     */
    fun trending(type: MediaType?, limit: Int?,
                 offset: Int?, rating: RatingType?,
                 completionHandler: CompletionHandler<ListMediaResponse>): Future<*>

    /**
     * The translate API draws on search, but uses the Giphy "special sauce" to handle translating from one vocabulary to another.
     * @param term term or phrase to translate into a GIF
     * @param type can be sticker or gif
     * @param rating  (optional) limit results to those rated (y,g, pg, pg-13 or r).
     * @param lang  (optional) specify default country for regional content; format is 2-letter ISO 639-1 country code.
     * @param completionHandler
     * @return
     */
    fun translate(term: String, type: MediaType?, rating: RatingType?,
                  lang: LangType?,
                  completionHandler: CompletionHandler<MediaResponse>): Future<*>

    /**
     * Returns a random GIF, limited by tag. Excluding the tag parameter will return a random GIF from the Giphy catalog.
     * @param tag the GIF tag to limit randomness by
     * @param type
     * @param rating limit results to those rated (y,g, pg, pg-13 or r).
     * @param completionHandler
     * @return
     */
    fun random(tag: String, type: MediaType?, rating: RatingType?,
               completionHandler: CompletionHandler<MediaResponse>): Future<*>


    /**
     * Returns a list of categories
     * @param limit
     * @param offset
     * @param sort
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun categoriesForGifs(limit: Int?, offset: Int?,
                          sort: String?,
                          completionHandler: CompletionHandler<ListCategoryResponse>): Future<*>

    /**
     * Returns a list of subcategories for a category
     * @param categoryEncodedName
     * @param limit
     * @param offset
     * @param sort
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun subCategoriesForGifs(categoryEncodedName: String,
                             limit: Int?, offset: Int?,
                             sort: String?,
                             completionHandler: CompletionHandler<ListCategoryResponse>): Future<*>

    /**
     * Returns a list of gifs based on category & subcategory
     * @param categoryEncodedName
     * @param subCategoryEncodedName
     * @param limit
     * @param offset
     * @param ratingType
     * @param langType
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun gifsByCategory(categoryEncodedName: String,
                       subCategoryEncodedName: String,
                       limit: Int?, offset: Int?,
                       ratingType: RatingType?, langType: LangType?,
                       completionHandler: CompletionHandler<ListMediaResponse>): Future<*>

    /**
     * Returns meta data about a GIF, by GIF id
     * @param gifId the id of the gif we want to return
     * @param completionHandler
     * @return
     */
    fun gifById(gifId: String,
                completionHandler: CompletionHandler<MediaResponse>): Future<*>

    /**
     * Returns meta data about multiple gifs
     * @param gifIds the list of ids of the gifs we want to return
     * @return
     */
    fun gifsByIds(gifIds: List<String>,
                  completionHandler: CompletionHandler<ListMediaResponse>): Future<*>

    /**
     * Returns meta data about multiple gifs
     * @param term the list of ids of the gifs we want to return
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun termSuggestions(term: String,
                        completionHandler: CompletionHandler<ListTermSuggestionResponse>): Future<*>


    /**
     * Returns all sticker packs
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun stickerPacks(completionHandler: CompletionHandler<ListStickerPacksResponse>): Future<*>

    /**
     * Returns all child sticker packs for a given pack id
     * @param packId The ID of the sticker pack to get the children of
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun stickerPackChildren(packId: String,
                            completionHandler: CompletionHandler<ListStickerPacksResponse>): Future<*>

    /**
     * Returns an individual sticker pack
     * @param packId The ID of the sticker pack
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun stickerPackById(packId: String,
                        completionHandler: CompletionHandler<StickerPackResponse>): Future<*>

    /**
     * Gets all individual stickers for a given sticker pack
     * @param packId The ID of the sticker pack
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun stickersByPackId(packId: String,
                         limit: Int?, offset: Int?,
                         completionHandler: CompletionHandler<ListMediaResponse>): Future<*>

    /**
     * Gets all gifs for a channel id
     * @param channelId The ID of the channel
     * @param completionHandler
     * @return
     */
    @Deprecated("This endpoint is no longer supported")
    fun channelContent(channelId: String, mediaType: MediaType?,
                       limit: Int?, offset: Int?,
                       completionHandler: CompletionHandler<ListMediaResponse>): Future<*>
}

