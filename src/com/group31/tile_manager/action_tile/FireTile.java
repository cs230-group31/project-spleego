package com.group31.tile_manager.action_tile;

import javafx.scene.image.Image;

public class FireTile extends ActionTile {

    /**
     * Fire tile ensures no player can step on a tile that is on fire, because it's on fire.
     * @param currentImage Image of the tile.
     * @param id id of the tile
     */
    public FireTile(int id, Image currentImage) {
        super(id,  currentImage);
    }

}
