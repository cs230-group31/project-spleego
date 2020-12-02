package com.group31.tile_manager.action_tile;
import com.group31.logger.Logger;

public class ActionTile {

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds weight of the tile.
     */
    private final int weight;

    /**
     * true if action file, false if not.
     */
    private boolean isActionTile;

    /**
     * Class constructor.
     * @param id identifies the tile
     * @param weight the weight of the tile
     */
    public ActionTile(int id, int weight) {
        this.id = id;
        this.weight = weight;
        Logger.log("Action tile created", Logger.Level.INFO);
    }

    /**
     * Gets ID of the tile.
     * @return Tile's ID.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Gets weight of the tile (likeliness to be pulled out of the silkbag.
     * @return Tile's weight.
     */
    public int getWeight() {
        return this.weight;
    }

    // TODO: I don't think this is needed, but can add back in if it is.
//    public boolean isAction(){
//        return isActionTile = true;
//    }

//    /**
//    * This class is a subclass of Tile and represents
//    * an action tile
//    * IMPORTANT: when player takes an action tile needs to setCoord()
//    * because tiles inside silkbag have no coords
//    */
//    public class ActionTile extends Tile {
//    /**
//     * Uses superclass constructor
//     * @param routing
//     * @param id
//     * @param coord
//     */
//    public ActionTile(String routing, int id, int[] coord){
//        super(routing, id, coord, true);
//        Logger.log("Action tile created", Logger.Level.INFO);
//    }

//    /**
//     * Uses superclass constructor with no coords
//     * @param routing
//     * @param id
//     * @param actionTile
//     */
//    public ActionTile(String routing, int id, boolean actionTile){
//        super(routing, id, true);
//       Logger.log("Action tile created", Logger.Level.INFO);
//    }
//    }
}
