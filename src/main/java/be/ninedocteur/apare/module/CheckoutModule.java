package be.ninedocteur.apare.module;

import be.ninedocteur.apare.api.module.Module;

import javax.swing.*;

public class CheckoutModule extends Module {
    public CheckoutModule() {
        super("Check Out", "1.0", "Loris Populaire");
    }


    @Override
    public void onLaunch() {
        createWindow("Checkout v1.0", 280, 280, true);
        JFrame frame = getWindow();
        
    }
}
