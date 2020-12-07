package com.group31.graphics;

import java.util.ArrayList;

import com.group31.controller.Validation;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.player.PlayerProfile;
import com.group31.services.serializer.Serializer;
import com.group31.tile_manager.Tile;
import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.gameboard.Gameboard;
import com.group31.logger.Logger;
import com.group31.main.Main;
import com.group31.player.Player;
import com.group31.saveload.Save;
import com.group31.services.FileManager;
import com.group31.settings.Settings;
import com.group31.tile_manager.FloorTile;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * @author Emily
 */
public class Game extends Application {

    /**
     * Space between tiles in pixels.
     */
    private static final double TILE_SPACING = Settings.getSettingAsDouble("tile_spacing");
    /**
     * File Path for the table background image.
     */
    private static final String TABLE_IMAGE_URL = Settings.get("table_image_url");
    /**
     * File Path for the unpressed `EXIT` button.
     */
    private static final String CLOSE_UNPRESSED_URL = "resources/images/close unpressed.png";
    /**
     * File Path for the pressed `EXIT` button.
     */
    private static final String CLOSE_PRESSED_URL = "resources/images/close pressed.png";
    /**
     * File Path for the return button image.
     */
    private static final String RETURN_BUTTON_URL = "resources/images/return button.png";
    /**
     * File Path for the draw tile button.
     */
    private static final String DRAW_TILE_URL = "resources/images/draw tile.png";
    /**
     * Player one's hand of tiles.
     */
    private static HBox playerOneHand;
    /**
     * Player two's hand of tiles.
     */
    private static VBox playerTwoHand;
    /**
     * Player three's hand of tiles.
     */
    private static HBox playerThreeHand;
    /**
     * Player four's hand of tiles.
     */
    private static VBox playerFourHand;
    /**
     * The currently drawn tile.
     */
    private static ImageView currentDrawnTile;
    /**
     * FlowPane for the drawtile button and tile next to it.
     */
    private static FlowPane drawShowTile;

    /**
     * Tile representative of the back of another tile.
     */
    private static final Tile FACE_DOWN_TILE = new Tile(-1);
    /**
     * Player numbers enum.
     */
    public enum PlayerNumber {
        /**
         * Player one.
         */
        ONE,
        /**
         * Player two.
         */
        TWO,
        /**
         * Player three.
         */
        THREE,
        /**
         * Player four.
         */
        FOUR
    }
    /**
     * Scene for the main menu.
     */
    private static Scene mainScene;

    /**
     * Stage instance.
     */
    private static Stage stageWindow;

    /**
     * Player Profiles.
     */
    private static ArrayList<PlayerProfile> profiles;

