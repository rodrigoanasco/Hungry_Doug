package com.phase2;

import org.junit.Test;

public class SoundEffectTest {

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

    @Test
    public void testPlayIOException() {
        // Call the method with a nonexistent or restricted file
        SoundEffect.play("/restricted.wav");

        // If no exceptions are thrown, the test passes
        System.out.println("Handled IOException gracefully.");
    }

    @Test
    public void testPlayUnsupportedAudioFile() {
        // Call the method with an unsupported file type
        SoundEffect.play("/unsupported.txt");

        // Ensure no exceptions are thrown
        System.out.println("Handled UnsupportedAudioFileException gracefully.");
    }



}
