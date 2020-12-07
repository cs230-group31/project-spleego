package com.group31.graphics.start_game_screens;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.gameboard.Gameboard;
import com.group31.graphics.Game;
import com.group31.graphics.view_controllers.LevelSelectionController;
import com.group31.logger.Logger;
import com.group31.player.PlayerProfile;
import com.group31.saveload.Load;
import com.group31.tile_manager.silk_bag.SilkBag;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author liam, Aaron
 */
public class LevelSelection {

    /**
     * Starts the scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     * @param playerSelection instance of the Player Selection scene
     * @param playerProfiles player profiles
     */
    private void start(Stage stage, Scene mainMenu, Scene playerSelection, ArrayList<PlayerProfile> playerProfiles) {
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
            String fileName = "default level.txt";
            Controller.getInstance().setPlayers(LevelSelectionController.initPlayers(playerProfiles, fileName));
            Controller.getInstance().startGame();
            Game.launch(stage, mainMenu, playerProfiles);
        });
        returnMainMenu.setOnMouseClicked(e -> stage.setScene(mainMenu));
        backToPlayerProfile.setOnMouseClicked(e -> stage.setScene(playerSelection));

        buttonBox.getChildren().addAll(start, backToPlayerProfile, returnMainMenu);

        for (String gameSaveName : LevelSelectionController.getGamesWithPlayerParticipation(playerProfiles)) {
            Button gameSave = new Button(gameSaveName);
            gameSave.setOnMouseClicked(e -> {
                LevelSelectionController.loadGame(gameSaveName);
                Controller.getInstance().startGame();
                Game.launch(stage, mainMenu, playerProfiles);
            });
            gameSaveButtons.getChildren().add(gameSave);
        }

        for (String defaultLevelName: LevelSelectionController.getPredefinedLevelNames()) {
            Button defaultLevelButton = new Button(defaultLevelName);
            defaultLevelButton.setOnMouseClicked(e -> {
                try {
                    String fileName = String.format("%s.txt", defaultLevelName);
                    HashMap<String, Object> components = Load.loadNewGameFromFile(fileName);
                    Gameboard gameboard = (Gameboard) components.get("Gameboard");
                    SilkBag silkbag = (SilkBag) components.get("SilkBag");
                    Controller controller = Controller.getInstance();
                    controller.init(gameboard, silkbag);
                    Controller.getInstance().setPlayers(LevelSelectionController.initPlayers(playerProfiles, fileName));
                    Controller.getInstance().startGame();
                    Game.launch(stage, mainMenu, playerProfiles);
                } catch (NoSuchDirectory | FileNotFoundException ex) {
                    Logger.log(ex.getMessage(), Logger.Level.ERROR);
                }
            });
            gameSaveButtons.getChildren().add(defaultLevelButton);
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
     * @param playerProfiles player profiles
     */
    public static void launch(Stage stage, Scene mainMenu, Scene playerSelection,
                              ArrayList<PlayerProfile> playerProfiles) {
        LevelSelection levelSelection = new LevelSelection();
        levelSelection.start(stage, mainMenu, playerSelection, playerProfiles);
    }

}
