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

class DimensionTest {

    @Test
    void getDimensionKeyReturnsCorrectValue() {
        Dimension dimension = new Dimension();
        dimension.setDimensionKey("Test Key");
        assertEquals("Test Key", dimension.getDimensionKey());
    }

    @Test
    void getValuesReturnsCorrectValues() {
        Dimension dimension = new Dimension();
        dimension.setValues(Arrays.asList("Value1", "Value2"));
        assertEquals(Arrays.asList("Value1", "Value2"), dimension.getValues());
    }

    @Test
    void getValuesReturnsEmptyListWhenNoValuesSet() {
        Dimension dimension = new Dimension();
        assertNull(dimension.getValues());
    }

    @Test
    void setValuesSetsValuesCorrectly() {
        Dimension dimension = new Dimension();
        dimension.setValues(Arrays.asList("Value1", "Value2"));
        assertEquals(Arrays.asList("Value1", "Value2"), dimension.getValues());
    }

    @Test
    void setValuesSetsEmptyListWhenNullPassed() {
        Dimension dimension = new Dimension();
        dimension.setValues(null);
        assertNull(dimension.getValues());
    }
}