/*
 * Created by Bogdan Tirca on 5/4/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pagination(
        /**
         * @return total number of results
         */
        @SerializedName("total_count")
        val totalCount: Int = 0,
        /**
         * @return number of results in the current response
         */
        val count: Int = 0,
        /**
         * @return offset used for current response
         */
        val offset: Int = 0,
        /**
         * @return next page of results
         */
        @SerializedName("next_page")
        val nextPage: String? = null,
        /**
         * @return a cursor pointing to the next page of results
         */
        @SerializedName("next_cursor")
        val nextCursor: String? = null
) : Parcelable
