package com.group31.tile_manager;

import com.group31.settings.Settings;
import javafx.scene.image.Image;
import com.group31.logger.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class is subclass of Tile (no action tile).
 * @author aaron
 */
public class FloorTile extends Tile {
    /**
     * Holds the routing of the tile.
     */
    private final String routing;
    /**
     * Stores whether the tile is on fire or not.
     */
    private boolean onFire;
    /**
     * Stores whether the tile is on ice or not.
     */
    private boolean onIce;

    /**
     *
     * @param id the Tile's ID
     * @param routing routing (valid connections to other tiles) of the tile
     * @param currentImage the image the tile should display
     */
    public FloorTile(int id, String routing, Image currentImage) {
        super(id, false, currentImage);
        this.routing = routing;
        Logger.log(String.format("Tile with ID %s created. Routing: %s", this.getId(), this.getRouting()),
                Logger.Level.INFO);
    }

    /**
     * FloorTile constructor using just an ID to grab all the needed information.
     * @param id
     */
    public FloorTile(int id) {
        super(id);
        this.routing = Settings.get(String.format("tile_route_id_%s", getId()));
        Image tileImage = null;
        try {
            tileImage = new Image(new FileInputStream("resources/images/tiles/" + getId() + ".png"),
                    64, 64, true, false);
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        this.setCurrentImage(tileImage);
        Logger.log(String.format("Tile with ID %s created. Routing: %s", this.getId(), this.getRouting()),
                Logger.Level.INFO);
    }
    /**
     * @return the routing of this tile, representing the sides you can
     * travel to from this tile
     */
    public String getRouting() {
        return routing;
    }

    /**
     * Returns whether the Tile is currently on fire.
     * @return True if the tile is on fire, false otherwise.
     */
    public boolean isOnFire() {
        return onFire;
    }

    /**
     * @param onFire boolean variable for if the tile is on fire
     */
    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    /**
     * Returns whether the Tile is currently on ice.
     * @return True if the tile is on ice, false otherwise.
     */
    public boolean isOnIce() {
        return onIce;
    }

    /**
     * @param onIce boolean variable for if the tile is on ice
     */
    public void setOnIce(boolean onIce) {
        this.onIce = onIce;
    }


//    /**
//     * change the current tile position on the gameboard.
//     * @param incAmount amount of movement
//     */
//    public void incCoords(int[] incAmount){
//        coord[X] += incAmount[X];
//        coord[Y] += incAmount[Y];
//    }


//    private void movementEffect(int id){
//
//        switch (this.id) {
//            case 1:
//                //movement effect code
//                break;
//            case 2:
//                //movement effect code
//                break;
//            case 3:
//                //movement effect code
//                break;
//            case 4:
//                //movement effect code
//                break;
//            case 5:
//                //movement effect code
//                break;
//            case 6:
//                //movement effect code
//                break;
//            case 7:
//                //movement effect code
//                break;
//            case 8:
//                //movement effect code
//                break;
//            case 9:
//                //movement effect code
//                break;
//
//        }
//    }

//    /**
// * This class is subclass of Tile (no action tile)
//     **/
//public class FloorTile extends Tile {
//    /**
//     *  Uses superclass Constructor
//     * @param routing
//     * @param id
//     * @param coord
//     */
//    public FloorTile(String routing, int id, int[] coord){
//        super(routing, id, coord, false);
//        Logger.log("Floor tile created", Logger.Level.INFO);
//    }
//    public FloorTile(String routing, int id) {
//        super(routing, id, false);
//        Logger.log("Floor tile created", Logger.Level.INFO);
//    }
//}

}
