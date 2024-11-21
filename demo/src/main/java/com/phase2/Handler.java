package com.phase2;

import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;

/**
 * The Handler class is responsible for managing all game objects in the game.
 * It updates and renders each object in the game loop.
 */
public class Handler {

    private Boolean debug = false;
    private Game game; //A reference to the Game Instance

    // list of all objects in game (Doug, enemies, food, etc)
    LinkedList<GameObject> objects = new LinkedList<GameObject >();

    // New list for obstacles only (e.g., bushes)
    LinkedList<GameObject> obstacles = new LinkedList<GameObject>();

    
    /**
     * Constructs a Handler with a reference to the main game instance.
     * 
     * @param game The main game instance.
     */
    public Handler(Game game){
        this.game = game;
    }
    /**
     * Updates all game objects in the game.
     * This is called for every game tick.
     */
    public synchronized void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }

    /**
     * Returns the main game instance associated with this handler.
     * 
     * @return The main Game instance.
     */
    public Game getGameInstance(){
        return game;
    }

    /**
     * Renders all game objects.
     * 
     * @param g the Graphics object used for rendering
     */
    public void render(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i); 
            if (debug) tempObject.renderHitBox(g);
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

        //TODO test
        if (object.getId() == ID.OBSTACLE) {
            this.obstacles.add(object);
        }
    }

    /**
     * Removes a game object from the game.
     * 
     * @param object the GameObject to be removed
     */
    public synchronized void removeObject(GameObject object) {
        this.objects.remove(object);

        //TODO test
        if (object.getId() == ID.OBSTACLE) {
            this.obstacles.remove(object);
        }
    }

    /**
     * Enables or disables debug mode.
     * When debug mode is enabled, hitboxes of game objects are rendered.
     * 
     * @param d True to enable debug mode, false to disable.
     */
    public void setDebug(Boolean d) {
        this.debug = d;
    }

    public synchronized void clearObjects() {
        objects.clear();
    }    



    //TODO test
    /**
     * Returns the list of obstacles.
     *
     * @return The list of obstacle objects.
     */
    public LinkedList<GameObject> getObstacles() {
        return obstacles;
    }

    
}
