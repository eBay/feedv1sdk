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
package com.ebay.feed.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedTokenExceptionTest {

    @Test
    void throwsWithMessageConstructor() {
        String message = "Test message";
        try {
            throw new FeedTokenException(message);
        } catch (FeedTokenException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    void throwsWithMessageAndCauseConstructor() {
        String message = "Test message";
        Exception cause = new Exception("Test cause");
        try {
            throw new FeedTokenException(message, cause);
        } catch (FeedTokenException e) {
            assertEquals(message, e.getMessage());
            assertEquals(cause, e.getCause());
        }
    }

    @Test
    void throwsWithNullMessage() {
        String message = null;
        try {
            throw new FeedTokenException(message);
        } catch (FeedTokenException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    void throwsWithNullMessageAndCause() {
        String message = null;
        Exception cause = null;
        try {
            throw new FeedTokenException(message, cause);
        } catch (FeedTokenException e) {
            assertNull(e.getMessage());
            assertNull(e.getCause());
        }
    }
}