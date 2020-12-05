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
import com.group31.tile_manager.action_tile.*;

/**
 * Generates tiles for players/game to use.
 * @author Alvaro
 */
public class SilkBag {
    /**
     * Odds of each type of tile (%)
     */
    private final static int FLOORODD = 50;
    private final static int FIREODD = 15;
    private final static int FREEZEODD = 15;
    private final static int BACKTRACKODD = 10;
    private final static int DOUBLEMOVEODD = 10;
    /**
     * To use in random tile generator, odds are
     * between [0-100]%
     */
    private final static int MAXODD = 100;
    /**
     * Amount of tiles to be generated
     */
    private final static int MAXTILES = 50;
    /**
     * Keeps track of every tile.
     */
    private ArrayList<Tile> tiles;
    /**
     * Stores the routings of each FloorTile for use in the FloorTile constructor.
     */
    private static HashMap<Integer, String> tileRoutings = new HashMap<>();
    /**
     * Stores the total amount of tiles.
     */
    private double numOfTiles;
    //TODO: SilkBag does more than generate random tiles, it is the controller for tile distribution

    /**
     * Class Constructor
     */
    public SilkBag() {
        int[] odds = {FLOORODD, FIREODD, FREEZEODD, BACKTRACKODD, DOUBLEMOVEODD};
        randomTilesGen(MAXTILES, odds);
    }

    /**
     * generates and add to bag a floorTile
     */
    private void genFloorTile() {
        Random rand = new Random();
        int id = rand.nextInt(11);
        Tile tile = new FloorTile();
        tiles.add(tile);
    }

    /**
     * generates and add to bag an actionTile
     * @param id the id of the actionTile
     */
    private void genActionTile(int id) {
        Tile tile;
        if (id == ActionTile.FIRETILE){
            tile = new FireTile(id);
            tiles.add(tile);
        }else if(id == ActionTile.FREEZETILE){
            tile = new FreezeTile(id);
            tiles.add(tile);
        }else if(id == ActionTile.BACKTRACKTILE){
            tile = new BackTrackTile(id);
            tiles.add(tile);
        }else if(id == ActionTile.DOUBLEMOVETILE){
            tile = new DoubleMoveTile(id);
            tiles.add(tile);
        }
    }

    /**
     * Generates the tiles of the bag randomly, each type of tile has a different odd to appear
     * you can change the odds in the constants of this class
     * @param tileAmount amount of tiles to be generated
     * @param odds array with each odd: {FLOORODD, FIREODD, FREEZEODD, BACKTRACKODD, DOUBLEMOVEODD}
     */
    public void randomTilesGen(int tileAmount, int[] odds) {
        int index1;
        int randomNumber;
        Random rand = new Random();

        for (index1 = 0; index1 < tileAmount; index1++) {
            randomNumber = rand.nextInt(MAXODD);
            if (randomNumber < odds[0]) {
                genFloorTile();
            } else if (randomNumber < (odds[0] + odds[1])) {
                genActionTile(ActionTile.FIRETILE);
            } else if (randomNumber < (odds[0] + odds[1] + odds[2])) {
                genActionTile(ActionTile.FREEZETILE);
            } else if (randomNumber < (odds[0] + odds[1] + odds[2] + odds[3])) {
                genActionTile(ActionTile.BACKTRACKTILE);
            } else if (randomNumber < (odds[0] + odds[1] + odds[2] + odds[3] + odds[4])) {
                genActionTile(ActionTile.DOUBLEMOVETILE);
            }
        }
    }
}