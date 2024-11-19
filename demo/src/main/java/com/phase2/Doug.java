package com.phase2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents the main character, Doug, in the game.
 * Doug has health, a score, and can be rendered and updated 
 * (ticked) within the game.
 */
public class Doug extends GameObject{

    Handler handler;

    private static Doug instance;

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

    /**
     * Returns the singleton instance of Doug, initializing it if necessary.
     * This method is used to create Doug with specific initial parameters.
     * 
     * @param x The initial x-coordinate of Doug's position.
     * @param y The initial y-coordinate of Doug's position.
     * @param id The ID that identifies this GameObject as Doug.
     * @param handler The handler that manages game objects.
     * @return The singleton instance of Doug.
     */
     public static Doug getInstance(int x, int y, ID id, Handler handler) {
        if (instance == null) {
            instance = new Doug(x, y, id, handler);
        }
        return instance;
    }

    /**
     * Returns the singleton instance of Doug. 
     * If Doug has not been initialized, an exception is thrown.
     * 
     * @return The singleton instance of Doug.
     * @throws IllegalStateException If Doug has not been initialized.
     */
    public static Doug getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Doug has not been initialized. Call getInstance(x, y, id, handler) first.");
        }
        return instance;
    }
    
    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
    public Rectangle getBounds() {
        int offset = 5;
        return new Rectangle(x + offset,y + offset,48 - (2* offset),48 - (2*offset));
    }

    /**
     * Updates Doug's state for each tick of the game loop.
     */
    @Override
    public void tick() {
        boolean wasMoving = moving;

        // Predict new positions based on velocity
        int predictedX = x + (int) (velX / speed);
        int predictedY = y + (int) (velY / speed);

        // Handle collision before updating position
        collision(predictedX, predictedY);

        // Determine if Doug is moving
        moving = (velX != 0 || velY != 0);

        // Update facing direction based on velocity
        if (velX > 0) {
           facingRight = true;
        } else if (velX < 0) {
           facingRight = false;
        }

        // Reset frame if movement state changes
        if (moving != wasMoving) {
           currentFrame = 0;
        }

        // Update animation frame
        frameCount++;
        if (frameCount >= frameDelay) {
          frameCount = 0;
         currentFrame = (currentFrame + 1) % (moving ? walkSprites.length : idleSprites.length);
        }

        // Clamp Doug's position to keep him within the screen bounds
        x = Game.clamp(x, 0, Game.WIDTH - 75);
        y = Game.clamp(y, 0, Game.HEIGHT - 30);
    }



    /**
     * Handles collision detection for Doug. 
     * Checks for interactions with other game objects and updates health, score, or game state accordingly.
     */
    private void collision(int predictedX, int predictedY) {
        boolean canMoveX = true;
        boolean canMoveY = true;
    
        // Check for collisions in the X direction
        Rectangle predictedBoundsX = new Rectangle(predictedX, y, getBounds().width, getBounds().height);
        for (GameObject temp : handler.objects) {
            if (temp.getBounds().intersects(predictedBoundsX)) {
                switch (temp.getId()) {
                    case ENEMY:
                        // Collision behavior for enemy
                        if (temp instanceof MovingEnemy || temp instanceof Punishment) {
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
                                if (reward.getType() == RewardType.BONE) Score.boneScore++;
                                if (reward.getType() == RewardType.STEAK) ((Steak)reward).setAlive(false);
                            }
                        }
                        break;
                    
                    case EXIT:
                        if (temp instanceof Exit) {
                            if (Score.boneScore >= Score.boneTotal) {
                                Game gameinstance = handler.getGameInstance();
                                if(gameinstance != null){
                                    gameinstance.setGameWon(true);
                                }
                            }
                        }
                        break;
                    // case OBSTACLE:
                    //     // Collision behavior for obstacles
                    //     break;
    
                    case OBSTAClE:
                        // Collision behavior for obstacles
                        if (temp instanceof Obstacle) {
                            canMoveX = false; // Prevent movement in x direction if collision occurs
                        }
                        break;
    
                    default:
                        // Default behavior for other object types
                        break;
                }
            }
        }
        
        // Check for collisions in the Y direction
        Rectangle predictedBoundsY = new Rectangle(x, predictedY, getBounds().width, getBounds().height);
        for (GameObject temp : handler.objects) {
            if (temp.getBounds().intersects(predictedBoundsY)) {
                switch (temp.getId()) {
                    case ENEMY:
                        // Collision behavior for enemy
                        if (temp instanceof MovingEnemy || temp instanceof Punishment) {
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
                                if (reward.getType() == RewardType.BONE) Score.boneScore++;
                                if (reward.getType() == RewardType.STEAK) ((Steak)reward).setAlive(false);
                            }
                        }
                        break;
    
                    case OBSTAClE:
                        // Collision behavior for obstacles
                        if (temp instanceof Obstacle) {
                            canMoveY = false; // Prevent movement in y direction if collision occurs
                        }
                        break;
    
                    default:
                        // Default behavior for other object types
                        break;
                }
            }
        }
    
        // Update position if no collision is predicted in the x direction
        if (canMoveX) {
            x += velX / speed; // Only update x if no collision in the x direction
        } else {
            velX = 0; // Stop horizontal movement
        }
    
        // Update position if no collision is predicted in the y direction
        if (canMoveY) {
            y += velY / speed; // Only update y if no collision in the y direction
        } else {
            velY = 0; // Stop vertical movement
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

}
