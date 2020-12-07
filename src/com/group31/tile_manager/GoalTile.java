package com.group31.tile_manager;

import javafx.scene.image.Image;

/**
 * This represents the Goal Tile.
 * @author Aaron
 */
public class GoalTile extends FloorTile {

    /**
     * Goal tile, when players land on this particular tile, they win the game.
     * @param currentImage The tile's image.
     */
    public GoalTile(Image currentImage) {
        super(0, "abcd", currentImage);
    }
}
