package com.example.newgame;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class GamePlayScreen{

    private ScoreBoard score;
    private Hero hero;
    private Pillar currentPillar;
    private Pillar nextPillar;
    private final Pane GamePlayRoot;


    public GamePlayScreen(ScoreBoard score, Hero hero, Pillar currentPillar, Pane pane){

        this.score = score;
        this.hero = hero;
        this.currentPillar = currentPillar;
        this.GamePlayRoot = pane;

    }

    public ScoreBoard getScore() {
        return score;
    }

    public void setScore(ScoreBoard score) {
        this.score = score;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Pillar getCurrentPillar() {
        return currentPillar;
    }

    public void setCurrentPillar(Pillar currentPillar) {
        this.currentPillar = currentPillar;
    }

    public Pillar getNextPillar() {
        return nextPillar;
    }

    public void setNextPillar(Pillar nextPillar) {
        this.nextPillar = nextPillar;
    }

    public void updateScore(int score){

        // keep updating score on the side/above if hero is alive
    }

    public void moveHero() {



    }

    private void resetElements() {

    }

    public void spawnPillar(){
        // spawn random pillar

        this.nextPillar = new Pillar();

        Rectangle newRect = this.nextPillar.getPillar();

        newRect.setX(this.nextPillar.getPillar_X_coordinate());
        newRect.setY(this.nextPillar.getPillar_Y_coordinate());

        newRect.setWidth(this.nextPillar.getPillar().getWidth());
        newRect.setHeight(this.nextPillar.getPillar_height());

        newRect.setFill(this.nextPillar.getPillar().getFill());
        newRect.setVisible(true);
        this.GamePlayRoot.getChildren().add(newRect);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), newRect);
        transition.setToY(386 - this.nextPillar.getPillar_Y_coordinate());
        transition.play();

    }

    private double getDistance(){
        // method to get distance between pillars
        double currentPlatformRightEdge = this.currentPillar.getPillar_X_coordinate() + this.currentPillar.getPillar().getWidth();
        double nextPlatformLeftEdge = this.nextPillar.getPillar_X_coordinate();

        return nextPlatformLeftEdge - currentPlatformRightEdge;
    }

}
