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
package com.ebay.feed;

import java.io.*;
import java.util.zip.GZIPInputStream;

import com.ebay.feed.api.FeedClient;
import com.ebay.feed.exceptions.*;
import com.ebay.feed.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides the main functionality for the FeedV1SDK.
 * It includes methods for downloading, unzipping, and filtering feed files.
 */
public class FeedV1SDK {

    private final FeedClient feedClient = new FeedClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(
        FeedV1SDK.class.getName());

    /**
     * Filters feed by item.
     *
     * @param rangeValue             the range value
     * @param queueSize              the queue size
     * @param feedtype               the feed type
     * @param categoryId             the category ID
     * @param marketplaceId          the marketplace ID
     * @param itemId                 the item ID
     * @param zippedOutputFilename   the zipped output filename
     * @param unzippedOutputFilename the unzipped output filename
     * @param filteredOutputFilename the filtered output filename
     * @return true if the item is found, false otherwise
     * @throws FeedTokenException      if there is an error with the feed token
     * @throws ClientResponseException if there is an error with the client response
     */
    public boolean filterByItem(String rangeValue, int queueSize,
        String feedtype, String categoryId, String marketplaceId, String itemId,
        String zippedOutputFilename, String unzippedOutputFilename,
        String filteredOutputFilename)
        throws FeedTokenException, ClientResponseException {

        logger.debug("Download Latest File-Start");
        downloadLatestFile(rangeValue, queueSize, feedtype, categoryId,
            marketplaceId, zippedOutputFilename);
        logger.debug("Download Latest File-Complete");
        logger.debug("Unzip Downloaded File-Start");
        String unzippedFile = unzip(zippedOutputFilename,
            unzippedOutputFilename);
        logger.debug("Unzip Downloaded File-Complete. Unzipped filename = "
            + unzippedFile);
        logger.debug("Finding Item in Feed " + itemId);
        boolean found = findItem(itemId, unzippedOutputFilename,
            filteredOutputFilename);
        if (found) {
            logger.debug("Item " + itemId + " Found in Feed");
        } else {
            logger.debug("Item " + itemId + " Not Found in Feed");
        }
        return found;
    }

    /**
     * Downloads the latest file.
     *
     * @param rangeValue           the range value
     * @param queueSize            the queue size
     * @param feedType             the feed type
     * @param categoryId           the category ID
     * @param marketplaceId        the marketplace ID
     * @param zippedOutputFilename the zipped output filename
     * @throws ClientResponseException if there is an error with the client response
     * @throws FeedTokenException      if there is an error with the feed token
     */
    void downloadLatestFile(String rangeValue, int queueSize, String feedType,
        String categoryId, String marketplaceId, String zippedOutputFilename)
        throws ClientResponseException, FeedTokenException {
        String response = feedClient.getFiles(feedType, categoryId,
            marketplaceId);
        try {
            FilesMetadataResponse filesMetadataResponse = objectMapper.readValue(
                response, FilesMetadataResponse.class);
            String fileMetadataResponse = feedClient.getFile(
                filesMetadataResponse.getFileMetadata().get(0).getFileId(),
                marketplaceId);
            FileMetadata fileMetadata = objectMapper.readValue(
                fileMetadataResponse, FileMetadata.class);

            feedClient.downloadFile(rangeValue, queueSize,
                fileMetadata.getFileId(), marketplaceId, zippedOutputFilename);
        } catch (IOException e) {
            throw new ClientResponseException(
                "Exception in reading the response " + e.getMessage());
        }
    }

    public String unzip(String zippedOutputFilename,
        String unzippedOutputFilename) {
        try (GZIPInputStream gzis = new GZIPInputStream(
            new FileInputStream(zippedOutputFilename));
            FileOutputStream out = new FileOutputStream(
                unzippedOutputFilename)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            return unzippedOutputFilename;
        } catch (IOException ex) {
            logger.error("IOException", ex);
            return null;
        }
    }

    /**
     * Finds an item in the feed.
     *
     * @param itemId                 the item ID
     * @param unzippedOutputFilename the unzipped output filename
     * @param filteredOutputFilename the filtered output filename
     * @return true if the item is found, false otherwise
     */
    public boolean findItem(String itemId, String unzippedOutputFilename,
        String filteredOutputFilename) {
        try (BufferedReader dataReader = new BufferedReader(
            new FileReader(unzippedOutputFilename));
            FileWriter fw = new FileWriter(filteredOutputFilename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            String itemInfo;
            while ((itemInfo = dataReader.readLine()) != null) {
                if (itemInfo.contains(itemId)) {
                    logger.debug("Found " + itemId + " in Feed ");
                    logger.debug("Writing to file " + filteredOutputFilename);
                    out.println(itemInfo);
                    return true;
                }
            }
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return false;
    }
}