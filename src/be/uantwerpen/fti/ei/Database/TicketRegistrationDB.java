package be.uantwerpen.fti.ei.Database;

import be.uantwerpen.fti.ei.Register_entry.TicketRegisterEntry;
import be.uantwerpen.fti.ei.Register_entry.TicketRegisterEntryNull;
import be.uantwerpen.fti.ei.Tickets.Tickets;

import java.security.PublicKey;
import java.util.HashMap;

public class TicketRegistrationDB extends DatabaseTickets {
    private static TicketRegistrationDB instance;
    private final HashMap<Tickets, TicketRegisterEntry> db;

    private TicketRegistrationDB(){
        this.db = new HashMap<>();
    }

    public static TicketRegistrationDB getInstance(){
        if(instance == null){
            instance = new TicketRegistrationDB();
        }
        return instance;
    }

    @Override
    public void addEntry(Tickets t, TicketRegisterEntry te){
        this.db.put(t,te);
    }

    @Override
    public TicketRegisterEntry getEntry(Tickets t){
        return this.db.getOrDefault(t, new TicketRegisterEntryNull());
    }
}
