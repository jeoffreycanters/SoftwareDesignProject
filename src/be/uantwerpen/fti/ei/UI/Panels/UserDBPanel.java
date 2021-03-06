package be.uantwerpen.fti.ei.UI.Panels;

import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.UI.Windows.UserWindow;
import be.uantwerpen.fti.ei.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserDBPanel extends JPanel implements ActionListener, Observer {
    private ArrayList<User> UserArrayList = new ArrayList<>();
    private DefaultListModel<User> ListModel = new DefaultListModel<>();
    private JList<User> List;

    public UserDBPanel(){
        Database.getUserDB().addObserver(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        JLabel Label = new JLabel("Users");
        this.add(Label,c);

        Database.getUserDB().forEach(UserArrayList::add);

        UserArrayList.forEach(ListModel::addElement);
        List = new JList<>(ListModel);
        List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        List.setLayoutOrientation(JList.VERTICAL);
        c.gridx = 1;
        c.gridy = 1;
        this.add(List,c);

        JButton AddUserButton = new JButton("Add user");
        AddUserButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 0;
        this.add(AddUserButton,c);

        JButton RemoveUserButton = new JButton("Remove user");
        RemoveUserButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 2;
        this.add(RemoveUserButton,c);
    }

    public void Refresh(){
        UserArrayList.clear();
        Database.getUserDB().forEach(UserArrayList::add);
        ListModel.clear();
        UserArrayList.forEach(ListModel::addElement);
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Add user":
                UserWindow UW = new UserWindow();
                break;
            case "Remove user":
                AtomicBoolean error = new AtomicBoolean(false);
                if(List.getSelectedValue() != null){
                    Database.getTicketDB().forEach(ticket -> {
                        if(ticket.getPayer().equals(List.getSelectedValue().getID()))
                            error.set(true);
                        else if(ticket.getIndebted().containsKey(List.getSelectedValue().getID()))
                            error.set(true);
                    });
                    if(!error.get())
                        Database.getUserDB().remove(List.getSelectedValue().getID());
                    else
                        JOptionPane.showMessageDialog(null, "Remove the tickets containing this person", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                break;
        }
        Refresh();
    }

    @Override
    public void update(Observable o, Object arg) {
        Refresh();
    }
}
