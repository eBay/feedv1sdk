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
package com.ebay.feed.api;

import java.net.http.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

import com.ebay.feed.constants.ClientConstants;
import com.ebay.feed.exceptions.*;
import com.ebay.feed.util.FeedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible for interacting with the eBay Feed API.
 * It provides methods to get feed types, access, files and download files.
 */
public class FeedClient {

    // Instance of FeedUtil class. This class provides utility methods for interacting with the eBay Feed API.
    private FeedUtil feedUtil = new FeedUtil();
    // Logger instance for logging events, info, errors etc. in the FeedClient class.
    private static final Logger logger = LoggerFactory.getLogger(
        FeedClient.class.getName());

    /**
     * This method is used to get a specific feed type.
     *
     * @param feedtype      The type of feed to get.
     * @param marketplaceId The marketplace ID.
     * @return The response body as a string.
     * @throws FeedTokenException      If there is an issue with the token.
     * @throws ClientResponseException If there is an issue with the client response.
     */
    public String getFeedtype(String feedtype, String marketplaceId)
        throws FeedTokenException, ClientResponseException {
        return makeApiCall(
            ClientConstants.FEEDTYPE_BASE_URL + ClientConstants.FORWARD_SLASH
                + feedtype, marketplaceId);
    }

    /**
     * This method is used to get access to the eBay Feed API.
     *
     * @return The response body as a string.
     * @throws ClientResponseException If there is an issue with the client response.
     * @throws FeedTokenException      If there is an issue with the token.
     */
    public String getAccess()
        throws ClientResponseException, FeedTokenException {
        return makeApiCall(ClientConstants.ACCESS_BASE_URL, null);
    }

    /**
     * This method is used to get all available feed types.
     *
     * @param marketplaceId The marketplace ID.
     * @return The response body as a string.
     * @throws FeedTokenException      If there is an issue with the token.
     * @throws ClientResponseException If there is an issue with the client response.
     */
    public String getFeedtypes(String marketplaceId)
        throws FeedTokenException, ClientResponseException {
        return makeApiCall(ClientConstants.FEEDTYPE_BASE_URL, marketplaceId);
    }

    /**
     * This method is used to get files for a specific feed type and category from the eBay Feed API.
     *
     * @param feedtype      The type of feed to get files for.
     * @param categoryId    The ID of the category to get files for.
     * @param marketplaceId The marketplace ID.
     * @return The response body as a string.
     * @throws FeedTokenException      If there is an issue with the token.
     * @throws ClientResponseException If there is an issue with the client response.
     */
    public String getFiles(String feedtype, String categoryId,
        String marketplaceId)
        throws FeedTokenException, ClientResponseException {
        String url =
            ClientConstants.FILE_BASE_URL + ClientConstants.QUESTION_MARK
                + ClientConstants.FEED_TYPE_ID + ClientConstants.EQUAL
                + feedtype + ClientConstants.AND + ClientConstants.CATEGORY_IDS
                + ClientConstants.EQUAL + categoryId;
        return makeApiCall(url, marketplaceId);

    }

    /**
     * This method is used to get a specific file from the eBay Feed API.
     *
     * @param fileid        The ID of the file to get.
     * @param marketplaceId The marketplace ID.
     * @return The response body as a string.
     * @throws FeedTokenException      If there is an issue with the token.
     * @throws ClientResponseException If there is an issue with the client response.
     */
    public String getFile(String fileid, String marketplaceId)
        throws FeedTokenException, ClientResponseException {
        return makeApiCall(
            ClientConstants.FILE_BASE_URL + ClientConstants.FORWARD_SLASH
                + fileid, marketplaceId);
    }

    /**
     * This method is used to download a specific file from the eBay Feed API.
     *
     * @param rangeValue     The range of bytes to download.
     * @param fileid         The ID of the file to download.
     * @param marketplaceId  The marketplace ID.
     * @param outputFilename The name of the output file.
     * @throws ClientResponseException If there is an issue with the client response.
     * @throws FeedTokenException      If there is an issue with the token.
     */
    public void downloadFile(String rangeValue, int queueSize, String fileid,
        String marketplaceId, String outputFilename)
        throws ClientResponseException, FeedTokenException {
        String baseURL =
            ClientConstants.FILE_BASE_URL + ClientConstants.FORWARD_SLASH
                + fileid + ClientConstants.DOWNLOAD;
        feedUtil.callGetParallel(rangeValue, queueSize, baseURL, marketplaceId,
            outputFilename);
    }

    /**
     * This method is used to make an API call to the eBay Feed API.
     *
     * @param url           The URL of the API endpoint.
     * @param marketplaceId The marketplace ID.
     * @return The response body as a string.
     * @throws FeedTokenException      If there is an issue with the token.
     * @throws ClientResponseException If there is an issue with the client response.
     */
    private String makeApiCall(String url, String marketplaceId)
        throws FeedTokenException, ClientResponseException {
        CompletableFuture<HttpResponse<String>> responseFuture = feedUtil.call(
            marketplaceId, null, url);
        HttpResponse<String> response = null;
        try {
            response = responseFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            printHeaders(response);
            throw new ClientResponseException(
                "The call to API threw exception " + e.getMessage());
        }
        return response.body();
    }

    /**
     * This method is used to print the headers of the HTTP response.
     *
     * @param response The HTTP response.
     * @throws ClientResponseException If there is an issue with the client response.
     */
    private static void printHeaders(HttpResponse<String> response)
        throws ClientResponseException {
        if (response != null && response.body().contains("errors")) {
            logger.debug("Debugging information. Response headers "
                + response.headers());
        }
    }

}