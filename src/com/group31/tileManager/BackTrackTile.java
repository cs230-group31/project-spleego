package com.group31.tileManager;

import javafx.scene.image.Image;

public class BackTrackTile extends ActionTile{

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds weight of the tile.
     */
    private int weight;

    public BackTrackTile(int id, int weight){
        super(id, weight);
        this.id = 13;
        this.weight = 10;
    }

    /**
     * @param id Sets the id.
     */
    //public void setId(){
    //    this.id = 13;
    //}

    /**
     * @param weight Sets the weight.
     */
    //public void setWeight(){
    //   this.weight = 10;
    //}

    /*
    public void BackTrackEffect(){

    }*/

}