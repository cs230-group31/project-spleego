package com.group31.graphics;

import com.group31.settings.Settings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TutorialPage {
    private void start(Stage stage, Scene mainMenu) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();
        String tutorialText = Settings.get("tutorial_text");
        Text tutorial = new Text(tutorialText);
        Button button = new Button("Return");
        button.setOnMouseClicked(e -> stage.setScene(mainMenu));

        root.setCenter(tutorial);
        root.setBottom(button);

        scene.setRoot(root);
        stage.setScene(scene);

    }

    public static void launch(Stage stage, Scene mainMenu) {
        TutorialPage tutorialPage = new TutorialPage();
        tutorialPage.start(stage, mainMenu);
    }

}
