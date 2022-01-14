package be.uantwerpen.fti.ei.test;

import be.uantwerpen.fti.ei.Check.Check;
import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Factory.TicketEvenFactory;
import be.uantwerpen.fti.ei.Factory.TicketRandomFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import be.uantwerpen.fti.ei.Controller.Controller;
import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Tickets.TicketRandom;
import be.uantwerpen.fti.ei.Users.User;

import java.util.HashMap;
import java.util.UUID;

public class test {
    Controller controller;
    User u1;
    User u2;
    User u3;
    TicketRandom ticketR;
    TicketEven ticketE;

    @Before
    public void initialize(){
        this.controller = new Controller();
        this.u1 = new User("Max");
        this.u2 = new User("Jeoffrey");
        this.u3 = new User("Jens");
        Database.getUserDB().add(u1.getID(),u1);
        Database.getUserDB().add(u2.getID(),u2);
        Database.getUserDB().add(u3.getID(),u3);
        this.ticketR = (TicketRandom) controller.createTickets(new TicketRandomFactory(), "TicketRandomTest", u1);
        this.ticketE = (TicketEven) controller.createTickets(new TicketEvenFactory(), "TicketEvenTest", u1);
    }

    @Test
    public void addUserT(){
        User u4 = new User("Thomas");
        int size = Database.getUserDB().getSize();
        Assert.assertNull(Database.getUserDB().get(u4.getID()));
        Database.getUserDB().add(u4.getID(),u4);
        Assert.assertEquals(size+1, Database.getUserDB().getSize(), 0.01);
    }

    @Test
    public void createEvenTicket(){
        int size = Database.getTicketDB().getSize();
        TicketEven te = (TicketEven) controller.createTickets(new TicketEvenFactory(), "TestEvenTicket", u1);
        Assert.assertEquals(size+1, Database.getTicketDB().getSize(), 0.01);
        Assert.assertNotNull(Database.getTicketDB().get(te.getID()));
        Assert.assertEquals("TestEvenTicket", Database.getTicketDB().get(te.getID()).getTicket());
        Assert.assertEquals(u1.getID(),Database.getTicketDB().get(te.getID()).getPayer());

    }

    @Test
    public void createRandomTicketT(){
        int size = Database.getTicketDB().getSize();
        TicketRandom tr = (TicketRandom) controller.createTickets(new TicketRandomFactory(), "TestRandomTicket", u1);
        Assert.assertEquals(size+1, Database.getTicketDB().getSize(), 0.01);
        Assert.assertNotNull(Database.getTicketDB().get(tr.getID()));
        Assert.assertEquals("TestRandomTicket", Database.getTicketDB().get(tr.getID()).getTicket());
        Assert.assertEquals(u1.getID(),Database.getTicketDB().get(tr.getID()).getPayer());
    }

    @Test
    public void removeTicketT(){
        int size = Database.getTicketDB().getSize();
        Database.getTicketDB().remove(ticketE.getID());
        Assert.assertEquals(size-1, Database.getTicketDB().getSize(), 0.01);
        Database.getTicketDB().remove(ticketR.getID());
        Assert.assertEquals(size-2, Database.getTicketDB().getSize(), 0.01);
    }

    @Test
    public void CalculateT(){
        ticketE.addIndebted(u1);
        ticketE.addIndebted(u2);
        ticketE.addIndebted(u3);
        ticketE.setCost(30.0);

        TicketEven te2 = (TicketEven) controller.createTickets(new TicketEvenFactory(), "EvenTicket2", u2);
        te2.addIndebted(u1);
        te2.addIndebted(u2);
        te2.addIndebted(u3);
        te2.setCost(15.0);

        ticketR.addIndebted(u1, 3.0);
        ticketR.addIndebted(u2, 4.0);
        ticketR.addIndebted(u3, 5.0);

        Check check = new Check(Database.getTicketDB(), Database.getUserDB());
        HashMap<UUID, Double> calculate = check.calculateCheck();

        Assert.assertEquals(24, calculate.get(u1.getID()), 0.01);
        Assert.assertEquals(-4, calculate.get(u2.getID()), 0.01);
        Assert.assertEquals(-20, calculate.get(u3.getID()), 0.01);
    }
}
