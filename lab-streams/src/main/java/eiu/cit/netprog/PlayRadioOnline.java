package eiu.cit.netprog;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayRadioOnline {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://ice10.outlaw.fm/KIEV2");
            BufferedInputStream bis = new BufferedInputStream(u.openStream());
            Player mp3Player = new Player(bis);
            mp3Player.play();

        } catch (IOException | JavaLayerException ex) {

        }
    }
}
