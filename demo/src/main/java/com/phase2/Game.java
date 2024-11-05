package com.phase2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * The Game class manages the main game loop and window.
 * It initializes the game components, handles input, and manages the game's continuous execution.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1300, HEIGHT = 750; // sets window size
    public static final int BLOCK_SIZE[] = {50,50};

    public static final int GRID_SIZE = 1000;

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

    // Define grid as a map of cells containing game objects
    public static HashMap<String, ArrayList<GameObject>> grid = new HashMap<>();

    public static int getCellIndex(int coordinate) {
        return coordinate / GRID_SIZE;
    }

    // Method to add an object to the grid
    public static void addToGrid(GameObject object) {
        int cellX = getCellIndex(object.getX());
        int cellY = getCellIndex(object.getY());
        String key = cellX + "," + cellY;

        grid.putIfAbsent(key, new ArrayList<>());
        grid.get(key).add(object);
    }

    // Method to remove an object from the grid
    public static void removeFromGrid(GameObject object) {
        int cellX = getCellIndex(object.getX());
        int cellY = getCellIndex(object.getY());
        String key = cellX + "," + cellY;

        if (grid.containsKey(key)) {
            grid.get(key).remove(object);
        }
    }
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
        //

        // TODO for random movement testing only 
        // r = new Random();
        // TODO if multiple levels, create attribute for # of rats
        // for(int i = 0; i < 20; i++)
        // handler.addObject(new Rat(r.nextInt(WIDTH - 200, HEIGHT - 150, 5))); // Penalty points set to 5
        handler.addObject(new Rat(WIDTH - 200, HEIGHT - 150)); // Penalty points set to 5

        
        // Adding bushes as obstacles
        int bushWidth = 30;
        int bushHeight = 30;
        
        // Adding bushes in each corner
        Bush bushTopLeft = new Bush(0, 0);
        handler.addObject(bushTopLeft);
        addToGrid(bushTopLeft);
        
        Bush bushTopRight = new Bush(Game.WIDTH - bushWidth - 10, 0);
        handler.addObject(bushTopRight);
        addToGrid(bushTopRight);
        
        Bush bushBottomLeft = new Bush(0, Game.HEIGHT - bushHeight - 35);
        handler.addObject(bushBottomLeft);
        addToGrid(bushBottomLeft);
        
        Bush bushBottomRight = new Bush(Game.WIDTH - bushWidth - 15, Game.HEIGHT - bushHeight - 35);
        handler.addObject(bushBottomRight);
        addToGrid(bushBottomRight);
        
        // Adding bushes along the top border, excluding the corners
        for (int x = bushWidth; x <= Game.WIDTH - bushWidth; x += bushWidth) {
            Bush bush = new Bush(x, 0);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding bushes along the bottom border, excluding the corners
        for (int x = bushWidth; x <= Game.WIDTH - bushWidth - 15; x += bushWidth) {
            Bush bush = new Bush(x, Game.HEIGHT - bushHeight - 35);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding bushes along the left border, excluding the corners
        for (int y = bushHeight; y <= Game.HEIGHT - bushHeight; y += bushHeight) {
            Bush bush = new Bush(0, y);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding bushes along the right border, excluding the corners
        for (int y = bushHeight; y <= Game.HEIGHT - bushHeight - 35; y += bushHeight) {
            Bush bush = new Bush(Game.WIDTH - bushWidth - 15, y);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding maze bushes from maze coordinates
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
            Bush bush = new Bush(coord[0], coord[1]);
            handler.addObject(bush);
            addToGrid(bush);
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
        /* 
        // Adding bushes as obstacles
        int bushWidth = 30;
        int bushHeight = 30;
        
        // Adding bushes in each corner
        Bush bushTopLeft = new Bush(0, 0);
        handler.addObject(bushTopLeft);
        addToGrid(bushTopLeft);
        
        Bush bushTopRight = new Bush(Game.WIDTH - bushWidth - 10, 0);
        handler.addObject(bushTopRight);
        addToGrid(bushTopRight);
        
        Bush bushBottomLeft = new Bush(0, Game.HEIGHT - bushHeight - 35);
        handler.addObject(bushBottomLeft);
        addToGrid(bushBottomLeft);
        
        Bush bushBottomRight = new Bush(Game.WIDTH - bushWidth - 15, Game.HEIGHT - bushHeight - 35);
        handler.addObject(bushBottomRight);
        addToGrid(bushBottomRight);
        
        // Adding bushes along the top border, excluding the corners
        for (int x = bushWidth; x <= Game.WIDTH - bushWidth; x += bushWidth) {
            Bush bush = new Bush(x, 0);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding bushes along the bottom border, excluding the corners
        for (int x = bushWidth; x <= Game.WIDTH - bushWidth - 15; x += bushWidth) {
            Bush bush = new Bush(x, Game.HEIGHT - bushHeight - 35);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding bushes along the left border, excluding the corners
        for (int y = bushHeight; y <= Game.HEIGHT - bushHeight; y += bushHeight) {
            Bush bush = new Bush(0, y);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding bushes along the right border, excluding the corners
        for (int y = bushHeight; y <= Game.HEIGHT - bushHeight - 35; y += bushHeight) {
            Bush bush = new Bush(Game.WIDTH - bushWidth - 15, y);
            handler.addObject(bush);
            addToGrid(bush);
        }
        
        // Adding maze bushes from maze coordinates
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
            Bush bush = new Bush(coord[0], coord[1]);
            handler.addObject(bush);
            addToGrid(bush);
        }

         */
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