package be.ninedocteur.apare.module;

import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.frames.NotifyFrame;
import be.ninedocteur.apare.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginModule extends Module implements ActionListener {
    JButton loginButton = new JButton();
    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();

    public LoginModule() {
        super("Login", "1.0", "Loris Populaire");
    }

    @Override
    public void onLaunch() {
        createWindow("Login v1.0", 480, 256, false);
        JFrame frame = getWindow();
        userField.setBounds(0, 0, 10, 10);
        passField.setBounds(0, 10, 10, 10);
        loginButton.setBounds(0, 30, 10, 10);
        frame.add(userField);
        frame.add(passField);
        frame.add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            NotifyFrame notifyFrame = new NotifyFrame("You clicked login button!"); //TEST
            Logger.send("User " + userField.getText() + " tried to connect", Logger.Type.WARN);
        }
    }
}
