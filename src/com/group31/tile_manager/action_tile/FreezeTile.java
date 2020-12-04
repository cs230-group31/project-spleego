package com.group31.tile_manager.action_tile;

import javafx.scene.image.Image;

public class FreezeTile extends ActionTile {

    /**
     * Tile that freezes a player in place.
     * @param currentImage the Tile's image
     * @param id id of the tile
     */
    public FreezeTile(int id, Image currentImage) {
        super(id,  currentImage);
    }

}
