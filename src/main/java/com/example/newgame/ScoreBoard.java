package com.example.newgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

//import static com.example.newgame.GamePlayController.cherryCount;
//import static com.example.newgame.GamePlayController.cherryCountLabel;

public class ScoreBoard extends GameElements {

    private Rectangle scoreBox;
    private Label scoreLabelNum;

    private Label scoreLabel;

    private Pane pane;
    private int scoreValue;

    private Label cherryCountLabel;

    private int cherryCount;
    private List<ScoreObserver> observers;

    public ScoreBoard(Rectangle scoreBox, Label scoreLabel, Label scoreLabelNum, Label cherryCountLabel, Pane pane) {

        this.scoreBox = scoreBox;
        this.scoreLabel = scoreLabel;
        this.scoreLabelNum = scoreLabelNum;
        this.pane = pane;
        this.scoreValue = 0;
        this.cherryCountLabel = cherryCountLabel;
        this.observers = new ArrayList<>();
        updateScore();

//        this.pane.getChildren().add(this.scoreLabelNum);
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {

        this.scoreValue = scoreValue;
        notifyObservers();
    }

    public int getCherryCount() {
        return cherryCount;
    }

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public void updateScore() {
        scoreLabelNum.setText(String.valueOf(scoreValue));
        if (cherryCountLabel != null) {
            cherryCountLabel.setText(String.valueOf(cherryCount));
        }
    }

    public void increaseScore(int amount) {
        scoreValue += amount;
        updateScore();
        notifyObservers();
    }

    public void increaseCherryCount(int amount) {
        cherryCount += amount;
        updateScore();
        notifyObservers();
    }
    public void attachObserver(ScoreObserver observer) {
        observers.add(observer);
    }
    public void detachObserver(ScoreObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        for (ScoreObserver observer : observers) {
            observer.updateScore(scoreValue);
        }
    }


    @Override
    public double getPositionX() {
        return 0;
    }

    @Override
    public double getPositionY() {
        return 0;
    }
}
