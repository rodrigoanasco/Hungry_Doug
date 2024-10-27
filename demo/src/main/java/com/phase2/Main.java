package com.phase2;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame(); // library for a new window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // able to close the window
        window.setResizable(false); // cannot resize window
        window.setTitle("Hungry Doug");

        GamePanel gamePanel = new GamePanel ();
        gamePanel.startGameThread();
        window.add(gamePanel);

        window.pack(); // too see panel

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}