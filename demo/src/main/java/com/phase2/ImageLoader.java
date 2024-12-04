package com.phase2;


import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Utility class for loading and scaling images
 */
public class ImageLoader {
    /**
     * Loads an image from the specified path and scales it to the given width and height.
     *
     * @param path  The path to the image file.
     * @param width The desired width of the image.
     * @param height The desired height of the image.
     * @return The loaded and scaled image.
     */
    public static Image loadImage(String path, int width, int height) {
        try {
            Image image = ImageIO.read(ImageLoader.class.getResource(path));
            return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
