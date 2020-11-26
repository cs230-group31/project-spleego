package com.group31.tile_manager;

import javafx.scene.image.Image;
import java.util.HashMap;


/**
 * This class represents a tile which has coordinates [x, y] in the
 * game board
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
     * 
     */
    private final int X = 0;
    private final int Y = 1;
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

    /**
     * Stores image of the tile.
     */
    private Image currentImage;
    /*private String routing;
    private int id;
    private Image imageTexture;
    private int[] coord;
    private boolean actionTile;
    private double weight;/*

    /**
     * Class constructor.
     * @param routing of the tile
     * @param id identifies the tile
     * @param coord stores current coords of tile
     * @param currentImage the image of the tile
     */
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
    }*/
    }

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
    }*/

    /**
     * get the tile id
     * @return the id
     */
    public int getId(){
        return id;
    }

    /**
     * @return The weight of the tile.
     */    
    //public double getWeight() {
    //    return weight;
    //}

    /**
     * @return how the tile currently looks.
     */
    public Image getCurrentImage() {
        return currentImage;
    }

    /**
     * get the routing
     * @return the routing of the tile
     */
    public String getRouting() {
        return routing;
    }

    /**
     * get the tile's coords
     * @return the tile's coords
     */
    public int[] getCoords(){
        return coord;
    }

    /**
     * change the current tile position on the gameboard
     * @param incAmount amount of movement
     */
    public void incCoords(int[] incAmount){
        coord[X] += incAmount[X];
        coord[Y] += incAmount[Y];
    }

    /**
     * @param currentImage how the tile should look.
     */
    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    /**
     * determnine if the tile is an action tile
     * @return true if tile is an action tile
     */
    public boolean isAction(){
        return isActionTile = false;
    }

    /**
     * to ask if the tile is an action tile
     * @return true if tile is an action tile
     */
    //public boolean isActionTile(){
    //    return actionTile;
    //}
}
