package com.group31.tileManager;
import javafx.scene.image.Image;
import java.util.HashMap;


/**
 * This class represents a tile which has coordinates [x, y] in the game board.
 * @author Alvaro
 */
public class Tile {
     /**
     * Holds record of all tile image variations.
     */
    private static final HashMap<Integer, Image> TILE_IMAGES = new HashMap<>();
    /**
     * Location of tile images in directory.
     */
    private static final String TILES_LOCATION = "resources/images/tiles";
    /**
     * holds the routing of the tile.
     */
    private String routing;
    /**
     * holds the image of the tile.
     */
    private Image currentImage;

    /**
     * Identifies the tile.
     */
    private final int id;
    /**
     * Stores coords of the tile.
     */
    private int[] coord;

    /**
     * true if action file, false if not.
     */
    private boolean isActionTile;

    private int X = 0;
    private int Y = 1;

    /**
     * Class constructor.
     * @param id identifies the tile
     * @param coord stores current coords of tile
     * @param currentImage the image of the tile
     */
    public Tile(String routing, int id, int[] coord, Image currentImage) {
        this.routing = routing;
        this.id = id;
        this.coord[X] = coord[X];
        this.coord[Y] = coord[Y];
        this.currentImage = currentImage;
    }

    /**
     * Get tile ID.
     * @return ID of the tile
     */
    public int getId() {
        return id;
    }

    /**
     * @return how the tile currently looks.
     */
    public Image getCurrentImage() {
        return currentImage;
    }

    /**
     * @return the routing of the tile.
     */
    public String getRouting() {
        return routing;
    }

    /**
     * @param currentImage how the tile should look.
     */
    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    public boolean isAction(){
        return isActionTile = false;
    }
}
