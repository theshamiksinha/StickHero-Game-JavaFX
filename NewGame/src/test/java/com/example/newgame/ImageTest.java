package com.example.newgame;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageTest {

    @Test
    void testImageEquality() throws IOException {
        // Load images from files
        BufferedImage expectedImage = ImageIO.read(getClass().getResource("/Images/Background1.png"));
//        BufferedImage actualImage = ImageIO.read(getClass().getResource("/Images/EndGameMessagePNG.png"));

        // Resize images to the same dimensions (if needed)
        // expectedImage = Scalr.resize(expectedImage, 100);
        // actualImage = Scalr.resize(actualImage, 100);
        InputStream inputStream = getClass().getResourceAsStream("/Images/EndGameMessagePNG.png");
        BufferedImage actualImage = ImageIO.read(inputStream);

        // Compare pixel values
        double differencePercentage = calculateDifferencePercentage(expectedImage, actualImage);

        // Assert that the images are similar with a tolerance value
        assertTrue(differencePercentage < 5.0, "Images are not equal");
    }

    private double calculateDifferencePercentage(BufferedImage expected, BufferedImage actual) {
        int width = expected.getWidth();
        int height = expected.getHeight();
        int numDifferentPixels = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < actual.getWidth() && y < actual.getHeight() && x < expected.getWidth() && y < expected.getHeight()) {
                    if (expected.getRGB(x, y) != actual.getRGB(x, y)) {
                        numDifferentPixels++;
                    }
                } else {
                }
            }
        }

        return (double) numDifferentPixels / (width * height) * 100.0;
    }

}
