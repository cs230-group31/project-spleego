package com.group31.tileManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generates tiles for players/game to use.
 * @author Alvaro
 */
public class SilkBag {
    /**
     * Keeps track of every tile.
     */
    private ArrayList<Tile> tiles;
    /**
     * Stores the weighting (chance) for each tile.
     */
    private HashMap<Integer, Double> tileWeights;

    /**
     * Class constructor.
     * @param tilesNumber number of tiles inside the bag
     */
    public SilkBag(int tilesNumber) {
        tiles = new ArrayList<>();
        for (int index = 0; index < tilesNumber; index++) {
            tiles.add(genTile());
        }
    }

    /**
     * Generates a Tile.
     * @return a Tile
     */
    private Tile genTile() {
        Tile tile = null;
        return tile;
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
