package com.phase2;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * The Window class is responsible for creating the main game window.
 * It extends Canvas and sets up the JFrame to display the game.
 */
public class Window extends Canvas {

  /**
   * Constructor for creating a game window.
   * It initializes the JFrame with the specified width, height, and title, 
   * and attaches the game to the frame.
   * 
   * @param width  the width of the game window
   * @param height the height of the game window
   * @param title  the title of the window
   * @param game   the Game object to be attached to the window
   */
  public Window(int width, int height, String title, Game game) {
    JFrame frame = new JFrame(title);

    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(true); // make window resizable
    frame.setLocationRelativeTo(null);

    // Adds the game instance to the frame and makes it visible
    frame.add(game);
    frame.setVisible(true);
    game.start();
  }
}