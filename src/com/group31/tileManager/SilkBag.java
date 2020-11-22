package com.group31.tileManager;

import java.util.ArrayList;

/**
 * this class stores a set of tiles, players can
 * can catch tiles from the bag
 * @author Alvaro
 */
public class SilkBag {

    private ArrayList<Tile> tiles;

    /**
     * Class constructor
     * @param tilesNumber number of tiles inside the bag
     */
    public SilkBag(int tilesNumber){
        tiles = new ArrayList<>();
        for (int index = 0; index < tilesNumber; index++){
            tiles.add(genTile());
        }
    }

    /**
     * Generates a Tile
     * @return a Tile
     */
    private Tile genTile(){
        Tile tile = new Tile();
        return tile;
    }

    /**
     * testing
     * @param index
     * @return
     */
    //public Tile getTile(int index){
    //
    //}

    /**
     * pseudo random tile generation using weights
     * @param tiles arraylist of tiles
     * @return tile
     */
    public Tile tileWeight(ArrayList<Tile> tiles) {
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
    }
}
