package be.ninedocteur.apare.content;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Item {
    private static ArrayList<Item> itemList = new ArrayList<Item>();
    private String imageLink;
    private String itemName;
    private double price;
    public Item(String imageLink, String itemName, double price){
        this.imageLink = imageLink;
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public double getPrice() {
        return price;
    }

    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    public static void addToItemList(Item item){
        getItemList().add(item);
    }

    public static void loadItemsFromFile() {
        try {
            getOrCreateItemsFile();
            BufferedReader reader = new BufferedReader(new FileReader("C:/ApareProject/Items/items.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String imageLink = parts[0];
                    String itemName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    Item item = new Item(imageLink, itemName, price);
                    itemList.add(item);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void downloadImages() {
        for (Item item : itemList) {
            String imageLink = item.getImageLink();
            String fileName = imageLink.substring(imageLink.lastIndexOf('/') + 1);
            try {
                URL url = new URL(imageLink);
                InputStream is = url.openStream();
                getOrCreateImagesDir();
                FileOutputStream fos = new FileOutputStream("C:/ApareProject/Images/" + fileName);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                is.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Image getImageForItem(Item item) {
        String fileName = item.getImageLink().substring(item.getImageLink().lastIndexOf('/') + 1);
        String filePath = "C:/ApareProject/Images/" + fileName;
        Image image = null;
        try {
            image = new ImageIcon(filePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void saveItem() {
        try {
            getOrCreateItemsFile();
            PrintWriter writer = new PrintWriter(new FileWriter("C:/ApareProject/Items/items.txt"));
            for (Item item : itemList) {
                String line = item.getImageLink() + ";" + item.getItemName() + ";" + item.getPrice();
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getOrCreateItemsDir() {
        File itemsDir = new File("C:\\ApareProject\\Items\\");
        if (!itemsDir.exists()) {
            itemsDir.mkdirs();
        }
        return itemsDir;
    }

    public static File getOrCreateImagesDir() {
        File imagesDir = new File("C:\\ApareProject\\Images\\");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }
        return imagesDir;
    }

    public static File getOrCreateItemsFile() throws IOException {
        File itemsDir = getOrCreateItemsDir();
        File itemsFile = new File(itemsDir, "items.txt");
        if (!itemsFile.exists()) {
            try {
                itemsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return itemsFile;
    }

}
