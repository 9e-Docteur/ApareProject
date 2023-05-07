package be.ninedocteur.apare;

import be.ninedocteur.apare.api.ModAddon;
import be.ninedocteur.apare.content.Item;
import be.ninedocteur.apare.utils.Downloader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Apare {
    public static List<ModAddon> modList = new ArrayList();

    public static void main(String[] args) throws IOException {
        ApareFrame apareFrame = new ApareFrame();
        getModList().add(new ApareBaseAddon("Apare Project Base", "0.1 DEV"));
        Item.getOrCreateItemsFile();
        Item.getOrCreateItemsDir();
        Item.getOrCreateImagesDir();
        Item.downloadImages();
        Item.loadItemsFromFile();
        Apare.downloadAssets();
        for(ModAddon mod : modList){
            ModAddon.loadMod(mod);
        }
    }

    public static List<ModAddon> getModList() {
        return modList;
    }

    public static void registerModAddon(ModAddon modAddon){
        modList.add(modAddon);
    }

    public static void downloadAssets() throws IOException {
        Downloader.downloadImage("https://juststickers.in/wp-content/uploads/2019/11/Internet-error.png", "defaultIcon");
    }

}
