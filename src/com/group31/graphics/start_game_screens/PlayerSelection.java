package com.group31.graphics.start_game_screens;

import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.player.PlayerProfile;
import com.group31.saveload.Load;
import com.group31.services.serializer.Serializer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.scene.control.Label;

/**
 * @author Kevin, Liam, Aaron
 */
public class PlayerSelection {

    /**
     * List of ComboBoxes to update.
     */
    private static final ArrayList<ComboBox<String>> COMBO_BOXES = new ArrayList<>();
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


        ObservableList<Integer> playerCountList = FXCollections.observableArrayList();
        playerCountList.addAll(TWO, THREE, FOUR);
        Button setPlayerCount = new Button("Confirm count");
        ComboBox<Integer> numOfPlayers = new ComboBox<>(playerCountList);
        numOfPlayers.setItems(playerCountList);
        HBox playerBox = new HBox();
        setPlayerCount.setOnMouseClicked(e -> {
            updatePlayerSelection(numOfPlayers.getValue(), mainPane, playerBox);
            setPlayerCount.setDisable(true);
        });

        numOfPlayers.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                setPlayerCount.setDisable(false);
            }
        });

        createPlayer.setOnMouseClicked(e -> {
            // Serialized
            new PlayerProfile(newPlayerName.getText()).save();
            newPlayerName.clear();
            setPlayerCount.setDisable(false);
        });

        newPlayerName.setPromptText("Enter name:");

        selectLevel.setOnMouseClicked(e -> LevelSelection.launch(stage, mainMenu, scene, getPlayerProfiles()));
        returnMainMenu.setOnMouseClicked(e -> stage.setScene(mainMenu));

        mainPane.getChildren().addAll(returnMainMenu, playerNameBox, numOfPlayers, setPlayerCount);
        HBox levelSelectBox = new HBox();
        levelSelectBox.getChildren().add(selectLevel);
        root.setTop(tutorial);
        root.setCenter(mainPane);
        root.setBottom(levelSelectBox);
        scene.setRoot(root);
        stage.setScene(scene);

    }

    /**
     * Returns a list of players in the game.
     * @return a list of players in the game
     */
    private static ArrayList<PlayerProfile> getPlayerProfiles() {
        ArrayList<PlayerProfile> playersInGame = new ArrayList<>();
        String object = "player";
        PlayerProfile profile = null;
        for (ComboBox<String> comboBox : COMBO_BOXES) {
            String name = String.format("PlayerProfile_%s", comboBox.getValue());
            try {
                profile = (PlayerProfile) Serializer.deserialize(name, object);
            } catch (ObjectNeverSerialized e) {
                Logger.log(e.getMessage(), Logger.Level.ERROR);
            }
            if (profile != null) {
                playersInGame.add(profile);
                Serializer.serialize(profile, name, object);
            }
        }
        return playersInGame;
    }

    /**
     * Updates dropdown menus according to number of players.
     * @param numPlayers number of players
     * @param mainPane the main pane
     * @param playerBox the HBox for dropdown menus
     */
    public static void updatePlayerSelection(int numPlayers, FlowPane mainPane, HBox playerBox) {
        COMBO_BOXES.clear();
        mainPane.getChildren().remove(playerBox);
        playerBox.getChildren().clear();
        ArrayList<PlayerProfile> players = Load.getPlayerProfiles();
        ArrayList<String> playerNames = new ArrayList<>();
        for (PlayerProfile player : players) {
            playerNames.add(player.getName());
        }
        for (int i = 0; i < numPlayers; i++) {
            Label playerLabel = new Label("Player " + (i + 1) + ":");
            playerBox.getChildren().add(playerLabel);
            ComboBox<String> nameDropDown = new ComboBox<>(FXCollections.observableArrayList(playerNames));
            COMBO_BOXES.add(nameDropDown);
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
