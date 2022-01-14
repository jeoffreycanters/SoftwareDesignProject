package be.uantwerpen.fti.ei.Controller;

import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Users.User;
import  be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Factory.TicketFactory;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.UUID;

public class Controller {

    public Tickets createTickets(TicketFactory tf, String ticketType, UUID Payer){
        Tickets t = tf.createTickets(ticketType, Payer);
        Database.getTicketDB().add(t.getID(),t);
        return t;
    }

    public Tickets createTickets(TicketFactory tf, String ticketType, UUID Payer, ArrayList<UUID> users){
        Tickets t = tf.createTickets(ticketType, Payer);
        t.setIndebted(users);
        Database.getTicketDB().add(t.getID(),t);
        return t;
    }

    public Tickets createTickets(TicketFactory tf, String ticketType, User Payer, ArrayList<UUID> users){
        Tickets t = tf.createTickets(ticketType, Payer.getID());
        t.setIndebted(users);
        Database.getTicketDB().add(UUID.randomUUID(), t);
        return t;
    }

     public Tickets createTickets(TicketFactory tf, String ticketType, User Payer){
        Tickets t = tf.createTickets(ticketType,Payer.getID());
        Database.getTicketDB().add(t.getID(), t);
        return t;
     }
}
