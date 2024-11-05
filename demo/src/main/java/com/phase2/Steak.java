package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Steak extends Reward {

    private Image steakSprite;
    private int lifetime;
    private int spawntime;
    private int deathtime;
    private Boolean isAlive = true;
    Random r;

    public Steak(int x, int y) {
        super(x,y, RewardType.STEAK, 20);
        r = new Random();
        this.lifetime = 0;
        this.spawntime = r.nextInt(5)+1;
        this.deathtime = r.nextInt(25+spawntime)+10+spawntime;
        try {
            steakSprite = ImageIO.read(getClass().getResource("/Steak.png"));
            steakSprite = steakSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);
    }

    public void setAlive(Boolean b) {
        this.isAlive = b;
    }

    public void tick() {
        //to be implemented: checks if colliding with doug
        //System.out.println(this.lifetime);
        if (this.lifetime < this.spawntime*60 || this.lifetime > this.deathtime*60) {
            this.collected = true;
            lifetime++;
        }
        else {
            if (isAlive) {
                lifetime++;
                this.collected =false;
            }
            else {
                this.collected = true;
            }
        }
    }
    
    public void render(Graphics g) {
        // renderHitBox(g,OBJECT_SIZE[0],OBJECT_SIZE[1]);
        if (!this.collected) {
            g.drawImage(steakSprite, x, y, null);
            g.setColor(Color.BLACK);
            g.drawString(""+((this.deathtime)-this.lifetime/60), x, y);
        }
    }

}