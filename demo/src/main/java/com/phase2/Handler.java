package com.phase2;

import java.awt.Graphics;
import java.util.LinkedList;

import com.phase2.GameObjects.GameObject;
import com.phase2.Trackers.ID;

/**
 * The Handler class is responsible for managing all game objects in the game.
 * It updates and renders each object in the game loop.
 */
public class Handler {

    private int tickCount = 0;
    private Game game; //A reference to the Game Instance
    private static Handler handler = null;

    // list of all objects in game (Doug, enemies, food, etc)
    LinkedList<GameObject> objects = new LinkedList<GameObject >();

    // New list for obstacles only (e.g., bushes)
    LinkedList<GameObject> obstacles = new LinkedList<GameObject>();

    
    /**
     * Constructs a Handler with a reference to the main game instance.
     */
    public Handler(Game game){
        this.game = game;
    }


    /**
     * Updates all game objects in the game.
     * This is called for every game tick.
     */
    public synchronized void tick() {
        tickCount++;
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }

    /**
    * Returns the number of ticks processed by the game.
    *
    * @return The total tick count.
    */
    /* Removed in refactoring, never used
    public synchronized int getTickCount() {
        return tickCount;
    } */

    /**
     * Returns the main game instance associated with this handler.
     * 
     * @return The main Game instance.
     */
    public Game getGameInstance(){
        return game;
    }

    /**
     * Returns the main Handler instance associated with this handler.
     * 
     * @return The main Handler instance.
     */
    public static synchronized Handler getHandlerInstance(Game game){
        if (handler == null) {
            handler = new Handler(game);
        }
        return handler;
    }

    /**
     * Returns the singleton instance of Handler. 
     * If Doug has not been initialized, an exception is thrown.
     * 
     * @return The singleton instance of Handler.
     * @throws IllegalStateException If Handler has not been initialized.
     */
    public static Handler getHandlerInstance() {
        if (handler == null) {
            throw new IllegalStateException("Handler has not been initialized. Call getHandlerInstance(Game) first.");
        }
        return handler;
    }
    
    /**
     * Set instance (Just for testing)
     * Resets the singleton instance
     */
    public static void setHandlerInstance(){
        handler = null;
    }


    /**
     * Renders all game objects.
     * 
     * @param g the Graphics object used for rendering
     */
    public void render(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i); 
            tempObject.render(g);
        }
    }

    /**
     * Adds a new game object to the game.
     * 
     * @param object the GameObject to be added
     */
    public synchronized void addObject(GameObject object) {
        this.objects.add(object);

        if (object.getId() == ID.OBSTACLE) {
            this.obstacles.add(object);
        }
    }

    /**
     * Clears all game objects from the list of objects.
     * This will remove all objects currently tracked by the handler.
    */

     public synchronized void clearObjects() {
        objects.clear();
    }

    /**
     * Clears all obstacles from the list of obstacles.
     * This will remove all obstacles currently tracked by the handler.
     */
    public synchronized void clearObstacles() {
        obstacles.clear();
    }

    
}
