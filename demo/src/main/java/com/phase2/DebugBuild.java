package com.phase2;

/**
 * A test build to run the game in debug mode.
 * This build enables hitboxes and other debug features for testing purposes.
 */
public class DebugBuild {
    public static void main(String[] args) {
        Game test = new Game();
        test.debugMode(true);
    }
}