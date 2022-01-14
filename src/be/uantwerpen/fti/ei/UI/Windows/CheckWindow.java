package be.uantwerpen.fti.ei.UI.Windows;

import be.uantwerpen.fti.ei.Check.Check;
import be.uantwerpen.fti.ei.Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

public class CheckWindow extends JFrame implements ActionListener, Observer {

    private Check check;

    public CheckWindow() {
        super("MoneyTracker");
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Database.getUserDB().addObserver(this);
        Database.getTicketDB().addObserver(this);

        initialise();
        this.setVisible(true);

    }

    private void initialise() {
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        c.gridx=1;
        c.gridy=0;
        JLabel label = new JLabel("The Calculation");
        this.add(label,c);



        check = new Check(Database.getTicketDB(), Database.getUserDB());
        JTextArea textArea = new JTextArea(check.print());
        c.gridy = 1;
        this.add(textArea, c);

        c.gridx = 0;
        c.gridy = 2;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        this.add(backButton, c);

        c.gridx = 2;
        c.gridy = 2;
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(this);
        this.add(exportButton,c);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back")){
            this.setVisible(false);
            this.dispose();
        }
        else if (e.getActionCommand().equals("Export"))
            Export();
    }

    private void Export(){
        try {
            File moneyTracker = new File("./MoneyTracker.txt");
            if(moneyTracker.createNewFile()){
                WriteToFile();
            }
            else
                WriteToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void WriteToFile()
    {
        try
        {
            FileWriter fileWriter = new FileWriter("./MoneyTracker.txt",true);
            fileWriter.write("********************************\n");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            fileWriter.write(dtf.format(now)+"\n\n");
            fileWriter.write("Tickets: \n\n");
            Database.getTicketDB().forEach(t-> {
                try {
                    fileWriter.write(t.toString()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.write("\nCheck calculation: \n\n" + check.print());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setVisible(false);
        this.dispose();
    }
}
