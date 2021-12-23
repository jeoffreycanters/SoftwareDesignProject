package be.uantwerpen.fti.ei.Factory;

import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Tickets.TicketRandom;
import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;

import java.util.ArrayList;
import java.util.UUID;

public abstract class TicketFactory {

    public abstract Tickets createTicket(String ticketType, UUID Payer);



}
