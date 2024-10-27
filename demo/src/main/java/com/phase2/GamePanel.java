package com.phase2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener { // implements Runnable for our gameThread, and KeyListener for keyboard input
    // screen settings
    final int originalTileSize = 16; //16x16 tile of any character (default size for sprites)
    final int scale = 3; // to scale these characters ()

    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16; // 16 tiles is the total tile size horizontally
    final int maxScreenRow = 12; // 12 tile size vertically
    // 4x3

    final int screenWidth = tileSize * maxScreenCol; // 48*16 = 768 pixels width
    final int screenHeight = tileSize * maxScreenRow; // 48*12 = 576 pixels height

    Thread gameThread; // something you can start or stop (clock)
    Doug doug; // Instance of Doug to be controlled

    public GamePanel() { // constructor of screen
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // if true, all drawing from this component will be
        // done in an offscreen painting buffer

        this.addKeyListener(this); // add KeyListener to detect key events for movement
        this.setFocusable(true); // ensures that GamePanel can receive key events

        doug = new Doug();
    }

    public void startGameThread() { //thread constructor
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // our game loop!
        while (gameThread != null) {
            // update character's position and repaint the panel
            update();
            repaint(); // redraws Doug's position on the screen
            try {
                Thread.sleep(16); // approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // any game logic updates can go here
        // right now, Doug's position updates happen based on key input
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw Doug at his current position
        g.setColor(Color.RED); // set color for Doug (e.g., red square)
        g.fillRect(doug.getPositionX(), doug.getPositionY(), tileSize, tileSize);
    }

    // handling keyboard input for movement
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> doug.move("UP");      
            case KeyEvent.VK_DOWN -> doug.move("DOWN"); 
            case KeyEvent.VK_LEFT -> doug.move("LEFT");  
            case KeyEvent.VK_RIGHT -> doug.move("RIGHT");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used but required for KeyListener interface
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but required for KeyListener interface
    }
}