    /**
     * Creates the board.
     * @param stage JavaFX Stage of the main window
     */
    public void start(Stage stage) {
        stageWindow = stage;
        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        GridPane board = new GridPane();
        try {
            Image tableImg = new Image(new FileInputStream(TABLE_IMAGE_URL));
            BackgroundImage bg = new BackgroundImage(tableImg,
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            root.setBackground(new Background(bg));
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        board.setAlignment(Pos.CENTER);
        board.setHgap(TILE_SPACING);
        board.setVgap(TILE_SPACING);

        // set up player hands
        playerOneHand = new HBox();
        playerTwoHand = new VBox();
        playerThreeHand = new HBox();
        playerFourHand = new VBox();
        Controller controller = Controller.getInstance();

        StackPane topThing = new StackPane();
        topThing.getChildren().add(playerOneHand);
        ImageButton close = new ImageButton(CLOSE_UNPRESSED_URL, CLOSE_PRESSED_URL);
        close.setOnMouseClicked(e -> saveAndExit(stage));
        topThing.getChildren().add(close);
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        topThing.setPickOnBounds(false);
        root.setTop(topThing);

        root.setRight(playerTwoHand);

        StackPane bottomPane = new StackPane();
        bottomPane.getChildren().add(playerThreeHand);
        ImageButton drawTile = new ImageButton(DRAW_TILE_URL);
        drawTile.setOnMouseClicked(e -> {
            if ((controller.getPlayerMoved() != Controller.MoveMade.REQUIRED
                    && controller.getFloorTilePlaced() != Controller.TilePlaced.REQUIRED)) {
                drawTile();
            }
        });
        drawShowTile.getChildren().add(drawTile);
        bottomPane.getChildren().add(drawShowTile);
        ImageButton returnSign = new ImageButton(RETURN_BUTTON_URL);
        returnSign.setOnMouseClicked(e -> saveAndExit(stage));
        HBox bottomBox = new HBox();
        bottomBox.getChildren().add(returnSign);
        bottomBox.setPickOnBounds(false);
        bottomPane.getChildren().add(bottomBox);
        playerThreeHand.setPickOnBounds(false);
        drawShowTile.setPickOnBounds(false);
        bottomPane.setPickOnBounds(false);
        drawShowTile.setAlignment(Pos.BOTTOM_LEFT);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        root.setBottom(bottomPane);

        root.setLeft(playerFourHand);

        root.setCenter(board);
        drawTileArrows(board);
        drawGameBoard(board);
        scene.setRoot(root);
        stage.setScene(scene);

        drawPlayers(board);
    }

    /**
     * Puts the players onto the board.
     * @param board game board the players are to be put on
     */
    private static void drawPlayers(GridPane board) {
        Controller controller = Controller.getInstance();
        Player[] players = controller.getPlayers();
        for (Player player : players) {
            int[] location = player.getCurrentLocation();
            ImageView playerSprite = new ImageView(player.getSprite());
            stackNodeAt(board, playerSprite, location[0] + 1, location[1] + 1);
        }
    }

    /**
     * Skips a players turn if they are unable to move.
     */
    private static void skipPlayerMovement() {
        Controller controller = Controller.getInstance();
        controller.setPlayerMoved(Controller.MoveMade.NOT_REQUIRED);
    }

    /**
     * Takes a JavaFX Node and places it on top fo the StackPane at given location.
     * @param board grid pane of tiles
     * @param node JavaFX node to display
     * @param atRow target row
     * @param atCol target column
     */
    private static void stackNodeAt(GridPane board, Node node, int atRow, int atCol) {
        board.add(node, atRow, atCol);
    }

    /**
     * Draws the tile onto the game board.
     */
    private void drawTile() {

        Controller controller = Controller.getInstance();
        int playerTurn = controller.getPlayerTurn();
        Player currentPlayer = controller.getPlayers()[playerTurn - 1];
        Tile drawnTile = controller.getSilkbag().drawTile();
        drawnTile.updateDrawnThisTurn(true);
        setCurrentDrawnTile(drawnTile);
        if (drawnTile.isActionTile()) {
            currentPlayer.recieveTile(drawnTile);
            Game.updatePlayerHand(controller.getPlayerTurnEnum(), currentPlayer.getHand());
        } else {
            controller.setCurrentFloorTile(new FloorTile(drawnTile));
            controller.setFloorTilePlaced(Controller.TilePlaced.REQUIRED);
        }
        drawnTile.updateDrawnThisTurn(false);

    }

    /**
     * Starts the player movement.
     * @param board game board the players move on
     */
    private static void startPlayerMove(GridPane board) {
        Controller controller = Controller.getInstance();
        Gameboard gameboard = controller.getGameboard();
        Player[] players = controller.getPlayers();
        Player currentPlayer = players[controller.getPlayerTurn() - 1];
        int playerX = currentPlayer.getCurrentLocation()[0];
        int playerY = currentPlayer.getCurrentLocation()[1];
        FloorTile playerTile = gameboard.getBoardState()[playerX][playerY];
        boolean upIsValid;
        try {
            upIsValid = Validation.validRouting(playerTile.getId(),
                    gameboard.getBoardState()[playerX][playerY - 1].getId(), "up");
        } catch (ArrayIndexOutOfBoundsException e) {
            upIsValid = false;
        }
        boolean downIsValid;
        try {
            downIsValid = Validation.validRouting(playerTile.getId(),
                    gameboard.getBoardState()[playerX][playerY + 1].getId(), "down");
        } catch (ArrayIndexOutOfBoundsException e) {
            downIsValid = false;
        }
        boolean leftIsValid;
        try {
            leftIsValid = Validation.validRouting(playerTile.getId(),
                    gameboard.getBoardState()[playerX - 1][playerY].getId(), "left");
        } catch (ArrayIndexOutOfBoundsException e) {
            leftIsValid = false;
        }
        boolean rightIsValid;
        try {
            rightIsValid = Validation.validRouting(playerTile.getId(),
                    gameboard.getBoardState()[playerX + 1][playerY].getId(), "right");
        } catch (ArrayIndexOutOfBoundsException e) {
            rightIsValid = false;
        }
        if (upIsValid) {
            ImageButton moveArrow = new ImageButton("resources/images/tiles/move up.png");
            if (controller.getPlayerMoved() == Controller.MoveMade.REQUIRED) {
                moveArrow.setOnMouseClicked(e -> {
                    currentPlayer.setLocation(playerX, playerY - 1);
                    drawGameBoard(board);
                    drawPlayers(board);
                    controller.setPlayerMoved(Controller.MoveMade.MOVED);
                    Player winner = controller.hasWon();
                    if (winner != null) {
                        Logger.log(winner.getName() + " has won!", Logger.Level.INFO);
                        declareWinner(winner.getName(), stageWindow, mainScene);
                    }
                });
                stackNodeAt(board, moveArrow, playerX + 1, playerY);
            }
        }
        if (downIsValid) {
            ImageButton moveArrow = new ImageButton("resources/images/tiles/move down.png");
            if (controller.getPlayerMoved() == Controller.MoveMade.REQUIRED) {
                moveArrow.setOnMouseClicked(e -> {
                    currentPlayer.setLocation(playerX, playerY + 1);
                    drawGameBoard(board);
                    drawPlayers(board);
                    controller.setPlayerMoved(Controller.MoveMade.MOVED);
                    Player winner = controller.hasWon();
                    if (winner != null) {
                        Logger.log(winner.getName() + " has won!", Logger.Level.INFO);
                        declareWinner(winner.getName(), stageWindow, mainScene);
                    }
                });
                stackNodeAt(board, moveArrow, playerX + 1, playerY + 2);
            }
        }
        if (leftIsValid) {
            ImageButton moveArrow = new ImageButton("resources/images/tiles/move left.png");
            if (controller.getPlayerMoved() == Controller.MoveMade.REQUIRED) {
                moveArrow.setOnMouseClicked(e -> {
                    currentPlayer.setLocation(playerX - 1, playerY);
                    drawGameBoard(board);
                    drawPlayers(board);
                    controller.setPlayerMoved(Controller.MoveMade.MOVED);
                    Player winner = controller.hasWon();
                    if (winner != null) {
                        Logger.log(winner.getName() + " has won!", Logger.Level.INFO);
                        declareWinner(winner.getName(), stageWindow, mainScene);
                    }
                });
                stackNodeAt(board, moveArrow, playerX, playerY + 1);
            }
        }
        if (rightIsValid) {
            ImageButton moveArrow = new ImageButton("resources/images/tiles/move right.png");
            if (controller.getPlayerMoved() == Controller.MoveMade.REQUIRED) {
                moveArrow.setOnMouseClicked(e -> {
                    currentPlayer.setLocation(playerX + 1, playerY);
                    drawGameBoard(board);
                    drawPlayers(board);
                    controller.setPlayerMoved(Controller.MoveMade.MOVED);
                    Player winner = controller.hasWon();
                    if (winner != null) {
                        Logger.log(winner.getName() + " has won!", Logger.Level.INFO);
                        declareWinner(winner.getName(), stageWindow, mainScene);
                    }
                });
                stackNodeAt(board, moveArrow, playerX + 2, playerY + 1);
            }
        }
        if ((!upIsValid && !downIsValid && !leftIsValid && !rightIsValid)) {
            skipPlayerMovement();
        }
        controller.nextPlayerTurn();
    }

    /**
     * Saves and exits the game.
     * @param stage JavaFX Stage of the main window
     */
    private void saveAndExit(Stage stage) {
        Save.saveAll(profiles);
        Controller.resetInstance();
        Main.initController();
        stage.setScene(mainScene);
    }

    /**
     * Sets the current draw tile on to the game board.
     * @param tile tile to be drawn on to the board
     */
    private static void setCurrentDrawnTile(Tile tile) {
        drawShowTile.getChildren().remove(currentDrawnTile);
        Image tileImg = null;
        try {
            FileManager.setDirectory(Settings.get("tile_images_url"), false);
            tileImg = FileManager.readImage(tile.getId() + "", "png");
        } catch (NoSuchDirectory noSuchDirectory) {
            noSuchDirectory.printStackTrace();
        } catch (IOException e) {
            Logger.log(e.toString(), Logger.Level.ERROR);
        }
        currentDrawnTile = new ImageView(tileImg);
        drawShowTile.getChildren().add(currentDrawnTile);
    }

    /**
     * Declares the winner and updates the stats of everyone that played.
     * @param winner winner of the game
     * @param stage JavaFX Stage of the main window
     * @param mainMenu user is returned to mainMenu when winning screen is over
     */
    private static void declareWinner(String winner, Stage stage, Scene mainMenu) {
        String object = "player";
        String winnerFileName = String.format("PlayerProfile_%s", winner);
        try {
            PlayerProfile winningProfile = (PlayerProfile) Serializer.deserialize(winnerFileName, object);
            winningProfile.incrementGamesWon();
            winningProfile.save();
            for (Player player : Controller.getInstance().getPlayers()) {
                if (!player.getName().equals(winner)) {
                    String loserFileName = String.format("PlayerProfile_%s", player.getName());
                    PlayerProfile profile = (PlayerProfile) Serializer.deserialize(loserFileName, object);
                    profile.incrementGamesLost();
                    profile.save();
                }
            }
        } catch (ObjectNeverSerialized e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        Controller.resetInstance();
        WinnerScreen.launch(stage, mainMenu, winner);
    }

    /**
     * Creates an instance of Game and starts it.
     * @param stage JavaFX Stage of the main window.
     * @param mainScene JavaFX Scene of the main menu.
     * @param profiles player profiles
     */
    public static void launch(Stage stage, Scene mainScene, ArrayList<PlayerProfile> profiles) {
        Game game = new Game();
        init(mainScene, profiles);
        game.start(stage);
    }

    /**
     * Initialises the main menu scene into this Game instance so it can be referenced.
     * @param mainMenuScene JavaFX Scene of the main menu.
     * @param playerProfiles Player profiles
     */
    private static void init(Scene mainMenuScene, ArrayList<PlayerProfile> playerProfiles) {
        mainScene = mainMenuScene;
        drawShowTile = new FlowPane();
        profiles = playerProfiles;
        setCurrentDrawnTile(FACE_DOWN_TILE);
    }

    /**
     * Renders the arrows where players are allowed to insert tiles.
     * This is achieved by checking every row and column for fixed tiles.
     * @param board represents the grid of tiles
     */
    public static void drawTileArrows(GridPane board) {
        Controller controller = Controller.getInstance();
        int boardRows = controller.getGameboard().getBoardRows();
        int boardCols = controller.getGameboard().getBoardCols();
        Gameboard gameboard = controller.getGameboard();
        for (int col = 0; col < boardCols; col++) {
            if (!gameboard.rowHasFixedTile(col)) {
                ImageButton arrowUpButton = new ImageButton("resources/images/tiles/arrow up.png");
                ImageButton arrowDownButton = new ImageButton("resources/images/tiles/arrow down.png");
                // temporary final variable otherwise lambda complains
                int finalCol = col;

                arrowUpButton.setOnMouseClicked(e -> {
                    if (controller.getFloorTilePlaced() == Controller.TilePlaced.REQUIRED) {
                        gameboard.addTileToCol(finalCol, "down");
                        drawGameBoard(board);
                        controller.setFloorTilePlaced(Controller.TilePlaced.PLACED);
                        setCurrentDrawnTile(FACE_DOWN_TILE);
                        drawPlayers(board);
                        controller.setPlayerMoved(Controller.MoveMade.REQUIRED);
                        startPlayerMove(board);
                    }
                });

                arrowDownButton.setOnMouseClicked(e -> {
                    if (controller.getFloorTilePlaced() == Controller.TilePlaced.REQUIRED) {
                        gameboard.addTileToCol(finalCol, "up");
                        drawGameBoard(board);
                        controller.setFloorTilePlaced(Controller.TilePlaced.PLACED);
                        setCurrentDrawnTile(FACE_DOWN_TILE);
                        drawPlayers(board);
                        controller.setPlayerMoved(Controller.MoveMade.REQUIRED);
                        startPlayerMove(board);
                    }
                });

                StackPane tileStackOne = new StackPane(arrowDownButton);
                StackPane tileStackTwo = new StackPane(arrowUpButton);
                board.add(tileStackOne, col + 1, 0);
                board.add(tileStackTwo, col + 1, boardRows + 1);
            }
        }
        for (int row = 0; row < boardRows; row++) {
            if (!gameboard.colHasFixedTile(row)) {
                ImageButton arrowRightButton = new ImageButton("resources/images/tiles/arrow right.png");
                ImageButton arrowLeftButton = new ImageButton("resources/images/tiles/arrow left.png");
                int finalRow = row;

                arrowLeftButton.setOnMouseClicked(e -> {
                    if (controller.getFloorTilePlaced() == Controller.TilePlaced.REQUIRED) {
                        gameboard.addTileToRow(finalRow, "right");
                        drawGameBoard(board);
                        controller.setFloorTilePlaced(Controller.TilePlaced.PLACED);
                        setCurrentDrawnTile(FACE_DOWN_TILE);
                        drawPlayers(board);
                        controller.setPlayerMoved(Controller.MoveMade.REQUIRED);
                        startPlayerMove(board);
                    }
                });

                arrowRightButton.setOnMouseClicked(e -> {
                    if (controller.getFloorTilePlaced() == Controller.TilePlaced.REQUIRED) {
                        gameboard.addTileToRow(finalRow, "left");
                        drawGameBoard(board);
                        controller.setFloorTilePlaced(Controller.TilePlaced.PLACED);
                        setCurrentDrawnTile(FACE_DOWN_TILE);
                        drawPlayers(board);
                        controller.setPlayerMoved(Controller.MoveMade.REQUIRED);
                        startPlayerMove(board);
                    }
                });

                StackPane tileStackOne = new StackPane(arrowRightButton);
                StackPane tileStackTwo = new StackPane(arrowLeftButton);
                board.add(tileStackOne, 0, row + 1);
                board.add(tileStackTwo, boardCols + 1, row + 1);
            }
        }
    }

    /**
     * Renders the game board by iterating through the amount of rows and columns
     * starting from 1 to make room for the arrows denoting valid place points.
     * @param board represents the grid of tiles
     */
    public static void drawGameBoard(GridPane board) {
        Controller controller = Controller.getInstance();
        int boardRows = controller.getGameboard().getBoardRows();
        int boardCols = controller.getGameboard().getBoardRows();
        Gameboard gameboard = controller.getGameboard();
        for (int row = 1; row <=  boardRows; row++) {
            for (int col = 1; col <= boardCols; col++) {
                FloorTile tileAtCoords = gameboard.getBoardState()[row - 1][col - 1];
                board.getChildren().remove(tileAtCoords);
                Image tileImg = null;
                try {
                    FileManager.setDirectory(Settings.get("tile_images_url"), false);
                    tileImg = FileManager.readImage(tileAtCoords.getId() + "", "png");
                } catch (NoSuchDirectory noSuchDirectory) {
                    noSuchDirectory.printStackTrace();
                } catch (IOException e) {
                    Logger.log(e.toString(), Logger.Level.ERROR);
                }
                ImageView imageView = new ImageView(tileImg);
                StackPane tileStack = new StackPane(imageView);
                tileStack.setPickOnBounds(false);
                board.add(tileStack, row, col);
            }
        }
    }

    /**
     * Redraws the player's hand with the tiles provided.
     * @param player which position the players are at the table,
     *               starting with 1 at the top going clockwise
     * @param hand the hand of the corresponding player
     */
    public static void updatePlayerHand(PlayerNumber player, ArrayList<Tile> hand) {
        switch (player) {
            case ONE:
                playerOneHand.getChildren().clear();
                for (Tile tile : hand) {
                    playerOneHand.getChildren().add(new ImageView(tile.getCurrentImage()));
                }
                break;
            case TWO:
                playerTwoHand.getChildren().clear();
                for (Tile tile : hand) {
                    playerTwoHand.getChildren().add(new ImageView(tile.getCurrentImage()));
                }
                break;
            case THREE:
                playerThreeHand.getChildren().clear();
                for (Tile tile : hand) {
                    playerThreeHand.getChildren().add(new ImageView(tile.getCurrentImage()));
                }
                break;
            case FOUR:
                playerFourHand.getChildren().clear();
                for (Tile tile : hand) {
                    playerFourHand.getChildren().add(new ImageView(tile.getCurrentImage()));
                }
                break;
            default:
                break;
        }
    }
}
