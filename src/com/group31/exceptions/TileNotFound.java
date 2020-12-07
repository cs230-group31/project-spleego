package com.group31.exceptions;

public class TileNotFound extends Exception {

    /**
     * Tile is not on the gameboard.
     * @param errorMessage Custom error message.
     */
    public TileNotFound(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Tile is not on the gameboard.
     */
    public TileNotFound() {

    }

}
