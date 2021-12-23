package be.uantwerpen.fti.ei.Tickets;

import be.uantwerpen.fti.ei.Users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Tickets {
    protected String ticket;
    protected UUID ID;
    protected UUID user;
    protected double price = 0;
    protected HashMap<UUID, Double> Indebted;

    public Tickets(String ticket, UUID user){
        this.ticket = ticket;
        this.user = user;
        this.ID = UUID.randomUUID();
        this.Indebted = new HashMap<>();
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public UUID getID() {
        return ID;
    }

    public UUID getUser() {
        return user;
    }

    public double getPrice() {
        return price;
    }

    public HashMap<UUID, Double> getIndebted() {
        return Indebted;
    }

    public void setIndebted(HashMap<UUID, Double> indebted) {
        Indebted = indebted;
    }

    public void setIndebted(ArrayList<UUID> users){
        this.Indebted.clear();
        for(UUID user: users){
            this.Indebted.put(user, 0.0);
        }
    }

    public Boolean addIndebted(User user){
        if(this.Indebted.containsKey(user.getID()))
            return false;
        this.Indebted.put(user.getID(), 0.0);
        return true;
    }

    public void setIndebted(List<User> users){
        ArrayList<UUID> UUIDList = (ArrayList<UUID>) users.stream().map(User::getID).collect(Collectors.toList());
    }

    @Override
    public String toString(){
        String string = String.format("%s : %.2f paid by %s. Persons: ",ticket,price, DatabaseUsers.getPersonDB().get(user).getName());
    }
}
