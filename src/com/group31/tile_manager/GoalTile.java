package com.group31.tile_manager;
import com.group31.logger.Logger;

import javafx.scene.image.Image;

/**
 * This represents the Goal Tile.
 */
public class GoalTile extends FloorTile {
    /**
     *  Constructor for GoalTile.
     * @param routing  sides that this tile connects
     * @param id id of the tile
     * @param coord keeps the coordinates of the tile on the gameboard
     * @param currentImage image to display for that tile
     */
    public GoalTile(String routing, int id, int[] coord, Image currentImage) {
        super(routing, id, coord, currentImage);
    }

}
