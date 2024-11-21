package com.phase2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The KeyInput class handles keyboard input and updates the game objects accordingly.
 */
public class KeyInput extends KeyAdapter{

    private Handler handler;
    private Game game;
    private boolean up = false, down = false, left = false, right = false;

    /**
     * Constructor for KeyInput.
     * 
     * @param handler the Handler object responsible for managing game objects
     */
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    /**
     * Handles key press events and updates the game objects' positions.
     * 
     * @param e the KeyEvent triggered when a key is pressed
     */
    @Override
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ESCAPE){
            game.togglePause();
            return;
        }

        if(game.isPaused()){
            return;
        }

        // for testing only
        // System.out.println(key);
        //

        // loops through all objects in game to find doug. there must be a bette  way to do this since he's the only movable object
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.DOUG) {
                // Reset both velocities to prevent diagonal movement
                tempObject.setVelX(0);
                tempObject.setVelY(0);

                // Handle directional input, only allowing one axis of movement at a time
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    up = true;
                    down = false; // Ensure down is not active
                    tempObject.setVelY(-5); // Move up
                } 
                else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    down = true;
                    up = false; // Ensure up is not active
                    tempObject.setVelY(5); // Move down
                } 
                else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    left = true;
                    right = false; // Ensure right is not active
                    tempObject.setVelX(-5); // Move left
                } 
                else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    right = true;
                    left = false; // Ensure left is not active
                    tempObject.setVelX(5); // Move right
                }
            }
        }

        // close game on esc
        if(key == KeyEvent.VK_ESCAPE) game.togglePause();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(game.isPaused()){
            return;
        }

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.DOUG) {
                // Handle key release and stop movement appropriately
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    up = false;
                    if (down) {
                        tempObject.setVelY(5); // Continue moving down if down is still pressed
                    } 
                    else {
                        tempObject.setVelY(0); // Stop vertical movement
                    }
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    down = false;
                    if (up) {
                        tempObject.setVelY(-5); // Continue moving up if up is still pressed
                    } 
                    else {
                        tempObject.setVelY(0); // Stop vertical movement
                    }
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    left = false;
                    if (right) {
                        tempObject.setVelX(5); // Continue moving right if right is still pressed
                    } 
                    else {
                        tempObject.setVelX(0); // Stop horizontal movement
                    }
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    right = false;
                    if (left) {
                        tempObject.setVelX(-5); // Continue moving left if left is still pressed
                    } 
                    else {
                        tempObject.setVelX(0); // Stop horizontal movement
                    }
                }
            }
        }
    }
}