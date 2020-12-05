package com.group31.graphics;

import com.group31.controller.Controller;
import com.group31.gameboard.Gameboard;
import com.group31.logger.Logger;
import com.group31.saveload.Save;
import com.group31.settings.Settings;
import com.group31.tile_manager.FloorTile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
     * Represents the board of tiles.
     */
    private static GridPane board;
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
     * Scene for the main menu.
     */
    private static Scene mainScene;

    /**
     * Creates the board.
     * @param stage JavaFX Stage of the main window
     */
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        board = new GridPane();
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

        StackPane topThing = new StackPane();
        topThing.getChildren().add(playerOneHand);
        ImageButton close = new ImageButton(CLOSE_UNPRESSED_URL, CLOSE_PRESSED_URL);
        close.setOnMouseClicked(e -> saveAndExit(stage));
        topThing.getChildren().add(close);
        topThing.setAlignment(close, Pos.TOP_RIGHT);
        topThing.setPickOnBounds(false);
        root.setTop(topThing);
        root.setRight(playerTwoHand);
        root.setBottom(playerThreeHand);
        root.setLeft(playerFourHand);
        root.setCenter(board);
        drawTileArrows(board);
        drawGameBoard(board);
        scene.setRoot(root);
        stage.setScene(scene);
    }

    private void saveAndExit(Stage stage) {
        Save.saveAll();
        stage.setScene(mainScene);
    }

    /**
     * Creates an instance of Game and starts it.
     * @param stage JavaFX Stage of the main window.
     * @param mainScene JavaFX Scene of the main menu.
     */
    public static void launch(Stage stage, Scene mainScene) {
        Game game = new Game();
        init(mainScene);
        game.start(stage);
    }

    /**
     * Initialises the main menu scene into this Game instance so it can be referenced.
     * @param mainMenuScene JavaFX Scene of the main menu.
     */
    private static void init(Scene mainMenuScene) {
        mainScene = mainMenuScene;
    }

    /**
     * Renders the arrows where players are allowed to insert tiles.
     * This is achieved by checking every row and column for fixed tiles.
     * @param board represents the grid of tiles
     */
    public static void drawTileArrows(GridPane board) {
        Controller controller = Controller.getInstance();
        int boardRows = controller.getGameboard().getBoardRows();
        int boardCols = controller.getGameboard().getBoardRows();
        Gameboard gameboard = controller.getGameboard();
        for (int col = 0; col < boardRows; col++) {
            if (!gameboard.rowHasFixedTile(col)) {
                ImageButton arrowUpButton = new ImageButton("resources/images/tiles/arrow up.png");
                ImageButton arrowDownButton = new ImageButton("resources/images/tiles/arrow down.png");
                // temporary final variable otherwise lambda complains
                int finalCol = col;

                // bottom button clicked (this doesn't work)
                arrowUpButton.setOnMouseClicked(e -> {
                    gameboard.addTileToCol(finalCol, "down");
                    drawGameBoard(board);
                });

                // top button clicked (works fine)
                arrowDownButton.setOnMouseClicked(e -> {
                    gameboard.addTileToCol(finalCol, "up");
                    drawGameBoard(board);
                });

                StackPane tileStackOne = new StackPane(arrowDownButton);
                StackPane tileStackTwo = new StackPane(arrowUpButton);
                board.add(tileStackOne, col + 1, 0);
                board.add(tileStackTwo, col + 1, boardRows + 1);
            }
        }
        for (int row = 0; row < boardCols; row++) {
            if (!gameboard.colHasFixedTile(row)) {
                ImageButton arrowRightButton = new ImageButton("resources/images/tiles/arrow right.png");
                ImageButton arrowLeftButton = new ImageButton("resources/images/tiles/arrow left.png");
                int finalRow = row;

                // right button clicked (doesn't work)
                arrowLeftButton.setOnMouseClicked(e -> {
                    gameboard.addTileToRow(finalRow, "right");
                    drawGameBoard(board);
                });

                // left button clicked (works fine)
                arrowRightButton.setOnMouseClicked(e -> {
                    gameboard.addTileToRow(finalRow, "left");
                    drawGameBoard(board);
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
                FloorTile boardStateAtCoords = gameboard.getBoardState()[row - 1][col - 1];
                Image tileImg = boardStateAtCoords.getCurrentImage();
                ImageView imageView = new ImageView(tileImg);
                StackPane tileStack = new StackPane(imageView);
                tileStack.setPickOnBounds(false);
                board.add(tileStack, row, col);
            }
        }
    }

//    /**
//     * Redraws the player's hand with the tiles provided.
//     * @param player which position the players are at the table,
//     *               starting with 1 at the top going clockwise
//     * @param hand the hand of the corresponding player
//     */
//    public static void updatePlayerHand(int player, ArrayList<Tile> hand) {
//        switch (player) {
//            case 1:
//                playerOneHand.getChildren().clear();
//                for (Tile tile : hand) {
//                    playerOneHand.getChildren().add(new ImageView(tile.getCurrentImage()));
//                }
//                break;
//            case 2:
//                playerTwoHand.getChildren().clear();
//                for (Tile tile : hand) {
//                    playerTwoHand.getChildren().add(new ImageView(tile.getCurrentImage()));
//                }
//                break;
//            case 3:
//                playerThreeHand.getChildren().clear();
//                for (Tile tile : hand) {
//                    playerThreeHand.getChildren().add(new ImageView(tile.getCurrentImage()));
//                }
//                break;
//            case 4:
//                playerFourHand.getChildren().clear();
//                for (Tile tile : hand) {
//                    playerFourHand.getChildren().add(new ImageView(tile.getCurrentImage()));
//                }
//                break;
//            default:
//                break;
//        }
//    }
}
