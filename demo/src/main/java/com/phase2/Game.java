package com.phase2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * The Game class manages the main game loop and window.
 * It initializes the game components, handles input, and manages the game's continuous execution.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; // sets window size

    private Thread thread; 
    private boolean running = false;

    private Handler handler;

    /**
     * Constructor for the Game class.
     * Initializes the handler and sets up the game window.
     */
    public Game() {

        handler = new Handler();

        this.addKeyListener(new KeyInput(handler)); // recieves keyboard input

        new Window(WIDTH, HEIGHT, "Doug Game", this);

        // used for testing only
        handler.addObject(new Doug(200, 200, ID.DOUG));
        //

    }

    /**
     * Starts the game in a new thread.
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Stops the game thread safely.
     */
    public synchronized void stop () {
        try {
                thread.join();
                running = false;
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    /**
     * The main game loop that handles game updates and rendering.
     * Continuously runs while the game is active.
     */
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        // int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;    
            }
            if (running) {
                render();
            }
            // frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
                // frames = 0;
            }
        }
        stop();
    }

    /**
     * Updates the game state for each game tick.
     */
    private void tick(){
        handler.tick();
    }

    /**
     * Renders the game graphics.
     * 
     * @param g the Graphics object used for drawing the game components
     */
    private void render(){
        BufferStrategy  bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // for testing, can change this
        g. setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //

        handler.render(g);

        g.dispose(); 
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

 }