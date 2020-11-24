package com.group31.graphics;

import com.group31.logger.Logger;
import com.group31.main.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class MainMenu extends Application {
    /**
     * Height of the window in pixels.
     */
    private static final double WINDOW_HEIGHT = 720.0;
    /**
     * Width of the window in pixels.
     */
    private static final double WINDOW_WIDTH = 1280.0;
    /**
     * Space between buttons in pixels.
     */
    private static final double BUTTON_SPACING = 15.0;
    /**
     * File Path for the menu background image.
     */
    private static final String MENU_IMAGE_URL = "resources/images/background 1280 x 720.png";
    /**
     * File Path for the title image.
     */
    private static final String TITLE_IMAGE_URL = "resources/images/title.png";
    /**
     * File Path for the unpressed `START` button.
     */
    private static final String START_UNPRESSED_URL = "resources/images/start unpressed.png";
    /**
     * File Path for the pressed `START` button.
     */
    private static final String START_PRESSED_URL = "resources/images/start pressed.png";
    /**
     * File Path for the unpressed `LEADERBOARD` button.
     */
    private static final String LEADERBOARD_UNPRESSED_URL = "resources/images/leaderboard unpressed.png";
    /**
     * File Path for the pressed `LEADERBOARD` button.
     */
    private static final String LEADERBOARD_PRESSED_URL = "resources/images/leaderboard pressed.png";
    /**
     * File Path for the unpressed `HOW TO PLAY` button.
     */
    private static final String HOW_TO_PLAY_UNPRESSED_URL = "resources/images/how to play unpressed.png";
    /**
     * File Path for the pressed `HOW TO PLAY` button.
     */
    private static final String HOW_TO_PLAY_PRESSED_URL = "resources/images/how to play pressed.png";
    /**
     * File Path for the unpressed `SETTINGS` button.
     */
    private static final String SETTINGS_UNPRESSED_URL = "resources/images/settings unpressed.png";
    /**
     * File Path for the pressed `SETTINGS` button.
     */
    private static final String SETTINGS_PRESSED_URL = "resources/images/settings pressed.png";
    /**
     * File Path for the unpressed `EXIT` button.
     */
    private static final String EXIT_UNPRESSED_URL = "resources/images/exit unpressed.png";
    /**
     * File Path for the pressed `EXIT` button.
     */
    private static final String EXIT_PRESSED_URL = "resources/images/exit pressed.png";

    /**
     * Takes the main stage and displays a background with buttons.
     * @param stage JavaFX Stage of the main window.
     */
    public void start(final Stage stage) {
        stage.setHeight(WINDOW_HEIGHT);
        stage.setWidth(WINDOW_WIDTH);
        stage.setMaxHeight(WINDOW_HEIGHT);
        stage.setMaxWidth(WINDOW_WIDTH);
        stage.setTitle("Spooky Spleego");

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();

        try {
            Image menuImg = new Image(new FileInputStream(MENU_IMAGE_URL));
            BackgroundImage bg = new BackgroundImage(menuImg,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            root.setBackground(new Background(bg));
        } catch (FileNotFoundException e) {
            Logger.log(e.toString(), Logger.Level.ERROR);
        }

        ImageButton start = new ImageButton(START_UNPRESSED_URL, START_PRESSED_URL);
        ImageButton leaderboard = new ImageButton(LEADERBOARD_UNPRESSED_URL, LEADERBOARD_PRESSED_URL);
        ImageButton howToPlay = new ImageButton(HOW_TO_PLAY_UNPRESSED_URL, HOW_TO_PLAY_PRESSED_URL);
        ImageButton settings = new ImageButton(SETTINGS_UNPRESSED_URL, SETTINGS_PRESSED_URL);
        ImageButton exit = new ImageButton(EXIT_UNPRESSED_URL, EXIT_PRESSED_URL);
        exit.setOnMouseClicked(e -> Platform.exit());
        start.setOnMouseClicked(e -> Game.launch(stage));

        VBox buttonBox = new VBox();
        buttonBox.getChildren().add(start);
        buttonBox.getChildren().add(leaderboard);
        buttonBox.getChildren().add(howToPlay);
        buttonBox.getChildren().add(settings);
        buttonBox.getChildren().add(exit);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(BUTTON_SPACING);

        Image titleImg = null;
        try {
           titleImg = new Image(new FileInputStream(TITLE_IMAGE_URL), 315.0, 210.0, true, false);
        } catch (FileNotFoundException e) {
            Logger.log(e.toString(), Logger.Level.ERROR);
        }

        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(new ImageView(titleImg));
        Text motd = new Text(Main.getMotd("http://cswebcat.swansea.ac.uk/", "puzzle", "message",
                "?solution="));
        motd.setFont(new Font("Comic Sans MS Bold", 20.0));
        motd.setFill(Color.WHITE);
        motd.setStroke(Color.DARKRED);
        motd.setStrokeWidth(1);
        motd.setWrappingWidth(600.0);
        titleBox.getChildren().add(motd);

        root.setTop(titleBox);
        root.setLeft(new VBox());
        root.setCenter(buttonBox);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }
    ///**
     //* Updates the size of the window.
    //*@param stage JavaFX Stage of the main window.
     //*/
    /*
    public void updateRes(final Stage stage) {
        stage.setHeight();
    }
    */

    /**
     * Launches the GUI.
     * @param args Runtime Arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
