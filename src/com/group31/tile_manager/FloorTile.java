package com.group31.tile_manager;

import javafx.scene.image.Image;
import com.group31.logger.Logger;
import java.util.UUID;

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
     * Instance's UUID.
     */
    private final String uuid;

    /**
     *
     * @param routing routing (valid connections to other tiles) of the tile
     * @param currentImage the image the tile should display
     */
    public FloorTile(String routing, Image currentImage) {
        super(false, currentImage);
        this.uuid = UUID.randomUUID().toString();
        this.routing = routing;
        Logger.log(String.format("Tile with ID %s created. Routing: %s", this.getUuid(), this.getRouting()),
                Logger.Level.INFO);
    }

    /**
     * Gets the instance's UUID.
     * @return Instance's UUID.
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * @return the routing of this tile, representing the sides you can
     * travel to from this tile.
     */
    public String getRouting() {
        return routing;
    }

    /**
     * @return image of the tile.
     */
    public Image getCurrentImage() {
        return super.getCurrentImage();
    }

    /**
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
