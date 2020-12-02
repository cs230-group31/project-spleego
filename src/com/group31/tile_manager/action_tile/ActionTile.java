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
     * gets the id of the tile.
     * @return id of the tile
     */
    public int getID() {
        return id;
    }

    /**
     *  gets the weight of the tile.
     * @return  the weight of the tile
     */
    public int getWeight() {
        return weight;
    }

    /**
     *  determines whether the tile is an action tile or not.
     * @return the boolean isActionTile
     */
    public boolean isAction() {
        return isActionTile = true;
    }

    /**
    * This class is a subclass of Tile and represents
    * an action tile
    * IMPORTANT: when player takes an action tile needs to setCoord()
    * because tiles inside silkbag have no coords
    */
    //public class ActionTile extends Tile {
    /**
     * Uses superclass constructor
     * @param routing sides that this tile connects
     * @param id id of the tile
     * @param coord keeps the coordinates of the tile on the gameboard
     */
    //public ActionTile(String routing, int id, int[] coord){
    //    super(routing, id, coord, true);
    //    Logger.log("Action tile created", Logger.Level.INFO);
    //}

    /**
     * Uses superclass constructor with no coords
     * @param routing sides that this tile connects
     * @param id id of the tile
     * @param actionTile
     */
    //public ActionTile(String routing, int id, boolean actionTile){
    //    super(routing, id, true);
    //   Logger.log("Action tile created", Logger.Level.INFO);
    //}
    //}
}
