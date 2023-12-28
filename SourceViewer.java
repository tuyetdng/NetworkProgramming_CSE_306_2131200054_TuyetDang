package eiu.cit.netprog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {
    public static void main(String[] args) {

        try {
            URL url = new URL(args[0]);
            InputStream in = url.openStream();
            OutputStream os = new FileOutputStream("src\\main\\java\\eiu\\cit\\n" + //
                    "etprog\\video.mp3");
            BufferedOutputStream bos = new BufferedOutputStream(os);
            BufferedInputStream bis = new BufferedInputStream(in);
            

            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
            }
            bos.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
