package com.example.newgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class MainPage extends Application {

    public static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //deserialize();
        addMusic();

        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        primaryStage.setTitle("Stick Hero");

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/StickHeroCharacter.png")));
        primaryStage.getIcons().add(icon);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addMusic(){

        Media sound = new Media(getClass().getResource("/sounds/calmBackground.mp3").toString());

        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch();
    }
}