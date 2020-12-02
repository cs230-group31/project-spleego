package com.group31.tile_manager.silk_bag;

import com.group31.tile_manager.Tile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generates tiles for players/game to use.
 * @author Alvaro
 */
public class SilkBag {
    /**
     * sets the maximum number of tiles.
     */
    //TODO: how much is maxtile?
    public final int MAXTILES = 15;
    /**
     * Keeps track of every tile.
     */
    private ArrayList<Tile> tiles;
    /**
     * Stores the weighting (chance) for each tile.
     */
    private HashMap<Integer, Double> tileWeights;

    /**
     * Stores the total amount of tiles.
     */
    //private double numOfTiles;

    /**
     * Class constructor.
     * //@param MAXTILES number of tiles inside the bag
     */
    public SilkBag() {
        tiles = new ArrayList<>();
        for (int index = 0; index < MAXTILES; index++) {
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

    private void addWeights(int MAXTILES){
        tileWeights.put(1, (double) (60/MAXTILES));
        tileWeights.put(2, (double) (60/MAXTILES));
        tileWeights.put(3, (double) (60/MAXTILES));
        tileWeights.put(4, (double) (60/MAXTILES));
        tileWeights.put(5, (double) (60/MAXTILES));
        tileWeights.put(6, (double) (60/MAXTILES));
        tileWeights.put(7, (double) (60/MAXTILES));
        tileWeights.put(8, (double) (60/MAXTILES));
        tileWeights.put(9, (double) (60/MAXTILES));
        tileWeights.put(10, (double) (60/MAXTILES));

        tileWeights.put(11, (double) (40/MAXTILES));
        tileWeights.put(12, (double) (40/MAXTILES));
        tileWeights.put(13, (double) (40/MAXTILES));
        tileWeights.put(14, (double) (40/MAXTILES));
    }

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
