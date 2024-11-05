package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The MainMenu class displays the game's main menu and handles input to start the game or view instructions.
 * It also allows navigation with both arrow keys and WASD.
 */
public class MainMenu implements KeyListener {
    private Game game;
    private int selectedButton = 0; // Tracks which button is selected
    private boolean inInstructions = false; // Tracks if we are on the instructions screen

    private final Rectangle startButton = new Rectangle(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 60, 200, 50);
    private final Rectangle instructionsButton = new Rectangle(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 5, 200, 50);
    private final Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 70, 200, 50);

    public MainMenu(Game game) {
        this.game = game;
        game.addKeyListener(this);
    }

    /**
     * Renders the main menu or instructions screen based on the current state.
     *
     * @param g the Graphics object used for drawing the menu or instructions
     */
    public void render(Graphics g) {
        if (inInstructions) {
            renderInstructions(g);
        } else {
            renderMenu(g);
        }
    }

    /**
     * Renders the main menu with selectable buttons.
     * 
     * @param g The Graphics object used for rendering.
     */
    private void renderMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString("Doug Game", Game.WIDTH / 2 - 150, Game.HEIGHT / 3);

        g.setFont(new Font("Arial", Font.PLAIN, 30));

        // Render each button with highlighting for the selected button
        renderButton(g, "Start Game", startButton, selectedButton == 0);
        renderButton(g, "Instructions", instructionsButton, selectedButton == 1);
        renderButton(g, "Exit", exitButton, selectedButton == 2);

        // Instructions for selecting options, positioned slightly above the Exit button
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Use Arrow Keys or WASD to navigate and press ENTER to select", Game.WIDTH / 2 - 250, Game.HEIGHT / 2 + 180);
    }

    /**
     * Renders the instructions screen with gameplay instructions.
     * 
     * @param g The Graphics object used for rendering.
     */
    private void renderInstructions(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.drawString("Instructions", Game.WIDTH / 2 - 100, 100);

        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString("- Use arrow keys or WASD to navigate Doug.", 100, 200);
        g.drawString("- Collect treats to earn points.", 100, 250);
        g.drawString("- Avoid enemies to stay alive.", 100, 300);
        g.drawString("- Press ESC to return to the main menu at any time.", 100, 350);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press ENTER to go back to the main menu.", Game.WIDTH / 2 - 150, Game.HEIGHT - 50);
    }

    /**
     * Renders an individual button with optional highlighting if selected.
     * 
     * @param g The Graphics object used for rendering.
     * @param text The text to display on the button.
     * @param button The rectangle representing the button's position and size.
     * @param selected True if the button is currently selected, false otherwise.
     */
    private void renderButton(Graphics g, String text, Rectangle button, boolean selected) {
        g.setColor(selected ? Color.YELLOW : Color.WHITE); // Highlight selected button
        g.drawRect(button.x, button.y, button.width, button.height);
        g.drawString(text, button.x + 20, button.y + 35);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (inInstructions) {
            if (key == KeyEvent.VK_ENTER) {
                inInstructions = false; // Go back to main menu
            }
        } else {
            switch (key) {
                // Arrow keys or WASD for navigation
                case KeyEvent.VK_UP, KeyEvent.VK_W -> selectedButton = (selectedButton + 2) % 3; // Navigate up
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> selectedButton = (selectedButton + 1) % 3; // Navigate down
                case KeyEvent.VK_ENTER -> handleButtonAction(); // Activate button
                case KeyEvent.VK_ESCAPE -> game.togglePause(); // Toggle pause menu
            }
        }
    }

    private void handleButtonAction() {
        switch (selectedButton) {
            case 0 -> game.startGame(); // Start game
            case 1 -> inInstructions = true; // Show instructions screen
            case 2 -> System.exit(0); // Exit game
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

