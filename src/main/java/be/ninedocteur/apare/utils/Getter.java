package be.ninedocteur.apare.utils;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Getter {
    private static String imageDir = "C:/ApareProject/Images/";

    public static ImageIcon getImage(String fileName) {
        File file = new File(imageDir + fileName + ".png");
        return new ImageIcon(file.getAbsolutePath());
    }

    public static ImageIcon getListImage(String fileName) {
        File file = new File(imageDir + fileName + ".png");
        return new ImageIcon(file.getAbsolutePath());
    }
}
