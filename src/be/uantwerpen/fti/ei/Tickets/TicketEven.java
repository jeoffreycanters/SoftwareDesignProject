package be.uantwerpen.fti.ei.Tickets;

import java.util.UUID;

public class TicketEven extends Tickets{
    public TicketEven(String name, UUID value){
        super(name, "Split even", value);
    }
}
