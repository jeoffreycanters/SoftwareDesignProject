package be.uantwerpen.fti.ei.UI.Windows;

import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserWindow extends JFrame implements ActionListener {
    private JTextField TextField;

    public UserWindow(){
        super("Money Tracker");
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setVisible(true);
    }

    private void Initialise(){
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());
        this.setSize(700,700);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        c.gridx = 1;
        c.gridy = 0;
        JLabel Label = new JLabel("Username");
        this.add(Label,c);

        this.TextField = new JTextField(25);
        TextField.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        this.add(TextField,c);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        this.add(CancelButton, c);

        JButton ConfirmButton = new JButton("Create User");
        ConfirmButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        this.add(ConfirmButton, c);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Create User")){
            User user = new User(TextField.getText());
            Database.getUserDB().add(user.getID(), user);
            this.setVisible(false);
            this.dispose();
        }
        else if(e.getActionCommand().equals("Cancel")){
            this.setVisible(false);
            this.dispose();
        }
    }
}
