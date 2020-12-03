package com.group31.tile_manager;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.UUID;


/**
 * This class represents a tile which has coordinates [x, y] in the
 * game board.
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
     * true if action file, false if not.
     */
    private boolean actionTile;
    /**
     * Image of the tile
     */
    private Image currentImage;


    public Tile(boolean actionTile, Image currentImage) {
        // TODO: add a method that checks if the instance of a tile is an action tile or if it's a floor
        // TODO: tile then return true or false depending on if it's an action tile or not.
        this.actionTile = actionTile;
        this.currentImage = currentImage;
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

    /**
     * @return true if tile is an action tile
     */
    public boolean isActionTile() {
        return actionTile;
    }
}

