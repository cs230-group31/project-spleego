package com.group31.tile_manager.action_tile;

import javafx.scene.image.Image;

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
     * @param currentImage The tile's image.
     */
    public DoubleMoveTile(int id, Image currentImage) {
        super(id, currentImage);
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
