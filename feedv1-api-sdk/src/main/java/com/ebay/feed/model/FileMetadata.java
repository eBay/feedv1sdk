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
 * This class represents the metadata of a file.
 * It includes information such as the file ID, feed type ID, feed scope, marketplace ID, dimensions, feed date, format, schema version, size, and access.
 * It uses Jackson annotations for JSON serialization and deserialization.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileMetadata {

    private String fileId;
    private String feedTypeId;
    private String feedScope;
    private String marketplaceId;
    private List<Dimension> dimensions;
    private String feedDate;
    private String format;
    private String schemaVersion;
    private Long size;
    private String access;

    /**
     * Returns the file ID.
     *
     * @return the file ID
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Sets the file ID.
     *
     * @param fileId the file ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * Returns the feed type ID.
     *
     * @return the feed type ID
     */
    public String getFeedTypeId() {
        return feedTypeId;
    }

    /**
     * Sets the feed type ID.
     *
     * @param feedTypeId the feed type ID
     */
    public void setFeedTypeId(String feedTypeId) {
        this.feedTypeId = feedTypeId;
    }

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
     * Returns the marketplace ID.
     *
     * @return the marketplace ID
     */
    public String getMarketplaceId() {
        return marketplaceId;
    }

    /**
     * Sets the marketplace ID.
     *
     * @param marketplaceId the marketplace ID
     */
    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    /**
     * Returns the dimensions.
     *
     * @return the dimensions
     */
    public List<Dimension> getDimensions() {
        return dimensions;
    }

    /**
     * Sets the dimensions.
     *
     * @param dimensions the dimensions
     */
    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Returns the feed date.
     *
     * @return the feed date
     */
    public String getFeedDate() {
        return feedDate;
    }

    /**
     * Sets the feed date.
     *
     * @param feedDate the feed date
     */
    public void setFeedDate(String feedDate) {
        this.feedDate = feedDate;
    }

    /**
     * Returns the format.
     *
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format.
     *
     * @param format the format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Returns the schema version.
     *
     * @return the schema version
     */
    public String getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * Sets the schema version.
     *
     * @param schemaVersion the schema version
     */
    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    /**
     * Returns the size.
     *
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size the size
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * Returns the access.
     *
     * @return the access
     */
    public String getAccess() {
        return access;
    }

    /**
     * Sets the access.
     *
     * @param access the access
     */
    public void setAccess(String access) {
        this.access = access;
    }
}