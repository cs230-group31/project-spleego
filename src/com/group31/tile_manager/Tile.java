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
    /** The ID of this tile, corresponding to the type
     * and rotation of the tile.
     */
    private final int id;
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
     * @param id the Tile's ID
     * @param actionTile Is the tile an action tile?
     * @param currentImage the Tile's image
     */
    public Tile(int id, boolean actionTile, Image currentImage) {
        this.id = id;
        this.actionTile = actionTile;
        this.currentImage = currentImage;
    }

    /**
     * Tile is a piece that can be played on the gameboard. There are special tiles (action tiles) and regular tiles
     * (floor tiles) as well as a goal tile.
     * @param id the Tile's ID
     * @param actionTile Is the tile an action tile?
     * @param currentImage the Tile's image
     */
    public Tile(int id) {
        this.id = id;
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

    /**
     * Returns the Tile's ID.
     * @return the ID of the Tile
     */
    public int getId() {
        return id;
    }
}

