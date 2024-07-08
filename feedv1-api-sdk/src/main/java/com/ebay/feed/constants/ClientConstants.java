/*
 * *
 *  * Copyright 2024 eBay Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 */

/**
 * This class contains constants used by the client to interact with the eBay Feed API.
 */
package com.ebay.feed.constants;

import java.util.ArrayList;
import java.util.List;

public class ClientConstants {

    public static final String BEARER = "Bearer ";
    public static final int RANGE = 10240000;
    public static final List<String> scopes = new ArrayList<>() {
        {
            add("https://api.ebay.com/oauth/api_scope/buy.item.feed");
        }
    };
    /**
     * The base URL for the eBay Feed API.
     */
    public static final String BASE_URL = "https://api.ebay.com/buy/feed/v1";

    /**
     * The base URL for file-related operations in the eBay Feed API.
     */
    public static final String FILE_BASE_URL = BASE_URL + "/file";

    /**
     * The base URL for feed type-related operations in the eBay Feed API.
     */
    public static final String FEEDTYPE_BASE_URL = BASE_URL + "/feed_type";

    /**
     * The base URL for access-related operations in the eBay Feed API.
     */
    public static final String ACCESS_BASE_URL = BASE_URL + "/access";

    /**
     * A constant representing the question mark character, used in URL parameters.
     */
    public static final String QUESTION_MARK = "?";

    /**
     * A constant representing the feed type ID parameter in the eBay Feed API.
     */
    public static final String FEED_TYPE_ID = "feed_type_id";

    /**
     * A constant representing the ampersand character, used in URL parameters.
     */
    public static final String AND = "&";

    /**
     * A constant representing the category IDs parameter in the eBay Feed API.
     */
    public static final String CATEGORY_IDS = "category_ids";

    /**
     * A constant representing the download path in the eBay Feed API.
     */
    public static final String DOWNLOAD = "/download";

    /**
     * A constant representing the equal character, used in URL parameters.
     */
    public static final String EQUAL = "=";

    /**
     * A constant representing the forward slash character, used in URL paths.
     */
    public static final String FORWARD_SLASH = "/";

}