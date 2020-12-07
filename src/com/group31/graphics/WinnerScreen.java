package com.group31.graphics;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author liam
 */
public class WinnerScreen {


    private void start(Stage stage, Scene mainMenu, String winner) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        Text winnerText = new Text(String.format("%s won the game!", winner));
        Button button = new Button("Return");

        button.setOnMouseClicked(e -> stage.setScene(mainMenu));

        root.setCenter(winnerText);
        root.setBottom(button);
        scene.setRoot(root);
        stage.setScene(scene);

    }


    /**
     * Launches the winner screen.
     * @param stage stage to display on (window)
     * @param mainMenu the main menu scene
     * @param winner the winner of the game
     */
    public static void launch(Stage stage, Scene mainMenu, String winner) {
        WinnerScreen winnerScreen = new WinnerScreen();
        winnerScreen.start(stage, mainMenu, winner);
    }

}
