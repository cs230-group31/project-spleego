package com.group31.controller;

import com.group31.exceptions.InvalidMoveDirection;
import com.group31.gameboard.Gameboard;
import com.group31.graphics.Game;
import com.group31.leaderboard.Leaderboard;
import com.group31.logger.Logger;
import com.group31.main.Main;
import com.group31.player.Player;
import com.group31.services.serializer.Serializer;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.silk_bag.SilkBag;
import com.group31.tile_manager.Tile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Controller implements Serializable {
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
     * Instance of the controller.
     */
    private static Controller instance = null;
    /**
     * Array of players that are playing the game.
     */
    private ArrayList<Player> players;
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
     * Keeps track of if a floor tile has been placed yet.
     * Use The Enum.
     */
    private TilePlaced floorTilePlaced;
    /**
     *  Tracks if the game has been won.
     */
    private boolean gameWon;
    /**
     * Which player's turn it is.
     */
    private int playerTurn;

    /**
     * UUID of an instance of the controller.
     */
    private final String uuid;

    /**
     * Controller deals with game logic, loading and saving.
     */
    private Controller() {
        gameWon = false;
        this.uuid = UUID.randomUUID().toString();
    }

    /**
     * @param gameboard The game board for a game.
     * @param silkBag The silk bag for a game.
     */
    public void init(Gameboard gameboard, SilkBag silkBag) {
        this.gameboard = gameboard;
        this.silkBag = silkBag;
    }

    /**
     * Adds players to the controller.
     * @param players players to add
     */
    public void addPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    //TODO: THIS IS SHIT AND WRONG AND WE NEED TO CHANGE IT
    //TODO: MODIFY BY PUTTING LOTS OF THESE THINGS IN GAME
    //TODO: AND REFERENCING THE CONTROLLER'S VARIABLES IN GAME
    /**
     * Plays the game by looping until a player has won.
     */
    public void playGame() {
        Player winner = null;
        while (!gameWon) {
            Player currentPlayer = players.get(playerTurn);
            Tile drawnTile = silkBag.drawTile();
            drawnTile.updateDrawnThisTurn(true);
            if (drawnTile.isActionTile()) {
                currentPlayer.recieveTile(drawnTile);
                Game.updatePlayerHand(playerTurn, currentPlayer.getHand());
            } else {
                currentFloorTile = (FloorTile) drawnTile;
                floorTilePlaced = TilePlaced.REQUIRED;
            }

            drawnTile.updateDrawnThisTurn(false);
            winner = hasWon();
            playerTurn++;
        }
        assert winner != null;
        Logger.log(winner.getName() + " has won!", Logger.Level.INFO);
    }

    /**
     * Starts a new game by making the playerTurn 0.
     */
    public void startGame() {
        playerTurn = 0;
        floorTilePlaced = TilePlaced.NOT_REQUIRED;
        playGame();
    }

    /**
     * Checks every player's location and compares if they are in the same place as a goal tile.
     * @return True if a player is on top of a goal tile, false otherwise.
     */
    private Player hasWon() {
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
     * Takes a tile and gives it to a player.
     * @param player Player we want to give the tile to.
     * @return A Tile for the player.
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
     * If the player's location one turn and two turns ago are not on fire,
     * move them back two turns, otherwise move them back one turn.
     * If their location one turn ago is on fire, do not move them.
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
     * Saves the game.
     */
    public void saveGame() {
        // save the game passing info to the file reader
    }

    /**
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
     * @return the FloorTile the player just drew
     */
    public FloorTile getCurrentFloorTile() {
        return currentFloorTile;
    }

    /**
     * Creates a new instance of Controller if one does not exist,
     * then returns the current instance of Controller.
     * @return the current Controller instance
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Sets the Controller to a given instance.
     * @param controller the Controller for this class to become
     */
    public static void setInstance(Controller controller) {
        instance = controller;
    }

    /**
     * Sets the floorTilePlaced to PLACED.
     */
    public void setFloorTilePlaced() {
        this.floorTilePlaced = TilePlaced.PLACED;
    }

    /**
     * Adds every player to the leaderboard.
     */
    // All players should be added when the game starts, if the players aren't loaded in.
    public void addPlayersToLeaderboard() {
        for (Player player : this.players) {
            Leaderboard.addPlayer(player);
        }
    }

    /**
     * Save the controller to a file.
     */
    public void save() {

        String object = "controller";
        String name = String.format("Game_%s", this.uuid);
        Serializer.serialize(this, name, object);

    }

}
