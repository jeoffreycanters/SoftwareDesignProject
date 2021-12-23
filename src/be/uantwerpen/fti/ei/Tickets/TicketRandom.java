package be.uantwerpen.fti.ei.Tickets;

import java.util.UUID;
import java.util.HashMap;
import be.uantwerpen.fti.ei.Users.User;

public class TicketRandom extends Tickets{

    public TicketRandom(String ticketType, UUID Payer) {super(ticketType, Payer);}

    public void calculateCost(){
        double cost = 0.0;
        for (UUID ID: this.Indebted.keySet()){
            cost += this.Indebted.get(ID);
        }
        this.cost=cost;
    }

   @Override
    public  Boolean addIndebted(User user){return super.addIndebted(user);}

    @Override
    public void setIndebted(HashMap<UUID, Double> indebted){
        super.setIndebted(indebted);
        calculateCost();
    }

    public Boolean addIndebted(User user, double cost){
        if(super.addIndebted(user)){
            this.Indebted.put(user.getID(), cost);
            calculateCost();
            return true;
        }
        return false;
    }
}
