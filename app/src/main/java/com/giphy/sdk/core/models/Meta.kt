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
class Meta(
        /**
         * @return error status code
         */
        var status: Int = 0,
        /**
         * @return error message
         */
        var msg: String? = null,
        /**
         * @return response id of the request
         */
        @SerializedName("response_id")
        val responseId: String? = null

) : Parcelable
