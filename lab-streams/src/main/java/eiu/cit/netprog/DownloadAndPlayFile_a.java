package eiu.cit.netprog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class DownloadAndPlayFile_a {
    public static void main(String[] args) {
        URL url;
        try {
            url = new URL("https://www.tanbinhtech.com:8443/sample1.wav");
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream(
                    "E:\\Documents\\CSE 306_NP\\lab-streams\\src\\main\\resources\\fileDownload\\downloadFile2.wav");

            int i = 0;
            while ((i = is.read()) != -1) {
                fos.write(i);
            }
            fos.close();

            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(
                    "E:\\Documents\\CSE 306_NP\\lab-streams\\src\\main\\resources\\fileDownload\\downloadFile2.wav")
                    .getAbsoluteFile());
            Clip playSound = AudioSystem.getClip();
            playSound.open(ais);
            playSound.start();

            // Pause the thread to play the other thread
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
