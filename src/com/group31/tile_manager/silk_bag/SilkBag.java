package com.group31.tile_manager.silk_bag;

import com.group31.logger.Logger;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generates tiles for players/game to use.
 * @author Alvaro
 */
public class SilkBag {
    /**
     * Amount of tiles to be generated
     */
    public final int MAXTILES = 50;
    /**
     * To use in random tile generator, odds are
     * between [0-100]%
     */
    public final int MAXODD = 100;
    /**
     * Keeps track of every tile.
     */
    private final ArrayList<Tile> tiles;
    /**
     * Stores the routings of each FloorTile for use in the FloorTile constructor.
     */
    private static  HashMap<Integer, String> tileRoutings = new HashMap<>();
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
     * @param tiles ArrayList of tiles to put into the SilkBag.
     * @param maxTiles amount of tiles in the SilkBag.
     */
    public SilkBag(ArrayList<Tile> tiles, int maxTiles) {
        this.tiles = tiles;
        for (int index = 0; index < maxTiles; index++) {
            tiles.add(genFloorTile());
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

}
public ArrayList<Tile> randomTilesGen(int tileAmount, int[] odds) {
    tiles = new ArrayList<Tile>();
    int index1 = 0;
    int randomNumber = 0;
    Random rand = new Random();

    for(index1 = 0; index1 < tileAmount; index1++){
        randomNumber = rand.nextInt(MAXODD);
        if(randomNumber < odds[0]){
            getTile()
        }else if(randomNumber < (odds[0] + odds[1])){

        }else if(randomNumber < (odds[0] + odds[1] + odds[2])){

        }else if(randomNumber < (odds[0] + odds[1] + odds[2] + odds[3])){

        }else if(randomNumber < (odds[0] + odds[1] + odds[2] + odds[3] + odds[4])){

        }
    return tiles;
}