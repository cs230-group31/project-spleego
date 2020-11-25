package com.group31.tileManager;
import com.group31.logger.Logger;

/**
 * This class is a subclass of Tile and represents
 * an action tile
 * IMPORTANT: when player takes an action tile needs to setCoord()
 * because tiles inside silkbag have no coords
 */
public class ActionTile extends Tile {
    /**
     * Uses superclass constructor
     * @param routing
     * @param id
     * @param coord
     */
    public ActionTile(String routing, int id, int[] coord){
        super(routing, id, coord, true);
        Logger.log("Action tile created", Logger.Level.INFO);
    }

    /**
     * Uses superclass constructor with no coords
     * @param routing
     * @param id
     * @param actionTile
     */
    public ActionTile(String routing, int id, boolean actionTile){
        super(routing, id, true);
        Logger.log("Action tile created", Logger.Level.INFO);
    }
}
