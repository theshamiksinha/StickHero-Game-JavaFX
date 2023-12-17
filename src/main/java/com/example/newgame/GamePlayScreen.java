package com.example.newgame;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import java.util.Random;

//import static com.example.newgame.GamePlayController.cherryX;

public class GamePlayScreen{

    private ScoreBoard score;
    private Hero hero;
    private Pillar currentPillar;
    private Pillar nextPillar;
    private final Pane GamePlayRoot;
    private GamePlayController gamePlayController;

    private Cherry nextCherry;

    public ImageView nextCherryImage;


    public GamePlayScreen(ScoreBoard score, Hero hero, Pillar currentPillar, Pane pane, GamePlayController g){

        this.score = score;
        this.hero = hero;
        this.currentPillar = currentPillar;
        this.GamePlayRoot = pane;
        this.gamePlayController = g;

    }

    public Cherry getNextCherry() {
        return nextCherry;
    }

    public void setNextCherry(Cherry nextCherry) {
        this.nextCherry = nextCherry;
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

    public void spawnPillar(){
        // spawn random pillar

        this.nextPillar = new Pillar(); //random dimensions

        Rectangle newRect = this.nextPillar.getPillar();

        newRect.setX(this.nextPillar.getPillar_X_coordinate());
        newRect.setY(this.nextPillar.getPillar_Y_coordinate());

        newRect.setWidth(this.nextPillar.getPillar().getWidth());
        newRect.setHeight(this.nextPillar.getPillar_height());

        newRect.setFill(this.nextPillar.getPillar().getFill());
        newRect.setVisible(true);
        this.GamePlayRoot.getChildren().add(newRect);

        Timeline pillarTimeline = new Timeline();

        KeyValue pillarKeyValue = new KeyValue(
                newRect.translateYProperty(),
                386 - this.nextPillar.getPillar_Y_coordinate()
        );
        KeyFrame pillarKeyFrame = new KeyFrame(Duration.seconds(2), pillarKeyValue);

        pillarTimeline.getKeyFrames().add(pillarKeyFrame);
        pillarTimeline.play();


//        this.spawnCherry();

    }

    public void spawnCherry() {

        this.nextCherry = new Cherry(this.currentPillar.getPillar().getLayoutX(), this.nextPillar.getPillar().getLayoutX());

        this.nextCherryImage = this.nextCherry.getCherry();

        double spawnDistance = getRandomNumber(this.currentPillar.getPillar().getWidth()+10,this.nextPillar.getPillar_X_coordinate()-100);

        this.nextCherryImage.setX(spawnDistance);

        gamePlayController.setCherryX(spawnDistance);

        System.out.println("this is cherry X " + spawnDistance);

        this.nextCherryImage.setY(410);

        this.nextCherry.setX_dis(spawnDistance);
        this.nextCherry.setY_dis(410);

        this.nextCherryImage.setVisible(this.nextCherry.getCherrySpawn());

        GamePlayRoot.getChildren().add(this.nextCherryImage);
    }

    public double getDistance(){
        // method to get distance between pillars
        double currentPlatformRightEdge = this.currentPillar.getPillar_X_coordinate() + this.currentPillar.getPillar().getWidth();
        double nextPlatformLeftEdge = this.nextPillar.getPillar_X_coordinate();

        return nextPlatformLeftEdge - currentPlatformRightEdge;
    }

    public static double getRandomNumber(double minValue, double maxValue) {
        Random random = new Random();
        return minValue + (maxValue - minValue) * random.nextDouble();
    }

}
