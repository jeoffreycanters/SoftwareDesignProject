package be.uantwerpen.fti.ei.UI.Panels;

import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RandomPanel extends JPanel implements ActionListener, TicketPanel {
    private ArrayList<User> UserArrayList = new ArrayList<>();
    private ArrayList<User> RemainingUsers = new ArrayList<>();
    private ArrayList<JSplitPane> SplitPanesArray = new ArrayList<>();
    private ArrayList<JSpinner> SpinnersArray = new ArrayList<>();
    private ArrayList<JComboBox<Object>> ComboBoxArray = new ArrayList<>();
    private int i = 0;
    private int row = 0;
    JButton AddUserButton = new JButton("Add user");
    JButton RemoveUserButton = new JButton("Remove user");

    public RandomPanel(){
        this.setLayout(new GridBagLayout());
        Insets Inset = new Insets(10,10,10,10);
        JLabel Label = new JLabel("Add users to ticket");
        AddComponent(Label, 0, 0, 1, 1, new Insets(10,50,10,10), false);
        Database.getUserDB().forEach(UserArrayList::add);

    }

    private void AddComponent(Component component, int row, int column, int width, int height, Insets insets, boolean fill){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = width;
        c.gridheight = height;
        c.insets = insets;
        if(fill)
            c.fill = GridBagConstraints.HORIZONTAL;
        else
            c.fill = GridBagConstraints.NONE;
        this.add(component, c);
    }
}
