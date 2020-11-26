package com.group31.graphics;

import com.group31.logger.Logger;
<<<<<<< HEAD
=======
import com.group31.tileManager.Tile;
>>>>>>> origin/merge-master
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game extends Application {
    /**
     * Space between tiles in pixels.
     */
    private static final double TILE_SPACING = 5.0;
    /**
     * File Path for the table background image.
     */
    private static final String TABLE_IMAGE_URL = "resources/images/table.png";
    /**
     * Creates the board.
     * @param stage JavaFX Stage of the main window.
     */
    public void start(Stage stage) {
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
<<<<<<< HEAD
            Logger.log(e.getMessage(), Logger.Level.ERROR);
=======
            Logger.log(e.toString(), Logger.Level.ERROR);
>>>>>>> origin/merge-master
        }
        board.setAlignment(Pos.CENTER);
        board.setHgap(TILE_SPACING);
        board.setVgap(TILE_SPACING);
        root.setLeft(new VBox());
        root.setTop(new HBox());
        root.setRight(new VBox());
        root.setBottom(new HBox());
        root.setCenter(board);
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
}
