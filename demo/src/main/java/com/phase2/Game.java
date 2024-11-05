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

    public static final int WIDTH = 1300, HEIGHT = 750; // sets window size
    public static final int BLOCK_SIZE[] = {50,50};

    private Thread thread; 
    private boolean running = false;
    private boolean paused = true; //Game starts in the menu (paused)

    // TODO random for testing only
    // private Random r;
    private Handler handler;
    private Health health;
    private Score score;
    private MainMenu mainMenu; //Menu instance

    // BufferedImage for the background
    private BufferedImage background;
    private BufferedImage bushImage; 

    // private static final double BUSH_SCALE_FACTOR = 0.025;

    /**
     * Constructor for the Game class.
     * Initializes the handler and sets up the game window.
     */
    public Game() {

    // TODO if multiple levels, create attribute for numbers of enemies/rewards to generate
    // then pass to a main class?


        handler = new Handler();
        mainMenu = new MainMenu(this); //Initialize Main Menu
        
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

        health = new Health(); 
        score = new Score();

        // Used for testing only
        handler.addObject(new Doug(200, 200, ID.DOUG, handler));
        handler.addObject(new Apple(BLOCK_SIZE[0], BLOCK_SIZE[1], RewardType.APPLE));
        handler.addObject(new Bone(BLOCK_SIZE[0], 2*BLOCK_SIZE[1], RewardType.BONE));
        handler.addObject(new Steak(BLOCK_SIZE[0], 3*BLOCK_SIZE[1], RewardType.STEAK));
        handler.addObject(new Mushroom(BLOCK_SIZE[0], 4*BLOCK_SIZE[1], RewardType.MUSHROOM));
        handler.addObject(new Exit(5*BLOCK_SIZE[0], 5*BLOCK_SIZE[1]));
        //

        // TODO for random movement testing only 
        // r = new Random();
        // TODO if multiple levels, create attribute for # of rats
        // for(int i = 0; i < 20; i++)
        // handler.addObject(new Rat(r.nextInt(WIDTH - 200, HEIGHT - 150, 5))); // Penalty points set to 5
        handler.addObject(new Rat(WIDTH - 200, HEIGHT - 150)); // Penalty points set to 5

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
        this.requestFocus();
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
                if (!paused) tick();
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
        health.tick();
        score.tick();
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
        g2d.drawImage(bushImage, 0, 0, bushWidth, bushHeight, null); // Top-left corner
        g2d.drawImage(bushImage, WIDTH - bushWidth - 10, 0, bushWidth, bushHeight, null); // Top-right corner with slight offset
        g2d.drawImage(bushImage, 0, HEIGHT - bushHeight - 35, bushWidth, bushHeight, null); // Bottom-left corner with offset
        g2d.drawImage(bushImage, WIDTH - bushWidth - 15, HEIGHT - bushHeight - 35, bushWidth, bushHeight, null); // Bottom-right corner with offset

        // Manually place bushes along the top border, excluding the corners
        for (int x = bushWidth; x <= WIDTH - bushWidth; x += bushWidth) {
            g2d.drawImage(bushImage, x, 0, bushWidth, bushHeight, null); // Top border
        }

        // Manually place bushes along the bottom border, excluding the corners
        for (int x = bushWidth; x <= WIDTH - bushWidth - 15; x += bushWidth) {
            g2d.drawImage(bushImage, x, HEIGHT - bushHeight - 25, bushWidth, bushHeight, null); // Bottom border with offset
        }

        // Manually place bushes along the left border, excluding the corners
        for (int y = bushHeight; y <= HEIGHT - bushHeight; y += bushHeight) {
            g2d.drawImage(bushImage, 0, y, bushWidth, bushHeight, null); // Left border
        }

        // Manually place bushes along the right border, excluding the corners
        for (int y = bushHeight; y <= HEIGHT - bushHeight - 35; y += bushHeight) {
            g2d.drawImage(bushImage, WIDTH - bushWidth - 15, y, bushWidth, bushHeight, null); // Right border with offset
        }

        int[][] mazeBushCoordinates = {
            {90, HEIGHT - bushHeight - 35}, {90, HEIGHT - bushHeight * 2 - 35}, {90, HEIGHT - bushHeight * 3 - 35}, 
            {90, HEIGHT - bushHeight * 4 - 35}, {90, HEIGHT - bushHeight * 5 - 35}, {120, HEIGHT - bushHeight * 5 - 35},
            {150, HEIGHT - bushHeight * 5 - 35}, {180, HEIGHT - bushHeight * 5 - 35}, {210, HEIGHT - bushHeight * 5 - 35},
            {240, HEIGHT - bushHeight * 5 - 35},{270, HEIGHT - bushHeight * 5 - 35},{300, HEIGHT - bushHeight * 5 - 35},
            {90, HEIGHT - bushHeight * 9 - 35},{90, HEIGHT - bushHeight * 10 - 35},{90, HEIGHT - bushHeight * 11 - 35},
            {90, HEIGHT - bushHeight * 12 - 35}, {120, HEIGHT - bushHeight * 12 - 35}, {150, HEIGHT - bushHeight * 12 - 35},
            {180, HEIGHT - bushHeight * 12 - 35}, {120, HEIGHT - bushHeight * 9 - 35}, {150, HEIGHT - bushHeight * 9 - 35},
            {180, HEIGHT - bushHeight * 9 - 35}
        };

        for (int[] coord : mazeBushCoordinates) {
            g2d.drawImage(bushImage, coord[0], coord[1], 30, 30, null);
        }


        if(paused){
            mainMenu.render(g); //Render the main menu if paused
        } else {
            handler.render(g2d); // Render the actual game
            health.render(g2d);
            score.render(g2d);
            g.dispose();
            bs.show();
        }
        // Render game objects
        //handler.render(g2d);

        // health bar render
        //health.render(g2d);

        //score.render(g2d);

        g.dispose();
        bs.show();
    }

    // TODO javadoc
    // prevents out of bounds movement
    public static int clamp(int var, int min, int max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public void debugMode(Boolean debug) {
        handler.setDebug(debug);
    }

    public static void main(String[] args) {
        new Game();
    }

}