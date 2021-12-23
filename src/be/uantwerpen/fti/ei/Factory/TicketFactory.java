package be.uantwerpen.fti.ei.Factory;

import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Tickets.TicketRandom;
import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;

import java.util.ArrayList;
import java.util.UUID;

public abstract class TicketFactory {
    public abstract Tickets CreateTicket(String ticket, UUID user);

    public Tickets CreateTicket(String ticket, UUID user, ArrayList<UUID> users){
        Tickets ticketNew = CreateTicket(ticket, user);
        //ticket1.setIndebted(users)
        return ticketNew;
    }

    public Tickets CreateTicket(String ticket, User user){
        return CreateTicket(ticket, user); //user.getID()
    }

    public Tickets CreateTicket(String ticket, User user, ArrayList<UUID> users){
        Tickets ticketNew = CreateTicket(ticket, user);
        //ticketNew.setIndebted(users)
        return ticketNew;
    }

    public static TicketFactory
}
