package com.example.newgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static com.example.newgame.MainPage.*;

public class EndGameController implements ScoreObserver {

    @FXML
    public Pane EndGameRoot;
    @FXML
    public Circle restartButton;
    @FXML
    public Circle reviveButton;
    @FXML
    public Circle homeButton;
    @FXML
    public Label myScore;
    @FXML
    public Label bestScore;
    @FXML
    public Label cherryScore;

    private int scoreValue;
    private int bestScoreValue;
    private int cherryScoreValue;

    public int getScoreValue() {
        return scoreValue;
    }

    public int getBestScoreValue() {
        return bestScoreValue;
    }

    public int getCherryScoreValue() {
        return cherryScoreValue;
    }

    public void setScore(int score) {
        this.scoreValue = score;
        scoreList.add(score);
    }


    public void setScoreLabel(int score) {
        myScore.setText(String.valueOf(score));
    }

    public void setCherryScoreValue(int cherryScoreValue) {
        this.cherryScoreValue = cherryScoreValue;
    }

    public void setCherryScore(int cherryScore) {
        this.cherryScore.setText(String.valueOf(cherryScore));
    }

    public int setBestScore() throws Exception {
        this.bestScoreValue = scoreList.stream().mapToInt(v -> v).max().orElseThrow(Exception::new);
        return this.bestScoreValue;

    }
    public void setBestScoreLabel(int score) { bestScore.setText(String.valueOf(bestScoreValue));
    }
    public void homeButtonClicked(MouseEvent mouseEvent) throws IOException, InterruptedException {

        this.goToHome();

        currentd.setLastScore(this.bestScoreValue);
        currentd.setLastCherryCount(this.getCherryScoreValue());

        getSerializeThread().join();
    }

    public void reviveButtonClicked(MouseEvent mouseEvent) throws IOException {

        this.revivePlayer();
    }

    public void restartButtonClicked(MouseEvent mouseEvent) throws IOException, InterruptedException {

        this.restartGame();

        currentd.setLastScore(this.bestScoreValue);
        currentd.setLastCherryCount(this.getCherryScoreValue());

        getSerializeThread().join();
    }


    public void goToHome() throws IOException {
        try {
            playSoundEffectCuteButton();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));

            Parent mainGameRoot = fxmlLoader.load();
            MainPageController mainPlayController = fxmlLoader.getController();
            mainPlayController.playSoundOnRestart();
            EndGameRoot.getChildren().setAll(mainGameRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void restartGame() throws IOException {
        try {
            playSoundEffectCuteButton();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));

            Parent GamePlayRoot = fxmlLoader.load();
            EndGameRoot.getChildren().setAll(GamePlayRoot);

            GamePlayController gamePlayController = fxmlLoader.getController();
            gamePlayController.restartPositionSet();
            gamePlayController.playSoundOnRestart();

            gamePlayController.shiftScoreBoard();
            gamePlayController.shiftCherryCount();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void revivePlayer() throws IOException {

        System.out.println(this.getCherryScoreValue());

        if(this.getCherryScoreValue() >= 1){
            playSoundEffectCuteButton();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));

                Parent GamePlayRoot = fxmlLoader.load();
                EndGameRoot.getChildren().setAll(GamePlayRoot);

                GamePlayController gamePlayController = fxmlLoader.getController();
                gamePlayController.restartPositionSet();
                gamePlayController.playSoundOnRestart();

                gamePlayController.shiftScoreBoard();
                gamePlayController.shiftCherryCount();
                gamePlayController.setScoreOnRevive(this.getScoreValue()- 3, this.getCherryScoreValue() -1, this);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            playSoundEffectWrongButton();
        }
    }

    @Override
    public void updateScore(int score) {

        this.setScore(score);

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

    private void playSoundEffectWrongButton() {

        // create a new media player and play the sound file
        Media media = new Media(getClass().getResource("/sounds/wrongPress.mp3").toString());
        MediaPlayer button = new MediaPlayer(media);
        button.setAutoPlay(true);
        button.setCycleCount(1);
        button.setStartTime(Duration.seconds(0));

        button.play();

    }
}
