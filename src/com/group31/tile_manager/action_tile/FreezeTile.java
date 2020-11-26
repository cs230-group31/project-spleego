package com.group31.tile_manager.action_tile;

import com.group31.tile_manager.action_tile.ActionTile;

public class FreezeTile extends ActionTile {

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds weight of the tile.
     */
    private final int weight;

    public FreezeTile(int id, int weight){
        super(id, weight);
        this.id = 12;
        this.weight = 10;
    }

    /**
     * @param id Sets the id.
     */
    //public void setId(){
    //   this.id = 12;
    //}

    /**
     * @param weight Sets the weight.
     */
    //public void setWeight(){
    //    this.weight = 10;
    //}

    /*
    public void freezeEffect(){
        
    }*/

}