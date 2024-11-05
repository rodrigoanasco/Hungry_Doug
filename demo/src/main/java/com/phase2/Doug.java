package com.phase2;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 * Represents the main character, Doug, in the game.
 * Doug has health, a score, and can be rendered and updated 
 * (ticked) within the game.
 */
public class Doug extends GameObject{

    Handler handler;

    protected int health;
    protected int score;

    private BufferedImage[] idleSprites;
    private BufferedImage[] walkSprites;
    private int currentFrame = 0;
    private int frameDelay = 5; // Controls the animation speed
    private int frameCount = 0;

    private boolean moving = false;
    private boolean facingRight = true; // Default to facing right

    private double speed = 1.5;

    private static final double SCALE_FACTOR = 1.2; // Scale Doug to be bigger/smaller

    /**
     * Initializes Doug's position, ID, health, and score.
     * Sets default velocity for testing purposes.
     *
     * @param x  The initial x-coordinate of Doug's position.
     * @param y  The initial y-coordinate of Doug's position.
     * @param id The ID that identifies this GameObject as Doug.
     */
    public Doug(int x, int y, ID id, Handler handler){
        super(x,y,id);


        this.handler = handler;

        this.health = 100;
        this.score = 0;

        try {
            // Gets sprite images from resources folder
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
        } 
        // If not print an error
        catch (IOException e) {
            e.printStackTrace();
        }
    

        // Used for testing only
        velX = 0;
        velY = 0;
        //
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,48,48);
    }

    /**
     * Updates Doug's state for each tick of the game loop.
     */
    @Override
    public void tick(){

        boolean wasMoving = moving;

        // Used for testing only
        // Moves + in x and y direction each tick of the game
        x += velX/speed;
        y += velY/speed;
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
            } 
            else {
                currentFrame = (currentFrame + 1) % idleSprites.length; // Cycle through idle frames
            }
        }

        // Doug can't move out oof bounds
        x = Game.clamp(x,0,Game.WIDTH - 100);
        y = Game.clamp(y,0,Game.HEIGHT - 100);

       collision(); 
    }

    private void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);
            
            if (getBounds().intersects(temp.getBounds())) {
                switch (temp.getId()) {
                    case ENEMY:
                        // Collision behavior for enemy
                        if (temp instanceof MovingEnemy || temp instanceof Punishment) {
                            // Cast temp to access getPenaltyPoints()
                            int penaltyPoints = temp instanceof MovingEnemy 
                                ? ((MovingEnemy) temp).getPenaltyPoints() 
                                : ((Punishment) temp).getPenaltyPoints();
            
                            Health.HEALTH -= penaltyPoints;
                        }
                        break;
                        
                    case REWARD:
                        // Collision behavior for reward
                        if (temp instanceof Reward) {
                            Reward reward = (Reward) temp;
                            if (!reward.isCollected()) {
                                int rewardAmount = reward.getRewardAmount();
                                Score.SCORE += rewardAmount;
                                reward.setCollected(true); // Mark as collected
                            }
                        }
                        break;
                        
                    // case OBSTACLE:
                    //     // Collision behavior for obstacles
                    //     break;
    
                    // Add more cases as needed for other object types
                    default:
                        // Default behavior, if any
                        break;
                }
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
        // g.setColor(Color.GREEN);
        // g.fillRect(x, y, 48, 48);
        //
        // renderHitBox(g,48,48);
    Graphics2D g2d = (Graphics2D) g;

    // Determine which sprite image to draw for Doug (Idle or Walking)
    BufferedImage spriteToDraw = moving ? walkSprites[currentFrame] : idleSprites[currentFrame];

    // Scale Doug according to SCALE_FACTOR
    int scaledWidth = (int) (spriteToDraw.getWidth() * SCALE_FACTOR);
    int scaledHeight = (int) (spriteToDraw.getHeight() * SCALE_FACTOR);

    if (facingRight) {
        // Draw normally if facing right
        g2d.drawImage(spriteToDraw, x, y-16, scaledWidth, scaledHeight, null);
    } 
    else {
        // Flip horizontally if facing left
        AffineTransform transform = new AffineTransform();
        transform.translate(x + scaledWidth, y-16); // Move to the correct position
        transform.scale(-SCALE_FACTOR, SCALE_FACTOR); // Flip horizontally
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
     * Add to Doug's score.
     *
     * @param score The score value to add for Doug.
     */
    public void addScore(int score){
        this.score += score;
    }

    /**
     * Subtract to Doug's score.
     *
     * @param score The score value to subtract for Doug.
     */
    public void subScore(int score){
        this.score -= score;
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
