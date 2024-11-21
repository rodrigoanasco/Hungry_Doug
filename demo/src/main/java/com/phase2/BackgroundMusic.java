package com.phase2;

import javax.sound.sampled.*;
import java.io.IOException;

public class BackgroundMusic {

    private Clip clip;

    /**
     * Plays a given .wav audio file in an infinite loop.
     *
     * @param filePath The relative path to the .wav file in the resources directory.
     */
    public void play(String filePath) {
        try {
            // Load the audio file as a stream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                getClass().getResource(filePath)
            );
            clip = AudioSystem.getClip();
    
            // Open the clip and start looping
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
            e.printStackTrace();
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
