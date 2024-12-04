package com.phase2;

import javax.sound.sampled.*;
import java.io.IOException;

public class BackgroundMusic {

    private Clip clip;

    /**
     * Plays a given .wav audio file in an infinite loop.
     *
     * @param filePath The relative path to the .wav file in the resources directory.
     * @param volume   The desired volume in decibels (negative values decrease volume).
     */
    public void play(String filePath, float volume) {
        try {
            // Load the audio file as a stream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                getClass().getResource(filePath)
            );
            clip = AudioSystem.getClip();

            // Open the clip and start looping
            clip.open(audioInputStream);

            // Adjust the volume using FloatControl
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Set volume (e.g., -10.0f for lower volume)

            // Start the clip and loop indefinitely
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + filePath + ". " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error while playing background music: " + filePath + ". " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Audio line unavailable for background music: " + filePath + ". " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Background music file not found: " + filePath + ". " + e.getMessage());
        }
    }

    /**
     * Stops the background music.
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
