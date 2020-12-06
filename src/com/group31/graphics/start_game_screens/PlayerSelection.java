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

        /*
        Controller controller = Controller.getInstance();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Name1", null, null, this.location));
        players.add(new Player("Name2", null, null, this.location));
        controller.setPlayers(players.toArray(new Player[0]));*/

        Label chooselabel =
                new Label("Choose number of players:");

        Label namelabel =
                new Label("Enter name:");

        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("2 players");
        comboBox.getItems().add("3 players");
        comboBox.getItems().add("4 players");

        playerTest.add("playerName1");
        playerTest.add("playerName2");
        playerTest.add("playerName3");
        playerTest.add("playerName4");

        HBox hbox = new HBox(comboBox);
        Controller controller = Controller.getInstance();

        play.setOnMouseClicked(e -> {
            Game.launch(stage, mainMenu);
            controller.startGame();
        });

        final TextField text1 = new TextField();
        final TextField text2 = new TextField();
        final TextField text3 = new TextField();

        text1.setPromptText("Enter name:");
        text2.setPromptText("Enter name:");
        text3.setPromptText("Enter name:");

        BooleanBinding booleanBind = text1.textProperty().isEmpty()
                .or(text2.textProperty().isEmpty());
        //.or(text3.textProperty().isEmpty());

        play.disableProperty().bind(booleanBind);

        EventHandler<ActionEvent> event = e -> {
            if (comboBox.getValue() == "2 players"){
                //make method to remove combo boxes

                ComboBox comboBox2 = new ComboBox();
                ComboBox comboBox3 = new ComboBox();

                for (int i = 0; i < playerTest.size(); i++) {
                    comboBox2.getItems().add(playerTest.get(i));
                    comboBox3.getItems().add(playerTest.get(i));
                }

                EventHandler<ActionEvent> event2 = e2 -> {
                    comboBox3.getItems().remove(comboBox2.getValue());

                    if (comboBox3.getValue() != null && comboBox3.getItems().contains(comboBox2.getValue())){
                        comboBox3.getItems().add(comboBox3.getValue());
                    }
                    text1.setText(String.valueOf(comboBox2.getValue()));
                    text2.setText(String.valueOf(comboBox3.getValue()));


                    //else{
                    //    comboBox2.getItems().add(comboBox2.getValue());
                    //    comboBox2.getItems().remove(comboBox3.getValue());
                    //}
                    //if (comboBox2.getValue() != null && !comboBox2.getItems().contains(comboBox3.getValue())){
                    //    comboBox2.getItems().add(comboBox2.getValue());
                    //}
                    //});
                    //playerTest.remove(comboBox2.getValue());
                    //text1.setText(String.valueOf(comboBox2.getValue()));
                    //text2.setText(String.valueOf(comboBox3.getValue()));
                    //if(comboBox2.getValue() == comboBox3.getValue()){
                    //    comboBox3.getItems().remove(comboBox2.getValue());
                    //}
                };

                comboBox2.setOnAction(event2);
                comboBox3.setOnAction(event2);

                //for (int i = 0; i < 2; i++) {
                    /*try {
                        for (String name : getPlayerNames()) {
                            comboBox2.getItems().addAll(
                                    name
                            );
                            comboBox3.getItems().addAll(
                                    name
                            );
                            EventHandler<ActionEvent> event2 = e2 -> {
                                controller.addPlayers(players);
                            };
                            comboBox2.setOnAction(event2);
                            comboBox3.setOnAction(event2);
                        }
                    } catch (NoSuchDirectory error) {
                        Logger.log("Player names error.", Logger.Level.ERROR);
                    }*/
                HBox hbox2 = new HBox(comboBox2);
                HBox hbox3 = new HBox(comboBox3);
                buttonBox.getChildren().addAll(hbox2, hbox3);

                play.setOnMouseClicked(e3 -> {
                            if ((comboBox2.getValue() != text1.getText() && playerTest.contains(text1.getText())) || (comboBox3.getValue() != text2.getText() && playerTest.contains(text2.getText()))){
                                Logger.log("Name already exists", Logger.Level.ERROR);
                                play.disableProperty();
                            }
                            if(!playerTest.contains(text1.getText())){
                                playerTest.add(text1.getText());
                                Logger.log("New player 1 added", Logger.Level.INFO);
                            }
                            if(!playerTest.contains(text2.getText())){
                                playerTest.add(text2.getText());
                                Logger.log("New player 2 added", Logger.Level.INFO);
                            }

                            Game.launch(stage, mainMenu);
                            controller.startGame();

                        }
                );
            }
            else if (comboBox.getValue() == "3 players"){
                ComboBox comboBox2 = new ComboBox();
                ComboBox comboBox3 = new ComboBox();
                ComboBox comboBox4 = new ComboBox();
                try {
                    for (String name : getPlayerNames()) {
                        comboBox2.getItems().addAll(
                                name
                        );
                        comboBox3.getItems().addAll(
                                name
                        );
                        comboBox4.getItems().addAll(
                                name
                        );
                        EventHandler<ActionEvent> event2 = e2 -> {
                            controller.addPlayers(players);
                        };
                        comboBox2.setOnAction(event2);
                        comboBox3.setOnAction(event2);
                        comboBox4.setOnAction(event2);
                    }
                } catch (NoSuchDirectory error) {
                    Logger.log("Player names error.", Logger.Level.ERROR);
                }
                HBox hbox2 = new HBox(comboBox2);
                HBox hbox3 = new HBox(comboBox3);
                HBox hbox4 = new HBox(comboBox4);
                buttonBox.getChildren().addAll(hbox2, hbox3, hbox4);
            }

            else if (comboBox.getValue() == "4 players"){
                ComboBox comboBox2 = new ComboBox();
                ComboBox comboBox3 = new ComboBox();
                ComboBox comboBox4 = new ComboBox();
                ComboBox comboBox5 = new ComboBox();
                try {
                    for (String name : getPlayerNames()) {
                        comboBox2.getItems().addAll(
                                name
                        );
                        comboBox3.getItems().addAll(
                                name
                        );
                        comboBox4.getItems().addAll(
                                name
                        );
                        comboBox5.getItems().addAll(
                                name
                        );
                        EventHandler<ActionEvent> event2 = e2 -> {
                            controller.addPlayers(players);
                        };
                        comboBox2.setOnAction(event2);
                        comboBox3.setOnAction(event2);
                        comboBox4.setOnAction(event2);
                        comboBox5.setOnAction(event2);
                    }
                } catch (NoSuchDirectory error) {
                    Logger.log("Player names error.", Logger.Level.ERROR);
                }
                HBox hbox2 = new HBox(comboBox2);
                HBox hbox3 = new HBox(comboBox3);
                HBox hbox4 = new HBox(comboBox4);
                HBox hbox5 = new HBox(comboBox5);
                buttonBox.getChildren().addAll(hbox2, hbox3, hbox4, hbox5);
            }
            buttonBox.getChildren().addAll(text1, text2);
        };
        comboBox.setOnAction(event);

        //selectLevel.setOnMouseClicked(e -> LevelSelection.launch(stage, mainMenu, scene));
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
