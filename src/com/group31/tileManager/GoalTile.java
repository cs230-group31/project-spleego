package com.group31.tileManager;

import javafx.scene.image.Image;

public class GoalTile{

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * holds the image of the tile.
     */
    private final Image currentImage;

    public GoalTile(){
        this.id = 0;
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
    public void gameWon(boolean goalReached){
        
    }*/

}