package com.phase2.Enemies;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.phase2.Handler;
import com.phase2.MovingEnemy;
import com.phase2.Trackers.Health;

/**
 * Represents the Rat enemy type in the game.
 * <p>
 * The Rat is a moving enemy that alternates between idle and walking animations.
 * This class handles the Rat's behavior, including movement and rendering.
 * </p>
 * 
 * @see MovingEnemy
 */
public class Rat extends MovingEnemy {

    /**
     * A handler for managing game objects.
     */
    Handler handler;

    /**
     * An array of sprites used for the Rat's idle animation.
     */
    private BufferedImage[] idleSprites;

    /**
     * An array of sprites used for the Rat's walking animation.
     */
    private BufferedImage[] walkSprites;

    /**
     * The current frame of the animation being displayed.
     */
    private int currentFrame = 0;

    /**
     * The delay between animation frames, controlling animation speed.
     */
    private int frameDelay = 25;

    /**
     * Counts the number of ticks since the last frame update.
     */
    private int frameCount = 0;

    /**
     * The scale factor for resizing the Rat's sprites.
     */
    private static final double SCALE_FACTOR = 1.25;

    /**
     * Constructs a new Rat enemy.
     *
     * @param x the initial x-coordinate of the Rat
     * @param y the initial y-coordinate of the Rat
     */
    public Rat(int x, int y) {
        super(x, y, EnemyType.RAT, Health.HEALTH);
        this.handler = Handler.getHandlerInstance();
        this.WIDTH = 32;
        this.HEIGHT = 32;

        try {
            // Load the idle and walk sprite sheets
            BufferedImage idleSheet = ImageIO.read(getClass().getResource("/ratIdle.png"));
            BufferedImage walkSheet = ImageIO.read(getClass().getResource("/ratWalk.png"));

            // Extract frames for idle animation (assuming 4 frames, each 32x32)
            idleSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                idleSprites[i] = idleSheet.getSubimage(i * 32, 0, WIDTH, HEIGHT);
            }

            // Extract frames for walking animation (assuming 4 frames, each 32x32)
            walkSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                walkSprites[i] = walkSheet.getSubimage(i * 32, 0, WIDTH, HEIGHT);
            }
        } catch (IOException e) {
            // Error occurred while loading Rat sprite images
            System.err.println("Failed to load Rat sprites: " + e.getMessage());
        }

        // Initially, the rat is not moving
        velX = 1;
        velY = 1;
    }

    /**
     * Updates the state of the Rat on each game tick.
     * This method is responsible for moving the Rat and updating its animation.
     */
    @Override
    public void tick() {
        move();
    }

    /**
     * Renders the visual representation of the Rat on the screen.
     *
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Determine which sprite to draw based on movement state
        BufferedImage spriteToDraw = moving ? walkSprites[currentFrame] : idleSprites[currentFrame];

        int scaledWidth = (int) (spriteToDraw.getWidth() * SCALE_FACTOR);
        int scaledHeight = (int) (spriteToDraw.getHeight() * SCALE_FACTOR);

        // Draw the sprite at the Rat's current position
        if (facingRight) {
            // Draw normally if facing right
            g2d.drawImage(spriteToDraw, x, y - 16, scaledWidth, scaledHeight, null);
        } else {
            // Flip horizontally if facing left
            AffineTransform transform = new AffineTransform();
            transform.translate(x + scaledWidth, y - 16); // Move to the correct position
            transform.scale(-SCALE_FACTOR, SCALE_FACTOR); // Flip horizontally
            g2d.drawImage(spriteToDraw, transform, null);
        }

        // Update frame for animation
        frameCount++;
        if (frameCount >= frameDelay) {
            frameCount = 0;
            currentFrame = (currentFrame + 1) % idleSprites.length; // Cycle through frames
        }
    }
}
