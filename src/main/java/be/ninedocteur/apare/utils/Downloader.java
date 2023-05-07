package be.ninedocteur.apare.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Downloader {
    public static void downloadImage(String url, String fileName) throws IOException {
        URL imageUrl = new URL(url);
        InputStream inputStream = imageUrl.openStream();
        OutputStream outputStream = new FileOutputStream("C:/ApareProject/Images/" + fileName + ".png");

        byte[] buffer = new byte[2048];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        inputStream.close();
        outputStream.close();
    }
}
