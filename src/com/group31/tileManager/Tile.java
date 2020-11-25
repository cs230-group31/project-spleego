package com.group31.tileManager;

import com.group31.logger.Logger;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private int id;

    /**
     * Class constructor.
     * @param id identifies the tile
     */
    public Tile(int id) {
        this.id = id;
        Image tileImg = null;
        File dir = new File(TILES_LOCATION);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                try {
                    tileImg = new Image(new FileInputStream(file.getPath()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                TILE_IMAGES.put(Integer.parseInt(file.getName().replaceFirst("[.][^.]+$", "")), tileImg);
            }
        } else {
            Logger.log("Directory not found", Logger.Level.ERROR);
        }
        currentImage = TILE_IMAGES.get(id);
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
