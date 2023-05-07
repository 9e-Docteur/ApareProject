package be.ninedocteur.apare.module;

import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.frames.NotifyFrame;
import be.ninedocteur.apare.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginModule extends Module implements ActionListener {
    private JLabel label1, label2, errorLabel;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginModule() {
        super("Login", "1.0", "Loris Populaire");
    }

    @Override
    public void onLaunch() {
        createWindow("Login v1.0", 300, 200, false);

        label1 = new JLabel("Username:");
        label2 = new JLabel("Password:");
        errorLabel = new JLabel("");

        textField = new JTextField(10);
        passwordField = new JPasswordField(10);

        loginButton = new JButton("Log in");
        loginButton.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(label1, c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(textField, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(label2, c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(passwordField, c);


        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, c);


        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(errorLabel, c);


        this.getWindow().add(panel);

        this.getWindow().setLocationRelativeTo(null);

        this.getWindow().setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = textField.getText();
        String password = new String(passwordField.getPassword());
        if(e.getSource() == loginButton){
            if (username.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(this.getWindow(), "Connection Successful");
                Logger.send("User connected", Logger.Type.SUCCESS);
            } else {
                errorLabel.setText("Username/password is incorrect.");
            }
        }
    }
}
