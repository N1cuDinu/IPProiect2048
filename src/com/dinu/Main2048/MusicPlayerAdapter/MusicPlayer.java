package com.dinu.Main2048.MusicPlayerAdapter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {
    public Clip clip;
    private String musicPath;

    public MusicPlayer(String musicPath) {
        this.musicPath = musicPath;
    }

    public void play() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicPath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void stop() {
        clip.stop();
        clip.close();
    }
    public void repeat() {
        clip.loop(10);
    }
}
