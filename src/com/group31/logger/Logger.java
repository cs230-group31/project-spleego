/**
 * Logger for the app.
 */
package com.group31.logger;

import java.sql.Timestamp;

public final class Logger {

    /**
     * Log levels that can be used.
     */
    public enum Level {
        /**
         * Info.
         */
        INFO,
        /**
         * Warning.
         */
        WARNING,
        /**
         * Error.
         */
        ERROR,
        /**
         * Verbose.
         */
        VERBOSE
    }

    /**
     * Log message to the console.
     * @param message Message to print to the console.
     * @param logLevel Severity of the message.
     */
    public static void log(final String message, final Level logLevel) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        System.out.printf("%s %s >> %s%n", time, logLevel, message);
    }
}
