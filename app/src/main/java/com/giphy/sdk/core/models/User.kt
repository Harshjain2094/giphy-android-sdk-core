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
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
        /**
         * @return user id
         */
        val id: String? = null,

        /**
         * @return avatar url
         */
        @SerializedName("avatar_url")
        val avatarUrl: String? = null,

        /**
         * @return banner url
         */
        @SerializedName("banner_url")
        val bannerUrl: String? = null,

        /**
         * @return profile url
         */
        @SerializedName("profile_url")
        val profileUrl: String? = null,

        /**
         * @return username
         */
        var username: String,

        /**
         * @return display name
         */
        @SerializedName("display_name")
        val displayName: String? = null,

        /**
         * @return twitter handle
         */
        val twitter: String? = null,

        /**
         * @return true if the user is public, false otherwise
         */
        @SerializedName("is_public")
        val isPublic: Boolean = false,

        /**
         * @return attribution display name
         */
        @SerializedName("attribution_display_name")
        val attributionDisplayName: String? = null,

        /**
         * @return name
         */
        val name: String? = null,

        /**
         * @return description
         */
        val description: String? = null,

        /**
         * @return facebook url
         */
        @SerializedName("facebook_url")
        val facebookUrl: String? = null,

        /**
         * @return twitter url
         */
        @SerializedName("twitter_url")
        val twitterUrl: String? = null,

        /**
         * @return instagram url
         */
        @SerializedName("instagram_url")
        val instagramUrl: String? = null,

        /**
         * @return tumblr url
         */
        @SerializedName("tumblr_url")
        val tumblrUrl: String? = null,

        /**
         * @return supress chrome
         */
        @SerializedName("suppress_chrome")
        val isSuppressChrome: Boolean = false,

        /**
         * @return website url
         */
        @SerializedName("website_url")
        val websiteUrl: String? = null,

        /**
         * @return displayable url of the website
         */
        @SerializedName("website_display_url")
        val websiteDisplayUrl: String? = null,

        @SerializedName("is_verified")
        var verified: Boolean = false

) : Parcelable
