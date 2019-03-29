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
class Image(

        @SerializedName("url")
        var gifUrl: String? = null,

        var width: Int = 0,

        var height: Int = 0,

        @SerializedName("size")
        val gifSize: Int = 0,

        var frames: Int = 0,

        @SerializedName("mp4")
        var mp4Url: String? = null,

        @SerializedName("mp4_size")
        val mp4Size: Int = 0,

        @SerializedName("webp")
        val webPUrl: String? = null,

        @SerializedName("webp_size")
        val webPSize: Int = 0,

        var mediaId: String? = null,

        var renditionType: RenditionType? = null

) : Parcelable