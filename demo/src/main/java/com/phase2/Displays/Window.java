package com.phase2.Displays;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.phase2.Game;

/**
 * The {@code Window} class is responsible for creating the main game window.
 * <p>
 * This class extends {@link Canvas} and uses a {@link JFrame} to display the game.
 * It sets the size, title, and properties of the window and attaches the game to it.
 * </p>
 */
public class Window extends Canvas {

    /**
     * Creates a game window with the specified dimensions, title, and game instance.
     * <p>
     * The window is created using a {@link JFrame} and is configured to be non-resizable.
     * The game instance is added to the frame, and the game is started.
     * </p>
     * 
     * @param width  the width of the game window in pixels
     * @param height the height of the game window in pixels
     * @param title  the title of the game window
     * @param game   the {@link Game} object to be attached to the window
     */
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        // Set the preferred, maximum, and minimum size of the frame
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        // Configure the frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application on exit
        frame.setResizable(false); // Prevent the window from being resized
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Attach the game instance to the frame
        frame.add(game);
        frame.setVisible(true);

        // Start the game
        game.start();
    }
}
