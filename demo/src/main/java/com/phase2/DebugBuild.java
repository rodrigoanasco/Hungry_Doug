package com.phase2;


/**
 * A test build to run the game
 * - currently has hitboxes
 */
public class DebugBuild {
    public static void main(String[] args) {
        Game test = new Game();
        test.debugMode(true);
    }
}