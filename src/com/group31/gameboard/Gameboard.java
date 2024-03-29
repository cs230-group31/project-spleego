package com.group31.gameboard;

import com.group31.controller.Controller;
import com.group31.exceptions.TileNotFound;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.Tile;
import com.group31.tile_manager.silk_bag.SilkBag;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * @author Aaron
 */
public class Gameboard implements Serializable {
    /**
     * Represents the tiles on the board.
     */
    private final FloorTile[][] boardState;
    /**
     * Represents fixed tiles on the board.
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
     * Gameboard represents the visual board and tiles, as well as handling game logic.
     * @param width  width of the GameBoard
     * @param height height of the GameBoard
     */
    public Gameboard(int width, int height) {
        boardRows = width;
        boardColumns = height;
        boardState = new FloorTile[boardRows][boardColumns];
        boardFixedTiles = new boolean[boardRows][boardColumns];
    }

    /**
     * Populates the board with silkbag tiles.
     * @param silkBag the silk bag for the game
     * @throws FileNotFoundException if the image file cannot be found
     */
    public void genBoard(SilkBag silkBag) throws FileNotFoundException {
        for (int r = 0; r < boardRows; r++) {
            for (int c = 0; c < boardColumns; c++) {
                boardState[r][c] = silkBag.genRandomFloorTile();
            }
        }
    }

    /**
     * Returns the fixture state of the tiles.
     * @param row row the tile is in
     * @param col column the tile is in
     * @return the fixture state of the tiles
     */
    public boolean isFixedTile(int row, int col) {
        return boardFixedTiles[row][col];
    }

    /**
     * Tags a tile as a fixed tile.
     * @param row row the tile is in
     * @param col column the tile is in
     * @param isFixed whether or not a tile must be fixed.
     */
    public void setFixedTile(int row, int col, boolean isFixed) {
        boardFixedTiles[row][col] = isFixed;
    }

    /**
     * Return the tile fixture status of a row.
     * @param row the row of tiles to check
     * @return the fixture status of a tile
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
     * Return the tile fixture status of a column.
     * @param col the row of tiles to check
     * @return the fixture status of a column
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
        for (int row = 0; row < boardRows; row++) {
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
     *
     * @param floorTile tile to add to the gameboard
     * @param tileRow   the row to set the tile
     * @param tileCol   the col to set the tile
     */
    public void setTile(FloorTile floorTile, int tileRow, int tileCol) {
        boardState[tileRow][tileCol] = floorTile;
    }

    /**
     * Takes a tile and inserts it into the corresponding row,
     * shifting the needed tiles across one and placing
     * the removed tile back into the bag.
     *
     * @param col       the column that identifies the row
     * @param direction the direction to send the tile
     */
    // Left and right buttons
    public void addTileToRow(int col, String direction) {
        Controller controller = Controller.getInstance();
        Player[] players = controller.getPlayers();
        for (Player player : players) {
            if (player.getCurrentLocation()[1] == col) {
                int playerX = player.getCurrentLocation()[0];
                int playerY = player.getCurrentLocation()[1];
                switch (direction) {
                    case "left":
                        if (playerX + 1 > boardRows - 1) {
                            playerX = 0;
                        } else {
                            playerX++;
                        }
                        player.setLocation(playerX, playerY);
                        break;
                    case "right":
                        if (playerX - 1 < 0) {
                            playerX = boardRows - 1;
                        } else {
                            playerX--;
                        }
                            player.setLocation(playerX, playerY);
                        break;
                    default:
                        Logger.log("Left/right arrow pressed but player was not moved.", Logger.Level.WARNING);
                        break;
                }
            }
        }
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
     *
     * @param row       the row that identifies the column
     * @param direction the direction to send the tile
     */
    // Up and Down buttons
    public void addTileToCol(int row, String direction) {
        Controller controller = Controller.getInstance();
        Player[] players = controller.getPlayers();
        for (Player player : players) {
            if (player.getCurrentLocation()[0] == row) {
                int playerX = player.getCurrentLocation()[0];
                int playerY = player.getCurrentLocation()[1];
                switch (direction) {
                    case "up":
                        if (playerY + 1 > boardColumns - 1) {
                            playerY = 0;
                        } else {
                            playerY++;
                        }
                            player.setLocation(playerX, playerY);
                        break;
                    case "down":
                        if (playerY - 1 < 0) {
                            playerY = boardColumns - 1;
                        } else {
                            playerY--;
                        }
                            player.setLocation(playerX, playerY);
                        break;
                    default:
                        Logger.log("Left/right arrow pressed but player was not moved.", Logger.Level.WARNING);
                        break;
                }
            }
        }
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

    /**
     * Returns a tile's location on the gameboard.
     *
     * @param tile tile instance to search for
     * @return a tile's location on the gameboard.
     * @throws TileNotFound if the tile is not found
     */
    private int[] tileLocation(FloorTile tile) throws TileNotFound {
        for (int row = 0; row <= boardRows - 1; row++) {
            for (int col = 0; col <= boardColumns - 1; col++) {
                if (boardState[row][col] == tile) {
                    return new int[]{row, col};
                }
            }
        }
        throw new TileNotFound("The tile cannot be found.");
    }

    /**
     * Returns the upper neighbour of a tile (if it exists).
     *
     * @param tile tile to search around
     * @return the upper neighbour of a tile (if it exists)
     * @throws TileNotFound if the tile is not found
     */
    public FloorTile getTileUpperNeighbour(FloorTile tile) throws TileNotFound {
        FloorTile neighbour = null;
        int[] location = tileLocation(tile);
        int tileX = location[0];
        int tileY = location[1];
        if (tileY + 1 < boardColumns) {
            neighbour = boardState[tileX][tileY + 1];
        }
        return neighbour;
    }

    /**
     * Returns the lower neighbour of a tile (if it exists).
     *
     * @param tile tile to search around
     * @return the lower neighbour of a tile (if it exists)
     * @throws TileNotFound if the tile is not found
     */
    public FloorTile getTileLowerNeighbour(FloorTile tile) throws TileNotFound {
        FloorTile neighbour = null;
        int[] location = tileLocation(tile);
        int tileX = location[0];
        int tileY = location[1];
        if (tileY - 1 > 0) {
            neighbour = boardState[tileX][tileY - 1];
        }
        return neighbour;
    }

    /**
     * Returns the left neighbour of a tile (if it exists).
     *
     * @param tile tile to search around
     * @return the left neighbour of a tile (if it exists)
     * @throws TileNotFound if the tile is not found
     */
    public FloorTile getTileLeftNeighbour(FloorTile tile) throws TileNotFound {
        FloorTile neighbour = null;
        int[] location = tileLocation(tile);
        int tileX = location[0];
        int tileY = location[1];
        if (tileX + 1 < boardRows) {
            neighbour = boardState[tileX + 1][tileY];
        }
        return neighbour;
    }

    /**
     * Returns the right neighbour of a tile (if it exists).
     *
     * @param tile tile to search around
     * @return the right neighbour of a tile (if it exists)
     * @throws TileNotFound if the tile is not found
     */
    public FloorTile getTileRightNeighbour(FloorTile tile) throws TileNotFound {
        FloorTile neighbour = null;
        int[] location = tileLocation(tile);
        int tileX = location[0];
        int tileY = location[1];
        if (tileX + 1 > 0) {
            neighbour = boardState[tileX - 1][tileY];
        }
        return neighbour;
    }

}
