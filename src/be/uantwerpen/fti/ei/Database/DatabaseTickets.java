package be.uantwerpen.fti.ei.Database;

import be.uantwerpen.fti.ei.Register_entry.TicketRegisterEntry;
import be.uantwerpen.fti.ei.Tickets.Tickets;

import java.util.Observable;


public abstract class DatabaseTickets extends Observable{
    public DatabaseTickets(){}
    public abstract void addEntry(Tickets t, TicketRegisterEntry te);
    public abstract TicketRegisterEntry getEntry(Tickets t);
}
