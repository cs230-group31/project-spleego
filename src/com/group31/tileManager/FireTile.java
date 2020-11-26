package com.group31.tileManager;

public class FireTile extends ActionTile{

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

    /**
     * @param weight Sets the weight.
     */
    //public void setWeight(){
    //    this.weight = 10;
    //}

    /*
    public void fireEffect(){
        
    }*/

}