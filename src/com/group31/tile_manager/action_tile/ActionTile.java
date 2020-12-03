package com.group31.tile_manager.action_tile;
import com.group31.logger.Logger;
import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;

public class ActionTile extends Tile {

    public final static int FIRETILE = 11;
    public final static int FREEZETILE = 12;
    public final static int BACKTRACKTILE = 13;
    public final static int DOUBLEMOVETILE = 14;
    /**
     * Class constructor.
     * @param id identifies the tile
     * @param currentImage image of the tile
     */
    public ActionTile(int id, Image currentImage) {
        super(id, true, currentImage);
        Logger.log("Action tile created", Logger.Level.INFO);
    }
}
