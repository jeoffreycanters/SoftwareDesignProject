package be.uantwerpen.fti.ei.Factory;

import be.uantwerpen.fti.ei.Tickets.TicketRandom;
import be.uantwerpen.fti.ei.Tickets.Tickets;

import java.util.UUID;

public class TicketRandomFactory extends TicketFactory{
    @Override
    public Tickets createTicket(String ticketType, UUID Payer) {
        return new TicketRandom(ticketType, Payer);
    }
}
