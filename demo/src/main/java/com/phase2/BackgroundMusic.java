package com.phase2;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The {@code BackgroundMusic} class is responsible for playing and managing background music in the game.
 * <p>
 * This class supports playing .wav audio files in a continuous loop and allows adjusting the playback volume.
 * </p>
 */
public class BackgroundMusic {

    private Clip clip;

    /**
     * Plays a given .wav audio file in an infinite loop.
     * <p>
     * This method loads the specified audio file, adjusts the playback volume, and starts looping the audio.
     * </p>
     *
     * @param filePath The relative path to the .wav file in the resources directory.
     * @param volume   The desired volume in decibels (negative values decrease volume, e.g., -10.0f).
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
     * <p>
     * This method stops playback of the audio file and releases associated resources.
     * </p>
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
