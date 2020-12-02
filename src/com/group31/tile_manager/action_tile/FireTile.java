package com.group31.tile_manager.action_tile;

import com.group31.logger.Logger;

/**
 * This class is a subclass of ActionTile
 * it has an fire effect
 */
public class FireTile extends ActionTile {
    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds weight of the tile.
     */
    private final int weight;

    public FireTile(int id, int weight){
        super(id, weight);
        this.id = 11;
        this.weight = 10;
    }

    /**
     * @param id Sets the id.
     */
    //public void setId(){
    //    this.id = 11;
    //}

    /* Uses superclass constructor
     * @param routing
     * @param id
     * @param actionTile
     */
    //public FireTile(String routing, int id, boolean actionTile){
    //    super(routing, id, actionTile);
    //}

    /**
     * method to activate action effect
     */
    public void fireEffect() {
        Logger.log("fire effect activated", Logger.Level.INFO);
        //TODO: functionality
    }

}