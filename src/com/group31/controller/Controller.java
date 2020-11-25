package com.group31.controller;

import com.group31.exceptions.InvalidMoveDirection;
import com.group31.gameboard.Gameboard;
import com.group31.player.Player;
import com.group31.services.FileManager;
import com.group31.tile_manager.Silkbag;
import com.group31.tile_manager.Tile;

public class Controller {

    /**
     * Array of players.
     */
    private Player[] players;
    /**
     * The game board.
     */
    private Gameboard gameboard;
    /**
     * The silk bag.
     */
    private Silkbag silkbag;
    /**
     * Legal place points for tiles on the game board.
     */
    private int[] permittedPlacePoint;
    /**
     * The file manager.
     */
    private FileManager fileManager;

    /**
     * Controller deals with game logic, loading and saving.
     * @param players Array of players playing the game.
     * @param gameboard The game board for a game.
     * @param silkbag The silk bag for a game.
     */
    public Controller(Player[] players, Gameboard gameboard, Silkbag silkbag) {
        this.players = players;
        this.gameboard = gameboard;
        this.silkbag = silkbag;
    }

    /**
     * Takes a tile and gives it to a player.
     * @param player Player we want to give the tile to.
     * @return A Tile for the player.
     */
    public Tile takeTile(Player player) {
        // give tile to player from silkbag
        return null;
    }

    /**
     * Moves the player to the co-ordinates specified.
     * @param x X co-ordinate to move the player to.
     * @param y Y co-ordinate to move the player to.
     */
    public void movePlayer(int x, int y) {
        // move a player on a board
    }

    /**
     * Draws a tile on the board.
     * @param tile Tile to draw.
     * @param x X co-ordinate where we want to draw the tile.
     * @param y Y co-ordinate where we want to draw the tile.
     */
    public void drawTileOnBoard(Tile tile, int x, int y) {
        // draw a tile on the gameboard
    }

    /**
     * Validates the player move.
     * @param player Player who has made the move.
     * @param playerX Player's X co-ordinate.
     * @param playerY Player's Y co-ordinate.
     * @param direction Direction of movement.
     * @return If the move is valid or not.
     * @throws InvalidMoveDirection If the direction is invalid.
     */
    public boolean validatePlayerMove(Player player, int playerX, int playerY, Movement.Move direction)
            throws InvalidMoveDirection {
        int maxBoardX = gameboard.getMaxX();
        int maxBoardY = gameboard.getMaxY();
        return Validation.validBoardMove(playerX, playerY, maxBoardX, maxBoardY, direction);
    }

    /**
     * Saves the game.
     */
    public void saveGame() {
        // save the game passing info to the file reader
    }

}