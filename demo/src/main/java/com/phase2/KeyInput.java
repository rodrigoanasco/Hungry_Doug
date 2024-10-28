package com.phase2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The KeyInput class handles keyboard input and updates the game objects accordingly.
 */
public class KeyInput extends KeyAdapter{

    private Handler handler;

    /**
     * Constructor for KeyInput.
     * 
     * @param handler the Handler object responsible for managing game objects
     */
    public KeyInput(Handler handler){
        this.handler = handler;
    }

    /**
     * Handles key press events and updates the game objects' positions.
     * 
     * @param e the KeyEvent triggered when a key is pressed
     */
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        // for testing only
        System.out.println(key);
        //

        // loops through all objects in game to find doug. there must be a bette  way to do this since he's the only movable object
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);
            if(temp.getId() == ID.DOUG){
                
                // can change key mapping here
                // Move up
                if(key == KeyEvent.VK_W) temp.setY(temp.getY() - 10);
                // Move down
                if(key == KeyEvent.VK_S) temp.setY(temp.getY() + 10);
                // Move left
                if(key == KeyEvent.VK_A) temp.setX(temp.getX() - 10);
                // Move right
                if(key == KeyEvent.VK_D) temp.setX(temp.getX() + 10);
            }
        }

    }

    /**
     * Handles key release events (currently not used).
     * 
     * @param e the KeyEvent triggered when a key is released
     */
    public void keyReleased(KeyEvent e){
 
    }
}
