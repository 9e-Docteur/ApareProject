package be.ninedocteur.apare.api.module;

import javax.swing.*;

public abstract class Module {
    private String name;
    private String version;
    private String author;
    public static JFrame frame = new JFrame();

    public Module(String name, String version, String author){
        this.name = name;
        this.version = version;
        this.author = author;
    }

    public abstract void onLaunch();

    public static void execute(Module module){
        module.onLaunch();
    }

    public static void createWindow(String name, int sizeX, int sizeY, boolean resizable){
        frame.setTitle(name);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(resizable);
        frame.setSize(sizeX, sizeY);
    }

    public static JFrame getWindow() {
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
