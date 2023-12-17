package com.example.newgame;

import javafx.animation.*;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

import static com.example.newgame.MainPage.mediaPlayer;


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
    public ImageView PlayButtonPNG;
    @FXML
    public ImageView TitlePNG;

    private Stage mainStage;
    private Scene mainScene;

    public void playButtonClicked(MouseEvent mouseEvent) throws Exception {
        System.out.println("Play button was pressed");

        playSoundEffectCuteButton();

        FadeTransition fadeOut1 = new FadeTransition(Duration.seconds(1),TitlePNG);
        fadeOut1.setFromValue(1.0);
        fadeOut1.setToValue(0.0);
        fadeOut1.play();

        FadeTransition fadeOut2 = new FadeTransition(Duration.seconds(1),playButtonCircle);
        fadeOut2.setFromValue(1.0);
        fadeOut2.setToValue(0.0);
        fadeOut2.play();

        FadeTransition fadeOut3 = new FadeTransition(Duration.seconds(1),PlayButtonPNG);
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
                gamePlayController.shiftCherryCount();

                gamePlayController.shiftScoreBoard();

                mainStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fadeOut3.play();
    }

    public void playSoundOnRestart(){

        Media media = new Media(getClass().getResource("/sounds/calmBackground.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.millis(0));

        mediaPlayer.play();
    }

    @FXML
    public void makeHeroJump(MouseEvent mouseEvent) {
        // Define jump parameters
        double jumpHeight = 10; // Set the height of the jump
        Duration duration = Duration.seconds(1); // Set the duration of the jump
        double initialY = 386.0-65;

        // Create a timeline for the upward movement
        Timeline upTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(heroCharacter.layoutYProperty(), heroCharacter.getLayoutY())),
                new KeyFrame(duration.divide(2), new KeyValue(heroCharacter.layoutYProperty(), heroCharacter.getLayoutY() - jumpHeight))
        );

        Timeline downTimeline = new Timeline(
                new KeyFrame(duration.divide(2), new KeyValue(heroCharacter.layoutYProperty(), initialY)),
                new KeyFrame(duration, new KeyValue(heroCharacter.layoutYProperty(), initialY))
        );

        // Sequentially play the up and down timelines to create a smooth jump effect
        upTimeline.setOnFinished(event -> downTimeline.play());
        upTimeline.play();
    }

    private void playSoundEffectCuteButton() {

        // create a new media player and play the sound file
        Media media = new Media(getClass().getResource("/sounds/cuteButtonPress.mp3").toString());
        MediaPlayer button = new MediaPlayer(media);
        button.setAutoPlay(true);
        button.setCycleCount(1);
        button.setStartTime(Duration.seconds(0));

        button.play();

    }

}
