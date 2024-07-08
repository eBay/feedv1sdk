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

/**
 * The FeedTokenException class extends the Exception class and is used to handle exceptions specific to the Feed API token.
 * This class has two constructors, each taking different parameters.
 *
 * @author rnallurebay
 * @version 1.0
 * @since 2024-01-01
 */
public class FeedTokenException extends Exception {
    /**
     * This constructor is used to create a new FeedTokenException with a specific message.
     *
     * @param message A string containing the detail message, which is saved for later retrieval by the Throwable.getMessage() method.
     */
    public FeedTokenException(String message) {
        super(message);
    }

    /**
     * This constructor is used to create a new FeedTokenException with a specific message and cause.
     *
     * @param message A string containing the detail message, which is saved for later retrieval by the Throwable.getMessage() method.
     * @param cause   The cause of the exception, which is saved for later retrieval by the Throwable.getCause() method. A null value is permitted and indicates that the cause is nonexistent or unknown.
     */
    public FeedTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}