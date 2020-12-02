package com.group31.tile_manager.action_tile;

public class DoubleMoveTile extends ActionTile {

    /**
     * Identifies the tile.
     */
    private int id;

    /**
     * holds weight of the tile.
     */
    private int weight;

    /**
     * Constructor for the class DoubleMoveTile.
     * @param id id of the tile
     * @param weight weight of the tile
     */
    public DoubleMoveTile(int id, int weight) {
        super(id, weight);
        this.id = 14;
        this.weight = 10;
    }

    /**
	 * @param id Sets the id.
	 */
    //public void setId(){
    //    this.id = 12;
    //}

    /**
     * @param weight Sets the weight.
     */
    //public void setWeight(){
    //    this.weight = 10;
    //}

    //public DoubleMoveTile(int id, boolean actionTile){
    //    super(id, actionTile);
    //}

    /*
    public void DoubleMoveEffect(){
        
    }*/

}