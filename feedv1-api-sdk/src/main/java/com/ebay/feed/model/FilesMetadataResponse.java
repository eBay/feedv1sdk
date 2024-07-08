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
 * This class represents the response of a file metadata request.
 * It includes details such as the href, limit, total, and file metadata.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilesMetadataResponse {

    private String href;
    private Integer limit;
    private Integer total;
    private List<FileMetadata> fileMetadata;

    /**
     * Returns the href.
     *
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the href.
     *
     * @param href the href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Returns the limit.
     *
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     *
     * @param limit the limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Returns the total.
     *
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param total the total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Returns the file metadata.
     *
     * @return the file metadata
     */
    public List<FileMetadata> getFileMetadata() {
        return fileMetadata;
    }

    /**
     * Sets the file metadata.
     *
     * @param fileMetadata the file metadata
     */
    public void setFileMetadata(List<FileMetadata> fileMetadata) {
        this.fileMetadata = fileMetadata;
    }
}