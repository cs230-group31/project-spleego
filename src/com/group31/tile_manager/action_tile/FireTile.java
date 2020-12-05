package com.group31.tile_manager.action_tile;

import com.group31.logger.Logger;
import javafx.scene.image.Image;

public class FireTile extends ActionTile {

    /**
     * Fire tile ensures no player can step on a tile that is on fire, because it's on fire.
     */
    public FireTile() {
        //Image image = new Image();
        super(ActionTile.FIRETILE/*, image*/);
    }

//    /**
//     * @param id Sets the id.
//     */
//    public void setId(){
//        this.id = 11;
//    }

    /* Uses superclass constructor
     * @param routing
     * @param id
     * @param actionTile
     */
    //public FireTile(String routing, int id, boolean actionTile){
    //    super(routing, id, actionTile);
    //}

    /**
     * method to activate action effect.
     */
    public void fireEffect() {
        Logger.log("fire effect activated", Logger.Level.INFO);
        // TODO: functionality
    }

}
