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
    public Doug(int x, int y, ID id){
        super(x,y,id);
        this.handler = Handler.getHandlerInstance();


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
     * @return The singleton instance of Doug.
     */
     public static Doug getInstance(int x, int y, ID id) {
        if (instance == null) {
            instance = new Doug(x, y, id);
        }
        return instance;
    }

    /**
     * Set instance (Just for testing)
     * Resets the singleton instance
     */
    public static void setInstance(){
        instance = null;
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
            throw new IllegalStateException("Doug has not been initialized. Call getInstance(x, y, id) first.");
        }
        return instance;
    }

    // TODO test
    public void resetPosition() {
        this.x = 200;
        this.y = 200;
    }
    //
    
    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
    @Override
    public Rectangle getBounds() {
        int offset = 5;
        return new Rectangle(x + offset,y + offset,48 - (2* offset),48 - (2*offset));
    }

    /**
     * Updates Doug's state for each tick of the game loop.
     */
    @Override
    public void tick() {
        int[] predictedPosition = predictNewPosition();
        handleCollision(predictedPosition[0], predictedPosition[1]);
        updateAnimationState();
        clampPosition();
    }

    private int[] predictNewPosition() {
        int predictedX = x + (int) (velX / speed);
        int predictedY = y + (int) (velY / speed);
        return new int[] { predictedX, predictedY };
    }

    private void handleCollision(int predictedX, int predictedY) {
        boolean canMoveX = checkCollisionX(predictedX);
        boolean canMoveY = checkCollisionY(predictedY);

        if (canMoveX) {
            x += velX / speed;
        } else {
            velX = 0;
        }

        if (canMoveY) {
            y += velY / speed;
        } else {
            velY = 0;
        }
    }

    private void updateAnimationState() {
        boolean wasMoving = moving;

        // Determine if Doug is moving
        moving = (velX != 0 || velY != 0);

        // Update facing direction based on velocity
        if (velX > 0) {
            facingRight = true;
        } else if (velX < 0) {
            facingRight = false;
        }

        // Reset animation frame if movement state changes
        if (moving != wasMoving) {
            currentFrame = 0;
        }

        // Update animation frame
        frameCount++;
        if (frameCount >= frameDelay) {
            frameCount = 0;
            currentFrame = (currentFrame + 1) % (moving ? walkSprites.length : idleSprites.length);
        }
    }

    private void clampPosition() {
        x = Game.clamp(x, 0, Game.WIDTH - 75);
        y = Game.clamp(y, 0, Game.HEIGHT - 30);
    }


    /**
     * Handles collision detection for Doug. 
     * Checks for interactions with other game objects and updates health, score, or game state accordingly.
     */
    private void collision(int predictedX, int predictedY) {
        boolean canMoveX = checkCollisionX(predictedX);
        boolean canMoveY = checkCollisionY(predictedY);
    
        // Update position if no collision blocks movement
        if (canMoveX) {
            x += velX / speed;
        } else {
            velX = 0;
        }
    
        if (canMoveY) {
            y += velY / speed;
        } else {
            velY = 0;
        }
    }
    
    private boolean checkCollisionX(int predictedX) {
        boolean canMoveX = true;
        Rectangle predictedBoundsX = new Rectangle(predictedX, y, getBounds().width, getBounds().height);
    
        synchronized (handler.objects) { // Ensure thread-safe access
            for (GameObject temp : handler.objects) {
                if (temp.getBounds().intersects(predictedBoundsX)) {
                    canMoveX = handleCollision(temp, true) && canMoveX;
                }
            }
        }
    
        return canMoveX;
    }
    
    private boolean checkCollisionY(int predictedY) {
        boolean canMoveY = true;
        Rectangle predictedBoundsY = new Rectangle(x, predictedY, getBounds().width, getBounds().height);
    
        synchronized (handler.objects) { // Ensure thread-safe access
            for (GameObject temp : handler.objects) {
                if (temp.getBounds().intersects(predictedBoundsY)) {
                    canMoveY = handleCollision(temp, false) && canMoveY;
                }
            }
        }
    
        return canMoveY;
    }
    
    private boolean handleCollision(GameObject temp, boolean isXAxis) {
        switch (temp.getId()) {
            case ENEMY:
                if (temp instanceof Punishment) {
                    handlePunishmentCollision((Punishment) temp);
                } else {
                    handleEnemyCollision(temp);
                }
                break;
    
            case REWARD:
                if (temp instanceof Reward) {
                    handleRewardCollision(temp);
                }
                break;
    
            case EXIT:
                if (temp instanceof Exit) {
                    handleExitCollision((Exit) temp);
                }
                break;
    
            case OBSTACLE:
                return handleObstacle(temp, isXAxis);
    
            default:
                // Handle other cases if necessary
                break;
        }
        return true;
    }
    
    private void handlePunishmentCollision(Punishment punishment) {
        if (!punishment.isCollected()) {
            punishment.setCollected(true);
            Health.HEALTH -= punishment.getPenaltyPoints();
            SoundEffect.play("/whimper.wav");
        }
    }
    
    private void handleExitCollision(Exit exit) {
        if (!exit.getHiddenStatus()) { // Check if all bones are collected
            //Game gameInstance = handler.getGameInstance(); // Access the Game instance
            if (!Score.touchingExit) {
                Score.touchingExit = true;//gameInstance.setGameWon(true); // Mark the game as won
            }
        }
    }
    
    private void handleEnemyCollision(GameObject temp) {
        if (temp instanceof MovingEnemy) {
            int penaltyPoints = ((MovingEnemy) temp).getPenaltyPoints();
            Health.HEALTH -= penaltyPoints; // Deduct health points
            SoundEffect.play("/squeak.wav"); // Play sound
        }
    }
    
    private void handleRewardCollision(GameObject temp) {
        if (temp instanceof Reward) {
            Reward reward = (Reward) temp;
            if (!reward.isCollected()) {
                reward.setCollected(true);
                Score.SCORE += reward.getRewardAmount();
                SoundEffect.play("/munch.wav");
                if (reward.getType() == RewardType.BONE) {
                    Score.boneScore++;
                } else if (reward.getType() == RewardType.STEAK) {
                    ((Steak) reward).setAlive(false);
                }
            }
        }
    }
    
    private boolean handleObstacle(GameObject temp, boolean isXAxis) {
        Rectangle obstacleBounds = temp.getBounds();
    
        if (isXAxis) {
            if (velX > 0) { // Moving right
                x = obstacleBounds.x - getBounds().width;
            } else if (velX < 0) { // Moving left
                x = obstacleBounds.x + obstacleBounds.width;
            }
            return false;
        } else {
            if (velY > 0) { // Moving down
                y = obstacleBounds.y - getBounds().height;
            } else if (velY < 0) { // Moving up
                y = obstacleBounds.y + obstacleBounds.height;
            }
            return false;
        }
    }
    
    /**
     * Renders Doug on the screen at his current position.
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
