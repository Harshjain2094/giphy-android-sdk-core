/*
 * Created by Bogdan Tirca on 5/8/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.models.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date> {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private final DateFormat dateFormatStories = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Date date = null;
        try {
            date = dateFormat.parse(json.getAsJsonPrimitive().getAsString());
        } catch (Exception e) {
            try {
                date = dateFormatStories.parse(json.getAsJsonPrimitive().getAsString());
            } catch (Exception e2) { }
        }
        return date;
    }
}