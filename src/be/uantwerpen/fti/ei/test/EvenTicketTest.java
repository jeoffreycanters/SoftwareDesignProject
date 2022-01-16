package be.uantwerpen.fti.ei.test;

import be.uantwerpen.fti.ei.Database.Database;
import be.uantwerpen.fti.ei.Factory.TicketEvenFactory;
import be.uantwerpen.fti.ei.Factory.TicketFactory;
import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EvenTicketTest {
    private TicketEven te;
    ArrayList<User> users;

    @Before
    public void initialize(){
        User u1 = new User("Max");
        User u2 = new User("Jeoffrey");
        User u3 = new User("Jens");

        Database.getUserDB().add(u1.getID(),u1);
        Database.getUserDB().add(u2.getID(),u2);
        Database.getUserDB().add(u3.getID(),u3);

        User[] ua = new User[]{u1,u2,u3};
        this.users = new ArrayList<>(Arrays.asList(ua));

        TicketFactory tf = new TicketEvenFactory();
        this.te = (TicketEven) tf.createTickets("Cinema", u1.getID());
        this.te.setIndebted(this.users);
    }

    @Test
    public void setTicket(){
        this.te.setTicket("Test");
        Assert.assertEquals("Test", this.te.getTicket());
    }

    @Test
    public void getPayer(){
        Assert.assertEquals(this.users.get(0).getID(), this.te.getPayer());
    }

    @Test
    public void setCost(){
        this.te.setCost(300.0);
        Assert.assertEquals(300.0, this.te.getCost(),0.01);
        Assert.assertEquals(100.0, this.te.getIndebted().get(this.users.get(0).getID()),0.01);
    }

    @Test
    public void addIndebted(){
        this.te.setCost(200.0);
        User u4 = new User("Thomas");
        this.users.add(u4);
        Assert.assertEquals(true, this.te.addIndebted(this.users.get(3)));
        Assert.assertEquals(50.0, this.te.getIndebted().get(this.users.get(3).getID()), 0.01);
        Assert.assertEquals(50.0, this.te.getIndebted().get(this.users.get(0).getID()), 0.01);
    }


}
