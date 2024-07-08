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
package com.ebay.feed.util;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import java.util.concurrent.*;

import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.*;
import com.ebay.feed.constants.ClientConstants;
import com.ebay.feed.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedUtil {

    private final HttpClient client = HttpClient.newHttpClient();
    private final OAuth2Api oauth2Api = new OAuth2Api();
    private static final Logger logger = LoggerFactory.getLogger(
        FeedUtil.class.getName());

    /**
     * Generates a list of ranges for data retrieval based on the provided range value and content size.
     *
     * @param rangeValue  The range value for data retrieval.
     * @param contentSize The total size of the content to be retrieved.
     * @return A list of ranges for data retrieval.
     */
    List<String> getRangeList(String rangeValue, long contentSize) {
        long requestRangeLowerLimit = 0;

        long deltaIncrease = rangeValue == null ?
            ClientConstants.RANGE :
            Long.parseLong(rangeValue);
        long requestRangeUpperLimit = Math.min(deltaIncrease, contentSize);
        String range =
            "bytes=" + requestRangeLowerLimit + "-" + requestRangeUpperLimit;

        boolean iterateCall = true;
        List<String> ranges = new ArrayList<>();

        while (iterateCall) {
            ranges.add(range);
            if (requestRangeUpperLimit >= contentSize) {
                break;
            }
            requestRangeLowerLimit = requestRangeUpperLimit + 1;
            requestRangeUpperLimit = requestRangeLowerLimit + deltaIncrease;
            range = "bytes=" + requestRangeLowerLimit + "-"
                + requestRangeUpperLimit;
        }

        return ranges;
    }

    /**
     * Retrieves data in chunks and writes it to the specified output file.
     *
     * @param queueSize      The size of the queue for concurrent data retrieval.
     * @param marketplaceId  The marketplace ID for the eBay API.
     * @param baseURL        The base URL for the eBay API.
     * @param ranges         The list of ranges for data retrieval.
     * @param outputFilename The name of the output file.
     * @throws FeedTokenException If there is an error retrieving the OAuth token.
     */
    void getDataInChunks(int queueSize, String marketplaceId, String baseURL,
        List<String> ranges, String outputFilename)
        throws FeedTokenException, ClientResponseException {
        Queue<CompletableFuture<HttpResponse<InputStream>>> responseQueue = new ConcurrentLinkedQueue<>();
        if (queueSize <= 0)
            queueSize = 1;
        List<List<String>> subRanges = this.partition(ranges, queueSize);

        for (List<String> subRange : subRanges) {
            for (String range : subRange) {
                CompletableFuture<HttpResponse<InputStream>> response = get(
                    marketplaceId, range, baseURL);
                //put response into queue
                responseQueue.add(response);

            }

            while (!responseQueue.isEmpty()) {
                HttpResponse<InputStream> response1 = null;

                try {

                    CompletableFuture<HttpResponse<InputStream>> isc = responseQueue.poll();
                    InputStream is = isc.get().body();
                    OutputStream outStream = new FileOutputStream(
                        outputFilename, true);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                    outStream.flush();
                    outStream.close();
                    is.close();
                } catch (InterruptedException | ExecutionException |
                         IOException e) {
                    throw new ClientResponseException(
                        "Failed to get response from queue or write to file",
                        e.getCause());
                }
            }

        }

    }

    /**
     * Partitions a list into sublists of the specified size.
     *
     * @param list      The list to be partitioned.
     * @param chunkSize The size of each partition.
     * @return A list of sublists.
     */
    private <T> List<List<T>> partition(List<T> list, int chunkSize) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            List<T> chunk = list.subList(i,
                Math.min(i + chunkSize, list.size()));
            partitions.add(chunk);
        }
        return partitions;
    }

    /**
     * Retrieves the content range for the specified marketplace and base URL.
     *
     * @param marketplaceId The marketplace ID for the eBay API.
     * @param baseURL       The base URL for the eBay API.
     * @return The content range.
     * @throws FeedTokenException      If there is an error retrieving the OAuth token.
     * @throws ClientResponseException If there is an error with the client response.
     */
    long getContentRange(String marketplaceId, String baseURL)
        throws FeedTokenException, ClientResponseException {
        String range = "bytes=" + 0 + "-" + 100;

        HttpResponse<InputStream> respMessage = null;
        try {
            respMessage = get(marketplaceId, range, baseURL).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ClientResponseException("Failed to get response API",
                e.getCause());
        }
        if (respMessage == null || respMessage.statusCode() != 206) {
            if (respMessage.statusCode() != 200) {
                throw new ClientResponseException(
                    "Unable to get content range. Response = "
                        + respMessage.statusCode() + " " + respMessage.body());
            }
        }
        HttpHeaders headers = respMessage.headers();

        if (headers == null || headers.firstValue("Content-Range").isEmpty()) {
            throw new ClientResponseException(
                "Unable to get content range. Content-Range header is not present");
        }
        Optional<String> contentRangeOpt = headers.firstValue("Content-Range");

        String contentRange = contentRangeOpt.get();
        return Long.parseLong(contentRange.split("/")[1]);

    }

    /**
     * Sends a GET request to the specified URL and returns the response as an InputStream.
     *
     * @param marketplaceId The marketplace ID for the eBay API.
     * @param range         The range for data retrieval.
     * @param url           The URL for the GET request.
     * @return A CompletableFuture of the HttpResponse.
     * @throws FeedTokenException If there is an error retrieving the OAuth token.
     */
    public CompletableFuture<HttpResponse<InputStream>> get(
        String marketplaceId, String range, String url)
        throws FeedTokenException {
        HttpRequest request = getHttpRequest(getToken(), marketplaceId, range,
            url);

        return client.sendAsync(request,
            HttpResponse.BodyHandlers.ofInputStream());
    }

    /**
     * Sends a GET request to the specified URL and returns the response as an InputStream.
     *
     * @param marketplaceId The marketplace ID for the eBay API.
     * @param range         The range for data retrieval.
     * @param url           The URL for the GET request.
     * @return A CompletableFuture of the HttpResponse.
     */
    public CompletableFuture<HttpResponse<String>> call(String marketplaceId,
        String range, String url) throws FeedTokenException {
        String token = getToken();

        HttpRequest request = getHttpRequest(token, marketplaceId, range, url);

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Constructs an HttpRequest with the specified parameters.
     *
     * @param token         The OAuth token for the eBay API.
     * @param marketplaceId The marketplace ID for the eBay API.
     * @param range         The range for data retrieval.
     * @param url           The URL for the GET request.
     * @return The constructed HttpRequest.
     */
    private static HttpRequest getHttpRequest(String token,
        String marketplaceId, String range, String url) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(URI.create(url)).header("Authorization", token)
            .header("Accept", "application/json");

        if (StringUtils.isNotBlank(marketplaceId)) {
            builder = builder.header("X-EBAY-C-MARKETPLACE-ID", marketplaceId);
        }
        if (range != null) {
            builder = builder.header("Range", range);
        }

        return builder.build();
    }

    /**
     * Retrieves the OAuth token for the eBay API.
     *
     * @return The OAuth token.
     * @throws FeedTokenException If there is an error retrieving the OAuth token.
     */
    String getToken() throws FeedTokenException {
        OAuthResponse oauthResponse = null;
        try {
            oauthResponse = oauth2Api.getApplicationToken(
                Environment.PRODUCTION, ClientConstants.scopes);
        } catch (IOException e) {
            throw new FeedTokenException(e.getMessage());
        }
        if (oauthResponse == null) {
            String message = "Fetch application token failed, with response null ";
            throw new FeedTokenException(message);

        }
        if (oauthResponse.getAccessToken() == null) {
            String message =
                "Fetch application token failed, with access token null "
                    + oauthResponse;
            throw new FeedTokenException(message);

        }
        if (!oauthResponse.getAccessToken().isPresent()
            || oauthResponse.getAccessToken().get().getToken() == null) {
            var message = "Fetch application token failed, with token null "
                + oauthResponse;
            throw new FeedTokenException(message);

        }
        Optional<AccessToken> accessToken = oauthResponse.getAccessToken();
        return ClientConstants.BEARER + accessToken.get().getToken();
    }

    /**
     * Retrieves data in parallel using the specified parameters.
     *
     * @param rangeValue     The range value for data retrieval.
     * @param queueSize      The size of the queue for concurrent data retrieval.
     * @param baseURL        The base URL for the eBay API.
     * @param marketplaceId  The marketplace ID for the eBay API.
     * @param outputFilename The name of the output file.
     * @throws ClientResponseException If there is an error with the client response.
     * @throws FeedTokenException      If there is an error retrieving the OAuth token.
     */
    public void callGetParallel(String rangeValue, int queueSize,
        String baseURL, String marketplaceId, String outputFilename)
        throws ClientResponseException, FeedTokenException {
        // Make REST GET call once in order to get the content size
        long contentSize = getContentRange(marketplaceId, baseURL);
        List<String> listOfRanges = getRangeList(rangeValue, contentSize);
        getDataInChunks(queueSize, marketplaceId, baseURL, listOfRanges,
            outputFilename);
    }
}



