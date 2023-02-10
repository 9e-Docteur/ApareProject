package be.ninedocteur.apare.frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotifyFrame extends JFrame implements ActionListener {

    public NotifyFrame(String message){
        this.setTitle("Notification");
        JLabel contentLabel = new JLabel();
        contentLabel.setText(message);
        contentLabel.setBounds(this.getWidth() /2, this.getHeight() /2, 0, 0);
        this.add(contentLabel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(256, 128);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
