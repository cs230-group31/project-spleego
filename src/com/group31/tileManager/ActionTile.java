package com.group31.tileManager;

import javafx.scene.image.Image;

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
    }

    public int getID(){
        return id;
    }

    public int getWeight(){
        return weight;
    }

    public boolean isAction(){
        return isActionTile = true;
    }
}
