package com.group31.controller;

import com.group31.exceptions.TileNotFound;
import com.group31.gameboard.Gameboard;
import com.group31.logger.Logger;
import com.group31.tile_manager.FloorTile;

import java.util.ArrayList;

public class Movement {

    /**
     * Directions that the player can use.
     */
    enum Move {

        /**
         * Left.
         */
        LEFT,
        /**
         * Right.
         */
        RIGHT,
        /**
         * Up.
         */
        UP,
        /**
         * Down.
         */
        DOWN

    }

}
