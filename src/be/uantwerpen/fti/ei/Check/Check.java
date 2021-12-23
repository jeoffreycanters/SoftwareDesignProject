package be.uantwerpen.fti.ei.Check;

import be.uantwerpen.fti.ei.Users.User;
import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Database.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;


public class Check {
    private Database<Tickets> ticketDB;
    private Database<User> userDB;
    private HashMap<UUID, Double> totalcheck;
    private ArrayList<String> solution = new ArrayList<>();

    public Check(Database<Tickets> ticketDB, Database<User> userDB){
        this.userDB = userDB;
        this.ticketDB = ticketDB;
        this.totalcheck = new HashMap<>();
    }

    public HashMap<UUID, Double> calculateCheck(){
        this.totalcheck.clear();
        this.userDB.forEach(x -> this.totalcheck.put(x.getID(), 0.0));
        for(Tickets t : ticketDB){
            if(totalcheck.containsKey(t.getPayer))
        }
    }



}
