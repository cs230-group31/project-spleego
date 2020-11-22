package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


public class Main extends Application {
    //set everything in leaderboard
    stage windowView;
    Button button;
    Color color;

    private int scoreB = 0;

    //score set
    private Text ScoreA = new Text("socreA"+scoreA);
    private Text ScoreB = new Text("socreB"+scoreA);



    @Override
    public void start(Stage primaryStage) throws Exception{

        //windowView = primaryStage;
        
        //title set
        primaryStage.setTitle("LEADERBOARD");

        //button set
        button = new Button();
        button.setText("START");
        button.setOnAction(this);


        //layout set
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        //COLOR set
        color = new Color(Color.ORANGE);


        Pane boardlayout = new Pane();
        boardlayout.getChildren().addAll();

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
