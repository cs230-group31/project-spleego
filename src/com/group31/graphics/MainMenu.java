package com.group31.graphics;

import com.group31.logger.Logger;
import com.group31.services.ApiRequest;
import com.group31.services.PuzzleSolver;
import com.group31.settings.Settings;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author aaron
 */
public class MainMenu extends Application {
    /**
     * Height of the window in pixels.
     */
    private static final double WINDOW_HEIGHT = Settings.getSettingAsDouble("window_height");
    /**
     * Width of the window in pixels.
     */
    private static final double WINDOW_WIDTH = Settings.getSettingAsDouble("window_width");
    /**
     * Space between buttons in pixels.
     */
    private static final double BUTTON_SPACING = Settings.getSettingAsDouble("button_spacing");
    /**
     * File Path for the menu background image.
     */
    private static final String MENU_IMAGE_URL = Settings.get("menu_image_url");
    /**
     * File Path for the title image.
     */
    private static final String TITLE_IMAGE_URL = Settings.get("title_image_url");
    /**
     * Height of the title image in pixels.
     */
    private static final double TITLE_IMAGE_HEIGHT = Settings.getSettingAsDouble("title_image_height");
    /**
     * Width of the window in pixels.
     */
    private static final double TITLE_IMAGE_WIDTH = Settings.getSettingAsDouble("title_image_width");
    /**
     * Name of the font.
     */
    private static final String FONT_FAMILY = Settings.get("font_family");
    /**
     * Size of the font.
     */
    private static final double FONT_SIZE = Settings.getSettingAsDouble("font_size");
    /**
     * Stroke surrounding the font in pixels.
     */
    private static final double FONT_STROKE = Settings.getSettingAsDouble("font_stroke");
    /**
     * Width of the window in pixels.
     */
    private static final double TEXT_WRAPPING_WIDTH = Settings.getSettingAsDouble("text_wrapping_width");
    /**
     * File Path for the unpressed `START` button.
     */
    private static final String START_UNPRESSED_URL = Settings.get("start_button_unpressed_url");
    /**
     * File Path for the pressed `START` button.
     */
    private static final String START_PRESSED_URL = Settings.get("start_button_pressed_url");
    /**
     * File Path for the unpressed `LEADERBOARD` button.
     */
    private static final String LEADERBOARD_UNPRESSED_URL = Settings.get("leaderboard_button_unpressed_url");
    /**
     * File Path for the pressed `LEADERBOARD` button.
     */
    private static final String LEADERBOARD_PRESSED_URL = Settings.get("leaderboard_button_pressed_url");
    /**
     * File Path for the unpressed `HOW TO PLAY` button.
     */
    private static final String HOW_TO_PLAY_UNPRESSED_URL = Settings.get("how_play_button_unpressed");
    /**
     * File Path for the pressed `HOW TO PLAY` button.
     */
    private static final String HOW_TO_PLAY_PRESSED_URL = Settings.get("how_play_button_pressed");
    /**
     * File Path for the unpressed `SETTINGS` button.
     */
    private static final String SETTINGS_UNPRESSED_URL = Settings.get("settings_button_unpressed");
    /**
     * File Path for the pressed `SETTINGS` button.
     */
    private static final String SETTINGS_PRESSED_URL = Settings.get("settings_button_pressed");
    /**
     * File Path for the unpressed `EXIT` button.
     */
    private static final String EXIT_UNPRESSED_URL = Settings.get("exit_button_unpressed");
    /**
     * File Path for the pressed `EXIT` button.
     */
    private static final String EXIT_PRESSED_URL = Settings.get("exit_button_pressed");
    /**
     * URL for MOTD request.
     */
    private static final String MOTD_URL_BASE = Settings.get("api_url_base");
    /**
     * Puzzle route.
     */
    private static final String PUZZLE_ROUTE = Settings.get("puzzle_route");
    /**
     * Message route.
     */
    private static final String MESSAGE_ROUTE = Settings.get("message_route");
    /**
     * Token identifier.
     */
    private static final String TOKEN_IDENTIFIER = Settings.get("token_identifier");

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
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }

        ImageButton start = new ImageButton(START_UNPRESSED_URL, START_PRESSED_URL);
        ImageButton leaderboard = new ImageButton(LEADERBOARD_UNPRESSED_URL, LEADERBOARD_PRESSED_URL);
        ImageButton howToPlay = new ImageButton(HOW_TO_PLAY_UNPRESSED_URL, HOW_TO_PLAY_PRESSED_URL);
        ImageButton settings = new ImageButton(SETTINGS_UNPRESSED_URL, SETTINGS_PRESSED_URL);
        ImageButton exit = new ImageButton(EXIT_UNPRESSED_URL, EXIT_PRESSED_URL);
        exit.setOnMouseClicked(e -> Platform.exit());
        start.setOnMouseClicked(e -> Game.launch(stage, scene));

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
           titleImg = new Image(new FileInputStream(TITLE_IMAGE_URL), TITLE_IMAGE_WIDTH,
                   TITLE_IMAGE_HEIGHT, true, false);
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }

        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(new ImageView(titleImg));

        FlowPane motdBox = new FlowPane();
        ApiRequest request = new ApiRequest(MOTD_URL_BASE, PUZZLE_ROUTE);
        Text motd = new Text(String.format("Fetching response from %s.", MOTD_URL_BASE));
        try {
            String res = request.getResponse();
            String puzzle = PuzzleSolver.solvePuzzle(request.getResponse());
            motd = new Text(new ApiRequest(MOTD_URL_BASE, MESSAGE_ROUTE, puzzle, TOKEN_IDENTIFIER).getResponse());
        } catch (IOException e) {
            Logger.log(String.format("No response from %s.", MOTD_URL_BASE), Logger.Level.ERROR);
            motd = new Text(String.format("No response from %s.", MOTD_URL_BASE));
        }
        motd.setFont(new Font(FONT_FAMILY, FONT_SIZE));
        motd.setFill(Color.WHITE);
        motd.setStroke(Color.DARKRED);
        motd.setStrokeWidth(FONT_STROKE);
        motd.setWrappingWidth(TEXT_WRAPPING_WIDTH);
        motdBox.getChildren().add(motd);

        root.setTop(titleBox);
        root.setLeft(new VBox());
        root.setCenter(buttonBox);
        root.setBottom(motdBox);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the GUI.
     * @param args Runtime Arguments.
     */
    public static void run(final String[] args) {
        launch(args);
    }
}
