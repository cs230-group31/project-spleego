package com.group31.tileManager;
import com.group31.logger.Logger;

import javafx.scene.image.Image;

/**
 * this is the goal tile
 */
public class GoalTile extends Tile{

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds the image of the tile.
     */
    private final Image currentImage;
    /**
     * holds the routing of the tile.
     */
    private final String routing;

    private final int X = 0;
    private final int Y = 0;

    public GoalTile(String routing, int id, int[] coord, Image currentImage){
        super(routing, id, coord, currentImage);
        this.routing = "abcd";
        this.id = 0;
        coord = new int[2];
        this.coord[X] = coord[X];
        this.coord[Y] = coord[Y];
        this.currentImage = new Image("resources/images/0.png");
    }

    /**
     * @param id Sets the id.
     */
    //private void setId(){
    //    this.id = 12;
    //}

    /**
     * @param currentImage Sets the image.
     */
    //private void setImage(){
    //    this.currentImage = new Image("resources/images/0.png");
    //}

    /*
    
    /**
     * when you reach this tile you win the game
     * @param goalReached
     */
    public void gameWon(boolean goalReached){
        Logger.log("you have won", Logger.Level.INFO);
    }

}