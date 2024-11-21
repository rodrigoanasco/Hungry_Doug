package com.phase2;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundEffect {
    /**
     * Plays a sound effect from a .wav file.
     *
     * @param filePath The path to the .wav file.
     */
    public static void play(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SoundEffect.class.getResource(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
