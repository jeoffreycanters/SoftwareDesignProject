package be.uantwerpen.fti.ei.UI.Panels;

import be.uantwerpen.fti.ei.Controller.Controller;
import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Factory.TicketEvenFactory;
import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EvenPanel extends JPanel implements ActionListener, TicketPanel {
    private ArrayList<Object> UserArrayList = new ArrayList<>();
    private ArrayList<User> RemainingUsers = new ArrayList<>();
    private ArrayList<JComboBox<Object>> ComboBoxArray = new ArrayList<>();
    private int i = 0;
    private int row = 0;
    JButton AddUserButton = new JButton("Add user");
    JButton RemoveUserButton = new JButton("Remove user");

    public EvenPanel(){
        this.setLayout(new GridBagLayout());
        JLabel Label = new JLabel("Add users to ticket");
        AddComponent(Label, 0,0,1,1, new Insets(10,50,10,10), false);
        Database.getUserDB().forEach(UserArrayList::add);
        ExtraUserLine();
        this.AddUserButton.addActionListener(this);
        this.RemoveUserButton.addActionListener(this);
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

    private void RemainingUsersUpdate(){
        RemainingUsers.clear();
        Database.getUserDB().forEach(RemainingUsers::add);
        for(JComboBox<Object> cb : ComboBoxArray){
            RemainingUsers.remove(cb.getSelectedItem());
        }
    }

    private void ExtraUserLine(){
        UserArrayList.clear();
        Database.getUserDB().forEach(UserArrayList::add);
        if(ComboBoxArray.size() < UserArrayList.size()){
            if(ComboBoxArray.size() > 0){
                ComboBoxArray.get(i-1).setEnabled(false);
            }
            RemainingUsersUpdate();
            JComboBox<Object> comboBox = new JComboBox<Object>(RemainingUsers.toArray());
            comboBox.addActionListener(this);
            ComboBoxArray.add(comboBox);
            AddComponent(ComboBoxArray.get(i), row = row + 1, 0, 1, 1, new Insets(10,10,10,10), false);
            i++;
            this.remove(AddUserButton);
            this.remove(RemoveUserButton);
            AddComponent(AddUserButton, row = row + 1, 0, 1, 1, new Insets(10,10,10,10), false);
            AddComponent(RemoveUserButton, row, 1, 1, 1, new Insets(10,10,10,10), false);
        }
    }

    private void LessUserLine(){
        if(i>1){
            i--;
            this.ComboBoxArray.get(i-1).setEnabled(true);
            this.remove(ComboBoxArray.get(i));
            ComboBoxArray.remove(i);
            row--;
            this.remove(AddUserButton);
            this.remove(RemoveUserButton);
            AddComponent(AddUserButton, row = row + 1, 0, 1, 1, new Insets(10,10,10,10), false);
            AddComponent(RemoveUserButton, row, 1, 1, 1, new Insets(10,10,10,10), false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Add user"))
            ExtraUserLine();
        else if(e.getActionCommand().equals("Remove user"))
            LessUserLine();
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public Tickets Create(String ticket, User user, double cost, Controller controller){
        TicketEvenFactory TEF = new TicketEvenFactory();
        TicketEven TE = (TicketEven) controller.createTickets(TEF, ticket, user);
        TE.setCost(cost);
        for(int j=0;j<i;j++){
            TE.addIndebted((User) ComboBoxArray.get(j).getSelectedItem());
        }
        return TE;
    }

}
