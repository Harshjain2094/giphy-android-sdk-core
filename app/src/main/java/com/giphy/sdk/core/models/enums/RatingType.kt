/*
 * Created by Bogdan Tirca on 4/26/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.models.enums

import com.google.gson.annotations.SerializedName

enum class RatingType private constructor(private val rating: String) {
    r("r"),
    y("y"),
    g("g"),
    pg("pg"),
    @SerializedName("pg-13")
    pg13("pg-13"),
    unrated("unrated"),
    nsfw("nsfw");

    override fun toString(): String {
        return this.rating
    }
}
