package com.group31.graphics;

import java.util.ArrayList;
import com.group31.tile_manager.Tile;
import com.group31.controller.Controller;
import com.group31.gameboard.Gameboard;
import com.group31.logger.Logger;
import com.group31.settings.Settings;
import com.group31.tile_manager.FloorTile;
import javafx.application.Application;
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
     * Player numbers enum.
     */
    public enum Player {
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
     * Creates the board.
     * @param stage JavaFX Stage of the main window.
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
        root.setTop(playerOneHand);
        root.setRight(playerTwoHand);
        root.setBottom(playerThreeHand);
        root.setLeft(playerFourHand);
        root.setCenter(board);
        drawGameBoard();
        scene.setRoot(root);
        stage.setScene(scene);
    }

    /**
     * Creates an instance of Game and starts it.
     * @param stage JavaFX Stage of the main window.
     */
    public static void launch(Stage stage) {
        Game game = new Game();
        game.start(stage);
    }

    /**
     * Retrieves the Controller as a static reference and then operates on the gameboard within.
     */
    public static void drawGameBoard() {
        Controller controller = Controller.getInstance();
        int boardRows = controller.getGameboard().getBoardRows();
        int boardCols = controller.getGameboard().getBoardRows();
        for (int row = 1; row <=  boardRows; row++) {
            for (int column = 1; column <= boardCols; column++) {
                Gameboard gameboard = controller.getGameboard();
                FloorTile boardStateAtCoords = gameboard.getBoardState()[row - 1][column - 1];
                Image tileImg  = boardStateAtCoords.getCurrentImage();
                ImageView imageView = new ImageView(tileImg);
                StackPane layout = new StackPane(imageView);
                board.add(layout, row, column);
            }
        }
    }

    /**
     * Redraws the player's hand with the tiles provided.
     * @param player which position the players are at the table,
     *               starting with 1 at the top going clockwise
     * @param hand the hand of the corresponding player
     */
    public static void updatePlayerHand(Player player, ArrayList<Tile> hand) {
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
