package eiu.cit.netprog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImage {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tanbinhtech.com:8443/july.webp");
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream(
                    "E:\\Documents\\CSE 306_NP\\lab-streams\\src\\main\\resources\\pic.jpg");

            int i = 0;
            while ((i = is.read()) != -1) {
                fos.write(i);
            }
            fos.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
        }
    }
}
