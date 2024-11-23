package com.phase2;

import org.junit.Test;

/**
 * Unit tests for the {@link SoundEffect} class.
 * 
 * This test class verifies the behavior of the {@code SoundEffect.play(String)} method
 * under various scenarios, ensuring that sound files are handled appropriately and
 * exceptions are managed gracefully.
 */
public class SoundEffectTest {

    /*
     * Tests that a valid sound file plays without throwing any exceptions.
     */
    @Test
    public void testPlayValidFile() {
        // This test verifies that a valid sound file plays without exceptions.
        SoundEffect.play("/bark.wav");

        // No assertions are needed here; the test passes if no exceptions are thrown.
        System.out.println("Played valid sound file successfully.");
    }

    @Test
    public void testPlayInvalidFile() {
        // This test checks that invalid sound files are handled gracefully.
        SoundEffect.play("/nonexistent.wav");

        // The test passes if no exceptions are thrown.
        System.out.println("Handled invalid sound file gracefully.");
    }

    /**
     * Tests that the method handles invalid sound files gracefully.
     */
    @Test
    public void testPlayIOException() {
        // Call the method with a nonexistent or restricted file
        SoundEffect.play("/restricted.wav");

        // If no exceptions are thrown, the test passes
        System.out.println("Handled IOException gracefully.");
    }

    /**
     * Tests that the method handles exceptions gracefully.
     */
    @Test
    public void testPlayUnsupportedAudioFile() {
        // Call the method with an unsupported file type
        SoundEffect.play("/unsupported.txt");

        // Ensure no exceptions are thrown
        System.out.println("Handled UnsupportedAudioFileException gracefully.");
    }



}
