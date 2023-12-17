package com.example.newgame;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;


public class Stick extends GameElements {

    private Hero heroID;
    private final float stickWidth = 3F;
    private final float growthIncrement = 10F;
    private Rectangle stick;
    private Pane pane;

    public Stick(Hero Owner, Pane pane){
        this.heroID = Owner;
        this.stick = new Rectangle();
        this.stick.setWidth(stickWidth);
        this.stick.setFill(Color.BLACK);
        this.stick.setStroke(Color.BLACK);
        this.pane = pane;
        this.stick.setLayoutX(heroID.getHeroCharacter().getLayoutX()+heroID.getHeroCharacter().getFitWidth() - 144);
        this.stick.setLayoutY(heroID.getHeroCharacter().getLayoutY()+67);
        this.getStick().setHeight(0.0);

        this.pane.getChildren().add(this.stick);
    }

    public Stick(Hero Owner, Pane pane, int y){
        this.heroID = Owner;
        this.stick = new Rectangle();
        this.stick.setWidth(stickWidth);
        this.stick.setFill(Color.BLACK);
        this.stick.setStroke(Color.BLACK);
        this.pane = pane;
        this.stick.setLayoutX(heroID.getHeroCharacter().getLayoutX()+heroID.getHeroCharacter().getFitWidth() - 4);
        this.stick.setLayoutY(heroID.getHeroCharacter().getLayoutY()+67);
        this.getStick().setHeight(0.0);

        this.pane.getChildren().add(this.stick);
    }


    public double getStick_length() {
        return this.stick.getHeight();
    }

    public void setStick_length(double stick_length) {
        this.stick.setHeight(stick_length);
    }

    public Rectangle getStick() {
        return stick;
    }

    public void setStick(Rectangle stick) {
        this.stick = stick;
    }

    public void growStick(){

//        System.out.println("Stick is growing...");

        this.stick.setVisible(true);
        // increasing the sticks length
        this.stick.setLayoutY(this.stick.getLayoutY() - growthIncrement);
        this.stick.setHeight(this.stick.getHeight() + growthIncrement);

        this.stick.setVisible(true);

    }

    public boolean stopGrowStick(double TargetX){

//        System.out.println("Stick Stopped growing");

        this.stick.setVisible(true);
        //fall stick code
        fallStick(TargetX);
        return true;
    }

    public void fallStick(double TargetX){

        //make the stick fall, allow more sticks to be generated
//        System.out.println("stick is falling");

        this.stick.setVisible(true);

        // Set the pivot point to the bottom center of the stick
        double pivotX = this.stick.getX() + this.stick.getWidth() / 2;
        double pivotY = this.stick.getY() + this.stick.getHeight();

        stick.getTransforms().clear();

        Rotate rotate = new Rotate(90, pivotX, pivotY);
        stick.getTransforms().add(rotate);

        Duration duration = Duration.seconds(1);
        double finalAngle = 90;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                new KeyFrame(duration, new KeyValue(rotate.angleProperty(), finalAngle))
        );

        timeline.setOnFinished(event -> {
            System.out.println("Timeline completed");// this will only execute when timeline animation are over
            // hence adding player moment animation here
            heroID.moveHero(TargetX);

        });

        timeline.play();

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
