package be.ninedocteur.apare.api.module;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class Module {
    private String name;
    private String version;
    private String author;
    public JFrame frame;

    public Module(String name, String version, String author){
        this.name = name;
        this.version = version;
        this.author = author;
    }

    public abstract void onLaunch();

    public static void execute(Module module){
        module.onLaunch();
    }

    public void createWindow(String name, int sizeX, int sizeY, boolean resizable){
        frame = new JFrame();
        frame.setTitle(name);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                if (frame != null) {
                    frame.dispose();
                }
            }
        });
        frame.setResizable(resizable);
        frame.setSize(sizeX, sizeY);
    }

    public JFrame getWindow() {
        return frame;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }
}
