package com.phase2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles keyboard input for the game. Listens for key presses and key releases
 * to control the game objects and game state (e.g., pausing the game).
 */
public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final Game game;

    // Maps to track key states
    private final Map<Integer, Boolean> keyStates = new HashMap<>();

    // Movement speed for Doug
    private static final int MOVE_SPEED = 5;

    // Tracks the last pressed movement key to prioritize direction
    private int lastMovementKey = -1;

    /**
     * Constructs the KeyInput object with the handler and game instance.
     *
     * @param handler The handler managing game objects.
     * @param game    The main game instance.
     */
    public KeyInput(Game game) {
        this.handler = Handler.getHandlerInstance();
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keyStates.put(key, true); // Mark the key as pressed

        // Handle global keys (e.g., pause)
        if (key == KeyEvent.VK_ESCAPE) {
            game.togglePause();
            return;
        }

        // Handle object-specific keys (e.g., movement)
        for (GameObject tempObject : handler.objects) {
            if (tempObject.getId() == ID.DOUG) {
                handleMovementKeys(key, tempObject);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keyStates.put(key, false); // Mark the key as released

        // Handle stopping movement for Doug
        for (GameObject tempObject : handler.objects) {
            if (tempObject.getId() == ID.DOUG) {
                updateMovement(tempObject);
            }
        }
    }

    /**
     * Handles movement keys for Doug.
     *
     * @param key        The key code of the pressed key.
     * @param tempObject The Doug object to control.
     */
    private void handleMovementKeys(int key, GameObject tempObject) {
        if (!game.isPaused()) { // Movement allowed only if the game is not paused
            lastMovementKey = key; // Track the last movement key pressed
            updateMovement(tempObject);
        }
    }

    /**
     * Updates Doug's velocity based on the current key states.
     * Ensures only one axis of movement is active at a time.
     *
     * @param tempObject The Doug object to control.
     */
    private void updateMovement(GameObject tempObject) {
        // Combine logic for WASD and arrow keys
        if (isKeyPressed(KeyEvent.VK_W) || isKeyPressed(KeyEvent.VK_UP)) {
            tempObject.setVelY(-MOVE_SPEED); // Move up
            tempObject.setVelX(0);
        } else if (isKeyPressed(KeyEvent.VK_S) || isKeyPressed(KeyEvent.VK_DOWN)) {
            tempObject.setVelY(MOVE_SPEED); // Move down
            tempObject.setVelX(0);
        } else if (isKeyPressed(KeyEvent.VK_A) || isKeyPressed(KeyEvent.VK_LEFT)) {
            tempObject.setVelX(-MOVE_SPEED); // Move left
            tempObject.setVelY(0);
        } else if (isKeyPressed(KeyEvent.VK_D) || isKeyPressed(KeyEvent.VK_RIGHT)) {
            tempObject.setVelX(MOVE_SPEED); // Move right
            tempObject.setVelY(0);
        } else {
            // Stop movement if no relevant key is pressed
            tempObject.setVelX(0);
            tempObject.setVelY(0);
        }
    }
    
    

    /**
     * Checks if a specific key is currently pressed.
     *
     * @param keyCode The key code to check.
     * @return True if the key is pressed, false otherwise.
     */
    public boolean isKeyPressed(int keyCode) {
        return keyStates.getOrDefault(keyCode, false);
    }
}
