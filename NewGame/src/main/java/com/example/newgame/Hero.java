package com.example.newgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Hero implements GameElements {

    private int high_score = 0;
    private ImageView heroCharacter;
    private boolean HeroAlive = true;
    private Stick heroStick;
//    private int currentImageIndex = 0;
//    private List<Image> animationFrames = new ArrayList<>();

    public Hero(ImageView him, Pane pane) {
        this.heroCharacter = him;
        this.heroStick = new Stick(this, pane);

    }

    public ImageView getHeroCharacter() {
        return heroCharacter;
    }

    public void setHeroCharacter(ImageView heroCharacter) {
        this.heroCharacter = heroCharacter;
    }

    public Stick getHeroStick() {
        return heroStick;
    }

    public void setHeroStick(Stick heroStick) {
        this.heroStick = heroStick;
    }

    public int getHigh_score() {
        return high_score;
    }

    public void setHigh_score(int high_score) {
        this.high_score = high_score;
    }

    public boolean isHeroAlive() {
        return HeroAlive;
    }

    public void setHeroAlive(boolean heroAlive) {
        HeroAlive = heroAlive;
    }

    @Override
    public double getPositionX() {
        return heroCharacter.getLayoutX();
    }

    @Override
    public double getPositionY() {
        return heroCharacter.getLayoutY();
    }
}
