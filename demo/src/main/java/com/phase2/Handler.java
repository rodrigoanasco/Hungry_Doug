package com.phase2;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    // list of all objects in game (Doug, enemies, food, etc)
    LinkedList<GameObject> objects = new LinkedList<GameObject >();
    
    // loops through all game objects and updates them
    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i); 
            tempObject.tick(); 
        }
    }

    //loops through all game objects and renders them
    public void render(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i); 
            tempObject.render(g);
        }
    }

    // adds object to list of game objects
    public void addObject(GameObject object){
        this.objects.add(object);
    }

    // removes object from list of game objects
    public void removeObject(GameObject object){
        this.objects.remove(object); 
    }

  
}
