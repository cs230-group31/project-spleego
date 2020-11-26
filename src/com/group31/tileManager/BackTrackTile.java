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


    /**
     * Uses superclass constructor
     * @param id
     * @param weight
     */
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

    /**
     * method to activate action effect
      */
    public void BackTrackEffect(){
        Logger.log("BackTrackEffect activated", Logger.Level.INFO);
        //TODO: functionality
    }

    /**
 * This class is a subclass of ActionTile
 * it has an BackTrack effect
 * i don't understand this well if someone read this
 * change this description
 */
//public class BackTrackTile extends ActionTile{
    /**
     * Uses superclass constructor
     * @param routing
     * @param id
     */
    //public BackTrackTile(String routing, int id){
     //   super(routing, id, true);

}