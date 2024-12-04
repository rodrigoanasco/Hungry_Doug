package com.phase2;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundEffect {
    /**
     * Plays a sound effect from a .wav file.
     *
     * @param filePath The path to the .wav file.
     */
    public static void play(String filePath) {
        URL resource = SoundEffect.class.getResource(filePath);

        if (resource == null) {
            System.err.println("Sound file not found: " + filePath);
            return; // Gracefully exit if the file is not found
        }

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + filePath + ". " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error while playing sound effect: " + filePath + ". " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Audio line unavailable for sound effect: " + filePath + ". " + e.getMessage());
        }
    }
}
