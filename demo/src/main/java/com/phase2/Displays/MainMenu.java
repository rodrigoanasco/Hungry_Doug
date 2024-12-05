package com.phase2.Displays;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.phase2.Game;
import com.phase2.SoundEffect;

/**
 * The {@code MainMenu} class manages the main menu of the game.
 * <p>
 * It displays menu options such as starting the game, viewing the rules, or exiting,
 * and handles user input for navigation and selection. Players can navigate the menu
 * using either arrow keys or WASD.
 * </p>
 */
public class MainMenu implements KeyListener {

    /**
     * The main game instance associated with this menu.
     */
    private Game game;

    /**
     * Tracks the currently selected button in the menu.
     */
    private int selectedButton = 0;

    /**
     * Tracks whether the instructions screen is currently displayed.
     */
    private boolean inInstructions = false;

    /**
     * The rectangle representing the "Start" button.
     */
    private final Rectangle startButton = new Rectangle(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 60, 200, 50);

    /**
     * The rectangle representing the "Instructions" button.
     */
    private final Rectangle instructionsButton = new Rectangle(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 5, 200, 50);

    /**
     * The rectangle representing the "Exit" button.
     */
    private final Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 70, 200, 50);

    /**
     * Constructs a {@code MainMenu} instance associated with the specified game.
     * 
     * @param game the main game instance.
     */
    public MainMenu(Game game) {
        this.game = game;
        game.addKeyListener(this);
    }

    /**
     * Gets the index of the currently selected button.
     * 
     * @return the index of the selected button.
     */
    public int getSelectedButton() {
        return selectedButton;
    }

    /**
     * Sets the index of the selected button. The index is bounded by the total
     * number of buttons using a modulo operation.
     * 
     * @param selectedButton the index of the button to select.
     */
    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton % 3;
    }

    /**
     * Checks whether the instructions screen is currently displayed.
     * 
     * @return {@code true} if the instructions screen is displayed, {@code false} otherwise.
     */
    public boolean isInInstructions() {
        return inInstructions;
    }

    /**
     * Sets whether the instructions screen should be displayed.
     * 
     * @param inInstructions {@code true} to display the instructions screen, {@code false} otherwise.
     */
    public void setInInstructions(boolean inInstructions) {
        this.inInstructions = inInstructions;
    }

    /**
     * Renders the menu or instructions screen based on the current state.
     * 
     * @param g the {@link Graphics} object used for rendering.
     */
    public void render(Graphics g) {
        if (inInstructions) {
            renderInstructions(g);
        } else {
            renderMenu(g);
        }
    }

    /**
     * Renders the main menu with all its options.
     * 
     * @param g the {@link Graphics} object used for rendering.
     */
    private void renderMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);

        // Draw the title
        FontMetrics metrics = g.getFontMetrics();
        int titleWidth = metrics.stringWidth("HUNGRY DOUG");
        g.drawString("HUNGRY DOUG", (Game.WIDTH - titleWidth) / 2, Game.HEIGHT / 3);

        g.setFont(new Font("Arial", Font.PLAIN, 30));

        // Render each button
        renderButton(g, "START", startButton, selectedButton == 0);
        renderButton(g, "RULES", instructionsButton, selectedButton == 1);
        renderButton(g, "EXIT", exitButton, selectedButton == 2);

        // Display navigation instructions
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Use Arrow Keys or WASD to navigate and press ENTER to select",
                Game.WIDTH / 2 - 250, Game.HEIGHT / 2 + 180);
    }

    /**
     * Renders the instructions screen with gameplay rules and controls.
     * 
     * @param g the {@link Graphics} object used for rendering.
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
        g.drawString("- Help Doug collect all his bones! Then grab the gem to escape the maze.", 100, 400);
        g.drawString("- Avoid non-dog-friendly food and stay clear of the rats!", 100, 450);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press ENTER to go back to the main menu.", Game.WIDTH / 2 - 150, Game.HEIGHT - 50);
    }

    /**
     * Renders an individual button with optional highlighting.
     * 
     * @param g        the {@link Graphics} object used for rendering.
     * @param text     the text displayed on the button.
     * @param button   the {@link Rectangle} representing the button's position and size.
     * @param selected {@code true} if the button is selected, {@code false} otherwise.
     */
    private void renderButton(Graphics g, String text, Rectangle button, boolean selected) {
        g.setColor(selected ? Color.YELLOW : Color.WHITE); // Highlight if selected
        g.drawRect(button.x, button.y, button.width, button.height);

        // Center the text in the button
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textX = button.x + (button.width - textWidth) / 2;
        int textY = button.y + (button.height - metrics.getHeight()) / 2 + metrics.getAscent();

        g.setColor(Color.WHITE);
        g.drawString(text, textX, textY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!game.isPaused() || game.isGameOver()) {
            return;
        }

        int key = e.getKeyCode();

        if (inInstructions) {
            if (key == KeyEvent.VK_ENTER) {
                inInstructions = false; // Go back to the main menu
            }
        } else {
            switch (key) {
                case KeyEvent.VK_UP, KeyEvent.VK_W -> selectedButton = (selectedButton + 2) % 3;
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> selectedButton = (selectedButton + 1) % 3;
                case KeyEvent.VK_ENTER -> handleButtonAction();
                case KeyEvent.VK_ESCAPE -> game.togglePause();
            }
        }
    }

    /**
     * Executes the action associated with the currently selected button.
     */
    private void handleButtonAction() {
        SoundEffect.play("/bark.wav");
        switch (selectedButton) {
            case 0 -> game.startGame();
            case 1 -> inInstructions = true;
            case 2 -> System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

