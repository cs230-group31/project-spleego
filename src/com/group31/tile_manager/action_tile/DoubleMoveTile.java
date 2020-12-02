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
     * Allows a player to make 2 moves at once.
     * @param id ID of the tile.
     * @param weight Weight of the tile (likeliness to be pulled from silkbag).
     */
    public DoubleMoveTile(int id, int weight) {
        super(id, weight);
        this.id = id;
        this.weight = weight;
    }

//    /**
//     * Sets the ID.
//     */
//    public void setId(){
//        this.id = 12;
//    }

//    /**
//     * @param weight Sets the weight.
//     */
//    public void setWeight(){
//        this.weight = 10;
//    }
//
//    public DoubleMoveTile(int id, boolean actionTile){
//        super(id, actionTile);
//    }


//    public void DoubleMoveEffect(){
//
//    }

}
