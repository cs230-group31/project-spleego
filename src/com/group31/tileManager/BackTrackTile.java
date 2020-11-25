package com.group31.tileManager;

import com.group31.logger.Logger;

/**
 * This class is a subclass of ActionTile
 * it has an BackTrack effect
 * i don't understand this well if someone read this
 * change this description
 */
public class BackTrackTile extends ActionTile{
    /**
     * Uses superclass constructor
     * @param routing
     * @param id
     */
    public BackTrackTile(String routing, int id){
        super(routing, id, true);

    }
    /**
     * method to activate action effect
      */
    public void BackTrackEffect(){
        Logger.log("BackTrackEffect activated", Logger.Level.INFO);
        //TODO: functionality
    }

}