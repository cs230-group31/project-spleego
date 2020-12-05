package com.group31.tile_manager.action_tile;

import javafx.scene.image.Image;

public class DoubleMoveTile extends ActionTile {

    /**
     * Tile that allows a user to make another move.
     * @param currentImage Tile's image.
     * @param id id of the tile
     */
    public DoubleMoveTile(int id, Image currentImage) {
        super(id,  currentImage);
    }
}
