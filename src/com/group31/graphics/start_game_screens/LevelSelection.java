package com.group31.graphics.start_game_screens;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.graphics.Game;
import com.group31.graphics.view_controllers.LevelSelectionController;
import com.group31.logger.Logger;
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
        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        Text title = new Text("Level Selection");
        Button start = new Button("Start New Game");
        Button returnMainMenu = new Button("Main Menu");
        Button backToPlayerProfile = new Button("Back");
        VBox gameSaveButtons = new VBox();
        VBox buttonBox = new VBox();
        VBox allButtons = new VBox();
        start.setOnMouseClicked(e -> {
            Game.launch(stage, mainMenu);
            Controller.getInstance().startGame();
        });
        returnMainMenu.setOnMouseClicked(e -> stage.setScene(mainMenu));
        backToPlayerProfile.setOnMouseClicked(e -> stage.setScene(playerSelection));

        buttonBox.getChildren().addAll(start, backToPlayerProfile, returnMainMenu);

        try {
            for (String name : LevelSelectionController.getSavedGamesName()) {
                Button gameSave = new Button(name);
                gameSave.setOnMouseClicked(e -> {
                    LevelSelectionController.loadGame(name);
                    Game.launch(stage, mainMenu);
                    Controller.getInstance().startGame();
                });
                gameSaveButtons.getChildren().add(gameSave);
            }
        } catch (NoSuchDirectory e) {
            Logger.log("Getting game save names threw an error.", Logger.Level.ERROR);
        }

        allButtons.getChildren().addAll(gameSaveButtons, buttonBox);

        root.setTop(title);
        root.setCenter(allButtons);
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
