package com.group31.tileManager;
import com.group31.logger.Logger;
/**
 * This class is subclass of Tile (no action tile)
 */
public class FloorTile extends Tile {
    /**
     *  Uses superclass Constructor
     * @param routing
     * @param id
     * @param coord
     * @param actionTile is set false
     */
    public FloorTile(String routing, int id, int[] coord, boolean actionTile){
        super(routing, id, coord, false);
        Logger.log("Floor tile created", Logger.Level.INFO);
    }
}
