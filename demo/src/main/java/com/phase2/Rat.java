package com.phase2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.lang.Math;

/**
 * Class for the moving enemy type Rat
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
public class Rat extends MovingEnemy {

    private BufferedImage[] idleSprites;
    private BufferedImage[] walkSprites;
    private int currentFrame = 0;
    private int frameDelay = 25; // Controls animation speed
    private int frameCount = 0;

    private boolean moving = false;
    private boolean pathfinding = false;
    private boolean facingRight = true;
    //testing Rectangle assumedBushHitbox = new Rectangle(500, 10, 32, 500);

    private static final double SCALE_FACTOR = 1.25;

    // private double speed = 1.5;

    public Rat(int x, int y) {
        super(x,y,EnemyType.RAT, Health.HEALTH);
        
        try {
            // Load the idle and walk sprite sheets
            BufferedImage idleSheet = ImageIO.read(getClass().getResource("/ratIdle.png"));
            BufferedImage walkSheet = ImageIO.read(getClass().getResource("/ratWalk.png"));

            // Extract frames for idle animation (assuming 4 frames, each 32x32)
            idleSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                idleSprites[i] = idleSheet.getSubimage(i * 32, 0, 32, 32);
            }

            // Extract frames for walking animation (assuming 4 frames, each 32x32)
            walkSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                walkSprites[i] = walkSheet.getSubimage(i * 32, 0, 32, 32);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initially, the rat is not moving
        velX = 1;
        velY = 1;
    }

    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    /**
     * What the object should do on each tick
     */
    public void tick() {
        
        // Access the singleton instance of Doug
        Doug doug = Doug.getInstance();
        
        Rectangle hitbox = this.getBounds();
        //Rectangle intersection = new Rectangle();
        //hitbox.setBounds((int)(x+0.75*velX), (int)(y+0.75*velX), (int)(hitbox.getWidth()+5*velX), (int)hitbox.getHeight()+5*velY);


        for (GameObject temp : Handler.objects) {
            if (temp instanceof Bush) {
                //intersection = temp.getBounds().intersection(hitbox);
                if (temp.getBounds().intersects(hitbox)) {


                }
            }
            
        }

    
        // Update position based on velocity
        x += velX;
        y += velY;

        // Flips direction upon hitting game boundary
        if(y < 0 || y >= Game.HEIGHT - 100) velY *= -1;
        if(x < 0 || x >= Game.WIDTH - 100) velX *= -1;

    }

    private boolean pathfind(Bush setBush, char direction) {
        LinkedList<Bush> bushList = new LinkedList<Bush >();
        Bush boundingBush1 = new Bush(0,0);
        Bush boundingBush2 = new Bush(0,0);;
        System.out.println("hello");
        if (direction == 'y') {
            int value = (this.y-setBush.getY() > 0) ? -1 : 1 ; 


            
            for (GameObject temp : Handler.objects) {
                if (temp instanceof Bush) {
                    if (Math.abs(setBush.getX()-temp.getX()) < 10){
                        bushList.add((Bush)temp);
                    }
                    if (Math.abs(setBush.getX()-(value*setBush.getBounds().getWidth())) -temp.getX() < 10) {
                        if (this.x - temp.getX() < this.x-boundingBush1.getX() || this.x - temp.getX() > 0) {
                            boundingBush1 = (Bush)temp;
                        }
                        if (this.x - temp.getX() > this.x-boundingBush1.getX() || this.x - temp.getX() < 0) {
                            boundingBush2 = (Bush)temp;
                        }
                    }
                }
            }
            
            int lastBush1 = isBushLastX(bushList, setBush, (int)(-1*setBush.getBounds().getWidth()));
            int lastBush2 = isBushLastX(bushList, setBush, (int)(1*setBush.getBounds().getWidth()));
            int leavePoint = 0;

            if (lastBush1 < boundingBush1.getX()) {
                lastBush1 = -1;
            }
            if (lastBush2 > boundingBush2.getX()) {
                lastBush2 = -1;
            }
            if (lastBush1 == -1) {
                leavePoint = lastBush2;
                return false;
            }
            else if (lastBush2 == -1) {
                leavePoint = lastBush1;
                return false;
            }
            else if (lastBush2 == -1 && lastBush1 == -1) {
                int a = isBushLastY(bushList, boundingBush2, (int)(value*setBush.getBounds().getWidth()));
                int b = isBushLastY(bushList, boundingBush2, (int)(value*setBush.getBounds().getWidth()));
                leavePoint = (a-b<0) ? a : b;
            }
            else {
                int a = lastBush1 - this.getX();
                int b = lastBush2 - this.getX();
                leavePoint = (a>b) ? a : b;
            }





            
            

        }
        else if (direction == 'x') {
            int value = (this.x-setBush.getX() > 0) ? -1 : 1 ; 
            
            for (GameObject temp : Handler.objects) {
                if (temp instanceof Bush) {
                    if ((Math.abs(setBush.getY()-temp.getY()) < 7)){
                        bushList.add((Bush)temp);
                    }
                    if (Math.abs(setBush.getY()-(value*setBush.getBounds().getHeight())) -temp.getY() < 7) {
                        if (this.y - temp.getY() < this.y-boundingBush1.getY() || this.y - temp.getY() > 0) {
                            boundingBush1 = (Bush)temp;
                        }
                        if (this.y - temp.getY() > this.y-boundingBush1.getY() || this.y - temp.getY() < 0) {
                            boundingBush2 = (Bush)temp;
                        }
                    }
                }

            }
            int lastBush1 = isBushLastY(bushList, setBush, (int)(-1*setBush.getBounds().getWidth()));
            int lastBush2 = isBushLastY(bushList, setBush, (int)(1*setBush.getBounds().getWidth()));
            int leavePoint = 0;

            if (lastBush1 < boundingBush1.getY()) {
                lastBush1 = -1;
            }
            if (lastBush2 > boundingBush2.getY()) {
                lastBush2 = -1;
            }
            if (lastBush1 == -1) {
                leavePoint = lastBush2;
                return false;
            }
            else if (lastBush2 == -1) {
                leavePoint = lastBush1;
                return false;
            }
            else if (lastBush2 == -1 && lastBush1 == -1) {
                int a = isBushLastY(bushList, boundingBush2, (int)(value*setBush.getBounds().getWidth()));
                int b = isBushLastY(bushList, boundingBush2, (int)(value*setBush.getBounds().getWidth()));
                leavePoint = (a-b<0) ? a : b;
            }
            else {
                int a = lastBush1 - this.getY();
                int b = lastBush2 - this.getY();
                leavePoint = (a>b) ? a : b;
            }


        }
        else {
            //error direction should be y or x
            return false;
        }


        




        return false;
    }

    private int isBushLastX(LinkedList<Bush> bushList, Bush wall, int mult) {
        //X direction
        int nextBushPos = wall.getX() + mult;
        //boolean nextBush = false;

        for (Bush temp : bushList) {
            if (temp.getX()-nextBushPos < 7) {
                nextBushPos = isBushLastX(bushList, temp, mult);
                break;
            }
        }

        return nextBushPos;
    }

    private int isBushLastY(LinkedList<Bush> bushList, Bush wall, int mult) {
        //X direction
        int nextBushPos = wall.getY() + mult;
        //boolean nextBush = false;

        for (Bush temp : bushList) {
            if (temp.getY()-nextBushPos < 7) {
                nextBushPos = isBushLastY(bushList, temp, mult);
                break;
            }
        }

        return nextBushPos;
    }
    
 
    /**
     * Visually renders the object
     */
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;


        //testing g.drawRect(500, 10, (int)assumedBushHitbox.getWidth(), (int)assumedBushHitbox.getHeight());
        
        // Rat is moving or idle
        BufferedImage spriteToDraw = moving ? walkSprites[currentFrame] : idleSprites[currentFrame];

        int scaledWidth = (int) (spriteToDraw.getWidth() * SCALE_FACTOR);
        int scaledHeight = (int) (spriteToDraw.getHeight() * SCALE_FACTOR);

        // Draw the sprite at the rat's current position
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
        //g2d.drawImage(spriteToDraw, x, y-16, scaledWidth, scaledHeight, null);

        // Update frame for animation
        frameCount++;
        if (frameCount >= frameDelay) {
            frameCount = 0;
            currentFrame = (currentFrame + 1) % idleSprites.length; // Cycle through frames
        }
    }

}
