package com.phase2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Game class manages the main game loop and window.
 * It initializes the game components, handles input, and manages the game's continuous execution.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1300, HEIGHT = 750;
    public static final int BLOCK_SIZE[] = {50, 50};

    private Thread thread;
    private boolean running = false;
    private boolean paused = true; // Game starts in the menu (paused)

    private Handler handler;
    private MainMenu mainMenu; // MainMenu instance

    private BufferedImage background;
    private BufferedImage bushImage;

    /**
     * Constructor for the Game class.
     * Initializes the handler and sets up the game window.
     */
    public Game() {
        handler = new Handler();
        mainMenu = new MainMenu(this); // Initialize MainMenu

        this.addKeyListener(new KeyInput(handler)); // Game key input

        loadImages();
        new Window(WIDTH, HEIGHT, "Doug Game", this);

        // Add game objects for testing
        handler.addObject(new Doug(200, 200, ID.DOUG));
        handler.addObject(new Apple(BLOCK_SIZE[0], BLOCK_SIZE[1], RewardType.APPLE));
        handler.addObject(new Bone(BLOCK_SIZE[0], 2 * BLOCK_SIZE[1], RewardType.BONE));
        handler.addObject(new Steak(BLOCK_SIZE[0], 3 * BLOCK_SIZE[1], RewardType.STEAK));
        handler.addObject(new Mushroom(BLOCK_SIZE[0], 4 * BLOCK_SIZE[1], RewardType.MUSHROOM));
        handler.addObject(new Rat(WIDTH - 200, HEIGHT - 150, 5));
    }

    /**
     * Loads images for the background and other elements.
     */
    private void loadImages() {
        try {
            background = ImageIO.read(getClass().getResource("/grassback.png"));
            bushImage = ImageIO.read(getClass().getResource("/bush.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts or resumes the game from the main menu.
     */
    public synchronized void startGame() {
        paused = false; // Start or resume the game
    }

    /**
     * Toggles the game's paused state, returning to the main menu if paused.
     */
    public void togglePause() {
        paused = !paused;
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
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main game loop that handles game updates and rendering.
     */
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (!paused) tick(); // Only tick if the game is not paused
                delta--;
            }
            render();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    /**
     * Updates the game state for each game tick.
     */
    private void tick() {
        handler.tick();
    }

    /**
     * Renders the main menu or the game graphics based on the current state.
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if (paused) {
            mainMenu.render(g); // Render main menu or instructions screen if paused
        } else {
            renderGame(g); // Render the actual game
        }

        g.dispose();
        bs.show();
    }

    /**
     * Renders the game graphics.
     * 
     * @param g the Graphics object used for drawing the game components
     */
    private void renderGame(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (background != null) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }

        int bushWidth = 30;
        int bushHeight = 30;

        g2d.drawImage(bushImage, 0, 0, bushWidth, bushHeight, null); // Top-left corner
        g2d.drawImage(bushImage, WIDTH - bushWidth - 10, 0, bushWidth, bushHeight, null); // Top-right corner
        g2d.drawImage(bushImage, 0, HEIGHT - bushHeight - 35, bushWidth, bushHeight, null); // Bottom-left corner
        g2d.drawImage(bushImage, WIDTH - bushWidth - 15, HEIGHT - bushHeight - 35, bushWidth, bushHeight, null); // Bottom-right

        // Bushes along borders
        for (int x = bushWidth; x <= WIDTH - bushWidth; x += bushWidth) {
            g2d.drawImage(bushImage, x, 0, bushWidth, bushHeight, null);
        }
        for (int x = bushWidth; x <= WIDTH - bushWidth - 15; x += bushWidth) {
            g2d.drawImage(bushImage, x, HEIGHT - bushHeight - 35, bushWidth, bushHeight, null);
        }
        for (int y = bushHeight; y <= HEIGHT - bushHeight; y += bushHeight) {
            g2d.drawImage(bushImage, 0, y, bushWidth, bushHeight, null);
        }
        for (int y = bushHeight; y <= HEIGHT - bushHeight - 35; y += bushHeight) {
            g2d.drawImage(bushImage, WIDTH - bushWidth - 15, y, bushWidth, bushHeight, null);
        }

        int[][] mazeBushCoordinates = {
            {90, HEIGHT - bushHeight - 35}, {90, HEIGHT - bushHeight * 2 - 35}, {90, HEIGHT - bushHeight * 3 - 35},
            {90, HEIGHT - bushHeight * 4 - 35}, {90, HEIGHT - bushHeight * 5 - 35}, {120, HEIGHT - bushHeight * 5 - 35},
            {150, HEIGHT - bushHeight * 5 - 35}, {180, HEIGHT - bushHeight * 5 - 35}, {210, HEIGHT - bushHeight * 5 - 35},
            {240, HEIGHT - bushHeight * 5 - 35}, {270, HEIGHT - bushHeight * 5 - 35}, {300, HEIGHT - bushHeight * 5 - 35},
            {90, HEIGHT - bushHeight * 9 - 35}, {90, HEIGHT - bushHeight * 10 - 35}, {90, HEIGHT - bushHeight * 11 - 35},
            {90, HEIGHT - bushHeight * 12 - 35}, {120, HEIGHT - bushHeight * 12 - 35}, {150, HEIGHT - bushHeight * 12 - 35},
            {180, HEIGHT - bushHeight * 12 - 35}, {120, HEIGHT - bushHeight * 9 - 35}, {150, HEIGHT - bushHeight * 9 - 35},
            {180, HEIGHT - bushHeight * 9 - 35}
        };

        for (int[] coord : mazeBushCoordinates) {
            g2d.drawImage(bushImage, coord[0], coord[1], 30, 30, null);
        }

        handler.render(g2d);
    }

    public static void main(String[] args) {
        new Game();
    }
}
