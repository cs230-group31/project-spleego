package com.group31.graphics.start_game_screens;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.graphics.Game;
import com.group31.graphics.view_controllers.LevelSelectionController;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.player.PlayerProfile;
import com.group31.saveload.Load;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


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
            Controller.getInstance().setPlayers(initPlayers(playerProfiles));
            Controller.getInstance().startGame();
            Game.launch(stage, mainMenu);
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

    private Player[] initPlayers(ArrayList<PlayerProfile> profiles) {
        ArrayList<Player> players = new ArrayList<>();
        String fileName = "default level.txt";
        String delimiter = ",";
        ArrayList<String> playerLocations = new ArrayList<>();
        try {
            playerLocations = (ArrayList<String>) Load.loadNewGameFromFile(fileName).get("playerLocations");
        } catch (FileNotFoundException | NoSuchDirectory e) {
           Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        for (int i = 0; i <= profiles.size() - 1; i++) {
            String[] locationsAsString = playerLocations.get(i).split(delimiter);
            int[] locations = new int[]
                    {Integer.parseInt(locationsAsString[0]), Integer.parseInt(locationsAsString[1])};
            players.add(new Player(profiles.get(i).getName(), locations));
        }
        return players.toArray(new Player[0]);
    }

    /**
     * Launches a new Level selection page scene.
     * @param stage instance of the stage (windows)
     * @param mainMenu instance of the Main Menu scene
     * @param playerSelection instance of the Player Selection scene
     * @param playerProfiles player profiles
     */
    public static void launch(Stage stage, Scene mainMenu, Scene playerSelection, ArrayList<PlayerProfile> playerProfiles) {
        LevelSelection levelSelection = new LevelSelection();
        levelSelection.start(stage, mainMenu, playerSelection, playerProfiles);
    }

}
