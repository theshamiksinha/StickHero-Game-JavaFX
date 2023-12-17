package com.example.newgame;

import java.io.Serializable;

import java.io.*;

public class Database implements Serializable {
    @Serial
    private static final long serialVersionUID=42L;
    private int lastScore;
    private int lastCherryCount;

    private static Database d;


    public Database(int lastScore, int lastCherryCount) {
        this.lastScore = lastScore;
        this.lastCherryCount = lastCherryCount;
    }

    public int getLastScore() {
        return this.lastScore;
    }

    public int getLastCherryCount() {
        return lastCherryCount;
    }

    public void setLastScore(int lastScore) {
        if (this.lastScore < lastScore) this.lastScore = lastScore;
    }

    public void setLastCherryCount(int lastCherryCount) {
        this.lastCherryCount = lastCherryCount;
    }

    public static void deleteAllProgress() {
        d = new Database(0,0);
    }

}

