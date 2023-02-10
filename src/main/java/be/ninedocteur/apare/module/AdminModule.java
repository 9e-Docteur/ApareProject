package be.ninedocteur.apare.module;

import be.ninedocteur.apare.api.module.Module;

import javax.swing.*;

public class AdminModule extends Module {
    public AdminModule() {
        super("Admin Center", "1.0", "Loris Populaire");
    }


    @Override
    public void onLaunch() {
        createWindow("Admin center v1.0", 280, 280, true);
        JFrame frame = getWindow();
        
    }
}
