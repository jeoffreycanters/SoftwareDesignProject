package be.uantwerpen.fti.ei.Controller;

import be.uantwerpen.fti.ei.Database.DatabaseTickets;
import be.uantwerpen.fti.ei.Register_entry.TicketRegisterEntry;
import be.uantwerpen.fti.ei.Tickets.Tickets;

public class TicketRegistrationController implements TicketController{
    private DatabaseTickets tdb;

    public TicketRegistrationController(DatabaseTickets tdb){
        this.tdb = tdb;
    }

    @Override
    public void SplitEven(Tickets t){
        TicketRegisterEntry entry = new TicketRegisterEntry(true);
        tdb.addEntry(t, entry);
    }

    @Override
    public void SplitRandom(Tickets t){
        TicketRegisterEntry entry = new TicketRegisterEntry(false);
        tdb.addEntry(t, entry);
    }
}
