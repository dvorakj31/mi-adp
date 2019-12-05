package cz.cvut.fit.miadp.mvcgame.sound;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
    private static final String EXPLOSION_PATH = "src/main/resources/sounds/explosion.wav";
    private static final String SHOOT_PATH = "src/main/resources/sounds/shoot.wav";
    private static final String FLY_PATH = "src/main/resources/sounds/fly.wav";
    public static synchronized void playSound(SoundType type) {
        File sound = null;
        switch(type) {
            case SHOOT_SOUND:
                sound = new File(SHOOT_PATH);
                break;
            case EXPLOSION_SOUND:
                sound = new File(EXPLOSION_PATH);
                break;
            case FLY_SOUND:
                sound = new File(FLY_PATH);
                break;
            default:
                break;
        }
        File finalSound = sound;
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream is = AudioSystem.getAudioInputStream(finalSound);
                clip.open(is);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }).start();
    }
}
