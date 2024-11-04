package com.phase2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

// For the background
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * The Game class manages the main game loop and window.
 * It initializes the game components, handles input, and manages the game's continuous execution.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1300, HEIGHT = 750; // sets window size
    public static final int BLOCK_SIZE[] = {50,50};

    private Thread thread; 
    private boolean running = false;

    private Handler handler;

    // BufferedImage for the background
    private BufferedImage background;
    private BufferedImage bushImage; 

    // private static final double BUSH_SCALE_FACTOR = 0.025;

    /**
     * Constructor for the Game class.
     * Initializes the handler and sets up the game window.
     */
    public Game() {

        handler = new Handler();

        this.addKeyListener(new KeyInput(handler)); // Recieves keyboard input

        // Load the background image
        try {
            background = ImageIO.read(getClass().getResource("/grassback.png"));
            bushImage = ImageIO.read(getClass().getResource("/bush.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        new Window(WIDTH, HEIGHT, "Doug Game", this);

        // Used for testing only
<<<<<<< HEAD
        handler.addObject(new Doug(200, 200, ID.DOUG));
        handler.addObject(new Apple(BLOCK_SIZE[0], BLOCK_SIZE[1], RewardType.APPLE));
        handler.addObject(new Bone(BLOCK_SIZE[0], 2*BLOCK_SIZE[1], RewardType.BONE));
        handler.addObject(new Steak(BLOCK_SIZE[0], 3*BLOCK_SIZE[1], RewardType.STEAK));
        handler.addObject(new Mushroom(BLOCK_SIZE[0], 4*BLOCK_SIZE[1], RewardType.MUSHROOM));
=======
        handler.addObject(new Doug(100, HEIGHT - 200, ID.DOUG));
>>>>>>> 2192457d79c000e4d5be68140c3677512b7cd43a
        //

        handler.addObject(new Rat(WIDTH - 200, HEIGHT - 150, 5)); // Penalty points set to 5

    }

    /**
     * Starts the game in a new thread.
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Stops the game thread safely.
     */
    public synchronized void stop () {
        try {
                thread.join();
                running = false;
        } 
        catch (Exception e) {
                e.printStackTrace();
        }
    }

    /**
     * The main game loop that handles game updates and rendering.
     * Continuously runs while the game is active.
     */
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        // int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;    
            }
            if (running) {
                render();
            }
            // frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
                // frames = 0;
            }
        }
        stop();
    }

    /**
     * Updates the game state for each game tick.
     */
    private void tick(){
        handler.tick();
    }

    /**
     * Renders the game graphics.
     * 
     * @param g the Graphics object used for drawing the game components
     */
    private void render(){
        BufferStrategy  bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

    if (background != null) {
        // Draw the background such that it covers the entire canvas
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
    } 
    else {
        // Testing purposes
        // INCASE BACKGROUND DOES NOT LOAD
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    // Bush dimensions
    int bushWidth = 30;
    int bushHeight = 30;

    // Manually place bushes in each corner
    g2d.drawImage(bushImage, 0, 0, bushWidth, bushHeight, null); // Top-left
    g2d.drawImage(bushImage, WIDTH - bushWidth, 0, bushWidth, bushHeight, null); // Top-right
    g2d.drawImage(bushImage, 0, HEIGHT - bushHeight, bushWidth, bushHeight, null); // Bottom-left
    g2d.drawImage(bushImage, WIDTH - bushWidth, HEIGHT - bushHeight, bushWidth, bushHeight, null); // Bottom-right

    // Manually place bushes along top and bottom borders (excluding corners)
    for (int x = bushWidth; x < WIDTH - bushWidth; x += bushWidth) {
        g2d.drawImage(bushImage, x, 0, bushWidth, bushHeight, null); // Top border
        g2d.drawImage(bushImage, x, HEIGHT - bushHeight, bushWidth, bushHeight, null); // Bottom border
    }

    // Manually place bushes along left and right borders (excluding corners)
    for (int y = bushHeight; y < HEIGHT - bushHeight; y += bushHeight) {
        g2d.drawImage(bushImage, 0, y, bushWidth, bushHeight, null); // Left border
        g2d.drawImage(bushImage, WIDTH - bushWidth, y, bushWidth, bushHeight, null); // Right border
    }

    // Render game objects
    handler.render(g2d);

    g.dispose();
    bs.show();
}

    public static void main(String[] args) {
        new Game();
    }

 }