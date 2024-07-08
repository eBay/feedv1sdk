package com.ebay.feed.examples;/*
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

import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.feed.FeedV1SDK;
import com.ebay.feed.exceptions.ClientResponseException;
import com.ebay.feed.exceptions.FeedTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class demonstrates the usage of the eBay Feed V1 SDK.
 */
public class FeedV1SDKUsage {

    // Logger instance for logging events, info, errors etc.
    private static final Logger logger = LoggerFactory.getLogger(
        FeedV1SDKUsage.class.getName());

    // Path to the configuration file for the eBay Feed V1 SDK
    private static final String CONFIG_PATH = "feedv1-api-sdk-examples/ebay-config-sample.yaml";

    // Instance of the eBay Feed V1 SDK
    private static final FeedV1SDK FEED_V1_SDK = new FeedV1SDK();

    // eBay category ID for the feed
    private static final String CATEGORY_ID = "6000";

    // eBay marketplace ID for the feed
    private static final String MARKETPLACE_ID = "EBAY_US";

    // Name of the file where the zipped output will be stored
    private static final String ZIPPED_OUTPUT_FILENAME = "feedDownloadTest.gz";

    // Name of the file where the unzipped output will be stored
    private static final String UNZIPPED_OUTPUT_FILENAME = "feedDownloadTest.tsv";

    // Name of the file where the filtered output will be stored
    private static final String FILTERED_OUTPUT_FILENAME = "feedFilteredItemTest.tsv";

    // Another Logger instance. This seems to be a duplicate and might not be necessary.
    private static final Logger LOGGER = LoggerFactory.getLogger(
        FeedV1SDKUsage.class.getName());

    // Range value for the feed filtering
    public static final String RANGE_VALUE = "102400000";

    public static void main(String[] args) {

        try {
            prepareFiles();
            CredentialUtil.load(new FileInputStream(CONFIG_PATH));
            filterItemThatHasCoupon(RANGE_VALUE, 1);
        } catch (IOException | ClientResponseException e) {
            logger.error("IOException", e);
        }

    }

    private static void prepareFiles()
        throws IOException, ClientResponseException {
        deleteIfExists(ZIPPED_OUTPUT_FILENAME);
        deleteIfExists(UNZIPPED_OUTPUT_FILENAME);
        deleteIfExists(FILTERED_OUTPUT_FILENAME);
    }

    private static void filterByItemId() {
        try {
            FEED_V1_SDK.filterByItem(null, 1, "CURATED_ITEM_FEED", CATEGORY_ID,
                MARKETPLACE_ID, "1234567890", ZIPPED_OUTPUT_FILENAME,
                UNZIPPED_OUTPUT_FILENAME, FILTERED_OUTPUT_FILENAME);
        } catch (FeedTokenException | ClientResponseException e) {
            LOGGER.error(e.getClass().getSimpleName(), e);
        }
    }

    private static void filterByItem(String feedType, String itemId,
        String rangeValue, int queueSize, String categoryId,
        String marketplaceId) {
        try {
            FEED_V1_SDK.filterByItem(rangeValue, queueSize, feedType,
                categoryId, marketplaceId, itemId, ZIPPED_OUTPUT_FILENAME,
                UNZIPPED_OUTPUT_FILENAME, FILTERED_OUTPUT_FILENAME);
        } catch (FeedTokenException | ClientResponseException e) {
            LOGGER.error(e.getClass().getSimpleName(), e);
        }
    }

    private static void filterItemThatHasCoupon(String rangeValue,
        int queueSize) {
        try {
            FEED_V1_SDK.filterByItem(rangeValue, queueSize, "PROMOTION",
                "11450", "EBAY_GB", "335095714738", ZIPPED_OUTPUT_FILENAME,
                UNZIPPED_OUTPUT_FILENAME, FILTERED_OUTPUT_FILENAME);
        } catch (FeedTokenException | ClientResponseException e) {
            LOGGER.error(e.getClass().getSimpleName(), e);
        }
    }

    private static void filterByItemIdWithRangeValue(String rangeValue,
        int queueSize) {
        try {
            FEED_V1_SDK.filterByItem(rangeValue, queueSize, "CURATED_ITEM_FEED",
                CATEGORY_ID, MARKETPLACE_ID, "1234567890",
                ZIPPED_OUTPUT_FILENAME, UNZIPPED_OUTPUT_FILENAME,
                FILTERED_OUTPUT_FILENAME);
        } catch (FeedTokenException | ClientResponseException e) {
            LOGGER.error(e.getClass().getSimpleName(), e);
        }
    }

    private static void deleteIfExists(String filename)
        throws ClientResponseException {
        try {
            Path downloadDirectoryPath = Paths.get(".").toAbsolutePath()
                .normalize();

            Path pathToFile = Paths.get(downloadDirectoryPath + "/" + filename);

            if (Files.exists(pathToFile)) {
                Files.delete(pathToFile);
            } else {
                Files.createDirectories(pathToFile.getParent());
            }
            Files.createFile(pathToFile);

        } catch (IOException e) {
            throw new ClientResponseException(
                "Failed to delete file " + filename, e.getCause());
        }
    }
}