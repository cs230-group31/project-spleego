package com.group31.tile_manager;

import javafx.scene.image.Image;
import com.group31.logger.Logger;

/**
 * This class is subclass of Tile (no action tile)
 */
public class FloorTile extends Tile{

    /**
     * holds the routing of the tile.
     */
    private String routing;
    /**
     * holds the image of the tile.
     */
    private Image currentImage;

    public FloorTile(String routing, int id, int[] coord, Image currentImage){
        super(routing, id, coord, currentImage);
        setRouting(id);
        setImage(id);
        Logger.log("Floor tile created", Logger.Level.INFO);
    }

    /**
	 * 
	 */
    /*
    public void setId(){
        this.id = id;
        Random r = new Random();
        int min = 1;
        int max = 10;
        int randomID = r.nextInt(max-min) + min;
        this.id = randomID;
    }*/

    public void setImage(int id){

        switch (id) {
            case 1:
                currentImage = new Image("resources/images/1.png");
                break;
            case 2:
                currentImage = new Image("resources/images/2.png");
                break;
            case 3:
                currentImage = new Image("resources/images/3.png");
                break;
            case 4:
                currentImage = new Image("resources/images/4.png");
                break;
            case 5:
                currentImage = new Image("resources/images/5.png");
                break;
            case 6:
                currentImage = new Image("resources/images/6.png");
                break;
            case 7:
                currentImage = new Image("resources/images/7.png");
                break;
            case 8:
                currentImage = new Image("resources/images/8.png");
                break;
            case 9:
                currentImage = new Image("resources/images/9.png");
                break;
            case 10:
                currentImage = new Image ("resources/images/10.png");
                break;
        }
    }


    private void setRouting(int id){

        switch (id) {
            case 1:
                this.routing = "db";
                break;
            case 2:
                this.routing = "ac";
                break;
            case 3:
                this.routing = "ab";
                break;
            case 4:
                this.routing = "cb";
                break;
            case 5:
                this.routing = "dc";
                break;
            case 6:
                this.routing = "da";
                break;
            case 7:
                this.routing = "abc";
                break;
            case 8:
                this.routing = "dcb";
                break;
            case 9:
                this.routing = "adc";
                break;
            case 10:
                this.routing = "dab";
                break;

        }
    }

    /**
     * change the current tile position on the gameboard
     * @param incAmount amount of movement
     */
    //public void incCoords(int[] incAmount){
        //coord[X] += incAmount[X];
        //coord[Y] += incAmount[Y];
    //}

    /*
    private void movementEffect(int id){

        switch (this.id) {
            case 1:
                //movement effect code
                break;
            case 2:
                //movement effect code
                break;
            case 3:
                //movement effect code
                break;
            case 4:
                //movement effect code
                break;
            case 5:
                //movement effect code
                break;
            case 6:
                //movement effect code
                break;
            case 7:
                //movement effect code
                break;
            case 8:
                //movement effect code
                break;
            case 9:
                //movement effect code
                break;
            
        }
    }*/

    /**
 * This class is subclass of Tile (no action tile)
 */
//public class FloorTile extends Tile {
    /**
     *  Uses superclass Constructor
     * @param routing
     * @param id
     * @param coord
     */
    /*public FloorTile(String routing, int id, int[] coord){
        super(routing, id, coord, false);
        Logger.log("Floor tile created", Logger.Level.INFO);
    }
    public FloorTile(String routing, int id) {
        super(routing, id, false);
        Logger.log("Floor tile created", Logger.Level.INFO);
    }
}*/

}
