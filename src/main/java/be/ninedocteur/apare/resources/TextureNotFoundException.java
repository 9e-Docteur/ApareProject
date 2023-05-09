package be.ninedocteur.apare.resources;

import java.io.IOException;

public class TextureNotFoundException extends IOException {
    public TextureNotFoundException(String imageName){
        super("Cannot find texture " + imageName);
    }
}
