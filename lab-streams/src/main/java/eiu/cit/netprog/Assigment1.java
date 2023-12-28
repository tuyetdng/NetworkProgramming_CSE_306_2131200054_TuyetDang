package eiu.cit.netprog;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Assigment1 {

    public static void main(String[] args) throws IOException {
        String pathName = "C:\\Users\\admin\\lab-streams\\src\\main\\resources\\doc1.txt";
        InputStream reader = null;
        int count = 0;
        reader = new FileInputStream(new File(pathName));

        int i = 0;
        while ((i = reader.read()) != -1) {
            count++;
        }
        System.out.println(count);

    }
}