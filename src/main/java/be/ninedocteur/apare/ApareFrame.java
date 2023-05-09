package be.ninedocteur.apare;

import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.api.module.ModuleRegistry;
import be.ninedocteur.apare.init.ModuleInit;
import be.ninedocteur.apare.resources.TextureLocation;
import be.ninedocteur.apare.utils.Logger;
import be.ninedocteur.apare.utils.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ApareFrame extends JFrame {

    private static Module moduleToLaunch;

    private static JFrame frame = new JFrame();
    private static JLabel chose = new JLabel();
    private static JLabel state = new JLabel();
    private static JLabel addon = new JLabel();
    private static JButton button = new JButton();
    private static boolean isLoaded = false;
    private static int width = 720;
    private static int height = 480;

    public ApareFrame() {
        init();
        if(!isLoaded){
            state.setText("Loading...");
            state.setBounds(getWidth() / 2, getHeight() / 2, 1, 1);
            frame.add(state);
        } else {
            frame.remove(state);
        }

        if(Apare.getModList().size() > 0){
            addon.setText(Apare.getModList().size() + " Addons installed.");
            addon.setBounds(getWidth() / 2, getHeight() / 2, 20, 20);
            frame.add(addon);
        }
        frame.setTitle("APARE Project");
        frame.setVisible(true);

        chose.setText("Choose a module to launch...");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Module module : ModuleRegistry.getModuleList()) {
            JPanel panel = new JPanel();
            JButton button = new JButton(module.getName());
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Module.execute(module);
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
            contentPane.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(contentPane);
        frame.add(scrollPane);

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(720, 480);
    }

    private static void init(){
        ModuleInit.init();
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == ApareFrame.button){
//            Module.execute(moduleToLaunch);
//            Logger.send("Launching " + moduleToLaunch.getName() + "...", Logger.Type.SUCCESS);
//        }
//    }

    public static void setLoaded(boolean isLoaded) {
        ApareFrame.isLoaded = isLoaded;
    }

    public static boolean isLoaded() {
        return isLoaded;
    }
}
