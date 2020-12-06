package com.group31.graphics.start_game_screens;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.saveload.Load;
import com.group31.services.FileManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class PlayerSelection {
    /**
     * Where the sprites be.
     */
    private final String spritesUrl = "resources/images/sprites/";

    /**
     * Player's starting locations.
     */
    // TESTING ONLY
    private final int[] location = new int[] {5, 5};

    /**
     * Starts the scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     */
    private void start(Stage stage, Scene mainMenu) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        Text tutorial = new Text("Player Selection");
        Button selectLevel = new Button("Select Level");
        Button returnMainMenu = new Button("Main Menu");
        VBox buttonBox = new VBox();

        Controller controller = Controller.getInstance();
        Image sprite1 = null;
        try {
            FileManager.setDirectory(spritesUrl, false);
            sprite1 = FileManager.readImage("blue", "png");
        } catch (NoSuchDirectory noSuchDirectory) {
            noSuchDirectory.printStackTrace();
        } catch (IOException e) {
            Logger.log(e.toString(), Logger.Level.ERROR);
        }
        Image sprite2 = null;
        try {
            FileManager.setDirectory(spritesUrl, false);
            sprite2 = FileManager.readImage("green", "png");
        } catch (NoSuchDirectory noSuchDirectory) {
            noSuchDirectory.printStackTrace();
        } catch (IOException e) {
            Logger.log(e.toString(), Logger.Level.ERROR);
        }
        HashMap<String, Object> components = null;
        try {
            components = Load.loadNewGameFromFile("default level.txt");
        } catch (NoSuchDirectory noSuchDirectory) {
            noSuchDirectory.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //TODO: make this better
        ArrayList<String> playerLocations = (ArrayList<String>) components.get("playerLocations");
        Player[] players = new Player[4];
        for (int i = 0; i < playerLocations.size(); i++) {
            String startingLocations = playerLocations.get(i);
            String[] coordinates = startingLocations.split(",");
            int playerX = Integer.parseInt(coordinates[0]) + 1;
            int playerY = Integer.parseInt(coordinates[1]) + 1;
            players[i] = new Player("Name" + i, sprite1, null, new int[]{playerX, playerY});
        }
        controller.setPlayers(players);

        selectLevel.setOnMouseClicked(e -> LevelSelection.launch(stage, mainMenu, scene));
        returnMainMenu.setOnMouseClicked(e -> stage.setScene(mainMenu));

        buttonBox.getChildren().addAll(selectLevel, returnMainMenu);

        root.setTop(tutorial);
        root.setCenter(buttonBox);
        scene.setRoot(root);
        stage.setScene(scene);

    }

    /**
     * Launches a new Player selection page scene.
     * @param stage instance of the stage (windows)
     * @param mainMenu instance of the Main Menu scene
     */
    public static void launch(Stage stage, Scene mainMenu) {
        PlayerSelection playerSelection = new PlayerSelection();
        playerSelection.start(stage, mainMenu);
    }

}
