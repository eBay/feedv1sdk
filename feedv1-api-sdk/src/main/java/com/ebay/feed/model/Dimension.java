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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * The Dimension class represents a dimension in the feed model.
 * It includes a dimension key and a list of values.
 *
 * @author rnallurebay
 * @version 1.0
 * @since 2024-01-01
 */ public class Dimension {
    private String dimensionKey; // The key of the dimension
    private List<String> values; // The list of values for the dimension

    /**
     * This method is used to get the dimension key.
     *
     * @return String This returns the dimension key.
     */
    public String getDimensionKey() {
        return dimensionKey;
    }

    /**
     * This method is used to set the dimension key.
     *
     * @param dimensionKey A string containing the dimension key.
     */
    public void setDimensionKey(String dimensionKey) {
        this.dimensionKey = dimensionKey;
    }

    /**
     * This method is used to get the list of values for the dimension.
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * This method is used to set the list of values for the dimension.
     *
     * @param values A list of strings containing the values for the dimension.
     */
    public void setValues(List<String> values) {
        this.values = values;
    }
}
