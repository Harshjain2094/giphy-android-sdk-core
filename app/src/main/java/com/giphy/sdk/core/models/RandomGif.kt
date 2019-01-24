/*
 * Created by Bogdan Tirca on 4/24/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.models

import android.os.Parcelable
import com.giphy.sdk.core.models.enums.MediaType
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class RandomGif(
        val type: MediaType? = null,

        val id: String,

        val url: String? = null,

        @SerializedName("image_original_url")
        val imageOriginalUrl: String? = null,

        @SerializedName("image_url")
        val imageUrl: String? = null,

        @SerializedName("image_mp4_url")
        val imageMp4Url: String? = null,

        @SerializedName("image_frames")
        val imageFrames: Int = 0,

        @SerializedName("image_width")
        val imageWidth: Int = 0,

        @SerializedName("image_height")
        val imageHeight: Int = 0,

        @SerializedName("fixed_height_downsampled_url")
        val fixedHeightDownsampledUrl: String? = null,

        @SerializedName("fixed_height_downsampled_width")
        val fixedHeightDownsampledWidth: Int = 0,

        @SerializedName("fixed_height_downsampled_height")
        val fixedHeightDownsampledHeight: Int = 0,

        @SerializedName("fixed_width_downsampled_url")
        val fixedWidthDownsampledUrl: String? = null,

        @SerializedName("fixed_width_downsampled_width")
        val fixedWidthDownsampledWidth: Int = 0,

        @SerializedName("fixed_width_downsampled_height")
        val fixedWidthDownsampledHeight: Int = 0,

        @SerializedName("fixed_height_small_url")
        val fixedHeightSmallUrl: String? = null,

        @SerializedName("fixed_height_small_still_url")
        val fixedHeightSmallStillUrl: String? = null,

        @SerializedName("fixed_height_small_width")
        val fixedHeightSmallWidth: Int = 0,

        @SerializedName("fixed_height_small_height")
        val fixedHeightSmallHeight: Int = 0,

        @SerializedName("fixed_width_small_url")
        val fixedWidthSmallUrl: String? = null,

        @SerializedName("fixed_width_small_still_url")
        val fixedWidthSmallStillUrl: String? = null,

        @SerializedName("fixed_width_small_width")
        val fixedWidthSmallWidth: Int = 0,

        @SerializedName("fixed_width_small_height")
        val fixedWidthSmallHeight: Int = 0,

        val username: String,

        val caption: String? = null

) : Parcelable {
    fun toGif(): Media {
        val media = Media(id = id, images = Images())
        media.type = type
        media.url = url

        media.user = User(username = username)
        
        media.images.original = Image()
        media.images.original!!.gifUrl = imageOriginalUrl
        media.images.original!!.mp4Url = imageMp4Url
        media.images.original!!.frames = imageFrames
        media.images.original!!.width = imageWidth
        media.images.original!!.height = imageHeight

        media.images.fixedHeightDownsampled = Image()
        media.images.fixedHeightDownsampled!!.gifUrl = fixedHeightDownsampledUrl
        media.images.fixedHeightDownsampled!!.width = fixedHeightDownsampledWidth
        media.images.fixedHeightDownsampled!!.height = fixedHeightDownsampledHeight

        media.images.fixedWidthDownsampled = Image()
        media.images.fixedWidthDownsampled!!.gifUrl = fixedWidthDownsampledUrl
        media.images.fixedWidthDownsampled!!.width = fixedWidthDownsampledWidth
        media.images.fixedWidthDownsampled!!.height = fixedWidthDownsampledHeight

        media.images.fixedHeightSmall = Image()
        media.images.fixedHeightSmall!!.gifUrl = fixedHeightSmallUrl
        media.images.fixedHeightSmall!!.width = fixedHeightSmallWidth
        media.images.fixedHeightSmall!!.height = fixedHeightSmallHeight

        media.images.fixedWidthSmall = Image()
        media.images.fixedWidthSmall!!.gifUrl = fixedWidthSmallUrl
        media.images.fixedWidthSmall!!.width = fixedWidthSmallWidth
        media.images.fixedWidthSmall!!.height = fixedWidthSmallHeight

        media.images.fixedHeightSmallStill = Image()
        media.images.fixedHeightSmallStill!!.gifUrl = fixedHeightSmallStillUrl

        media.images.fixedWidthSmallStill = Image()
        media.images.fixedWidthSmallStill!!.gifUrl = fixedWidthSmallStillUrl

        return media
    }
}
