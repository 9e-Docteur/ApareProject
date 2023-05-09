package be.ninedocteur.apare.utils;

import be.ninedocteur.apare.resources.TextureLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Screen {
    public static JLabel drawImage(TextureLocation textureLocation) {
        try {
            BufferedImage image = textureLocation.getImage();
            ImageIcon icon = new ImageIcon(image);
            return new JLabel(icon);
        } catch (IOException e) {
            System.err.println("Failed to load texture: " + e.getMessage());
            return null;
        }
    }

    public static JLabel drawResizedImage(TextureLocation textureLocation, int x, int y) {
        try {
            BufferedImage image = textureLocation.getImage();
            Image scaledImage = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            return new JLabel(icon);
        } catch (IOException e) {
            System.err.println("Failed to load texture: " + e.getMessage());
            return null;
        }
    }

}
