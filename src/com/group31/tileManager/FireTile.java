package com.group31.tileManager;

import com.group31.logger.Logger;

/**
 * This class is a subclass of ActionTile
 * it has an fire effect
 */
public class FireTile extends ActionTile{
    /**
     * Uses superclass constructor
     * @param routing
     * @param id
     * @param actionTile
     */
    public FireTile(String routing, int id, boolean actionTile){
        super(routing, id, actionTile);

    }
    /**
     * method to activate action effect
     */
    public void fireEffect() {
        Logger.log("fire effect activated", Logger.Level.INFO);
        //TODO: functionality
    }

}