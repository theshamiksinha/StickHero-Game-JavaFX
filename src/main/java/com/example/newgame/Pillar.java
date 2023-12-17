package com.example.newgame;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.*;

import java.util.ArrayList;

public class Pillar extends GameElements {
    private final int pillar_max_width = 200;
    private final int pillar_min_width = 100;
    private final int pillar_height = 116;
    private final int pillar_Y_coordinate = 1000;
    private int pillar_X_coordinate = 0;

    private Rectangle pillar;

    public Pillar(Rectangle pillar){

        this.pillar = pillar;
        this.pillar.setWidth((int)pillar.getWidth());
        this.pillar_X_coordinate = (int) pillar.getLayoutX();
    }

    public Pillar(){

        System.out.println("initialise -> gamescreen -> Spawn Pillar -> Pillar constructor");
        this.pillar = new Rectangle();

        int min = pillar_min_width;

        Random random = new Random();

        int randomWidth = random.nextInt(pillar_max_width - min) + min;

        this.pillar.setWidth(randomWidth);

        int minD = 500;
        int maxD = 650;

        Random randomD = new Random();

        this.pillar_X_coordinate = randomD.nextInt(maxD - minD) + minD;

        this.pillar.setFill(Color.BLACK);
    }


    public int getPillar_height() {
        return pillar_height;
    }

    public int getPillar_Y_coordinate() {
        return pillar_Y_coordinate;
    }

    public int getPillar_X_coordinate() {
        return pillar_X_coordinate;
    }

    public void setPillar_X_coordinate(int pillar_X_coordinate) {
        this.pillar_X_coordinate = pillar_X_coordinate;
    }

    public Rectangle getPillar() {
        return pillar;
    }

    public void setPillar(Rectangle pillar) {
        this.pillar = pillar;
    }

    @Override
    public double getPositionX() {
        return pillar.getLayoutX();
    }

    @Override
    public double getPositionY() {
        return pillar.getLayoutY();
    }
}