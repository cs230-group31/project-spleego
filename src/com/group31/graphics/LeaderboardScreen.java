package com.group31.graphics;

import com.group31.leaderboard.Leaderboard;
import com.group31.player.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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

    private void start(Stage stage, Scene mainMenu) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();

        TableView table = new TableView();

        TableColumn nameCol = new TableColumn("Player Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn winsCol = new TableColumn("Wins");
        winsCol.setCellValueFactory(new PropertyValueFactory<>("wins"));
        TableColumn lossesCol = new TableColumn("Losses");
        lossesCol.setCellValueFactory(new PropertyValueFactory<>("losses"));
        TableColumn gamesPlayedCol = new TableColumn("Games Played");
        gamesPlayedCol.setCellValueFactory(new PropertyValueFactory<>("gamesPlayed"));

        Label title = new Label("Leaderboard");

        final VBox leaderboardBox = new VBox();
        leaderboardBox.setSpacing(10);
        leaderboardBox.setPadding(new Insets(10, 0, 0, 10));
        leaderboardBox.getChildren().addAll(title, table);

        ObservableList<Player> playerData = Leaderboard.getLeaderboardData();
        table.setItems(playerData);
        table.getColumns().addAll(nameCol, winsCol, lossesCol, gamesPlayedCol);

        Button button = new Button("Return");
        button.setOnMouseClicked(e -> stage.setScene(mainMenu));

        root.setBottom(button);
        root.setCenter(leaderboardBox);

        scene.setRoot(root);
        stage.setScene(scene);

    }

    public static void launch(Stage stage, Scene mainMenu) {
        LeaderboardScreen leaderboard = new LeaderboardScreen();
        leaderboard.start(stage, mainMenu);
    }
}
