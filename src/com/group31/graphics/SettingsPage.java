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
public class SettingsPage {

    /**
     * Starts the scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     */
    private void start(Stage stage, Scene mainMenu) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        Text tutorial = new Text("SETTINGS");
        Button button = new Button("Return");

        button.setOnMouseClicked(e -> stage.setScene(mainMenu));

        root.setTop(tutorial);
        root.setBottom(button);
        scene.setRoot(root);
        stage.setScene(scene);

    }

    /**
     * Launches a new Settings page scene.
     * @param stage instance of the stage (windows)
     * @param mainMenu instance of the Main Menu scene
     */
    public static void launch(Stage stage, Scene mainMenu) {
        SettingsPage settingsPage = new SettingsPage();
        settingsPage.start(stage, mainMenu);
    }

}
