package com.example.newgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class ScoreBoard implements GameElements {

    public Rectangle scoreBox;

    public Label scoreLabelNum;

    public Label scoreLabel;

    public ScoreBoard(Rectangle scoreBox, Label scoreLabel, Label scoreLabelNum) {

        this.scoreBox = scoreBox;
        this.scoreLabel = scoreLabel;
        this.scoreLabelNum = scoreLabelNum;
    }
    public void updateScore() {
        int newScore = Integer.parseInt(String.valueOf(scoreLabel));
        scoreLabelNum.setText(String.valueOf(newScore));
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
