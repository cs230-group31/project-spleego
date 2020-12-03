package com.group31.tile_manager.action_tile;

public class FreezeTile extends ActionTile {

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds weight of the tile.
     */
    private final int weight;

    /**
     * TODO: Please ensure the following is actually what this class does:
     * FreezeTile stops a player moving while they are on the tile and the tile is frozen.
     * @param id ID of the tile.
     * @param weight Weight of the tile (likeliness to be pulled from SilkBag).
     */
    public FreezeTile(int id, int weight) {
        super(id, null);
        this.id = id;
        this.weight = weight;
    }

//    /**
//     * @param id Sets the id.
//     */
//    public void setId(){
//       this.id = 12;
//    }
//
//    /**
//     * @param weight Sets the weight.
//     */
//    public void setWeight(){
//        this.weight = 10;
//    }


//    public void freezeEffect(){
//
//    }

}
