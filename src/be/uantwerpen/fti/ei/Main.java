package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.Database.DatabaseTickets;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public Main(){}

    public void run(){
        DatabaseTickets dbt = TicketRegistrationDB.getInstance();
        TicketController register = new TicketRegistrationController(dbt);

    }
}
