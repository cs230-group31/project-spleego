package com.group31.tile_manager;

import javafx.scene.image.Image;
import com.group31.logger.Logger;

/**
 * This class is subclass of Tile (no action tile).
 */
public class FloorTile extends Tile {
    /**
     * holds the routing of the tile.
     */
    private String routing;
    /**
     * holds the image of the tile.
     */
    private Image currentImage;

    /**
     *  Constructor for FloorTile.
     * @param routing sides that this tile connects
     * @param id id of the tile
     * @param coord keeps the coordinates of the tile on the gameboard
     * @param currentImage image to display for that tile
     */
    public FloorTile(String routing, int id, int[] coord, Image currentImage) {
        super(id, false, currentImage);
        this.routing = routing;
        Logger.log("Floor tile created", Logger.Level.INFO);
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
