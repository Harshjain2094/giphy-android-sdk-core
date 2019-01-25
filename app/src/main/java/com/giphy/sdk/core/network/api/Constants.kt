/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.network.api

import android.net.Uri

object Constants {
    val SERVER_URL: Uri = Uri.parse("https://api.giphy.com")
    val MOBILE_API_URL: Uri = Uri.parse("https://x.giphy.com")

    val API_KEY = "api_key"

    object Paths {
        val SEARCH = "v1/%s/search"
        val TRENDING = "v1/%s/trending"
        val RANDOM = "v1/%s/random"
        val TRANSLATE = "v1/%s/translate"
        val CATEGORIES = "v1/gifs/categories"
        val SUBCATEGORIES = "v1/gifs/categories/%s"
        val GIFS_BY_CATEGORY = "v1/gifs/categories/%s/%s"
        val GIF_BY_ID = "v1/gifs/%s"
        val GIF_BY_IDS = "v1/gifs"
        val TERM_SUGGESTIONS = "v1/queries/suggest/%s"
        val STICKER_PACKS = "v1/stickers/packs"
        val STICKER_PACK_BY_ID = "v1/stickers/packs/%s"
        val STICKER_PACK_CHILDREN = "v1/stickers/packs/%s/children"
        val STICKERS_BY_PACK_ID = "v1/stickers/packs/%s/stickers"
        val GIFS_BY_CHANNEL_ID = "v1/channels/%s/%s"
    }
}
