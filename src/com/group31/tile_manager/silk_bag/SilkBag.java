package com.group31.tile_manager.silk_bag;

import com.group31.settings.Settings;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
//import com.group31.tile_manager.action_tile.FireTile;
//import com.group31.tile_manager.action_tile.FreezeTile;
//import com.group31.tile_manager.action_tile.DoubleMoveTile;
//import com.group31.tile_manager.action_tile.BackTrackTile;

/**
 * Generates tiles for players/game to use.
 * @author Alvaro, Liam, Moe, Aaron
 */
public class SilkBag {

    /**
     * Amount of tiles to be generated.
     */
    private final int maxTiles;

    /**
     * Stores the routings of each FloorTile for use in the FloorTile constructor.
     */
    private HashMap<Integer, String> tileRoutings;

    /**
     * Tile weights (amount of tiles we want on the gameboard (out of the total).
     */
    private final HashMap<String, Integer> weights;

    /**
     * Width of a tile.
     */
    private double tileWidth;

    /**
     * Height of a tile.
     */
    private double tileHeight;

    /**
     * Location of tile images.
     */
    private String tileImagesUrl;

    /**
     * Stores the total amount of tiles inside the bag.
     */
    private static double numOfTiles;

    /**
     * Keeps track of every tile.
     */
    private ArrayList<Tile> tiles;

    /**
     * SilkBag contains all the tiles that can be played on the board.
     * @param maxTiles Total amount of tiles.
     */
    public SilkBag(int maxTiles) {
        this.maxTiles = maxTiles;
        tiles = new ArrayList<>();

        this.tileRoutings = initRouting();
        this.weights = initWeights();
        this.tileImagesUrl = Settings.get("tile_images_url");
        this.tileHeight = Settings.getSettingAsDouble("tile_height");
        this.tileWidth = Settings.getSettingAsDouble("tile_width");
    }

    /**
     * SilkBag contains all the tiles that can be played on the board.
     * @param givenTiles all tiles within the SilkBag
     * @param maxTiles total amount of tiles
     */
    public SilkBag(int[] givenTiles, int maxTiles) {
        tiles = new ArrayList<>();
        this.maxTiles = maxTiles;
        for (int tileID : givenTiles) {
            tiles.add(new Tile(tileID));
        }

        this.tileRoutings = initRouting();
        this.weights = initWeights();
        this.tileImagesUrl = Settings.get("tile_images_url");
        this.tileHeight = Settings.getSettingAsDouble("tile_height");
        this.tileWidth = Settings.getSettingAsDouble("tile_width");
    }

    /**
     * Takes a tile and places it in the SilkBag.
     * @param tile the tile to add
     */
    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    /**
     * Returns an array of FloorTiles from the SilkBag.
     * @param numOfTiles the amount of Tiles to generate
     * @return an array of the drawn Tiles
     */
    public ArrayList<FloorTile> drawFloorTiles(int numOfTiles) {
        ArrayList<FloorTile> floorTiles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numOfTiles; i++) {
            int ranInt = random.nextInt(tiles.size());
            while (tiles.get(ranInt).isActionTile()) {
                ranInt = random.nextInt(tiles.size());
            }
            floorTiles.add((FloorTile) tiles.get(ranInt));
            tiles.remove(ranInt);
        }
        return floorTiles;
    }

    private HashMap<Integer, String> initRouting() {
        HashMap<Integer, String> routings = new HashMap<>();
        int numRoutes = Settings.getSettingAsInt("num_tile_routes");
        for (int i = 0; i <= numRoutes - 1; i++) {
            String tileRoutingSettingKey = String.format("tile_route_id_%s", i);
            String tileRoutingSettingValue = Settings.get(tileRoutingSettingKey);
            routings.put(i, tileRoutingSettingValue);
        }
        return routings;
    }

    private HashMap<String, Integer> initWeights() {
        HashMap<String, Integer> weights = new HashMap<>();
        weights.put("tile_weight_fire", Settings.getSettingAsInt("tile_weight_fire"));
        weights.put("tile_weight_freeze", Settings.getSettingAsInt("tile_weight_freeze"));
        weights.put("tile_weight_backtrack", Settings.getSettingAsInt("tile_weight_backtrack"));
        weights.put("tile_weight_doublemove", Settings.getSettingAsInt("tile_weight_doublemove"));
        weights.put("tile_weight_floortile", Settings.getSettingAsInt("tile_weight_floortile"));

        return weights;
    }

    /**
     * Generates a random floor tile.
     * @return the floor tile
     * @throws FileNotFoundException If the image file cannot be found.
     */
    public FloorTile genRandomFloorTile() throws FileNotFoundException {
        Random random = new Random();

        // Tiles routing size could be 11, but nextInt() takes 11 and produces range 0 to 10, which is what
        // we want as the tiles are named from 0 to 10.
        int ranInt = random.nextInt(tileRoutings.size());

        String imageFileLocation = String.format("%s%s.png", this.tileImagesUrl, ranInt);
        FileInputStream imageFile = new FileInputStream(imageFileLocation);
        Image tileImage = new Image(imageFile, this.tileWidth, this.tileHeight, true, false);

        String routing = tileRoutings.get(ranInt);
        return new FloorTile(ranInt, routing, tileImage);
    }

    /**
     * Takes in a FloorTile ID and returns the fully created FloorTile.
     * @param id the ID of the tile to be created
     * @return the generated FloorTile
     */
    public FloorTile genFloorTile(int id) throws FileNotFoundException {
        String imageFileLocation = String.format("%s%s.png", this.tileImagesUrl, id);
        FileInputStream imageFile = new FileInputStream(imageFileLocation);
        Image tileImage = new Image(imageFile, this.tileWidth, this.tileHeight, true, false);

        String routing = tileRoutings.get(id);
        return new FloorTile(id, routing, tileImage);
    }

//    private FireTile genFireTile() {
//        return new FireTile(null);
//    }
//
//    private FreezeTile getFreezeTile() {
//        return new FreezeTile(null);
//    }
//
//    private BackTrackTile getBackTrackTile() {
//        return new BackTrackTile(null);
//    }
//
//    private DoubleMoveTile genDoubleMoveTile() {
//        return new DoubleMoveTile(null);
//    }

    /**
     * Generates the tiles of the bag randomly, each type of tile has a different odd to appear
     * you can change the odds in the constants of this class.
     * @param maxTiles amount of tiles to be generated
     */
    public void getWeightedTiles(int maxTiles) {
        int numFloorTiles = Settings.getSettingAsInt("tile_weight_floortile");
        int numFireTiles = Settings.getSettingAsInt("tile_weight_fire");
        int numFreezeTiles = Settings.getSettingAsInt("tile_weight_freeze");
        int numBacktrackTiles = Settings.getSettingAsInt("tile_weight_backtrack");
        int numDoublemoveTiles = Settings.getSettingAsInt("tile_weight_doublemove");

        // Logger.log("Could not find tile image.", Logger.Level.ERROR);


//        int randomNumber;
//        Random rand = new Random();
//
//        for (int i = 0; i < maxTiles; i++) {
//            randomNumber = rand.nextInt(MAX_RAND_INT);
//
//        }
    }

}
