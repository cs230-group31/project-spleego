package com.group31.graphics;

import com.group31.logger.Logger;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageButton extends Parent {
    /**
     * Height of the button in pixels.
     */
    private static final double BUTTON_HEIGHT = 80.0;
    /**
     * Width of the button in pixels.
     */
    private static final double BUTTON_WIDTH = 504.0;

    /**
     * Represents a JavaFX button that presents as an image.
     * @param unpressed File Path of the unpressed button image.
     * @param pressed File Path of the pressed button image.
     */
    public ImageButton(final String unpressed, final String pressed) {
        Image unpressedImage = null;
        Image pressedImage = null;
        try {
            unpressedImage = new Image(new FileInputStream(unpressed), BUTTON_WIDTH, BUTTON_HEIGHT, false, false);
        } catch (FileNotFoundException e) {
            Logger.log(e.toString(), Logger.Level.ERROR);
        }
        try {
            pressedImage = new Image(new FileInputStream(pressed), BUTTON_WIDTH, BUTTON_HEIGHT, false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(unpressedImage);
        this.getChildren().add(imageView);

        Image finalPressedImage = pressedImage;
        imageView.setOnMousePressed(event ->
            imageView.setImage(finalPressedImage)
        );
        Image finalUnpressedImage = unpressedImage;
        imageView.setOnMouseReleased(event ->
            imageView.setImage(finalUnpressedImage)
        );
    }
}
