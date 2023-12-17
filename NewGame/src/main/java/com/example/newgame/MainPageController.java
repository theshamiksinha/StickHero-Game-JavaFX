package com.example.newgame;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class MainPageController {



    @FXML
    private Pane mainRoot;

    @FXML
    private ImageView heroCharacter;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Rectangle currentPillar;

    @FXML
    private Circle playButtonCircle;

    @FXML
    private Label playButtonLabel;

    @FXML
    private Label stickHeroLabel;



    private Stage mainStage;
    private Scene mainScene;

    //    public void playButtonClicked(MouseEvent mouseEvent) throws Exception{
    //
    //        System.out.println("Play button was pressed");
    //        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
    //        mainRoot=fxmlLoader.load();
    //        mainStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
    //        mainScene = new Scene(mainRoot);
    //        mainStage.setScene(mainScene);
    //
    //        mainStage.show();
    //
    //    }


    public void playButtonClicked(MouseEvent mouseEvent) throws Exception {
        System.out.println("Play button was pressed");

        FadeTransition fadeOut1 = new FadeTransition(Duration.seconds(1),stickHeroLabel);
        fadeOut1.setFromValue(1.0);
        fadeOut1.setToValue(0.0);
        fadeOut1.play();

        FadeTransition fadeOut2 = new FadeTransition(Duration.seconds(1),playButtonCircle);
        fadeOut2.setFromValue(1.0);
        fadeOut2.setToValue(0.0);
        fadeOut2.play();

        FadeTransition fadeOut3 = new FadeTransition(Duration.seconds(1),playButtonLabel);
        fadeOut3.setFromValue(1.0);
        fadeOut3.setToValue(0.0);
        fadeOut3.play();

        fadeOut3.setOnFinished(event -> {
            try {
                // Load the new scene after fade-out is complete
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
                mainRoot = fxmlLoader.load();
                mainStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                mainScene = new Scene(mainRoot);
                mainStage.setScene(mainScene);

                GamePlayController gamePlayController = fxmlLoader.getController();
                gamePlayController.shiftPlatformAndPlayer();

                gamePlayController.shiftScoreBoard();

                mainStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fadeOut3.play();
    }
}
