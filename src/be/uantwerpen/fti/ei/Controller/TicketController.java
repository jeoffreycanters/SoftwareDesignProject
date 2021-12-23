package be.uantwerpen.fti.ei.Controller;

import be.uantwerpen.fti.ei.Tickets.Tickets;

public interface TicketController {
    void SplitEven(Tickets t);
    void SplitRandom(Tickets t);
}
