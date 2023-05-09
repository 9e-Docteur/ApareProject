package be.ninedocteur.apare.module;

import be.ninedocteur.apare.Apare;
import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.content.Item;
import be.ninedocteur.apare.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemsModule extends Module {
    private JTextField imageLinkField = new JTextField(20);
    private JTextField itemNameField = new JTextField(20);
    private JTextField priceField = new JTextField(10);

    public ItemsModule() {
        super("Item Module", "1.0", "Loris Populaire");
    }

    @Override
    public void onLaunch() {
        createWindow("Add Item", 600, 600, false);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Image Link:"));
        panel.add(imageLinkField);
        panel.add(new JLabel("Item Name:"));
        panel.add(itemNameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String imageLink = imageLinkField.getText();
                String itemName = itemNameField.getText();
                double price = Double.parseDouble(priceField.getText().replace(',', '.')); // replace ',' with '.' for decimal parsing
                Item item = new Item(imageLink, itemName, price);
                Item.getItemList().add(item);
                Item.saveItem();
                Apare.logger.send("Registered a new item : " + imageLink + " " + itemName + ",  for " + price + "€", Logger.Type.SUCCESS);
                clearFields();
            }
        });
        panel.add(new JLabel(""));
        panel.add(addButton);

        this.getWindow().getContentPane().add(panel, BorderLayout.CENTER);
        this.getWindow().pack();
        this.getWindow().setVisible(true);
    }

    private void clearFields() {
        imageLinkField.setText("");
        itemNameField.setText("");
        priceField.setText("");
    }


}
