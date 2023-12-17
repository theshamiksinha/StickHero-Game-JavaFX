package com.example.newgame;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Stick implements GameElements {

    private Hero heroID;
    private final float stickWidth = 2F;
    private final float growthIncrement = 10F;
    private float stick_length;
    private Rectangle stick;
    private final Pane pane;

    public Stick(Hero Owner, Pane pane){
        this.heroID = Owner;
        this.stick = new Rectangle();
        this.stick.setWidth(stickWidth);
        this.stick.setFill(Color.BLACK);
        this.stick.setStroke(Color.BLACK);
        this.pane = pane;
        this.stick.setX(heroID.getPositionX() - 73);
        this.stick.setY(heroID.getPositionY() + 95);


        this.pane.getChildren().add(this.stick);


    }

    public float getStick_length() {
        return stick_length;
    }

    public void setStick_length(float stick_length) {
        this.stick_length = stick_length;
    }

    public void growStick(){

        System.out.println("Stick is growing...");

        //stick position relative to the hero


        // increasing the sticks length
        this.stick.setY(this.stick.getY() - growthIncrement);
        this.stick.setHeight(this.stick.getHeight() + growthIncrement);

        this.stick.setVisible(true);

    }

    public boolean stopGrowStick(){

        //stop growing the stick and dont allow ir to grow further
        System.out.println("Stick Stopped growing");


        //fall stick code
        fallStick();
        return true;
    }

    public void fallStick(){

        //make the stick fall, allow more sticks to be generated
        System.out.println("stick is falling");

        double stickLength = this.stick.getHeight();

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
