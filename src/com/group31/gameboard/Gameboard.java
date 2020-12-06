package com.group31.gameboard;

import com.group31.controller.Controller;
import com.group31.logger.Logger;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.Tile;
import com.group31.tile_manager.silk_bag.SilkBag;

import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * @author aaron
 */
public class Gameboard implements Serializable {
    /**
     * 2D array of the tiles on the board.
     */
    private final FloorTile[][] boardState;
    /**
     * 2D array to keep track of currently fixed tiles.
     */
    private boolean[][] boardFixedTiles;
    /**
     * The amount of rows the board has.
     */
    private final int boardRows;
    /**
     * The amount of columns the board has.
     */
    private final int boardColumns;

    /**
     * .
     * @param width width of the GameBoard
     * @param height height of the GameBoard
     */
    public Gameboard(int width, int height) {
        boardRows = width;
        boardColumns = height;
        boardState = new FloorTile[boardRows][boardColumns];
        boardFixedTiles = new boolean[boardRows][boardColumns];
    }

    /**
     * Fills the board with random floor tiles.
     * @param silkBag the silk bag for the game
     * @throws FileNotFoundException If the image file cannot be found.
     */
    public void genBoard(SilkBag silkBag) throws FileNotFoundException {
        for (int r = 0; r <  boardRows; r++) {
            for (int c = 0; c < boardColumns; c++) {
                boardState[r][c] = silkBag.genRandomFloorTile();
            }
        }
    }

    /**
     * Returns whether the tile at the coordinates provided
     * is fixed or not.
     * @param row row the tile is in
     * @param col column the tile is in
     * @return true if the tile cannot move, false if not
     */
    public boolean isFixedTile(int row, int col) {
        return boardFixedTiles[row][col];
    }

    /**
     * Sets the tile at the coordinates provided according
     * to the boolean provided.
     * @param row row the tile is in
     * @param col column the tile is in
     * @param isFixed true if the tile should not move
     */
    public void setFixedTile(int row, int col, boolean isFixed) {
        boardFixedTiles[row][col] = isFixed;
    }

    /**
     * Checks the row of tiles given, if any of them are fixed this returns true.
     * @param row the row of tiles to check
     * @return true if the row has any fixed tiles, false otherwise.
     */
    public boolean rowHasFixedTile(int row) {
        for (int col = 0; col < boardColumns; col++) {
            if (isFixedTile(row, col)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the columnn of tiles given, if any of them are fixed this returns true.
     * @param col the column of tiles to check
     * @return true if the row has any fixed tiles, false otherwise.
     */
    public boolean colHasFixedTile(int col) {
        for (int row = 0; row < boardRows; row++) {
            if (isFixedTile(row, col)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Insert tile into the Gameboard at the next empty position.
     * @param tile the Tile to be inserted
     */
    public void putTile(FloorTile tile) {
        int rowToInsert = 0;
        int colToInsert = 0;
        boolean addedTile = false;
        for (int row = 0; row <  boardRows; row++) {
            for (int col = 0; col < boardColumns; col++) {
                if (boardState[row][col] == null) {
                    if (!addedTile) {
                        rowToInsert = row;
                        colToInsert = col;
                        addedTile = true;
                    }
                }
            }
        }
        boardState[rowToInsert][colToInsert] = tile;
    }

    /**
     * Returns the Tile at the given coordinates.
     * @param row the row coordinate of the target position
     * @param col the column coordinate of the target position
     * @return the tile at the given coordinates
     */
    public FloorTile getTile(int row, int col) {
        return boardState[row][col];
    }

    /**
     * Sets the tile at the given coordinates to the tile provided.
     * @param floorTile tile to add to the gameboard
     * @param tileRow the row to set the tile
     * @param tileCol the col to set the tile
     */
    public void setTile(FloorTile floorTile, int tileRow, int tileCol) {
        boardState[tileRow][tileCol] = floorTile;
    }

    /**
     * Takes a tile and inserts it into the corresponding row,
     * shifting the needed tiles across one and placing
     * the removed tile back into the bag.
     * @param col the column that identifies the row
     * @param direction the direction to send the tile
     */
    // Left and right buttons
    public void addTileToRow(int col, String direction) {
        Controller controller = Controller.getInstance();
        SilkBag silkBag = controller.getSilkbag();
        FloorTile tile = controller.getCurrentFloorTile();
        Tile ejectedTile = null;
        switch (direction) {
            case "left":
                ejectedTile = boardState[boardRows - 1][col];
                silkBag.addTile(ejectedTile);
                for (int r = boardRows - 1; r > 0; r--) {
                    boardState[r][col] = boardState[r - 1][col];
                }
                boardState[0][col] = tile;
                break;
            case "right":
                ejectedTile = boardState[0][col];
                silkBag.addTile(ejectedTile);
                for (int r = 0; r < boardRows - 1; r++) {
                    boardState[r][col] = boardState[r + 1][col];
                }
                boardState[boardRows - 1][col] = tile;
                break;
            default:
                Logger.log("Left/right arrow pressed but tile was not moved.", Logger.Level.WARNING);
                break;
        }
        int ejectedTileId = ejectedTile.getId();
        Logger.log(String.format("Tile with ID: %s was ejected from the board.", ejectedTileId), Logger.Level.INFO);
    }

    /**
     * Takes a tile and inserts it into the corresponding column,
     * shifting the needed tiles across one and placing
     * the removed tile back into the bag.
     * @param row the row that identifies the column
     * @param direction the direction to send the tile
     */
    // Up and Down buttons
    public void addTileToCol(int row, String direction) {
        Controller controller = Controller.getInstance();
        SilkBag silkBag = controller.getSilkbag();
        FloorTile tile = controller.getCurrentFloorTile();
        Tile ejectedTile = null;
        switch (direction) {
            case "up":
                ejectedTile = boardState[row][boardColumns - 1];
                silkBag.addTile(ejectedTile);
                for (int c = boardColumns - 1; c > 0; c--) {
                    boardState[row][c] = boardState[row][c - 1];
                }
                boardState[row][0] = tile;
                break;
            case "down":
                ejectedTile = boardState[row][0];
                silkBag.addTile(ejectedTile);
                for (int c = 0; c < boardColumns - 1; c++) {
                    boardState[row][c] = boardState[row][c + 1];
                }
                boardState[row][boardColumns - 1] = tile;
                break;
            default:
                Logger.log("Up/down arrow pressed but tile was not moved.", Logger.Level.WARNING);
                break;
        }
        int ejectedTileId = ejectedTile.getId();
        Logger.log(String.format("Tile with ID: %s was ejected from the board.", ejectedTileId), Logger.Level.INFO);
    }

    /**
     * @return the board state
     */
    public FloorTile[][] getBoardState() {
        return boardState;
    }

    /**
     * @return the amount of rows for the board
     */
    public int getBoardRows() {
        return boardRows;
    }

    /**
     * @return the amount of rows for the board
     */
    public int getBoardCols() {
        return boardColumns;
    }
}
