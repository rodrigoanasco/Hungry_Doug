package com.phase2;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.Graphics2D; // Import for Graphics2D
import java.awt.geom.AffineTransform; // Import for AffineTransform

/**
 * Represents the main character, Doug, in the game.
 * Doug has health, a score, and can be rendered and updated 
 * (ticked) within the game.
 */
public class Doug extends GameObject{

    protected int health;
    protected int score;

    private BufferedImage[] idleSprites;
    private BufferedImage[] walkSprites;
    private int currentFrame = 0;
    private int frameDelay = 5; // Controls the animation speed
    private int frameCount = 0;

    private boolean moving = false;
    private boolean facingRight = true; // Default to facing right

    /**
     * Initializes Doug's position, ID, health, and score.
     * Sets default velocity for testing purposes.
     *
     * @param x  The initial x-coordinate of Doug's position.
     * @param y  The initial y-coordinate of Doug's position.
     * @param id The ID that identifies this GameObject as Doug.
     */
    public Doug(int x, int y, ID id){
        super(x,y,id);

        this.health = 100;
        this.score = 0;

        try {
            BufferedImage idleSheet = ImageIO.read(getClass().getResource("/Idle.png"));
            BufferedImage walkSheet = ImageIO.read(getClass().getResource("/Walk.png"));

            // Extract frames for idle animation (assuming 4 frames, each 48x48)
            idleSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                idleSprites[i] = idleSheet.getSubimage(i * 48, 0, 48, 48);
            }

            // Extract frames for walk animation (assuming 6 frames, each 48x48)
            walkSprites = new BufferedImage[6];
            for (int i = 0; i < 6; i++) {
                walkSprites[i] = walkSheet.getSubimage(i * 48, 0, 48, 48);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // used for testing only
        velX = 0;
        velY = 0;
        //
    }

    /**
     * Updates Doug's state for each tick of the game loop.
     */
    @Override
    public void tick(){

        boolean wasMoving = moving;

        // used for testing only
        // moves +1 in x and y direction each tick of the game
        x += velX;
        y += velY;
        //

        // Determine if Doug is moving
        moving = (velX != 0 || velY != 0);

       // Update facing direction based on velocity
    if (velX > 0) {
        facingRight = true;
    } 
    else if (velX < 0) {
        facingRight = false;
    }

    // Check if the moving state has changed
    if (moving != wasMoving) {
        // Reset frame to avoid out-of-bounds issues when changing states
        currentFrame = 0;
    }

    // Update animation frame every few ticks
    frameCount++;
    if (frameCount >= frameDelay) {
        frameCount = 0;
        if (moving) {
            currentFrame = (currentFrame + 1) % walkSprites.length; // Cycle through walk frames
        } else {
            currentFrame = (currentFrame + 1) % idleSprites.length; // Cycle through idle frames
        }
    }
}

    /**
     * Renders Doug on the screen as a green rectangle at his current position.
     * This is primarily used for testing.
     *
     * @param g The Graphics object used to render Doug.
     */
    @Override
    public void render(Graphics g){

        // used for testing only
        //g.setColor(Color.GREEN);
        //g.fillRect(x, y, 32, 32);
        //

    Graphics2D g2d = (Graphics2D) g;

    BufferedImage spriteToDraw = moving ? walkSprites[currentFrame] : idleSprites[currentFrame];

    if (facingRight) {
        // Draw normally if facing right
        g2d.drawImage(spriteToDraw, x, y, null);
    } 
    else {
        // Flip horizontally if facing left
        AffineTransform transform = new AffineTransform();
        transform.translate(x + spriteToDraw.getWidth(), y); // Move to the correct position
        transform.scale(-1, 1); // Flip horizontally
        g2d.drawImage(spriteToDraw, transform, null);
    }

    }

    /**
     * Gets Doug's current score.
     *
     * @return The score Doug has accumulated.
     */
    public int getScore(){
        return score;
    }

    /**
     * Sets Doug's score.
     *
     * @param score The score value to set for Doug.
     */
    public void setScore(int score){
        this.score = score;
    }

     /**
     * Gets Doug's current health level.
     *
     * @return The health level of Doug.
     */
    public int getHealth(){
        return health;
    }

    /**
     * Sets Doug's health level.
     *
     * @param health The health value to set for Doug.
     */
    public void setHealth(int health){
        this.health = health;
    }
}
