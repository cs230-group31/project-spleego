package com.group31.logger;

import java.sql.Timestamp;

/**
 * @author liam
 */
public class Logger {

    /**
     * Logs a message to the console.
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
    public static void log(String message, Level logLevel) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        switch (logLevel) {
            case ERROR:
            case WARNING:
                System.err.printf("%s %s >> %s%n", time, logLevel, message);
                break;
            default:
                System.out.printf("%s %s >> %s%n", time, logLevel, message);
                break;
        }

    }
}
