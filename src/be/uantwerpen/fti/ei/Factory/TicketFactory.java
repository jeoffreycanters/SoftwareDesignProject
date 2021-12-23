package be.uantwerpen.fti.ei.Factory;

import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Tickets.TicketRandom;
import be.uantwerpen.fti.ei.Tickets.Tickets;

import java.util.Objects;

public class TicketFactory {
    public Tickets getTickets(String name, String function, int value){
        if(Objects.equals(function,"Split even")){
            return new TicketEven(name, value);
        }
        if(Objects.equals(function,"Split random")){
            return new TicketRandom(name, value);
        }
        System.out.println("failed");
        return null;
    }
}
