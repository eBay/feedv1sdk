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
 * This class represents a supported feed in the system.
 * It includes details such as the feed scope, authorization scopes, status, supported schemas, supported marketplaces, and lookback.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedFeed {

    private String feedScope;
    private List<String> authorizationScopes;
    private String status;
    private List<SupportedSchema> supportedSchemas;
    private List<String> supportedMarketplaces;
    private String lookback;

    /**
     * Returns the feed scope.
     *
     * @return the feed scope
     */
    public String getFeedScope() {
        return feedScope;
    }

    /**
     * Sets the feed scope.
     *
     * @param feedScope the feed scope
     */
    public void setFeedScope(String feedScope) {
        this.feedScope = feedScope;
    }

    /**
     * Returns the authorization scopes.
     *
     * @return the authorization scopes
     */
    public List<String> getAuthorizationScopes() {
        return authorizationScopes;
    }

    /**
     * Sets the authorization scopes.
     *
     * @param authorizationScopes the authorization scopes
     */
    public void setAuthorizationScopes(List<String> authorizationScopes) {
        this.authorizationScopes = authorizationScopes;
    }

    /**
     * Returns the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the supported schemas.
     *
     * @return the supported schemas
     */
    public List<SupportedSchema> getSupportedSchemas() {
        return supportedSchemas;
    }

    /**
     * Sets the supported schemas.
     *
     * @param supportedSchemas the supported schemas
     */
    public void setSupportedSchemas(List<SupportedSchema> supportedSchemas) {
        this.supportedSchemas = supportedSchemas;
    }

    /**
     * Returns the supported marketplaces.
     *
     * @return the supported marketplaces
     */
    public List<String> getSupportedMarketplaces() {
        return supportedMarketplaces;
    }

    /**
     * Sets the supported marketplaces.
     *
     * @param supportedMarketplaces the supported marketplaces
     */
    public void setSupportedMarketplaces(List<String> supportedMarketplaces) {
        this.supportedMarketplaces = supportedMarketplaces;
    }

    /**
     * Returns the lookback.
     *
     * @return the lookback
     */
    public String getLookback() {
        return lookback;
    }

    /**
     * Sets the lookback.
     *
     * @param lookback the lookback
     */
    public void setLookback(String lookback) {
        this.lookback = lookback;
    }
}