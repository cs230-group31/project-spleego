package com.group31.gameboard;

import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.silk_bag.SilkBag;

import java.io.FileNotFoundException;

/**
 * @author aaron
 */
public class Gameboard {
    /**
     * 2D array of the tiles on the board.
     */
    private final FloorTile[][] boardState;
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
    }

    /**
     * Fills the board with random floor tiles.
     * @param silkBag the silk bag for the game
     * @throws FileNotFoundException If the image file cannot be found.
     */
    public void genBoard(SilkBag silkBag) throws FileNotFoundException {
        for (int r = 0; r <  boardRows; r++) {
            for (int c = 0; c < boardColumns; c++) {
                boardState[r][c] = silkBag.genFloorTile();
            }
        }
    }

    /**
     * Takes a tile and inserts it into the corresponding row,
     *      * shifting the needed tiles across one and placing
     *      * the removed tile back into the bag.
     * @param silkBag the silk bag of the game
     * @param tile the tile to insert
     * @param col the column that identifies the row
     */
    public void addTileToRow(SilkBag silkBag, FloorTile tile, int col) {
        silkBag.addTile(boardState[boardRows - 1][col]);
        for (int r = 0; r < boardRows - 1; r++) {
            boardState[r + 1][col] = boardState[r][col];
        }
        boardState[0][col] = tile;
    }

    /**
     * Takes a tile and inserts it into the corresponding column,
     * shifting the needed tiles across one and placing
     * the removed tile back into the bag.
     * @param silkBag the silk bag of the game
     * @param tile the tile to insert.
     * @param row the row that identifies the column
     */
    public void addTileToCol(SilkBag silkBag, FloorTile tile, int row) {
        silkBag.addTile(boardState[row][boardColumns]);
        for (int c = 0; c < boardColumns - 1; c++) {
            boardState[row][c + 1] = boardState[row][c];
        }
        boardState[row][0] = tile;
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
