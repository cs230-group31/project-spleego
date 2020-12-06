package com.group31.graphics.start_game_screens;

import com.group31.controller.Controller;
import com.group31.player.PlayerProfile;
import com.group31.saveload.Load;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.group31.graphics.Game;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.scene.control.Label;


public class PlayerSelection {

    /**
     * list of players.
     */
    private ArrayList<PlayerProfile> players = new ArrayList<>();

    /**
     * test array.
     */
    private ArrayList<String> playerTest = new ArrayList<>();

    /**
     * Starts the scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     */
    private void start(Stage stage, Scene mainMenu) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        Text tutorial = new Text("Player Selection");
        FlowPane mainPane = new FlowPane(Orientation.VERTICAL);
        //Button selectLevel = new Button("Select Level");
        Button returnMainMenu = new Button("Main Menu");

        HBox playerNameBox = new HBox();
        Label createPlayerLabel = new Label("Create a player");
        Button createPlayer = new Button("Create player");
        TextField newPlayerName = new TextField();
        newPlayerName.setPromptText("Enter the name of the new player: ");
        playerNameBox.getChildren().addAll(createPlayerLabel, newPlayerName, createPlayer);
        createPlayer.setOnMouseClicked(e -> new PlayerProfile(newPlayerName.getText()).save());


        ObservableList<Integer> playerCountList = FXCollections.observableArrayList();
        playerCountList.addAll(1, 2 , 3, 4);
        Button setPlayerCount = new Button("Confirm count");
        ComboBox<Integer> numOfPlayers = new ComboBox<>(playerCountList);
        numOfPlayers.setItems(playerCountList);
        setPlayerCount.setOnMouseClicked(e -> updatePlayerSelection(numOfPlayers.getValue(), mainPane));

        ArrayList<String> playerNamesAsString = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            playerNamesAsString.add(players.get(i).getName());
        }
        ComboBox<String> playerNamesToSelect = new ComboBox<>(FXCollections.observableArrayList(playerNamesAsString));


        Label chooseLabel = new Label("Choose number of players:");
        Button play = new Button("Play");

        playerTest.add("playerName1");
        playerTest.add("playerName2");
        playerTest.add("playerName3");
        playerTest.add("playerName4");

        HBox numPlayersBox = new HBox(numOfPlayers);
        Controller controller = Controller.getInstance();

        play.setOnMouseClicked(e -> {
            Game.launch(stage, mainMenu);
            controller.startGame();
        });

        newPlayerName.setPromptText("Enter name:");

        //selectLevel.setOnMouseClicked(e -> LevelSelection.launch(stage, mainMenu, scene));
        returnMainMenu.setOnMouseClicked(e -> stage.setScene(mainMenu));

        //buttonBox.getChildren().addAll(selectLevel, returnMainMenu);
        mainPane.getChildren().addAll(returnMainMenu, playerNameBox, numOfPlayers, setPlayerCount);

        root.setTop(tutorial);
        root.setCenter(mainPane);
        scene.setRoot(root);
        stage.setScene(scene);

    }

    /**
     *
     * @param numPlayers
     * @param mainPane
     */
    public static void updatePlayerSelection(int numPlayers, FlowPane mainPane) {
        ArrayList<String> playerNames = Load.getPlayerProfileNames();
        for (int i = 0; i < numPlayers; i++) {
            HBox playerBox = new HBox();
            Label playerLabel = new Label("Player " + (i + 1) + ":");
            playerBox.getChildren().add(playerLabel);
            ComboBox<String> nameDropDown = new ComboBox<>(FXCollections.observableArrayList(playerNames));
            playerBox.getChildren().add(nameDropDown);
            mainPane.getChildren().add(playerBox);
        }
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
