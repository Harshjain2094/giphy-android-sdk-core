/*
 * Created by Bogdan Tirca on 4/25/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.network.response

import com.giphy.sdk.core.models.Category
import com.giphy.sdk.core.models.Meta
import com.giphy.sdk.core.models.Pagination

class ListCategoryResponse(var data: List<Category>? = null,
                           var pagination: Pagination? = null,
                           var meta: Meta? = null) : GenericResponse {

}
