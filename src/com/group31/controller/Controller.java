package com.group31.controller;

import com.group31.exceptions.InvalidMoveDirection;
import com.group31.gameboard.Gameboard;
import com.group31.graphics.Game;
import com.group31.player.Player;
import com.group31.services.serializer.Serializer;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.silk_bag.SilkBag;
import com.group31.tile_manager.Tile;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author liam, Abdullah(Moe), Aaron
 */
public class Controller implements Serializable {
    /**
     * Dictates the placement properties of a tile.
     */
    public enum TilePlaced {
        /**
         * Tile has been placed.
         */
        PLACED,
        /**
         * Tile needs to be placed.
         */
        REQUIRED,
        /**
         * Tile does not need to be placed.
         */
        NOT_REQUIRED
    }

    /**
     * Dictates the state of movement for a player.
     */
    public enum MoveMade {
        /**
         * Player has been moved.
         */
        MOVED,
        /**
         * Player needs to be moved.
         */
        REQUIRED,
        /**
         * Player does not need to be moved.
         */
        NOT_REQUIRED
    }
    /**
     * Instance of the controller.
     */
    // Controller is a singleton, so it's static but not static at the same time?
    // Allows us to get an instance of the Controller without passing it around like the only can at a party.
    private static Controller instance = null;
    /**
     * Array of players that are playing the game.
     */
    private Player[] players;
    /**
     * Instance of the gameboard.
     */
    private Gameboard gameboard;
    /**
     * Instance of the silkBag.
     */
    private SilkBag silkBag;
    /**
     * The FloorTile waiting to be placed by the player.
     */
    private FloorTile currentFloorTile;
    /**
     * Keeps track of the placement status of a floor tile.
     */
    private TilePlaced floorTilePlaced;
    /**
     * Keeps track of the movement status of a player.
     */
    private MoveMade playerMoved;
    /**
     *  Keeps track of the game status. (Has the game been won).
     */
    private boolean gameWon;
    /**
     * Player's turn indicator.
     */
    private int playerTurn;
    /**
     * Keeps track of how many turns have passed.
     */
    private int globalTurnCount;
    /**
     * UUID of an instance of the controller.
     */
    private final String uuid;
    /**
     * The total turns in one round of the game.
     */
    private static int maxTurnCount;

    /**
     * Controller is responsible for game logic, loading and saving.
     */
    private Controller() {
        gameWon = false;

        // Each instance controller is assigned a random UUID, this helps us keep track of it when
        // it's serialized.
        this.uuid = UUID.randomUUID().toString();
    }

    /**
     * Returns the instance's UUID.
     * @return the instance's UUID
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Starts a new game.
     */
    public void startGame() {
        playerTurn = 1;
        floorTilePlaced = TilePlaced.NOT_REQUIRED;
    }

    /**
     * Checks every player's location, comparing it to the location of the goal tile. Should the player be
     * on the goal tile, they win the game.
     * @return true if a player is on top of a goal tile, false otherwise
     */
    public Player hasWon() {
        for (Player player : players) {
            int playerX = player.getCurrentLocation()[0];
            int playerY = player.getCurrentLocation()[1];
            if (gameboard.getTile(playerX, playerY).getId() == 0) {
                gameWon = true;
                return player;
            }
        }
        return null;
    }

    /**
     * Returns if the game is won.
     * @return if the game is won
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Returns the players in the game.
     * @return the players in the game
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets the players in Controller.
     * @param players new array of players
     */
    public void setPlayers(Player[] players) {
        this.players = players;
        maxTurnCount = players.length;
    }

    /**
     * Takes a tile and makes it the current floor tile.
     * @param drawnTile tile to set as the current tile
     */
    public void setCurrentFloorTile(FloorTile drawnTile) {
        currentFloorTile = drawnTile;
    }

    /**
     * Takes a tile and gives it to a player.
     * @param player Player to give the tile to.
     * @return a Tile for the player
     */
    public Tile takeTile(Player player) {
        // give tile to player from silkBag
        return null;
    }

