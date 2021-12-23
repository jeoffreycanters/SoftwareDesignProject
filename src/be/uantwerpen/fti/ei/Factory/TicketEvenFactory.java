package be.uantwerpen.fti.ei.Factory;

import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Tickets.Tickets;

import java.util.UUID;

public class TicketEvenFactory extends TicketFactory{
    @Override
    public Tickets createTicket(String ticketType, UUID Payer) {
        return new TicketEven(ticketType, Payer);
    }
}
