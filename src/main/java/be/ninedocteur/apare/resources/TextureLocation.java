package be.ninedocteur.apare.resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class TextureLocation {
    private String modName;
    private String texturePath;

    public TextureLocation(String modName, String texturePath) {
        this.modName = modName;
        this.texturePath = texturePath;
    }

    public String getTextureLocation() {
        return "assets/" + modName + "/textures/" + texturePath;
    }

    public BufferedImage getImage() throws IOException {
        String textureLocation = getTextureLocation();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(textureLocation);
        if (inputStream == null) {
            throw new TextureNotFoundException(textureLocation);
        }
        return ImageIO.read(inputStream);
    }
}
