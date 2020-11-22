package com.group31.tileManager;

import java.lang.reflect.Array;

/**
 * This class represents a tile which has coordinates [x, y] in the
 * game board
 * @author Alvaro
 */
public class Tile {

    private final int X = 0;
    private final int Y = 1;

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
    }

    /**
     * this constructor is only for testing
     * must remove before merge with master
     */
    public Tile(){
        routing = "com.group31.test.test";
        id = 1;
        coord = new int[2];
        coord[X] = 1;
        coord[Y] = 1;
        actionTile = true;
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

    public void loadImage(String imageName) {

        Image tileImg = new Image(imageName);
        image = tileImg.getImage();
    }

    public Image getImage() {
        return image;
    }
}
