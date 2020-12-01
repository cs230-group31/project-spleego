package com.group31.tile_manager;

import javafx.scene.image.Image;

/**
 * This represents the Goal Tile.
 */
public class GoalTile extends FloorTile {

    /**
     * Goal tile, when players land on this particular tile, they win the game.
     * @param routing The routing on the goal tile.
     * @param id The ID of the tile.
     * @param currentImage The tile's image.
     */
    public GoalTile(String routing, int id, Image currentImage) {
        super(routing, id, currentImage);
    }
//    public GoalTile(String routing, int id, int[] coord, Image currentImage) {
//        super(routing, id, coord, currentImage);
//    }

}
