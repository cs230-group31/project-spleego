package com.group31.graphics;

import com.group31.logger.Logger;
import com.group31.settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author liamdp, Aaron, Emily
 */
public class TutorialPage {
    /**
     * File Path for the menu background image.
     */
    private static final String MENU_IMAGE_URL = Settings.get("menu_image_url");
    /**
     * File Path for the how to play image.
     */
    private static final String HOW_TO_PLAY_URL = "resources/images/how to play overlay.png";
    /**
     * File Path for the return button image.
     */
    private static final String RETURN_BUTTON_URL = "resources/images/return button.png";

    /**
     * Starts the scene.
     * @param stage instance of the stage (window)
     * @param mainMenu instance of the Main Menu scene
     */
    private void start(Stage stage, Scene mainMenu) {

        Scene scene = new Scene(new Group());
        BorderPane root = new BorderPane();

        try {
            Image howToPlayImage = new Image(new FileInputStream(HOW_TO_PLAY_URL));
            ImageView htpImageView = new ImageView(howToPlayImage);
            root.setCenter(htpImageView);
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        try {
            Image menuImg = new Image(new FileInputStream(MENU_IMAGE_URL));
            BackgroundImage bg = new BackgroundImage(menuImg,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            root.setBackground(new Background(bg));
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        ImageButton returnSign = new ImageButton(RETURN_BUTTON_URL);
        returnSign.setOnMouseClicked(e -> stage.setScene(mainMenu));
        HBox bottomBox = new HBox();
        bottomBox.getChildren().add(returnSign);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        root.setBottom(bottomBox);

        scene.setRoot(root);
        stage.setScene(scene);

    }

    /**
     * Launches a new Tutorial page scene.
     * @param stage instance of the stage (windows)
     * @param mainMenu instance of the Main Menu scene
     */
    public static void launch(Stage stage, Scene mainMenu) {
        TutorialPage tutorialPage = new TutorialPage();
        tutorialPage.start(stage, mainMenu);
    }

}
