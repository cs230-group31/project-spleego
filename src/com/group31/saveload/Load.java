package com.group31.saveload;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.gameboard.Gameboard;
import com.group31.services.FileManager;
import com.group31.services.serializer.Serializer;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.silk_bag.SilkBag;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Load {
    /**
     * Location of the directory in which we store level files.
     */
    private static final String LEVEL_FILES_LOCATION = "data/level_files/";
    /**
     * Line the tiles in bag info is located.
     */
    private static final int TILES_IN_BAG_LINE = 0;
    /**
     * Line the board size info is located.
     */
    private static final int BOARD_SIZE_LINE = 1;
    /**
     * Line the number of fixed tiles info is located.
     */
    private static final int NUM_FIXED_TILES_LINE = 2;
    /**
     * Line the fixed tiles info is located.
     */
    private static final int FIXED_TILES_LINE = 3;
    /**
     * Line the player starting locations are.
     */
    private static final int PLAYERS_LOCATIONS_LINE = 4;

    /**
     * Default constructor.
     */
    public Load() {
    }

    /**
     * Accesses the file given in its parameter and returns it as a Gameboard.
     * @param filename name of the file to load
     * @return the generated gameboard
     */
    public static HashMap<String, Object> loadNewGameFromFile(String filename) throws NoSuchDirectory, FileNotFoundException {
        String fileToGet = LEVEL_FILES_LOCATION + filename;
        HashMap<String, Object> objects = new HashMap<>();
        FileManager.setDirectory(LEVEL_FILES_LOCATION, true);
        String[] allLines = FileManager.read(filename);
        String[] tilesInBag = allLines[TILES_IN_BAG_LINE].split(",");
        int[] tilesToAdd = new int[tilesInBag.length];
        for (int i = 0; i < tilesInBag.length; i++) {
            tilesToAdd[i] = Integer.parseInt(tilesInBag[i]);
        }
        SilkBag silkBag = new SilkBag(tilesToAdd, tilesToAdd.length);

        String[] boardSize = allLines[BOARD_SIZE_LINE].split(",");
        int boardWidth = Integer.parseInt(boardSize[0]);
        int boardHeight = Integer.parseInt(boardSize[1]);
        int numFixedTiles = Integer.parseInt(allLines[NUM_FIXED_TILES_LINE]);
        Gameboard gameboard = new Gameboard(boardWidth, boardHeight);
        String[] fixedTilesSplit = allLines[FIXED_TILES_LINE].split("/");
        for (int i = 0; i < numFixedTiles; i++) {
            String[] tileInfo = fixedTilesSplit[i].split(",");
            int tileID = Integer.parseInt(tileInfo[0]);
            int tileRow = Integer.parseInt(tileInfo[1]);
            int tileCol = Integer.parseInt(tileInfo[2]);
            gameboard.setTile(silkBag.genFloorTile(tileID), tileRow, tileCol);
            gameboard.setFixedTile(tileRow, tileCol, true);
        }
        int numFloorTilesNeeded = (boardWidth * boardHeight) - numFixedTiles;
        ArrayList<FloorTile> floorTiles = silkBag.drawFloorTiles(numFloorTilesNeeded);
        for (int i = 0; i < numFloorTilesNeeded; i++) {
            gameboard.putTile(floorTiles.get(i));
        }
        String[] playersLineSplit = allLines[PLAYERS_LOCATIONS_LINE].split("/");
        ArrayList<String> playerLocations = new ArrayList<>(Arrays.asList(playersLineSplit));

        objects.put("SilkBag", silkBag);
        objects.put("Gameboard", gameboard);
        objects.put("playerLocations", playerLocations);
        return objects;
    }

    /**
     * Returns a controller loaded from a file.
     * @param identifer firname of the controller to load
     * @throws ObjectNeverSerialized if the controller object being loaded has never been serialized.
     * @return a controller loaded from a file
     */
    public static Controller loadController(String identifer) throws ObjectNeverSerialized {
        String object = "controller";
        Controller loadedInstance = (Controller) Serializer.deserialize(identifer, object);
        return loadedInstance;
    }

}
