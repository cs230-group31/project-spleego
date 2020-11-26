package com.group31.exceptions;

public class NoSuchDirectory extends Exception {

    /**
     * The directory we want to read/write a file from/to does not exist (so has not been selected).
     * @param message Error message
     */
    public NoSuchDirectory(String message) {
        super(message);
    }

    /**
     * The directory we want to read/write a file from/to does not exist (so has not been selected).
     */
    public NoSuchDirectory() {

    }

}
