package com.group31.graphics.start_game_screens;

import com.group31.controller.Controller;
import com.group31.player.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.graphics.Game;
import com.group31.logger.Logger;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import com.group31.player.Player;
import java.io.File;
import java.util.ArrayList;
import com.group31.services.FileManager;
import com.group31.settings.Settings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Label;

import java.util.ArrayList;

import java.util.ArrayList;


public class PlayerSelection {

    ArrayList<Player> players = new ArrayList<>();

    //test array
    ArrayList<String> playerTest = new ArrayList<>();

    /**
     * Player's starting locations.
     */
    // TESTING ONLY
    private final int[] location = new int[] {5, 5};

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
        //Button selectLevel = new Button("Select Level");
        Button returnMainMenu = new Button("Main Menu");
        Button play = new Button("Play");
        VBox buttonBox = new VBox();



        Controller controller = Controller.getInstance();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Name1", null, null, this.location));
        players.add(new Player("Name2", null, null, this.location));

        controller.setPlayers(players.toArray(new Player[0]));

        selectLevel.setOnMouseClicked(e -> LevelSelection.launch(stage, mainMenu, scene));

        returnMainMenu.setOnMouseClicked(e -> stage.setScene(mainMenu));

        //buttonBox.getChildren().addAll(selectLevel, returnMainMenu);
        buttonBox.getChildren().addAll(play, returnMainMenu, chooselabel, hbox);

        root.setTop(tutorial);
        root.setCenter(buttonBox);
        scene.setRoot(root);
        stage.setScene(scene);

    }

    public static ArrayList<String> getPlayerNames() throws NoSuchDirectory {

        ArrayList<String> playerNames = new ArrayList<>();
        String directory = String.format("%scontroller", Settings.get("serialized_objects_folder"));
        FileManager.setDirectory(directory, true);
        File[] playerFiles = FileManager.getAllFilesInDir();
        for (File file : playerFiles) {
            String rawFileName = file.getName().replaceFirst("[.][^.]+$", "");
            playerNames.add(rawFileName);
        }
        return playerNames;
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
