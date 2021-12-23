package be.uantwerpen.fti.ei.Tickets;

import be.uantwerpen.fti.ei.Users.User;

import java.util.UUID;
import java.util.ArrayList;


public class TicketEven extends Tickets{

    public TicketEven(String ticketType, UUID Payer) {super(ticketType, Payer);}

    public void setCost(double cost){
        this.cost = cost;
        this.setIndebted(new ArrayList<>(this.Indebted.keySet()));
    }

    @Override
    public Boolean addIndebted(User user){
        if(super.addIndebted(user)){
            this.Indebted.forEach((ID, cost) -> this.Indebted.put(ID, this.cost / this.Indebted.size()));
            return true;
        }
        return false;
    }

    @Override
    public void setIndebted(ArrayList<UUID> users){
        this.Indebted.clear();
        for(UUID ID : users){
            this.Indebted.put(ID, this.cost/users.size());
        }
    }
}
