package com.group31.tile_manager.action_tile;
import com.group31.logger.Logger;
import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;

public class ActionTile extends Tile {

    /**
     * Class constructor.
     * @param currentImage image of the tile
     */
    public ActionTile(Image currentImage) {
        super(true, currentImage);
        Logger.log("Action tile created", Logger.Level.INFO);
    }
}
