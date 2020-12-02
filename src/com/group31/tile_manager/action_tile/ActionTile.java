package com.group31.tile_manager.action_tile;
import com.group31.logger.Logger;
import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;

public class ActionTile extends Tile {

    /**
     * Class constructor.
     * @param id identifies the tile
     * @param currentImage image of the tile
     */
    public ActionTile(int id, Image currentImage) {
        super(id, true, currentImage);
        Logger.log("Action tile created", Logger.Level.INFO);
    }

    // TODO: I don't think this is needed, but can add back in if it is.
//    public boolean isAction(){
//        return isActionTile = true;
//    }

//    /**
//    * This class is a subclass of Tile and represents
//    * an action tile
//    * IMPORTANT: when player takes an action tile needs to setCoord()
//    * because tiles inside silkbag have no coords
//    */
//    public class ActionTile extends Tile {
//    /**
//     * Uses superclass constructor
//     * @param routing
//     * @param id
//     * @param coord
//     */
//    public ActionTile(String routing, int id, int[] coord){
//        super(routing, id, coord, true);
//        Logger.log("Action tile created", Logger.Level.INFO);
//    }

//    /**
//     * Uses superclass constructor with no coords
//     * @param routing
//     * @param id
//     * @param actionTile
//     */
//    public ActionTile(String routing, int id, boolean actionTile){
//        super(routing, id, true);
//       Logger.log("Action tile created", Logger.Level.INFO);
//    }
//    }
}
