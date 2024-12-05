package com.phase2.GameProperties;

/**
 * The {@code GameConfig} class provides configuration constants for the game.
 * <p>
 * This class includes settings such as window dimensions, grid size, block size,
 * and file paths for assets like images and music. These constants are used 
 * throughout the game to ensure consistency in layout and design.
 * </p>
 */
public class GameConfig {

    /**
     * The width of the game window in pixels.
     */
    public static final int WINDOW_WIDTH = 1300;

    /**
     * The height of the game window in pixels.
     */
    public static final int WINDOW_HEIGHT = 750;

    /**
     * The size of the grid used in the game for positioning objects, in pixels.
     */
    public static final int GRID_SIZE = 1000;

    /**
     * The size of each block in the game grid, represented as an array [width, height].
     */
    public static final int[] BLOCK_SIZE = {50, 50};

    /**
     * The file path to the background image used in the game.
     */
    public static final String BACKGROUND_IMAGE_PATH = "/grassback.png";

    /**
     * The file path to the bush image used for obstacles in the game.
     */
    public static final String BUSH_IMAGE_PATH = "/bush.png";

    /**
     * The file path to the background music file used in the game.
     */
    public static final String BACKGROUND_MUSIC_PATH = "/backgroundmusic.wav";
}
