package eiu.cit.netprog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadFileFromTheInt {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tanbinhtech.com:8443/sample10.wav");
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream(
                    "E:\\Documents\\CSE 306_NP\\lab-streams\\src\\main\\resources\\fileDownload\\downloadFile.wav");

            int i = 0;
            while ((i = is.read()) != -1) {
                fos.write(i);
            }
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
