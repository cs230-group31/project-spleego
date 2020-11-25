package com.group31.tileManager;

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
    }
}
