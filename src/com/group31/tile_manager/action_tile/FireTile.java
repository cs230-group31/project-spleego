package com.group31.tile_manager.action_tile;

import com.group31.logger.Logger;
import com.group31.tile_manager.Tile;
import java.awt.Graphics;

import java.util.Scanner;



public class FireTile extends ActionTile {

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds weight of the tile.
     */
    private final int weight;

    /**
     * Fire tile ensures no player can step on a tile that is on fire, because it's on fire.
     * @param id ID of the tile.
     * @param weight Weight of the tile (likeliness to be pulled from the silkbag).
     */
    public FireTile(int id, int weight) {
        super(id, weight);
        this.id = id;
        this.weight = weight;
    }

    /**
     * BOOLEAN
     */
    static class BoolFire {
        public static void main(String args[]) {
            boolean a;

            a = false;
            Logger.log("a"+ a, Logger.Level.INFO);
            a = true;
            Logger.log("a"+ a, Logger.Level.INFO);

            if(a) Logger.log("YES", Logger.Level.INFO);
            a =false;
            if(a) Logger.log("NO", Logger.Level.INFO);

            Logger.log("FIRE IS", Logger.Level.INFO);
            
        }

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