    /**
     * Moves the player to the co-ordinates specified.
     * @param x X co-ordinate to move the player to.
     * @param y Y co-ordinate to move the player to.
     */
    public void movePlayer(int x, int y) {
        // move a player on a board
        // Handled somewhere else?
        // remove this?
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
        int maxBoardX = 0; //gameboard.getMaxX();
        int maxBoardY = 0; //gameboard.getMaxY();
        return Validation.validBoardMove(playerX, playerY, maxBoardX, maxBoardY, direction);
    }

    /**
     * Back track the player. (Result of the backtrack tile being used).
     * @param player the target player.
     */
    public void backtrackPlayer(Player player) {
        if (!gameboard.getBoardState()
                [player.getLastTwoTurns()[0]]
                [player.getLastTwoTurns()[1]].isOnFire()) {
            if (!gameboard.getBoardState()
                    [player.getLastTurn()[0]]
                    [player.getLastTurn()[1]].isOnFire()) {
                player.setLocation(player.getLastTwoTurns()[0],
                        player.getLastTwoTurns()[1]);
            }
        } else if (!gameboard.getBoardState()
                [player.getLastTurn()[0]]
                [player.getLastTurn()[1]].isOnFire()) {
            player.setLocation(player.getLastTurn()[0],
                    player.getLastTurn()[1]);
        }
    }
    /**
     * Returns the current player turn.
     * @return the current player turn
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * return the current player turn.
     * @return the current player turn.
     */
    public Game.PlayerNumber getPlayerTurnEnum() {
        return Game.PlayerNumber.values()[playerTurn];
    }
    /**
     * Sets the current turn.
     * @param playerTurn the turn count
     */
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
    /**
     * Increment the turn counter.
     */
    public void nextPlayerTurn() {
        nextGlobalTurn();
        if (getPlayerTurn() == maxTurnCount) {
            setPlayerTurn(0);
        }
        this.playerTurn++;
    }
    /**
     * Increment the global turn counter.
     */
    public void nextGlobalTurn() {
        this.globalTurnCount++;
    }
    /**
     * Returns the current state of floorTilePlaced.
     * @return the current state of floorTilePlaced
     */
    public TilePlaced getFloorTilePlaced() {
        return floorTilePlaced;
    }
    /**
     * Sets the state of the placement of a floor tile.
     * @param tilePlaced State of the tile.
     */
    public void setFloorTilePlaced(TilePlaced tilePlaced) {
        this.floorTilePlaced = tilePlaced;
    }
    /**
     * Returns the current state of playerMoved.
     * @return the current state of playerMoved
     */
    public MoveMade getPlayerMoved() {
        return playerMoved;
    }
    /**
     * Sets the state of the movement of a player.
     * @param playerMoved State of the player's movement.
     */
    public void setPlayerMoved(MoveMade playerMoved) {
        this.playerMoved = playerMoved;
    }
    /**
     * Returns the GameBoard.
     * @return the GameBoard
     */
    public Gameboard getGameboard() {
        return gameboard;
    }

    /**
     * Returns the SilkBag.
     * @return the SilkBag
     */
    public SilkBag getSilkbag() {
        return silkBag;
    }

    /**
     * Returns the FloorTile that the player just drew.
     * @return the FloorTile that the player just drew
     */
    public FloorTile getCurrentFloorTile() {
        return currentFloorTile;
    }
    /**
     * Returns an instance of the Controller.
     * @return an instance of the Controller
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Resets the controller's instance to a fresh controller.
     */
    // Dangerous :O
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Replaces the instance of the controller.
     * @param controller new instance
     */
    public static void setInstance(Controller controller) {
        instance = controller;
    }

    /**
     * Initialises the controller.
     * @param gameboard the game board for a game
     * @param silkBag the silk bag for a game
     */
    public void init(Gameboard gameboard, SilkBag silkBag) {
        this.gameboard = gameboard;
        this.silkBag = silkBag;
        setPlayerMoved(MoveMade.NOT_REQUIRED);
        setFloorTilePlaced(Controller.TilePlaced.NOT_REQUIRED);
    }

    /**
     * Save the controller to a file.
     */
    // Serialization... fancy :)
    public void save() {

        String object = "controller";
        String name = String.format("Game_%s", this.uuid);
        Serializer.serialize(this, name, object);

    }

}
