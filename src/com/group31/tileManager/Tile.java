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
     * holds the image of the tile.
     */
    private Image currentImage;

    /**
     * Identifies the tile.
     */
    private final int id;

    /**
     * Class constructor.
     * @param id identifies the tile
     * @param currentImage the image of the tile
     */
    public Tile(int id, Image currentImage) {
        this.id = id;
        this.currentImage = currentImage;
    }

    /**
     * Get tile ID.
     * @return ID of the tile
     */
    public int getId() {
        return this.id;
    }

  /**
     * @return how the tile currently looks.
     */
    public Image getCurrentImage() {
        return currentImage;
    }

    /**
     * @param currentImage how the tile should look.
     */
    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }
}
