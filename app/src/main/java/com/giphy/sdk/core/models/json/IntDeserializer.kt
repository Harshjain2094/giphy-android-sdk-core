/*
 * Created by Bogdan Tirca on 5/8/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.models.json

import android.text.TextUtils
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class IntDeserializer : JsonDeserializer<Int> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Int? {
        val jsonPrimitive = json.asJsonPrimitive
        if (jsonPrimitive.isString) {
            val numberText = json.asString
            return if (TextUtils.isEmpty(numberText)) {
                0
            } else Integer.parseInt(numberText)
        } else if (jsonPrimitive.isNumber) {
            return json.asInt
        }
        return 0
    }
}