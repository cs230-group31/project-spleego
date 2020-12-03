package com.group31.tile_manager;

import javafx.scene.image.Image;
import java.util.HashMap;


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
     * Image of the tile.
     */
    private Image currentImage;


    /**
     * Tile is a piece that can be played on the gameboard. There are special tiles (action tiles) and regular tiles
     * (floor tiles) as well as a goal tile.
     * @param actionTile Is the tile an action tile?
     * @param currentImage Tile's image.
     */
    public Tile(boolean actionTile, Image currentImage) {
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

