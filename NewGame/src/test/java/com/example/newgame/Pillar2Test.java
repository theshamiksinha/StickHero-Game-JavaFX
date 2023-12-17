package com.example.newgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pillar2Test {

    @Test
    void testGetPillar_height() {
        Pillar pillar = new Pillar();
        assertEquals(116, pillar.getPillar_height());
    }

    @Test
    void testGetPillar_Y_coordinate() {
        Pillar pillar = new Pillar();
        assertEquals(1000, pillar.getPillar_Y_coordinate());
    }

    @Test
    void testGetPillar_X_coordinate() {
        Pillar pillar = new Pillar();
        assertTrue(pillar.getPillar_X_coordinate() >= 300 && pillar.getPillar_X_coordinate() <= 650);
    }

//    @Test
//    void testGetPillar() {
//        Pillar pillar = new Pillar();
//        Rectangle expectedPillar = new Rectangle();
//        expectedPillar.setWidth(pillar.getPillar().getWidth());
//        expectedPillar.setFill(Color.BLACK);
//        assertEquals(expectedPillar, pillar.getPillar());
//    }

    @Test
    void testGetPositionX() {
        Pillar pillar = new Pillar();
        assertEquals(pillar.getPillar().getLayoutX(), pillar.getPositionX());
    }

    @Test
    void testGetPositionY() {
        Pillar pillar = new Pillar();
        assertEquals(pillar.getPillar().getLayoutY(), pillar.getPositionY());
    }
}
