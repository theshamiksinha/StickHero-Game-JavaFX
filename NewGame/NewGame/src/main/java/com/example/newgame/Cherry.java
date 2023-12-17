package com.example.newgame;

public class Cherry implements GameElements {
    private int no_of_cherry = 0;
    private float probCherrySpawn = 0.5F;
    private float probCherrySpawnDown = 0.5F;

    @Override
    public double getPositionX() {
        return 0;
    }

    @Override
    public double getPositionY() {
        return 0;
    }
}
