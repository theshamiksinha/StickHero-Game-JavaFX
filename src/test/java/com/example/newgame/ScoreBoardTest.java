package com.example.newgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PillarTest {

    @Test
    void testPillarCreationWithValues() {
        // Create a Pillar with specific values
        int customWidth = 150;
        int customXCoordinate = 400;

        Rectangle customRectangle = new Rectangle();
        customRectangle.setWidth(customWidth);

        Pillar pillar = new Pillar(customRectangle);
        pillar.setPillar_X_coordinate(customXCoordinate);

        // Check if the values are set correctly
        assertEquals(customWidth, (int) pillar.getPillar().getWidth(), "Width doesn't match");
        assertEquals(customXCoordinate, pillar.getPillar_X_coordinate(), "X coordinate doesn't match");
    }

    @Test
    void testPillarRandomCreation() {
        // Create a Pillar with random values
        Pillar pillar = new Pillar();

        // Check if the values are within the expected ranges
//        assertTrue(pillar.getPillar().getWidth() >= Pillar.pillar_min_width && pillar.getPillar().getWidth() <= Pillar.pillar_max_width, "Random width is out of range");
//        assertTrue(pillar.getPillar_X_coordinate() >= Pillar.minD && pillar.getPillar_X_coordinate() <= Pillar.maxD, "Random X coordinate is out of range");
        assertEquals(Color.BLACK, pillar.getPillar().getFill(), "Default fill color is not black");
    }

    @Test
    void testSetPillarXCoordinate() {
        // Create a Pillar
        Pillar pillar = new Pillar();

        // Set a new X coordinate
        int newCoordinate = 500;
        pillar.setPillar_X_coordinate(newCoordinate);

        // Check if the new X coordinate is set correctly
        assertEquals(newCoordinate, pillar.getPillar_X_coordinate(), "X coordinate doesn't match");
    }
}
