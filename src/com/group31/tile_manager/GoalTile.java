package com.group31.tile_manager;
import com.group31.logger.Logger;

import javafx.scene.image.Image;

/**
 * This represents the Goal Tile.
 */
public class GoalTile extends FloorTile{

    public GoalTile(String routing, int id, int[] coord, Image currentImage) {
        super(routing, id, coord, currentImage);
    }

}