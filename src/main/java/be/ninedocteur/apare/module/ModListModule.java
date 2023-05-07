package be.ninedocteur.apare.module;

import be.ninedocteur.apare.Apare;
import be.ninedocteur.apare.api.ModAddon;
import be.ninedocteur.apare.api.module.Module;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModListModule extends Module {
    public ModListModule() {
        super("Mod List", "1.0", "Loris Populaire");
    }

    @Override
    public void onLaunch() {
        createWindow("Mod List", 300, 600, false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<ModAddon> modList = Apare.getModList();
        if(!modList.isEmpty()){
            for (ModAddon mod : modList) {
                JLabel label = new JLabel(mod.getModName());
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(label);

                JLabel versionLabel = new JLabel("Version: " + mod.getModVersion());
                versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                versionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                panel.add(versionLabel);


                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        } else {
            JLabel label = new JLabel("No mod installed.");
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        this.getWindow().getContentPane().add(scrollPane);

        this.getWindow().pack();
        this.getWindow().setLocationRelativeTo(null);
        this.getWindow().setVisible(true);
    }
}
