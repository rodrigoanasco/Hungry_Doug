package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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

     // Getter and Setter for selectedButton
     public int getSelectedButton() {
        return selectedButton;
    }

    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton % 3;
    }

    // Getter for inInstructions
    public boolean isInInstructions() {
        return inInstructions;
    }

    //Setter for inInstructions (For testing purposes)
    public void setInInstructions(boolean inInstructions) {
        this.inInstructions = inInstructions;
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


        // Use FontMetrics to calculate the width of the title
        FontMetrics metrics = g.getFontMetrics();
        int titleWidth = metrics.stringWidth("HUNGRY DOUG");
        int titleX = (Game.WIDTH - titleWidth) / 2; // Center horizontally
        int titleY = Game.HEIGHT / 3; // Keep the same vertical position
        g.drawString("HUNGRY DOUG", titleX, titleY);

        // g.drawString("DOUG'S BIG ADVENTURE", Game.WIDTH / 2 - 150, Game.HEIGHT / 3);

        g.setFont(new Font("Arial", Font.PLAIN, 30));

        // Render each button with highlighting for the selected button
        renderButton(g, "START", startButton, selectedButton == 0);
        renderButton(g, "RULES", instructionsButton, selectedButton == 1);
        renderButton(g, "EXIT", exitButton, selectedButton == 2);

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
        g.drawString("Rules", Game.WIDTH / 2 - 100, 100);

        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString("- Use arrow keys or WASD to navigate Doug.", 100, 200);
        g.drawString("- Collect treats to earn points.", 100, 250);
        g.drawString("- Avoid enemies to stay alive.", 100, 300);
        g.drawString("- Press ESC to return to the main menu at any time.", 100, 350);
        g.drawString("- Help Doug collecting all of his bones! Then grab it's gem to escape from the maze", 100, 400);
        g.drawString("- Make sure to pick only dog friendly food & Don't let the rats hit you!", 100, 450);

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
        
        // g.drawString(text, button.x + 20, button.y + 35);

        // Set font and center text
        g.setFont(new Font("Arial", Font.PLAIN, 30)); // Adjust font size as needed
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int textX = button.x + (button.width - textWidth) / 2; // Center horizontally
        int textY = button.y + (button.height - textHeight) / 2 + metrics.getAscent(); // Center vertically

        g.setColor(Color.WHITE);
        g.drawString(text, textX, textY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(game.isPaused() == false || game.isGameOver() == true)
        return;

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
        // Play the barking sound effect
        SoundEffect.play("/bark.wav");
        
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

