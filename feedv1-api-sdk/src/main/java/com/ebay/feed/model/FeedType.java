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

import java.util.*;

import com.fasterxml.jackson.annotation.*;

/**
 * This class represents a type of feed.
 * It includes the feed type ID, a description, and a list of supported feeds.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedType {
    private String feedTypeId;
    private String description;
    private List<SupportedFeed> supportedFeeds = new ArrayList<SupportedFeed>();

    /**
     * Returns the feed type ID.
     *
     * @return a String representing the feed type ID.
     */
    public String getFeedTypeId() {
        return feedTypeId;
    }

    /**
     * Sets the feed type ID.
     *
     * @param feedTypeId a String representing the feed type ID.
     */
    public void setFeedTypeId(String feedTypeId) {
        this.feedTypeId = feedTypeId;
    }

    /**
     * Returns the description of the feed type.
     *
     * @return a String representing the description of the feed type.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the feed type.
     *
     * @param description a String representing the description of the feed type.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the list of supported feeds.
     *
     * @return a List of SupportedFeed objects.
     */
    public List<SupportedFeed> getSupportedFeeds() {
        return supportedFeeds;
    }

    /**
     * Sets the list of supported feeds.
     *
     * @param supportedFeeds a List of SupportedFeed objects.
     */
    public void setSupportedFeeds(List<SupportedFeed> supportedFeeds) {
        this.supportedFeeds = supportedFeeds;
    }

    /**
     * Adds a supported feed to the list.
     *
     * @param supportedFeed a SupportedFeed object to be added to the list.
     */
    public void addSupportedFeed(SupportedFeed supportedFeed) {
        supportedFeeds.add(supportedFeed);
    }
}