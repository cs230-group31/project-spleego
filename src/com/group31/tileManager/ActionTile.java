package com.group31.tileManager;

import javafx.scene.image.Image;
import com.group31.logger.Logger;

public class ActionTile {

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds weight of the tile.
     */
    private final int weight;

    /**
     * true if action file, false if not.
     */
    private boolean isActionTile;

    /**
     * Class constructor.
     * @param id identifies the tile
     * @param weight the weight of the tile
     */
    public ActionTile(int id, int weight) {
        this.id = id;
        this.weight = weight;
        Logger.log("Action tile created", Logger.Level.INFO);
    }

    public int getID(){
        return id;
    }

    public int getWeight(){
        return weight;
    }

    public boolean isAction(){
        return isActionTile = true;
    }

    /**
    * This class is a subclass of Tile and represents
    * an action tile
    * IMPORTANT: when player takes an action tile needs to setCoord()
    * because tiles inside silkbag have no coords
    */
    //public class ActionTile extends Tile {
    /**
     * Uses superclass constructor
     * @param routing
     * @param id
     * @param coord
     */
    //public ActionTile(String routing, int id, int[] coord){
    //    super(routing, id, coord, true);
    //    Logger.log("Action tile created", Logger.Level.INFO);
    //}

    /**
     * Uses superclass constructor with no coords
     * @param routing
     * @param id
     * @param actionTile
     */
    //public ActionTile(String routing, int id, boolean actionTile){
    //    super(routing, id, true);
    //   Logger.log("Action tile created", Logger.Level.INFO);
    //}
    //}
}
