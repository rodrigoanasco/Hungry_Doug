package com.phase2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
    private boolean gameWon = false; // Tracker to check if the game is won
    private boolean gameOver = false; //Game over tracker

    private Handler handler;

    private Doug doug;
    private Health health;
    private Score score;

    private MainMenu mainMenu; //Menu instance
    private WinningScreen winningScreen; //Tracking the winning screen
    private GameOverScreen gameOverScreen;

    // BufferedImage for the background
    private BufferedImage background;
    private BufferedImage bushImage; 

    // Define grid as a map of cells containing game objects
    public static HashMap<String, ArrayList<GameObject>> grid = new HashMap<>();

    private BackgroundMusic backgroundMusic;

    public static int getCellIndex(int coordinate) {
        return coordinate / GRID_SIZE;
    }

    /**
     * Add an objet to the grid
     * 
     * @param object the object to be added
     */
    public static void addToGrid(GameObject object) {
        int cellX = getCellIndex(object.getX());
        int cellY = getCellIndex(object.getY());
        String key = cellX + "," + cellY;

        grid.putIfAbsent(key, new ArrayList<>());
        grid.get(key).add(object);
    }

    /**
     * Remove an object from the grid
     * 
     * @param object the object to be removed
     */
    public static void removeFromGrid(GameObject object) {
        int cellX = getCellIndex(object.getX());
        int cellY = getCellIndex(object.getY());
        String key = cellX + "," + cellY;

        if (grid.containsKey(key)) {
            grid.get(key).remove(object);
        }
    }

    /**
     * Constructor for the Game class.
     * Initializes the handler and sets up the game window.
     */
    public Game() {
        
        
        backgroundMusic = new BackgroundMusic(); // Initialize the backgroundMusic object first
        backgroundMusic.play("/backgroundmusic.wav", -15.0f);

        handler = new Handler(this);
        mainMenu = new MainMenu(this); // Initialize Main Menu
        winningScreen = new WinningScreen(this); // Initialize the winning screen
        gameOverScreen = new GameOverScreen(this); // Initialize the game over screen

        this.addKeyListener(new KeyInput(handler, this)); // Recieves keyboard input

        // Load the background image
        try {
            background = ImageIO.read(getClass().getResource("/grassback.png"));
            bushImage = ImageIO.read(getClass().getResource("/bush.png"));
        } 
        catch (IOException e) { 
            e.printStackTrace();
        }

        new Window(WIDTH, HEIGHT, "Hungry Doug", this);

        health = new Health(); 
        score = new Score();

        initializeGameObjects();

    }

    /**
     * Generates a specified number of random game objects of a given type and adds them to the handler.
     * The objects are placed at random positions within the game window.
     * 
     * @param count The number of objects to generate.
     * @param objectType The class type of the game objects to create.
     * @param handler The handler responsible for managing the game objects.
     */
    public void generateRandomObjects(int count, Class<? extends GameObject> objectType, Handler handler, int minDistanceFromDoug) {
        Random r = new Random();
        Doug doug = Doug.getInstance();

        for (int i = 0; i < count; i++) {
            int randomX, randomY;

            // Generate positions until they are valid (not overlapping other objects)
            boolean validPosition;
            do {
                randomX = r.nextInt(Game.WIDTH - 200); // X coordinate within game width minus margin
                randomY = r.nextInt(Game.HEIGHT - 150); // Y coordinate within game height minus margin

                validPosition = isValidPosition(randomX, randomY, minDistanceFromDoug, doug, handler);
            } while (!validPosition);

            try {
                GameObject obj = objectType.getConstructor(int.class, int.class).newInstance(randomX, randomY);
                handler.addObject(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates a specified number of Rat enemies at random positions in the game.
     *
     * @param count The number of Rat enemies to generate.
     * @param handler The handler responsible for managing the game objects.
     * @param minDistanceFromDoug The minimum distance the Rats should spawn from Doug.
     */
    public void generateRandomEnemies(int count, Handler handler, int minDistanceFromDoug) {
        Random r = new Random();
        Doug doug = Doug.getInstance(); // Assuming Doug is a singleton

        for (int i = 0; i < count; i++) {
            int randomX, randomY;

            // Generate random positions until they are valid
            boolean validPosition;
            do {
                randomX = r.nextInt(Game.WIDTH - 200); // Adjust to avoid spawning too close to edges
                randomY = r.nextInt(Game.HEIGHT - 150);
                validPosition = isValidPosition(randomX, randomY, minDistanceFromDoug, doug, handler);
            } while (!validPosition);

            // Create a new Rat and add it to the handler
            Rat rat = new Rat(randomX, randomY, handler);
            handler.addObject(rat);
        }
    }

    /**
    * Validates whether a given position is suitable for object placement.
    *
    * The position is considered valid if:
    * - It is at least the specified minimum distance from Doug.
    * - It does not intersect with any existing objects or bushes managed by the handler.
    *
    * @param x
    * @param y
    * @param minDistanceFromDoug
    * @param doug
    * @param handler
    * @return True if position is valid, false otherwise
    */
    private boolean isValidPosition(int x, int y, int minDistanceFromDoug, Doug doug, Handler handler) {
        // Check distance from Doug
        if (distance(x, y, doug.getX(), doug.getY()) < minDistanceFromDoug) {
            return false;
        }
    
        // Check for collisions with existing objects
        for (GameObject obj : handler.objects) { // Loop directly through handler.objects
            if (obj instanceof Bush || obj instanceof GameObject) { // Avoid bushes and other game objects
                if (new Rectangle(x, y, 30, 30).intersects(obj.getBounds())) {
                    return false;
                }
            }
        }
    
        return true;
    }

     /**
      * Helper method to calculate distance between two points
      *
      * @param x1 x-coordinate of the first point
      * @param y1 y-coordinate of the first point
      * @param x2 x-coordinate of the second point
      * @param y2 y-coordinate of the second point
      * @return Euclidean distance between two points
      */
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
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
     * Checks if the game is in pause or not (Used for main menu)
     */
    public boolean isPaused(){
        return paused;
    }

    /**
     * Checks if the game is over or not (Used for main menu)
     */
    public boolean isGameOver(){
        return gameOver;
    }

    /**
     * Sets the game state to indicate whether the game has been won.
     * 
     * @param gameWon True if the game has been won, false otherwise.
     */
    public void setGameWon(boolean gameWon){
        this.gameWon = gameWon;
    }

    /**
     * Checks if the game has been won.
     * 
     * @return True if the game is won, false otherwise.
     */
    public boolean isGameWon(){
        return gameWon;
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
        if (!running) return; // Prevent redundant calls
    
        running = false; // Ensure the game loop exits
        try {
            if (backgroundMusic != null) {
                backgroundMusic.stop(); // Stop background music
            }
            if (thread != null) {
                thread.join(2000); // Wait for the thread to terminate
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println("Game stopped.");
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
                if (!paused && !gameOver) {
                    tick();
                }
                delta--;    
            }
            if (running) {
                render();
                // tick();
            }
            // frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        // stop();
    }

    /**
     * Updates the game state for each game tick.
     */
    private void tick(){
        if(!gameOver){
            handler.tick();
            health.tick();
            score.tick();
            checkGameOver();
        }
    }

    /**
    * Checks whether the game is currently running.
    *
    * @return {@code true} if the game is running, {@code false} otherwise.
    */
    public boolean isRunning() {
        return running;
    }

    /**
    * Returns the handler associated with the game.
    *
    * @return The {@link Handler} instance managing game objects.
    */
    public Handler getHandler() {
        return handler; // Ensure this matches the variable in your Game class
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
            // In case the backgroud image doesn't load
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
  
        if((paused == true) && (gameOver == false)){
            mainMenu.render(g); //Render the main menu if paused
        } 
        else if(gameWon){
            winningScreen.render(g);
        } else if(checkGameOver()){
            gameOverScreen.render(g);
        } else{
            handler.render(g2d); // Render the actual game
            health.render(g2d);
            score.render(g2d);
            g.dispose();
            bs.show();
        }

        g.dispose();
        bs.show();
    }

    /**
     * Clamps a variable to ensure it stays within the specified bounds.
     * 
     * @param var The variable to clamp.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The clamped value.
     */
    public static int clamp(int var, int min, int max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    // /**
    //  * Enables or disables debug mode for the game.
    //  * 
    //  * @param debug True to enable debug mode, false to disable it.
    //  */
    // public void debugMode(Boolean debug) {
    //     handler.setDebug(debug);
    // }

    /**
     * Resets the game to its initial state, including health, score, and game objects.
     */
    public void resetGame(){
        gameOver = false;
        paused = false;
        gameWon = false;

        Health.HEALTH = 200;
        Score.SCORE = 0;
        Score.boneScore = 0;
        Score.boneTotal = 0;

        handler.clearObjects();
        
        initializeGameObjects();
    } 

    private void initializeGameObjects() {
        // Recreate the player (Doug)
        doug = Doug.getInstance(200, 200, ID.DOUG, handler);
        doug.resetPosition();
        handler.addObject(doug);

        // Add bushes and maze structures
        addBushes();

        // Generate random enemies and items
        generateRandomEnemies(1, handler,100);
        generateRandomObjects(1, Bone.class, handler, 5);
        generateRandomObjects(5, Apple.class, handler,5);
        generateRandomObjects(5, Steak.class, handler, 5);
        generateRandomObjects(5, Mushroom.class, handler,5);
        generateRandomObjects(5, Onion.class, handler,5);
        generateRandomObjects(5, Chocolate.class, handler,5);
        generateRandomObjects(5, Whiskey.class, handler,5);
    
        // Add the exit point
        handler.addObject(new Exit(BLOCK_SIZE[0], 5 * BLOCK_SIZE[1]));
    
    
    }

    private void addBushes() {
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

            // little box on right
            {90, HEIGHT - bushHeight * 13 - 35}, {120, HEIGHT - bushHeight * 13 - 35}, {150, HEIGHT - bushHeight * 13 - 35},
            {180, HEIGHT - bushHeight * 13 - 35}, {120, HEIGHT - bushHeight * 9 - 35}, {150, HEIGHT - bushHeight * 9 - 35},
            {180, HEIGHT - bushHeight * 9 - 35}, {90, HEIGHT - bushHeight * 12 - 35},

            {210, HEIGHT - bushHeight * 9 - 35}, {240, HEIGHT - bushHeight * 9 - 35}, {270, HEIGHT - bushHeight * 9 - 35},
            {300, HEIGHT - bushHeight * 9 - 35},{330, HEIGHT - bushHeight * 9 - 35}, {360, HEIGHT - bushHeight * 9 - 35},
            {390, HEIGHT - bushHeight * 9 - 35}, {420, HEIGHT - bushHeight * 9 - 35}, {510, HEIGHT - bushHeight * 9 - 35},
            {540, HEIGHT - bushHeight * 9 - 35}, {570, HEIGHT - bushHeight * 9 - 35}, {600, HEIGHT - bushHeight * 9 - 35},
            {630, HEIGHT - bushHeight * 9 - 35}, {660, HEIGHT - bushHeight * 9 - 35}, {690, HEIGHT - bushHeight * 9 - 35},
            {690, HEIGHT - bushHeight * 8 - 35}, {690, HEIGHT - bushHeight * 7 - 35}, {690, HEIGHT - bushHeight * 6 - 35},
            {690, HEIGHT - bushHeight * 5 - 35}, {720, HEIGHT - bushHeight * 9 - 35}, {750, HEIGHT - bushHeight * 9 - 35},
            {780, HEIGHT - bushHeight * 9 - 35}, {810, HEIGHT - bushHeight * 9 - 35}, {840, HEIGHT - bushHeight * 9 - 35},
            {870, HEIGHT - bushHeight * 9 - 35}, {870, HEIGHT - bushHeight * 10 - 35},{870, HEIGHT - bushHeight * 11 - 35},
            {870, HEIGHT - bushHeight * 12 - 35}, {870, HEIGHT - bushHeight * 13 - 35}, {870, HEIGHT - bushHeight * 14 - 35},
            {870, HEIGHT - bushHeight * 15 - 35}, {870, HEIGHT - bushHeight * 16 - 35}, {870, HEIGHT - bushHeight * 17 - 35},
            {870, HEIGHT - bushHeight * 18 - 35}, {870, HEIGHT - bushHeight * 19 - 35}, {840, HEIGHT - bushHeight * 19 - 35},
            {810, HEIGHT - bushHeight * 19 - 35}, {780, HEIGHT - bushHeight * 19 - 35}, {750, HEIGHT - bushHeight * 19 - 35},
            {720, HEIGHT - bushHeight * 19 - 35}, {690, HEIGHT - bushHeight * 19 - 35},
            {990, HEIGHT - bushHeight * 19 - 35}, {1020, HEIGHT - bushHeight * 19 - 35}, {1050, HEIGHT - bushHeight * 19 - 35},
            {1080, HEIGHT - bushHeight * 19 - 35}, {1110, HEIGHT - bushHeight * 19 - 35}, {1140, HEIGHT - bushHeight * 19 - 35},
            {990, HEIGHT - bushHeight * 18 - 35}, {990, HEIGHT - bushHeight * 17 - 35}, {990, HEIGHT - bushHeight * 16 - 35},
            {990, HEIGHT - bushHeight * 15 - 35}, {1140, HEIGHT - bushHeight * 18 - 35}, {1140, HEIGHT - bushHeight * 17 - 35},
            {1140, HEIGHT - bushHeight * 16 - 35}, {1140, HEIGHT - bushHeight * 15 - 35}, {1140, HEIGHT - bushHeight * 11 - 35},
            {1140, HEIGHT - bushHeight * 10 - 35}, {1140, HEIGHT - bushHeight * 9 - 35}, {1140, HEIGHT - bushHeight * 8 - 35},
            {1140, HEIGHT - bushHeight * 7 - 35}, {1140, HEIGHT - bushHeight * 6 - 35}, {990, HEIGHT - bushHeight * 11 - 35},
            {990, HEIGHT - bushHeight * 10 - 35}, {990, HEIGHT - bushHeight * 9 - 35}, {990, HEIGHT - bushHeight * 8 - 35},
            {990, HEIGHT - bushHeight * 7 - 35}, {990, HEIGHT - bushHeight * 6 - 35}, {1020, HEIGHT - bushHeight * 6 - 35},
            {1050, HEIGHT - bushHeight * 6 - 35}, {1080, HEIGHT - bushHeight * 6 - 35}, {1110, HEIGHT - bushHeight * 6 - 35},
            {1170, HEIGHT - bushHeight * 6 - 35}, {1200, HEIGHT - bushHeight * 6 - 35}, {1230, HEIGHT - bushHeight * 6 - 35},



            {210, HEIGHT - bushHeight * 13 - 35}, {240, HEIGHT - bushHeight * 13 - 35}, {270, HEIGHT - bushHeight * 13 - 35},
            {270, HEIGHT - bushHeight * 14 - 35}, {270, HEIGHT - bushHeight * 14 - 35}, {270, HEIGHT - bushHeight * 15 - 35},
            {270, HEIGHT - bushHeight * 16 - 35}, {270, HEIGHT - bushHeight * 17 - 35}, {270, HEIGHT - bushHeight * 18 - 35},
            {270, HEIGHT - bushHeight * 19 - 35}, {300, HEIGHT - bushHeight * 19 - 35}, {330, HEIGHT - bushHeight * 19 - 35},
            {360, HEIGHT - bushHeight * 19 - 35}, {390, HEIGHT - bushHeight * 19 - 35}, {420, HEIGHT - bushHeight * 19 - 35},
            {450, HEIGHT - bushHeight * 19 - 35}, {480, HEIGHT - bushHeight * 19 - 35}, {510, HEIGHT - bushHeight * 19 - 35},
            {600, HEIGHT - bushHeight * 19 - 35}, {630, HEIGHT - bushHeight * 19 - 35}, {660, HEIGHT - bushHeight * 19 - 35}
        };
        
        for (int[] coord : mazeBushCoordinates) {
            Bush bush = new Bush(coord[0], coord[1]);
            handler.addObject(bush);
            addToGrid(bush);
        }
    }

    /**
     * Checks if the game is over based on the player's health.
     * 
     * @return True if the game is over (health is zero or below), false otherwise.
     */
    public boolean checkGameOver() {
        if (Health.HEALTH <= 0) {
            gameOver = true;
            paused = !paused;
            return true;
        }
        else{
            gameOver = false;
            return false;
        }
    }

    //TODO are we using this?
    public static void main(String[] args) {
        Game game = new Game();
    }

}