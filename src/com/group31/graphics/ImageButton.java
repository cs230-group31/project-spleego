package com.group31.graphics;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageButton extends Parent {

    public ImageButton(String unpressed, String pressed) {
        Image unpressedImage = null;
        Image pressedImage = null;
        try {
            unpressedImage = new Image(new FileInputStream(unpressed));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            pressedImage = new Image(new FileInputStream(pressed));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(unpressedImage);
        this.getChildren().add(imageView);

        Image finalUnpressedImage1 = unpressedImage;
        imageView.setOnMouseExited(event -> {
            imageView.setImage(finalUnpressedImage1);
            event.consume();
        });
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
