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

import java.lang.reflect.Array;
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
    private static ArrayList<String> playerTest = new ArrayList<>();

    /**
     * List of comboboxes to update.
     */
    private static ArrayList<ComboBox<String>> comboBoxes = new ArrayList<>();
    /**
     * One.
     */
    private static final int ONE = 1;
    /**
     * Two.
     */
    private static final int TWO = 2;
    /**
     * Three.
     */
    private static final int THREE = 3;
    /**
     * Four.
     */
    private static final int FOUR = 4;

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
        Button selectLevel = new Button("Select Level");
        Button returnMainMenu = new Button("Main Menu");

        HBox playerNameBox = new HBox();
        Label createPlayerLabel = new Label("Create a player");
        Button createPlayer = new Button("Create player");
        TextField newPlayerName = new TextField();
        newPlayerName.setPromptText("Enter the name of the new player: ");
        playerNameBox.getChildren().addAll(createPlayerLabel, newPlayerName, createPlayer);
        createPlayer.setOnMouseClicked(e -> {
            new PlayerProfile(newPlayerName.getText()).save();
            newPlayerName.clear();
        });


        ObservableList<Integer> playerCountList = FXCollections.observableArrayList();
        playerCountList.addAll(ONE, TWO, THREE, FOUR);
        Button setPlayerCount = new Button("Confirm count");
        ComboBox<Integer> numOfPlayers = new ComboBox<>(playerCountList);
        numOfPlayers.setItems(playerCountList);
        HBox playerBox = new HBox();
        ArrayList<PlayerProfile> players = Load.getPlayerProfiles();
        ArrayList<String> playerNames = new ArrayList<>();
        for (PlayerProfile player : players) {
            playerNames.add(player.getName());
        }
        setPlayerCount.setOnMouseClicked(e -> updatePlayerSelection(numOfPlayers.getValue(),
                mainPane, playerBox, playerNames));




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
     * Updates dropdown menus according to number of players.
     * @param numPlayers number of players
     * @param mainPane the main pane
     * @param playerBox the HBox for dropdown menus
     * @param playerNames arraylist of player names
     */
    public static void updatePlayerSelection(int numPlayers, FlowPane mainPane,
                                             HBox playerBox, ArrayList<String> playerNames) {
        mainPane.getChildren().remove(playerBox);
        playerBox.getChildren().clear();
        for (int i = 0; i < numPlayers; i++) {
            Label playerLabel = new Label("Player " + (i + 1) + ":");
            playerBox.getChildren().add(playerLabel);
            ComboBox<String> nameDropDown = new ComboBox<>(FXCollections.observableArrayList(playerNames));
            comboBoxes.add(nameDropDown);
            playerBox.getChildren().add(nameDropDown);
        }
        mainPane.getChildren().add(playerBox);
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
