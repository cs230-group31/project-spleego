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

import java.util.ArrayList;


public class PlayerSelection {

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
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Name1", null, null, this.location));
        players.add(new Player("Name2", null, null, this.location));
        controller.setPlayers(players.toArray(new Player[0]));

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
