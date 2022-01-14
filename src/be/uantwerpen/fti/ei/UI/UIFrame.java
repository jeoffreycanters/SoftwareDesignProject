package be.uantwerpen.fti.ei.UI;

import be.uantwerpen.fti.ei.Controller.Controller;
import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.UI.Panels.TicketsDBPanel;
import be.uantwerpen.fti.ei.UI.Panels.UserDBPanel;
import be.uantwerpen.fti.ei.UI.Windows.CheckWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class UIFrame extends JFrame implements Observer, ActionListener {
    private static Controller controller = new Controller();
    private CheckWindow CW;

    public UIFrame(){
        super("Money Tracker");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Database.getTicketDB().addObserver((this));
        Database.getUserDB().addObserver(this);
        initialise();
        this.setVisible(true);
    }

    private void initialise(){
        this.setLayout(new BorderLayout());
        JSplitPane SplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new UserDBPanel(), new TicketsDBPanel(controller));
        SplitPane.setResizeWeight(0.5);
        this.add(SplitPane,BorderLayout.CENTER);
        JButton CalcButton = new JButton("Calculate");
        CalcButton.setFont(new Font("Sans", Font.PLAIN, 20));
        CalcButton.addActionListener(this);
        this.add(CalcButton,BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Calculate"))
            CW = new CheckWindow();
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
