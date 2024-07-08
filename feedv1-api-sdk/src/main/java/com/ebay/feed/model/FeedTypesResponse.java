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
package com.ebay.feed.model;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

/**
 * This class represents a response from the FeedTypes API.
 * It includes a list of FeedType objects, a href string, a limit integer, and a total integer.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedTypesResponse {

    private List<FeedType> feedTypes;

    /**
     * Returns the list of FeedType objects.
     *
     * @return a List of FeedType objects.
     */
    public List<FeedType> getFeedTypes() {
        return feedTypes;
    }

    /**
     * Sets the list of FeedType objects.
     *
     * @param feedTypes a List of FeedType objects.
     */
    public void setFeedTypes(List<FeedType> feedTypes) {
        this.feedTypes = feedTypes;
    }

    private String href;

    /**
     * Returns the href string.
     *
     * @return a String representing the href.
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the href string.
     *
     * @param href a String representing the href.
     */
    public void setHref(String href) {
        this.href = href;
    }

    private Integer limit;

    /**
     * Returns the limit integer.
     *
     * @return an Integer representing the limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the limit integer.
     *
     * @param limit an Integer representing the limit.
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    private Integer total;

    /**
     * Returns the total integer.
     *
     * @return an Integer representing the total.
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Sets the total integer.
     *
     * @param total an Integer representing the total.
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
}