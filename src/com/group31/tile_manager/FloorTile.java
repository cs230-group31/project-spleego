package com.group31.tile_manager;

import javafx.scene.image.Image;
import com.group31.logger.Logger;

/**
 * This class is subclass of Tile (no action tile).
 * @author aaron
 */
public class FloorTile extends Tile {
    /**
     * holds the routing of the tile.
     */
    private final String routing;

//    /**
//     * <PLEASE ADD JAVADOC HERE>.
//     * @param routing
//     * @param id
//     * @param coord
//     * @param currentImage
//     */
//    public FloorTile(String routing, int id, int[] coord, Image currentImage) {
//        super(id, false, currentImage);
//        this.routing = routing;
//        Logger.log("Floor tile created", Logger.Level.INFO);
//    }

    /**
     *
     * @param routing routing (valid connections to other tiles) of the tile
     * @param id unique id of the tile
     * @param currentImage the image the tile should display
     */
    public FloorTile(String routing, int id, Image currentImage) {
    super(id, false, currentImage);
    this.routing = routing;
    Logger.log(this.getId() + " tile created, routing: " + this.getRouting(), Logger.Level.INFO);
    }

    /**
     * @return the routing of this tile
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
