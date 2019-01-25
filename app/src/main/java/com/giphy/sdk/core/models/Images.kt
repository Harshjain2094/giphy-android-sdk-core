/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.models

import android.os.Parcelable
import com.giphy.sdk.core.models.enums.RenditionType
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Images(
        /**
         * Height set to 200px. Good for mobile use
         */
        @SerializedName("fixed_height")
        val fixedHeight: Image? = null,
        /**
         * Static preview image for fixed_height
         */
        @SerializedName("fixed_height_still")
        val fixedHeightStill: Image? = null,
        /**
         * Height set to 200px. Reduced to 6 frames to minimize file size to the lowest.
         * Works well for unlimited scroll on mobile and as animated previews. See Giphy.com on mobile web as an example.
         */
        @SerializedName("fixed_height_downsampled")
        var fixedHeightDownsampled: Image? = null,

        /**
         * Width set to 200px. Good for mobile use
         */
        @SerializedName("fixed_width")
        val fixedWidth: Image? = null,
        /**
         * Static preview image for fixed_width
         */
        @SerializedName("fixed_width_still")
        val fixedWidthStill: Image? = null,
        /**
         * Width set to 200px. Reduced to 6 frames. Works well for unlimited scroll on mobile and as animated previews.
         */
        @SerializedName("fixed_width_downsampled")
        var fixedWidthDownsampled: Image? = null,

        /**
         * Height set to 100px. Good for mobile keyboards
         */
        @SerializedName("fixed_height_small")
        var fixedHeightSmall: Image? = null,

        /**
         * Static preview image for fixed_height_small
         */
        @SerializedName("fixed_height_small_still")
        var fixedHeightSmallStill: Image? = null,

        /**
         * Width set to 100px. Good for mobile keyboards
         */
        @SerializedName("fixed_width_small")
        var fixedWidthSmall: Image? = null,

        /**
         * Static preview image for fixed_width_small
         */
        @SerializedName("fixed_width_small_still")
        var fixedWidthSmallStill: Image? = null,

        /**
         * File size under 2mb
         */
        val downsized: Image? = null,
        /**
         * Static preview image for downsized
         */
        @SerializedName("downsized_still")
        val downsizedStill: Image? = null,
        /**
         * File size under 8mb
         */
        @SerializedName("downsized_large")
        val downsizedLarge: Image? = null,
        /**
         * File size under 5mb
         */
        @SerializedName("downsized_medium")
        val downsizedMedium: Image? = null,
        /**
         * Original file size and file dimensions. Good for desktop use
         */
        var original: Image? = null,
        /**
         * Preview image for original
         */
        @SerializedName("original_still")
        val originalStill: Image? = null,
        /**
         * Duration set to loop for 15 seconds. Only recommended for this exact use case
         */
        val looping: Image? = null,
        /**
         * File size under 50kb. Duration may be truncated to meet file size requirements. Good for thumbnails and previews.
         */
        val preview: Image? = null,
        /**
         * File size under 200kb
         */
        @SerializedName("downsized_small")
        val downsizedSmall: Image? = null,

        /**
         * ID of the Represented Object
         */
        var mediaId: String? = null

) : Parcelable {

    /**
     * Passed down the rendition type and media id to each image
     */
    fun postProcess() {
        if (original != null) {
            original!!.mediaId = mediaId
            original!!.renditionType = RenditionType.original
        }
        if (originalStill != null) {
            originalStill.mediaId = mediaId
            originalStill.renditionType = RenditionType.originalStill
        }
        if (fixedHeight != null) {
            fixedHeight.mediaId = mediaId
            fixedHeight.renditionType = RenditionType.fixedHeight
        }
        if (fixedHeightStill != null) {
            fixedHeightStill.mediaId = mediaId
            fixedHeightStill.renditionType = RenditionType.fixedHeightStill
        }
        if (fixedHeightDownsampled != null) {
            fixedHeightDownsampled!!.mediaId = mediaId
            fixedHeightDownsampled!!.renditionType = RenditionType.fixedHeightDownsampled
        }
        if (fixedWidth != null) {
            fixedWidth.mediaId = mediaId
            fixedWidth.renditionType = RenditionType.fixedWidth
        }
        if (fixedWidthStill != null) {
            fixedWidthStill.mediaId = mediaId
            fixedWidthStill.renditionType = RenditionType.fixedWidthStill
        }
        if (fixedWidthDownsampled != null) {
            fixedWidthDownsampled!!.mediaId = mediaId
            fixedWidthDownsampled!!.renditionType = RenditionType.fixedWidthDownsampled
        }
        if (fixedHeightSmall != null) {
            fixedHeightSmall!!.mediaId = mediaId
            fixedHeightSmall!!.renditionType = RenditionType.fixedHeightSmall
        }
        if (fixedHeightSmallStill != null) {
            fixedHeightSmallStill!!.mediaId = mediaId
            fixedHeightSmallStill!!.renditionType = RenditionType.fixedHeightSmallStill
        }
        if (fixedWidthSmall != null) {
            fixedWidthSmall!!.mediaId = mediaId
            fixedWidthSmall!!.renditionType = RenditionType.fixedWidthSmall
        }
        if (fixedWidthSmallStill != null) {
            fixedWidthSmallStill!!.mediaId = mediaId
            fixedWidthSmallStill!!.renditionType = RenditionType.fixedWidthSmallStill
        }
        if (downsized != null) {
            downsized.mediaId = mediaId
            downsized.renditionType = RenditionType.downsized
        }
        if (downsizedStill != null) {
            downsizedStill.mediaId = mediaId
            downsizedStill.renditionType = RenditionType.downsizedStill
        }
        if (downsizedLarge != null) {
            downsizedLarge.mediaId = mediaId
            downsizedLarge.renditionType = RenditionType.downsizedLarge
        }
        if (downsizedMedium != null) {
            downsizedMedium.mediaId = mediaId
            downsizedMedium.renditionType = RenditionType.downsizedMedium
        }
        if (looping != null) {
            looping.mediaId = mediaId
            looping.renditionType = RenditionType.looping
        }
        if (preview != null) {
            preview.mediaId = mediaId
            preview.renditionType = RenditionType.preview
        }
        if (downsizedSmall != null) {
            downsizedSmall.mediaId = mediaId
            downsizedSmall.renditionType = RenditionType.downsizedSmall
        }
    }
}
