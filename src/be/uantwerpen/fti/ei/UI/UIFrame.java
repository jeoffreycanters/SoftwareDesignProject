package be.uantwerpen.fti.ei.UI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observer;

public class UIFrame extends JFrame implements Observer, ActionListener {

    public UIFrame(){
        super("Money Tracker");
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    private void initialise(){

    }


}
