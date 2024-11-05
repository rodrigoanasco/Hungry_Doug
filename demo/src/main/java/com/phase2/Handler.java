package com.phase2;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * The Handler class is responsible for managing all game objects in the game.
 * It updates and renders each object in the game loop.
 */
public class Handler {

    //testing
    // private Doug doug;

    private Boolean debug = false;
    private Game game; //A reference to the Game Instance
    // list of all objects in game (Doug, enemies, food, etc)
    LinkedList<GameObject> objects = new LinkedList<GameObject >();
    
    public Handler(Game game){
        this.game = game;
    }
    /**
     * Updates all game objects in the game.
     * This is called for every game tick.
     */
    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i); 
            tempObject.tick(); 
        }
    }

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
    public void addObject(GameObject object){
        this.objects.add(object);
    }

    /**
     * Removes a game object from the game.
     * 
     * @param object the GameObject to be removed
     */
    public void removeObject(GameObject object){
        this.objects.remove(object); 
    }


    public void setDebug(Boolean d) {
        this.debug = d;
    }

    
}
