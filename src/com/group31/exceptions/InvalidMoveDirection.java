package com.group31.exceptions;

/**
 * @author Liam
 */
public class InvalidMoveDirection extends Exception {

    /**
     * The direction the application states a player has moved is invalid (not in the Movement enum).
     * @param errorMessage Error message.
     */
    public InvalidMoveDirection(String errorMessage) {
        super(errorMessage);
    }

}
