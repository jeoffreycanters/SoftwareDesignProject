package be.uantwerpen.fti.ei.UI.Panels;

import be.uantwerpen.fti.ei.Controller.Controller;
import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Factory.TicketRandomFactory;
import be.uantwerpen.fti.ei.Tickets.TicketRandom;
import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    public void ExtraUserLine(){
        UserArrayList.clear();
        Database.getUserDB().forEach(UserArrayList::add);
        if(ComboBoxArray.size() < UserArrayList.size()){
            if(ComboBoxArray.size() > 0){
                ComboBoxArray.get(i-1).setEnabled(false);
            }
            RemainingUsersUpdate();
            JComboBox<Object> comboBox = new JComboBox<Object>(RemainingUsers.toArray());
            ComboBoxArray.add(comboBox);
            comboBox.addActionListener(this);
            JScrollPane UserPane = new JScrollPane(comboBox);

            SpinnerNumberModel Model = new SpinnerNumberModel(0, 0, Double.POSITIVE_INFINITY, 1);
            JSpinner PriceField = new JSpinner(Model);
            SpinnersArray.add(PriceField);
            JScrollPane PricePane = new JScrollPane(PriceField);

            JSplitPane SplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, UserPane, PriceField);
            SplitPane.setResizeWeight(0.3);
            SplitPanesArray.add(SplitPane);
            i++;
            AddComponent(SplitPane, row=row+1,0,3,1, new Insets(10,10,10,10), false);

            this.remove(AddUserButton);
            this.remove(RemoveUserButton);
            AddComponent(AddUserButton, row = row + 1, 0, 1, 1, new Insets(10, 10, 10, 10), false);
            AddComponent(RemoveUserButton, row, 1, 1, 1, new Insets(10, 10, 10, 10), false);
        }
    }

    private void RemainingUsersUpdate(){
        RemainingUsers.clear();
        Database.getUserDB().forEach(RemainingUsers::add);
        for(JComboBox<Object> cb : ComboBoxArray){
            RemainingUsers.remove(cb.getSelectedItem());
        }
    }

    private void LessPersonLine(){
        if(i>1){
            i--;
            this.ComboBoxArray.get(i-1).setEnabled(true);
            this.ComboBoxArray.remove(i);
            this.SpinnersArray.remove(i);
            this.remove(SplitPanesArray.get(i));
            SplitPanesArray.remove(i);
            row--;
            this.remove(AddUserButton);
            this.remove(RemoveUserButton);
            AddComponent(AddUserButton, row = row + 1, 0, 1, 1, new Insets(10, 10, 10, 10), false);
            AddComponent(RemoveUserButton, row, 1, 1, 1, new Insets(10, 10, 10, 10), false);
        }
    }

    @Override
    public Tickets Create(String ticket, User user, double price, Controller controller) {
        TicketRandomFactory TRF = new TicketRandomFactory();
        TicketRandom TR = (TicketRandom) controller.createTicket(TRF, ticket, user);
        for(int j=0;j<i;j++){
            JSpinner Spinner = (JSpinner) (this.SpinnersArray.get(j));
            Double Price = (Double) (Spinner.getValue());
            TR.addIndebted((User) this.ComboBoxArray.get(j).getSelectedItem(), Price);
        }
        return TR;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add user"))
            ExtraUserLine();
        else if(e.getActionCommand().equals("Remove user"))
            LessPersonLine();
        SwingUtilities.updateComponentTreeUI(this);
    }
}
