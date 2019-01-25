/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.models

import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.VisibleForTesting
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.models.enums.RatingType
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Media(
        /**
         * @return id of the object
         */
        var id: String,
        /**
         * @return media type. Can be gif or sticker
         */
        var type: MediaType? = null,
        /**
         * @return slug
         */
        val slug: String? = null,
        /**
         * @return url
         */
        var url: String? = null,
        /**
         * @return bitly version of the url
         */
        @SerializedName("bitly_gif_url")
        val bitlyGifUrl: String? = null,
        /**
         * @return bitly version of the gif url
         */
        @SerializedName("bitly_url")
        val bitlyUrl: String? = null,
        /**
         * @return embed url
         */
        @SerializedName("embed_url")
        val embedUrl: String? = null,
        /**
         * @return source
         */
        val source: String? = null,
        /**
         * @return title
         */
        val title: String? = null,
        /**
         * @return rating of the gif
         */
        val rating: RatingType? = null,
        /**
         * @return content url
         */
        @SerializedName("content_url")
        val contentUrl: String? = null,
        /**
         * @return tags associated with the gif
         */
        val tags: List<String>? = null,
        /**
         * @return featured tags
         */
        @SerializedName("featured_tags")
        val featuredTags: List<String>? = null,
        /**
         * @return user who uploaded the gif
         */
        var user: User? = null,
        /**
         * @return images collection that contains all images types
         */
        var images: Images,

        /**
         * @return source tld
         */
        @SerializedName("source_tld")
        val sourceTld: String? = null,
        /**
         * @return source post url
         */
        @SerializedName("source_post_url")
        val sourcePostUrl: String? = null,

        /**
         * @return date when the gif was updated
         */
        @SerializedName("update_datetime")
        val updateDate: Date? = null,
        /**
         * @return date when the gif was created
         */
        @SerializedName("create_datetime")
        val createDate: Date? = null,
        /**
         * @return date when the gif was imported
         */
        @SerializedName("import_datetime")
        val importDate: Date? = null,
        /**
         * @return date when the gif was trending
         */
        @SerializedName("trending_datetime")
        val trendingDate: Date? = null,

        /**
         * @return true if gif is hidden, false otherwise
         */
        @SerializedName("is_hidden")
        val isHidden: Boolean = false,
        /**
         * @return true if this gif was removed, false otherwise
         */
        @SerializedName("is_removed")
        val isRemoved: Boolean = false,
        /**
         * @return true if is comunity gif
         */
        @SerializedName("is_community")
        val isCommunity: Boolean = false,
        /**
         * @return true if is anonymous
         */
        @SerializedName("is_anonymous")
        val isAnonymous: Boolean = false,
        /**
         * @return true if is featured, false otherwise
         */
        @SerializedName("is_featured")
        val isFeatured: Boolean = false,
        /**
         * @return true if realtime
         */
        @SerializedName("is_realtime")
        val isRealtime: Boolean = false,
        /**
         * @return true if indexable
         */
        @SerializedName("is_indexable")
        val isIndexable: Boolean = false,
        /**
         * @return true if sticker, false otherwise
         */
        @SerializedName("is_sticker")
        val isSticker: Boolean = false,
        /**
         * @return bottleData
         */
        @SerializedName("bottle_data")
        @set:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        var bottleData: BottleData? = null,

        /**
         * @return userDictionary
         */
        /**
         * Set userDictionary
         */
        @Transient
        var userDictionary: Bundle? = null

) : Parcelable {

    /**
     * @return tid
     */
    val tid: String?
        get() = if (bottleData != null) bottleData!!.tid else null


    /**
     * Passed down the media id to the @images field and call postProcess function on @images field
     */
    fun postProcess() {
        if (images != null) {
            images!!.mediaId = id
            images!!.postProcess()
        }
    }
}
