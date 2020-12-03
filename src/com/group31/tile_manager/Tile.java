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
     * Identifies the tile.
     */
    private final int id;
    /**
     * true if action file, false if not.
     */
    private boolean actionTile;
    /**
     * Image of the tile
     */
    private Image currentImage;
    /**
     * Class constructor for generic Tile.
     * Use this for storing tiles inside SilkBag.
     * @param id id of the tile
     * @param actionTile true if this tile is an ActionTile
     * @param currentImage image to display for that tile
     */
    public Tile(int id, boolean actionTile, Image currentImage) {
        this.id = id;
        this.actionTile = actionTile;
        this.currentImage = currentImage;
    }
    /**
     * @return the id
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



//TODO: first off pick one, then put it in floor tile becasue action tiles do not have coordinates
//    /**
//     * Tile X Coordinate.
//     */
//    private final int xCoord = 0;
//    /**
//     * Tile Y Coordinate.
//     */
//    private final int yCoord = 1;
//    /**
//     * Stores coords of the tile.
//     */
//    private int[] coord;

    /*private String routing;
    private double weight;*/

    /* /**
     * Class constructor.
     * @param routing of the tile
     * @param id identifies the tile
     * @param coord stores current coords of tile
     * @param currentImage the image of the tile
     *//*
    public Tile(String routing, int id, int[] coord, Image currentImage) {
        this.routing = routing;
        this.id = id;
        coord = new int[2];
        this.coord[X] = coord[X];
        this.coord[Y] = coord[Y];
        this.currentImage = currentImage;
        //currentImage = setImage(id);

    /**
     * Class constructor, no setting coords
     * use this for stores tiles inside silkbag
     * @param routing
     * @param id
     * @param actionTile
     */
    /*public Tile(String routing, int id, boolean actionTile) {
        this.routing = routing;
        this.id = id;
        this.actionTile = actionTile;

        currentImage = setImage(id);
    }
    }*/


    /*/**
     * @return The weight of the tile.
     */
    //public double getWeight() {
    //    return weight;
    //}



    /*/**
     * @return the tile's coords
     *//*
    public int[] getCoords() {
        return coord;
    }*/

    /*/**
     * change the current tile position on the gameboard
     * @param incAmount amount of movement
     *//*
    public void incCoords(int[] incAmount) {
        coord[X] += incAmount[X];
        coord[Y] += incAmount[Y];
    }*/

