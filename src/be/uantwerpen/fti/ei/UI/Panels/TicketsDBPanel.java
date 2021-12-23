package be.uantwerpen.fti.ei.UI.Panels;

import be.uantwerpen.fti.ei.Controller.Controller;
import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.UI.Windows.TicketWindow;
import be.uantwerpen.fti.ei.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TicketsDBPanel extends JPanel implements ActionListener, Observer {
    private Controller controller;
    private DefaultListModel<Tickets> ListModel = new DefaultListModel<>();
    private JList<Tickets> List;

    public TicketsDBPanel(Controller controller){
        Database.getTicketDB().addObserver(this);
        this.controller = controller;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        JLabel Label = new JLabel("Tickets");
        this.add(Label,c);
        Database.getTicketDB().forEach(ListModel::addElement);
        List = new JList<>(ListModel);
        List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        List.setLayoutOrientation(JList.VERTICAL);
        c.gridx = 1;
        c.gridy = 1;
        this.add(List,c);
        JButton AddTicketButton = new JButton("Add ticket");
        AddTicketButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 0;
        this.add(AddTicketButton,c);

        JButton RemoveTicketButton = new JButton("Remove ticket");
        RemoveTicketButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 2;
        this.add(RemoveTicketButton,c);
    }

    public void Refresh(){
        ListModel.clear();
        Database.getTicketDB().forEach(ListModel::addElement);
        SwingUtilities.updateComponentTreeUI(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Add ticket":
                new TicketWindow(controller);
                break;
            case "Remove ticket":
                if(List.getSelectedValue() != null)
                    Database.getTicketDB().remove(List.getSelectedValue().getID());
                break;
        }
        Refresh();
    }

    @Override
    public void update(Observable o, Object arg) {
        Refresh();
    }
}
