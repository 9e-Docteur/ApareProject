package be.ninedocteur.apare.frames;

import be.ninedocteur.apare.ApareFrame;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ConfigSetupFrame extends JFrame {
    private static final String CONFIG_PATH = "C:\\ApareProject\\config.json";
    private HashMap<String, JTextField> configString;
    private HashMap<String, JCheckBox> configBool;

    public ConfigSetupFrame(){
        super("Config Setup");
        configString = new HashMap<>();
        configBool = new HashMap<>();
        configBool.put("Online Mode", new JCheckBox("", false));

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel stringPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (String key : configString.keySet()) {
            stringPanel.add(new JLabel(key + ":"));
            stringPanel.add(configString.get(key));
        }

        JPanel boolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (String key : configBool.keySet()) {
            boolPanel.add(new JLabel(key + ":"));
            boolPanel.add(configBool.get(key));
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfig();
                ApareFrame apareFrame = new ApareFrame();
            }
        });
        bottomPanel.add(okButton);

        mainPanel.add(stringPanel, BorderLayout.NORTH);
        mainPanel.add(boolPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        setSize(new Dimension(400, 200));
        setVisible(true);
    }

    private void saveConfig() {
        JSONObject config = new JSONObject();
        for (String key : configString.keySet()) {
            config.put(key, configString.get(key).getText());
        }
        for (String key : configBool.keySet()) {
            config.put(key, configBool.get(key).isSelected());
        }
        try {
            FileWriter file = new FileWriter(CONFIG_PATH);
            file.write(config.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
