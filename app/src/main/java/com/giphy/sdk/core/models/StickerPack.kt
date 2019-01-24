/*
 * Created by Nima Khoshini on 10/24/17.
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
class StickerPack(
        val id: String? = null,

        @SerializedName("display_name")
        val displayName: String? = null,

        val parent: String? = null,

        val slug: String? = null,

        val type: String? = null,
        @SerializedName("content_type")

        val contentType: MediaType? = null,
        @SerializedName("short_display_name")

        val shortDisplayName: String? = null,

        val description: String? = null,

        @SerializedName("has_children")
        val isHasChildren: Boolean = false,

        val user: User? = null,

        @SerializedName("featured_gif")
        val featuredGif: Media? = null
) : Parcelable {

}
