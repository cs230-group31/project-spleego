package com.group31.graphics.start_game_screens;

import com.group31.controller.Controller;
import com.group31.graphics.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LevelSelection {

    /**
     * Starts the scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     * @param playerSelection instance of the Player Selection scene
     */
    private void start(Stage stage, Scene mainMenu, Scene playerSelection) {
        Controller controller = Controller.getInstance();
        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        Text tutorial = new Text("Level Selection");
        Button start = new Button("Start");
        Button returnMainMenu = new Button("Main Menu");
        Button backToPlayerProfile = new Button("Back");
        VBox buttonBox = new VBox();

        start.setOnMouseClicked(e -> {
            Game.launch(stage, mainMenu);
            controller.startGame();
        });
        returnMainMenu.setOnMouseClicked(e -> stage.setScene(mainMenu));
        backToPlayerProfile.setOnMouseClicked(e -> stage.setScene(playerSelection));

        buttonBox.getChildren().addAll(start, backToPlayerProfile, returnMainMenu);

        root.setTop(tutorial);
        root.setCenter(buttonBox);
        scene.setRoot(root);
        stage.setScene(scene);

    }

    /**
     * Launches a new Level selection page scene.
     * @param stage instance of the stage (windows)
     * @param mainMenu instance of the Main Menu scene
     * @param playerSelection instance of the Player Selection scene
     */
    public static void launch(Stage stage, Scene mainMenu, Scene playerSelection) {
        LevelSelection levelSelection = new LevelSelection();
        levelSelection.start(stage, mainMenu, playerSelection);
    }
}
