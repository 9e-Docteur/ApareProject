package be.ninedocteur.apare.module;

import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.content.Item;
import be.ninedocteur.apare.utils.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutModule extends Module {
    private List<Item> itemList;
    private List<Item> selectedItems;
    private Object[][] data;
    private JTable table;
    private JLabel totalLabel;

    public CheckoutModule() {
        super("Check Out", "1.0", "Loris Populaire");
    }


    @Override
    public void onLaunch() {
        createWindow("Checkout v1.0", 1280, 720, true);
        JFrame frame = this.getWindow();
        itemList = Item.getItemList();

        selectedItems = new ArrayList<>();

        int numRows = selectedItems.size() + 2;
        int numCols = 3;
        data = new Object[numRows][numCols];
        data[numRows - 2][0] = "Item";
        data[numRows - 1][0] = "Total";
        data[numRows - 1][2] = 0.0;

        for (int i = 0; i < selectedItems.size(); i++) {
            Item item = selectedItems.get(i);
            data[i][0] = item.getItemName();
            data[i][1] = 1;
            data[i][2] = item.getPrice();
        }


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));

        JPanel panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelGauche);
        contentPane.add(scrollPane);


        for (Item item : itemList) {
            JPanel panelItem = new JPanel();
            panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
            Image image;
            try {
                File file = new File(item.getImageLink());
                image = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(image.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                JLabel imageLabel = new JLabel(icon);
            } catch (IOException ex) {
                System.out.println("Could not read image file: " + ex.getMessage());
                ImageIcon defaultIcon = Getter.getImage("defaultIcon");
                image = defaultIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                ImageIcon defaultScaledIcon = new ImageIcon(image);
                JLabel defaultImageLabel = new JLabel(defaultScaledIcon);
            }
            JLabel labelImage = new JLabel(new ImageIcon(image));
            JLabel labelName = new JLabel(item.getItemName());
            JLabel labelPrice = new JLabel(item.getPrice() + "€");
            labelPrice.setFont(new Font("Arial", Font.PLAIN, 12));
            JButton button = new JButton("Ajouter au panier");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedItems.add(item);
                    updateTable();
                }
            });
            panelItem.add(labelImage);
            panelItem.add(labelName);
            panelItem.add(labelPrice);
            panelItem.add(button);
            panelItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelGauche.add(panelItem);
        }

        JPanel panelDroite = new JPanel();
        panelDroite.setLayout(new BorderLayout());

        String[] columnNames = {"Item", "Quantity", "Price"};
        table = new JTable(data, columnNames);
        panelDroite.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPane.add(panelDroite);


        double total = 0.0;
        for (int i = 0; i < data.length - 1; i++) {
            if(data[i][1] != null){
                total += (int) data[i][1] * (double) data[i][2];
            } else {
                total = 0;
            }
        }
        data[numRows - 1][2] = total;
        totalLabel = new JLabel("Total : " + total + "€");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelDroite.add(totalLabel, BorderLayout.SOUTH);
        JButton payButton = new JButton("Payer");
        panelDroite.add(payButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void updateTable() {
        List<Object[]> dataList = new ArrayList<Object[]>();
        double total = 0.0;

        for (Item item : itemList) {
            int quantity = 0;
            for (Item selectedItem : selectedItems) {
                if (selectedItem.getItemName().equals(item.getItemName())) {
                    quantity++;
                }
            }
            Object[] row = new Object[3];
            row[0] = item.getItemName();
            row[1] = quantity;
            row[2] = item.getPrice();
            dataList.add(row);
            total += quantity * item.getPrice();
        }

        data = dataList.toArray(new Object[dataList.size()][3]);
        data[data.length - 1][2] = total;
        totalLabel.setText("Total : " + total + "€");
        String[] columnNames = {"Item", "Quantity", "Price"};
        table.setModel(new DefaultTableModel(data, columnNames));
    }
}
