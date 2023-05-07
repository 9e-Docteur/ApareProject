package be.ninedocteur.apare.module;

import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.content.Item;
import be.ninedocteur.apare.utils.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ItemListModule extends Module {
    private ArrayList<Item> itemList;
    private JPanel itemPanel;

    public ItemListModule() {
        super("Item List", "1.0", "Loris Populaire");
        itemList = Item.getItemList();
    }

    @Override
    public void onLaunch() {
        createWindow("Item List", 400, 100, false);

        itemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)) {
            @Override
            public Dimension getPreferredSize() {
                int itemCount = Item.getItemList().size();
                int rows = (int) Math.ceil(itemCount / 3.0);
                int squareSize = Math.min(getWidth() / 3 - 20, getHeight() / rows - 20);
                int width = squareSize * 3 + 20 * 2;
                int height = squareSize * rows + 20 * (rows - 1) + 20 * 2;
                return new Dimension(width, height);
            }
        };
        addItemsToPanel();
        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        this.getWindow().add(scrollPane, BorderLayout.CENTER);
        this.getWindow().pack();
        this.getWindow().setLocationRelativeTo(null);
    }

    private void addItemsToPanel() {
        for (Item item : itemList) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setPreferredSize(new Dimension(100, 100));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            try {
                File file = new File(item.getImageLink());
                Image image = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(image.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
                JLabel imageLabel = new JLabel(icon);
                panel.add(imageLabel, BorderLayout.CENTER);
            } catch (IOException ex) {
                System.out.println("Could not read image file: " + ex.getMessage());
                ImageIcon defaultIcon = Getter.getImage("defaultIcon");
                Image defaultImage = defaultIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                ImageIcon defaultScaledIcon = new ImageIcon(defaultImage);
                JLabel defaultImageLabel = new JLabel(defaultScaledIcon);
                panel.add(defaultImageLabel, BorderLayout.CENTER);
            }


            JLabel nameLabel = new JLabel(item.getItemName());
            JLabel priceLabel = new JLabel(String.valueOf(item.getPrice()));
            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.add(nameLabel);
            textPanel.add(priceLabel);
            panel.add(textPanel, BorderLayout.SOUTH);
            itemPanel.add(panel);
        }
    }
}
