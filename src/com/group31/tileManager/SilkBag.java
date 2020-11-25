package com.group31.tileManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generates tiles for players/game to use.
 * @author Alvaro
 */
public class SilkBag {
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
     * Class constructor
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
        Tile tile = new FloorTile("bd", 0);
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
