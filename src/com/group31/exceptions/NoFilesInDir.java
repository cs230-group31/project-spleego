package com.group31.exceptions;

/**
 * @author Liam
 */
public class NoFilesInDir extends Exception {

    /**
     * There are no files in the directory.
     * @param errorMessage Custom error message.
     */
    public NoFilesInDir(String errorMessage) {
        super(errorMessage);
    }

    /**
     * There are no files in the directory.
     */
    public NoFilesInDir() {

    }

}
