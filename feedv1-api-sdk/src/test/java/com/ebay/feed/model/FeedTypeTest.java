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

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FeedTypeTest {

    @Test
    void getFeedTypeIdReturnsCorrectValue() {
        FeedType feedType = new FeedType();
        feedType.setFeedTypeId("Test Id");
        assertEquals("Test Id", feedType.getFeedTypeId());
    }

    @Test
    void getDescriptionReturnsCorrectValue() {
        FeedType feedType = new FeedType();
        feedType.setDescription("Test Description");
        assertEquals("Test Description", feedType.getDescription());
    }

    @Test
    void getSupportedFeedsReturnsCorrectValues() {
        FeedType feedType = new FeedType();
        SupportedFeed supportedFeed = new SupportedFeed();
        feedType.setSupportedFeeds(Arrays.asList(supportedFeed));
        assertEquals(Arrays.asList(supportedFeed),
            feedType.getSupportedFeeds());
    }

    @Test
    void getSupportedFeedsReturnsEmptyListWhenNoValuesSet() {
        FeedType feedType = new FeedType();
        assertEquals(Collections.emptyList(), feedType.getSupportedFeeds());
    }

    @Test
    void addSupportedFeedAddsFeedCorrectly() {
        FeedType feedType = new FeedType();
        SupportedFeed supportedFeed = new SupportedFeed();
        feedType.addSupportedFeed(supportedFeed);
        assertEquals(Arrays.asList(supportedFeed),
            feedType.getSupportedFeeds());
    }
}