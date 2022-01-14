package be.uantwerpen.fti.ei.UI.Windows;

import be.uantwerpen.fti.ei.Controller.Controller;
import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.UI.Panels.EvenPanel;
import be.uantwerpen.fti.ei.UI.Panels.RandomPanel;
import be.uantwerpen.fti.ei.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicketWindow extends JFrame implements ActionListener {
    private Controller controller;
    private GridBagConstraints c = new GridBagConstraints();
    private EvenPanel EP = new EvenPanel();
    private RandomPanel RP = new RandomPanel();
    private JSpinner PriceField;
    private JTextField TextField;
    private JComboBox<Object> PayerComboBox;
    private JButton ConfirmButton;
    private boolean isEvenTicket;

    public TicketWindow(Controller controller){
        super("Money Tracker");
        this.controller = controller;
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Initialise();
        this.setVisible(true);
    }

    private void Initialise(){
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());
        this.setSize(700,700);
        Insets inset = new Insets(10,10,10,10);

        JLabel label = new JLabel("Ticket Name");
        AddComponent(label, 0,1,1,1,inset, false);

        this.TextField = new JTextField(25);
        AddComponent(this.TextField,1,1,1,1,inset,true);

        label = new JLabel("Who paid, and how much?");
        AddComponent(label,2,1,1,1,inset,false);

        ArrayList<Object> userArrayList = new ArrayList<>();
        Database.getUserDB().forEach(userArrayList::add);
        this.PayerComboBox = new JComboBox<>(userArrayList.toArray());
        JScrollPane userPane = new JScrollPane(PayerComboBox);

        SpinnerNumberModel model = new SpinnerNumberModel(0,0,Double.POSITIVE_INFINITY,1);
        this.PriceField = new JSpinner(model);
        JScrollPane PricePane = new JScrollPane(PriceField);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,userPane,PricePane);
        splitPane.setResizeWeight(0.3);
        AddComponent(splitPane,3,0,3,1,inset,true);

        AddComponent(new JSeparator(JSeparator.HORIZONTAL),4,0,3,1,inset, true);

        JRadioButton equalTicketButton = new JRadioButton("Ticket evenly split");
        JRadioButton unEqualTicketButton = new JRadioButton("Ticket randomly split");
        ButtonGroup group = new ButtonGroup();
        group.add(equalTicketButton);
        group.add(unEqualTicketButton);
        AddComponent(equalTicketButton,5,1,1,1,inset,false);
        AddComponent(unEqualTicketButton,6,1,1,1,inset,false);
        equalTicketButton.addActionListener(this);
        unEqualTicketButton.addActionListener(this);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        AddComponent(cancelButton,8,0,1,1,inset,false);

        ConfirmButton = new JButton("Create Ticket");
        ConfirmButton.addActionListener(this);
        AddComponent(ConfirmButton,8,2,1,1,inset,false);
        ConfirmButton.setEnabled(false);

    }

    private void AddComponent(Component component, int row, int column, int width, int height, Insets insets, boolean fill){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Ticket evenly split":
                this.ConfirmButton.setEnabled(true);
                this.remove(RP);
                AddComponent(EP, 7,0,3,1, new Insets(10,0,10,10), false);
                PriceField.setVisible(true);
                isEvenTicket = true;
                break;
            case "Ticket randomly split":
                this.ConfirmButton.setEnabled(true);
                this.remove(EP);
                AddComponent(RP, 7, 0, 3, 1, new Insets(10, 10, 10, 10), false);
                PriceField.setVisible(false);
                PriceField.setValue(0);
                isEvenTicket = false;
                break;
            case "Cancel":
                this.setVisible(false);
                this.dispose();
                break;
            case"Create Ticket":
                if(isEvenTicket){
                    EP.Create(this.TextField.getText(), (User) this.PayerComboBox.getSelectedItem(), (Double) this.PriceField.getValue(), this.controller);
                    this.setVisible(false);
                    this.dispose();
                }
                else{
                    RP.Create(this.TextField.getText(), (User) this.PayerComboBox.getSelectedItem(), 0.0, this.controller);
                    this.setVisible(false);
                    this.dispose();
                }
                break;
            default:
                System.out.println(e.getActionCommand());
                break;
        }
        SwingUtilities.updateComponentTreeUI(this);
    }
}
