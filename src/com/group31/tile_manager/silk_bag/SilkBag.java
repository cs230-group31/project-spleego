package com.group31.tile_manager.silk_bag;

import com.group31.logger.Logger;
import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Generates tiles for players/game to use.
 * @author Alvaro
 */
public class SilkBag {
    //TODO: how much is maxtile?
    /**
     * Maximum amount of tiles a player can hold.
     */
    private final int maxTiles = 15;
    /**
     * The lowest number that represents a floor tile in tiles.
     */
    private static final int MIN_FLOOR_TILE = 1;
    /**
     * The highest number that represents a floor tile in tiles.
     */
    private static final int MAX_FLOOR_TILE = 10;
    //TODO: take from settings not here
    /**
     * The path to the folder containing all tile images.
     */
    private static final String TILE_IMAGE_URL = "resources/images/tiles/";
    /**
     * The width of a tile image in pixels.
     */
    private static final double TILE_WIDTH = 64.0;
    /**
     * The height of a tile image in pixels.
     */
    private static final double TILE_HEIGHT = 64.0;
    /**
     * Keeps track of every tile.
     */
    private final ArrayList<Tile> tiles;
//    /**
//     * Stores the weighting (chance) for each tile.
//     */
//    private final HashMap<Integer, Double> tileWeights;

//    /**
//     * Stores the total amount of tiles.
//     */
//    private double numOfTiles;
    //TODO: SilkBag does more than generate random tiles, it is the controller for tile distribution
    /**
     * Silk bag constructor.
     * @param tiles Arraylist of tiles to put into the silkbag.
     */
    public SilkBag(ArrayList<Tile> tiles) {
        this.tiles = tiles;
        for (int index = 0; index < maxTiles; index++) {
            tiles.add(genTile());
        }
    }
    /**
     * Generates a Tile.
     * @return a Tile
     */
    private Tile genTile() {
        // TODO: code random tile generator
        //test params
        Tile tile = null; //new FloorTile("bd", 0);
        return tile;
    }

    /**
     * Generates a random floor tile.
     * @return the floor tile
     */
    public static Tile genFloorTile() {
        Random random = new Random();
        int ranInt = random.nextInt(MAX_FLOOR_TILE - MIN_FLOOR_TILE) + MIN_FLOOR_TILE;
        Image tileImage = null;
        try {
            tileImage = new Image(new FileInputStream(TILE_IMAGE_URL + ranInt + ".png"),
                    TILE_WIDTH, TILE_HEIGHT, true, false);
        } catch (FileNotFoundException e) {
            Logger.log("Could not find tile image", Logger.Level.ERROR);
        }
        return new Tile(ranInt, false, tileImage);
    }

    // TODO: Rethink this!
//    private void addWeights(int MAXTILES){
//        tileWeights.put(1, (double) (60/MAXTILES));
//        tileWeights.put(2, (double) (60/MAXTILES));
//        tileWeights.put(3, (double) (60/MAXTILES));
//        tileWeights.put(4, (double) (60/MAXTILES));
//        tileWeights.put(5, (double) (60/MAXTILES));
//        tileWeights.put(6, (double) (60/MAXTILES));
//        tileWeights.put(7, (double) (60/MAXTILES));
//        tileWeights.put(8, (double) (60/MAXTILES));
//        tileWeights.put(9, (double) (60/MAXTILES));
//        tileWeights.put(10, (double) (60/MAXTILES));
//
//        tileWeights.put(11, (double) (40/MAXTILES));
//        tileWeights.put(12, (double) (40/MAXTILES));
//        tileWeights.put(13, (double) (40/MAXTILES));
//        tileWeights.put(14, (double) (40/MAXTILES));
//    }

    ///**
     //* Testing.
     //* @param index
     //* @return
     //*/
    //public Tile getTile(int index){
    //
    //}

    ///**
     //* Pseudo random tile generation using weights.
     //* @param tiles arraylist of tiles
     //* @return tile
     //*/
    /*public Tile tileWeight(ArrayList<Tile> tiles) {
        double completeWeight = 0.0;

        for (Tile tile : tiles) {
            completeWeight += tile.getWeight();
        }
        double r = Math.random() * completeWeight;
        double countWeight = 0.0;
        for (Tile tile : tiles) {
            countWeight += tile.getWeight();
            if (countWeight >= r) {
                return tile;
            }
        }
        throw new RuntimeException("Runtime error.");
    }*/
}