package com.group31.tileManager;

import com.group31.logger.Logger;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
     * holds the image of the tile.
     */
    private final int X = 0;
    private final int Y = 1;

    private Image currentImage;
    private String routing;
    private int id;
    private Image imageTexture;
    private int[] coord;
    private boolean actionTile;
    private double weight;

    /**
     * Class constructor
     * @param routing
     * @param id identifies the tile
     * @param coord current coords on the gameboard
     * @param actionTile says type of the tile
     */
    public Tile(String routing, int id, int[] coord, boolean actionTile){
        this.routing = routing;
        this.id = id;
        coord = new int[2];
        this.coord[X] = coord[X];
        this.coord[Y] = coord[Y];
        this.actionTile = actionTile;
      
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
     * get the routing
     * @return the rout
     */
    public String getRouting(){
        return routing;
    }

    /**
     * get the tile id
     * @return the id
     */
    public int getId(){
        return id;
    }
   // public image getTexture(){
   //     return imageTexture;
   // }
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
     * to ask if the tile is an action tile
     * @return true if tile is an action tile
     */
    public boolean isActionTile(){
        return actionTile;
    }

    /**
     * @return The weight of the tile.
     */
    public double getWeight() {
        return weight;
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
