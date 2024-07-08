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
 * This class represents a supported schema in the system.
 * It includes details such as the formats, schema version, definition, and deprecation status.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedSchema {
    private List<String> formats;
    private String schemaVersion;
    private String definition;
    private Boolean deprecated;

    /**
     * Returns the formats.
     *
     * @return the formats
     */
    public List<String> getFormats() {
        return formats;
    }

    /**
     * Sets the formats.
     *
     * @param formats the formats
     */
    public void setFormats(List<String> formats) {
        this.formats = formats;
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
     * Returns the definition.
     *
     * @return the definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Sets the definition.
     *
     * @param definition the definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Returns the deprecation status.
     *
     * @return the deprecation status
     */
    public Boolean getDeprecated() {
        return deprecated;
    }

    /**
     * Sets the deprecation status.
     *
     * @param deprecated the deprecation status
     */
    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }
}