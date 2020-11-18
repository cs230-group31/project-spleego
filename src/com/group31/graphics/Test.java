package com.group31.graphics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test extends Application {
    private static final double WINDOW_HEIGHT = 720.0;
    private static final double WINDOW_WIDTH = 1280.0;
    private static final double BUTTON_SPACING = 25.0;
    private static final String MENU_IMAGE_URL = "resources/images/main menu background.png";
    private static final String SETTINGS_UNPRESSED_URL = "resources/images/STOLEN settings unpressed.png";
    private static final String SETTINGS_PRESSED_URL = "resources/images/STOLEN settings pressed.png";
    private static final String EXIT_UNPRESSED_URL = "resources/images/STOLEN exit unpressed.png";
    private static final String EXIT_PRESSED_URL = "resources/images/STOLEN exit pressed.png";

    public void start(Stage stage) {
        stage.setHeight(WINDOW_HEIGHT);
        stage.setWidth(WINDOW_WIDTH);
        stage.setMaxHeight(WINDOW_HEIGHT);
        stage.setMaxWidth(WINDOW_WIDTH);
        stage.setTitle("Spooky Spleego");

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();

        try {
            Image menuImg = new Image(new FileInputStream(MENU_IMAGE_URL));
            BackgroundImage bg = new BackgroundImage(menuImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            root.setBackground(new Background(bg));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageButton settings = new ImageButton(SETTINGS_UNPRESSED_URL, SETTINGS_PRESSED_URL);
        ImageButton exit = new ImageButton(EXIT_UNPRESSED_URL, EXIT_PRESSED_URL);
        exit.setOnMouseClicked(e -> Platform.exit());

        VBox buttonBox = new VBox();
        buttonBox.getChildren().add(settings);
        buttonBox.getChildren().add(exit);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(BUTTON_SPACING);

        root.setLeft(new VBox());
        root.setCenter(buttonBox);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
