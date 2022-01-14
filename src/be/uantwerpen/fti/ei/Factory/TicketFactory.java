package be.uantwerpen.fti.ei.Factory;


import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;

import java.util.ArrayList;
import java.util.UUID;

public abstract class TicketFactory {

    public abstract Tickets createTickets(String ticketType, UUID Payer);

    public Tickets createTickets(String ticketType, UUID Payer, ArrayList<UUID> users){
        Tickets tickets = createTickets(ticketType, Payer);
        tickets.setIndebted(users);
        return tickets;
    }

    public Tickets createTickets(String ticketType, User Payer) {
        return createTickets(ticketType, Payer.getID());
    }

    public Tickets createTickets(String ticketType, User Payer, ArrayList<UUID> users){
        Tickets tickets = createTickets(ticketType, Payer);
        tickets.setIndebted(users);
        return tickets;
    }

    public static TicketFactory getEvenFactory(){return new TicketEvenFactory();}

    public static TicketFactory getRandomFactory(){return new TicketRandomFactory();}





}
