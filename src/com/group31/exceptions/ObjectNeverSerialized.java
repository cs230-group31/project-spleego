package com.group31.exceptions;

public class ObjectNeverSerialized extends Exception {

    public ObjectNeverSerialized(String errorMessage) {
        super(errorMessage);
    }

    public ObjectNeverSerialized() {

    }

}
