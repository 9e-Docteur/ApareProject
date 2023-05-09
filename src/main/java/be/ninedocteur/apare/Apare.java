package be.ninedocteur.apare;

import be.ninedocteur.apare.api.ModAddon;
import be.ninedocteur.apare.api.ModLoader;
import be.ninedocteur.apare.content.Item;
import be.ninedocteur.apare.frames.ConfigSetupFrame;
import be.ninedocteur.apare.utils.Downloader;
import be.ninedocteur.apare.utils.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Apare {
    public static List<ModAddon> modList = new ArrayList();
    public static double VERSION = 0.1;
    public static Logger logger = new Logger();

    public static void main(String[] args) throws IOException {
        ModLoader modLoader = new ModLoader();
        ApareConfig config = new ApareConfig();
        if(config.isConfigFilePresent()){
            config.checkForConfig();
            ApareFrame apareFrame = new ApareFrame();
        } else {
            ConfigSetupFrame configSetupFrame = new ConfigSetupFrame();
            config.createConfig("version", String.valueOf(VERSION));
        }
        //getModList().add(new ApareBaseAddon("Apare Project Base", "0.1 DEV"));
        Item.getOrCreateItemsFile();
        Item.getOrCreateItemsDir();
        Item.getOrCreateImagesDir();
        Item.downloadImages();
        Item.loadItemsFromFile();
        Apare.downloadAssets();
        for(ModAddon mod : modList){
            mod.loadMod();
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
        for(Item item : Item.getItemList()){
            Downloader.downloadImage(item.getImageLink(), item.getItemName());
        }
    }
}
