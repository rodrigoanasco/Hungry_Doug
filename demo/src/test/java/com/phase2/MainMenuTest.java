package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainMenuTest {
    private Game game;
    private MainMenu mainMenu;

    @Before
    public void setUp() {
        game = new Game();
        mainMenu = new MainMenu(game);
    }

    @Test
    public void testNavigationDown() {
        // Initial selectedButton should be 0 (START)
        assertEquals(0, mainMenu.getSelectedButton());

        // Simulate pressing DOWN key
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' '));

        // selectedButton should now be 1 (RULES)
        assertEquals(1, mainMenu.getSelectedButton());

        // Simulate pressing S key (another DOWN)
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S'));

        // selectedButton should now be 2 (EXIT)
        assertEquals(2, mainMenu.getSelectedButton());

        // Simulate pressing DOWN key again (should wrap around)
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' '));

        // selectedButton should now be 0 (START)
        assertEquals(0, mainMenu.getSelectedButton());
    }

    @Test
    public void testNavigationUp() {
        // Set selectedButton to 2 (EXIT)
        mainMenu.setSelectedButton(2);

        // Simulate pressing UP key
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' '));

        // selectedButton should now be 1 (RULES)
        assertEquals(1, mainMenu.getSelectedButton());

        // Simulate pressing W key (another UP)
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'));

        // selectedButton should now be 0 (START)
        assertEquals(0, mainMenu.getSelectedButton());

        // Simulate pressing UP key again (should wrap around)
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' '));

        // selectedButton should now be 2 (EXIT)
        assertEquals(2, mainMenu.getSelectedButton());
    }

    @Test
    public void testStartGame() {
        // Ensure game is paused initially
        assertTrue(game.isPaused());

        // selectedButton should be 0 (START)
        assertEquals(0, mainMenu.getSelectedButton());

        // Simulate pressing ENTER
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));

        // Game should now be running (not paused)
        assertFalse(game.isPaused());
    }

    @Test
    public void testOpenInstructions() {
        // Move selection to "RULES"
        mainMenu.setSelectedButton(1);
        assertEquals(1, mainMenu.getSelectedButton());

        // Simulate pressing ENTER
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));

        // inInstructions should now be true
        assertTrue(mainMenu.isInInstructions());
    }

    @Test
    public void testReturnFromInstructions() {
        // Open instructions
        mainMenu.setSelectedButton(1);
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
        assertTrue(mainMenu.isInInstructions());

        // Simulate pressing ENTER while in instructions
        mainMenu.keyPressed(new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));

        // Should return to main menu
        assertFalse(mainMenu.isInInstructions());
    }

    @Test
    public void testRenderInstructions() {
        // Set inInstructions to true
        mainMenu.setInInstructions(true);
        assertTrue(mainMenu.isInInstructions());

        // Create a mock Graphics object
        Graphics g = mock(Graphics.class);

        // Call render method
        mainMenu.render(g);

        // Verify that certain Graphics methods are called
        verify(g, atLeastOnce()).setColor(any(Color.class));
        verify(g, atLeastOnce()).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
        verify(g, atLeastOnce()).setFont(any(Font.class));
        verify(g, atLeastOnce()).drawString(anyString(), anyInt(), anyInt());
    }
}

