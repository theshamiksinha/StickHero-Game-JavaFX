package com.example.newgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Cherry extends GameElements {
    private final ImageView cherry = new ImageView(new Image(getClass().getResource("/Images/CherryPNG.png").toExternalForm()));
    private double x_dis;
    private double y_dis = 400;
    private boolean spawn = false;
    public Cherry(Double currentPillarX, Double nextPillarX) {
        Random random = new Random();
        this.cherry.setFitHeight(15);
        this.cherry.setFitWidth(25);

        float probCherrySpawn = 0.5F; // Adjust this probability as needed
        float probCherrySpawnDown = 1.0F; // Adjust this probability as needed

        float spawnProbability = random.nextFloat();
        if (spawnProbability < probCherrySpawn) {

            this.spawn = true;

            float cherryPosition = random.nextFloat();
            if (cherryPosition > probCherrySpawnDown) {
                // Cherry will spawn above
                this.y_dis -= 50; // Adjust the vertical position as needed
            }
        }

        this.x_dis = currentPillarX + random.nextDouble() * (nextPillarX - currentPillarX);
    }

    public void setX_dis(double x_dis) {
        this.x_dis = x_dis;
    }

    public void setY_dis(double y_dis) {
        this.y_dis = y_dis;
    }

    public ImageView getCherry() {
        return cherry;
    }

    public double getX_dis() {
        return x_dis;
    }

    public double getY_dis() {
        return y_dis;
    }

    public boolean getCherrySpawn() {
        return spawn;
    }

    @Override
    public double getPositionX() {
        return x_dis;
    }

    @Override
    public double getPositionY() {
        return y_dis;
    }
}
