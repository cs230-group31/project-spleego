package com.group31.graphics;

import com.group31.leaderboard.Leaderboard;
import com.group31.player.Player;
import com.group31.player.PlayerProfile;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeaderboardScreen {

    /**
     * Inset sizes for the bottom and right offsets.
     */
    private final int bottomAndRightInset = 0;

    /**
     * Inset sizes for the top and left offsets.
     */
    private final int topAndLeftInset = 10;

    /**
     * Starts the scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     */
    private void start(Stage stage, Scene mainMenu) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();

        TableView table = new TableView();
        Label title = new Label("Leaderboard");
        ObservableList<PlayerProfile> playerData = Leaderboard.getLeaderboardData();
        Button button = new Button("Return");
        VBox leaderboardBox = new VBox();

        // Create columns and associate them with the fields from Player.
        TableColumn nameCol = new TableColumn("Player Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn winsCol = new TableColumn("Wins");
        winsCol.setCellValueFactory(new PropertyValueFactory<>("wins"));
        TableColumn lossesCol = new TableColumn("Losses");
        lossesCol.setCellValueFactory(new PropertyValueFactory<>("losses"));
        TableColumn gamesPlayedCol = new TableColumn("Games Played");
        gamesPlayedCol.setCellValueFactory(new PropertyValueFactory<>("gamesPlayed"));


        leaderboardBox.setPadding(new Insets(
                this.topAndLeftInset, this.bottomAndRightInset, this.bottomAndRightInset, this.topAndLeftInset));
        leaderboardBox.getChildren().addAll(title, table);
        table.setItems(playerData);
        table.getColumns().addAll(nameCol, winsCol, lossesCol, gamesPlayedCol);
        button.setOnMouseClicked(e -> stage.setScene(mainMenu));

        root.setBottom(button);
        root.setCenter(leaderboardBox);
        scene.setRoot(root);
        stage.setScene(scene);

    }

    /**
     * Launches a new Leaderboard scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     */
    // this needs fixing
    public static void launch(Stage stage, Scene mainMenu) {
        LeaderboardScreen leaderboard = new LeaderboardScreen();
        leaderboard.start(stage, mainMenu);
    }
}
