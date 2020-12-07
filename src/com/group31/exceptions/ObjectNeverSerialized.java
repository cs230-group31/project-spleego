package com.group31.exceptions;

/**
 * @author Liam
 */
public class ObjectNeverSerialized extends Exception {

    /**
     * The object requested to be deserialized has not been serialized.
     * @param errorMessage Custom error message.
     */
    public ObjectNeverSerialized(String errorMessage) {
        super(errorMessage);
    }

    /**
     * The The object requested to be deserialized has not been serialized.
     */
    public ObjectNeverSerialized() {

    }

}
