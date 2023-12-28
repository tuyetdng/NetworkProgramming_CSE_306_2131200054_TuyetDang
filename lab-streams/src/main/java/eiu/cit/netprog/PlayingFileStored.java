package eiu.cit.netprog;

import java.io.File;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayingFileStored {
    public static void main(String[] args) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(
                    "E:\\Documents\\CSE 306_NP\\lab-streams\\src\\main\\resources\\fileStored\\sample1.wav")
                    .getAbsoluteFile());
            Clip playSound = AudioSystem.getClip();
            playSound.open(ais);
            playSound.start();

            //Pause the thread to play the other thread
            while (!playSound.isRunning())
                Thread.sleep(10);
            while (playSound.isRunning())
                Thread.sleep(10);
            playSound.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
