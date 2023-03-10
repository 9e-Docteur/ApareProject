package be.ninedocteur.apare;

import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.api.module.ModuleRegistry;
import be.ninedocteur.apare.init.ModuleInit;
import be.ninedocteur.apare.utils.Logger;
import javafx.scene.control.ButtonType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ApareFrame extends JFrame implements ActionListener {

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

        ArrayList<JButton> buttonList = new ArrayList();
        for(Module module : ModuleRegistry.moduleList){
            JPanel panel = new JPanel();
            panel.add(button);
            panel.add(state);
            buttonList.add(button);
            panel.setSize(new Dimension(50, 20));
            button.setBounds(getWidth() / 2, ModuleRegistry.getModuleList().size() + 20, 20, 120);
            button.setSize(new Dimension(70, 20));
            ApareFrame.moduleToLaunch = module;
            button.setText(module.getName());
            button.addActionListener(this);
            frame.add(button);
            frame.add(panel);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(720, 480);
    }

    private static void init(){
        ModuleInit.init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ApareFrame.button){
            Module.execute(moduleToLaunch);
            Logger.send("Launching " + moduleToLaunch.getName() + "...", Logger.Type.SUCCESS);
        }
    }

    public static void setLoaded(boolean isLoaded) {
        ApareFrame.isLoaded = isLoaded;
    }

    public static boolean isLoaded() {
        return isLoaded;
    }
}
