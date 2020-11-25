package com.group31.tileManager;
import com.group31.logger.Logger;

/**
 * This class is a subclass of Tile and represents
 * an action tile
 */
public class ActionTile extends Tile {
    /**
     * Uses superclass constructor
     * @param routing
     * @param id
     * @param coord
     * @param actionTile set true
     */
    public ActionTile(String routing, int id, int[] coord, boolean actionTile){
        super(routing, id, coord, true);
        Logger.log("Action tile created", Logger.Level.INFO);
    }
}
