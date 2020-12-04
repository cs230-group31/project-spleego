package com.group31.saveload;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.gameboard.Gameboard;
import com.group31.services.FileManager;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.silk_bag.SilkBag;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("CheckStyle")
public class Load {
    /**
     * Location of the directory in which we store level files.
     */
    private static final String LEVEL_FILES_LOCATION = "data/level_files";
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
    public HashMap<String, Object> loadNewGameFromFile(String filename) throws FileNotFoundException, NoSuchDirectory {
        HashMap<String, Object> objects = new HashMap<>();
        String[] allLines = FileManager.read(filename);
        String[] tilesInBag = allLines[0].split(",");
        int[] tilesToAdd = new int[tilesInBag.length];
        for (int i = 0; i < tilesInBag.length; i++) {
            tilesToAdd[i] = Integer.parseInt(tilesInBag[i]);
        }
        SilkBag silkBag = new SilkBag(tilesToAdd, tilesToAdd.length);

        String[] boardSize = allLines[1].split(",");
        int boardWidth = Integer.parseInt(boardSize[0]);
        int boardHeight = Integer.parseInt(boardSize[1]);
        int numFixedTiles = Integer.parseInt(allLines[2]);
        Gameboard gameboard = new Gameboard(boardWidth, boardHeight);
        String[] fixedTilesSplit = allLines[3].split("/");
        for (int i = 0; i < numFixedTiles; i++) {
            String[] tileInfo = fixedTilesSplit[i].split(",");
            int tileID = Integer.parseInt(tileInfo[0]);
            int tileRow = Integer.parseInt(tileInfo[1]);
            int tileCol = Integer.parseInt(tileInfo[2]);
            gameboard.putTile(silkBag.genFloorTile(tileID), tileRow, tileCol);
            gameboard.setFixedTile(tileRow, tileCol, true);
        }
        int numFloorTilesNeeded = (boardWidth * boardHeight) - numFixedTiles;
        ArrayList<FloorTile> floorTiles = silkBag.drawFloorTiles(numFloorTilesNeeded);
        for (int row = 0; row < boardWidth; row++) {
            for (int col = 0; col < boardWidth; col++) {
                if (!gameboard.isFixedTile(row, col)) {
                    gameboard.putTile(floorTiles.get(0), row, col);
                    floorTiles.remove(0);
                }
            }
        }

        objects.put("SilkBag", silkBag);
        objects.put("Gameboard", gameboard);
        return objects;
    }
}
