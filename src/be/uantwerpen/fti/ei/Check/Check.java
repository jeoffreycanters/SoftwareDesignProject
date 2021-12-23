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

            if(totalcheck.containsKey(t.getPayer()))
                totalcheck.put(t.getPayer(), totalcheck.get(t.getPayer()) + t.getCost());
            else
                System.out.format("User %s, who paid ticket %s not found", t.getPayer().toString(),t.getID().toString());
            for(UUID ID: t.getIndebted().keySet()){
                if(totalcheck.containsKey(ID))
                    totalcheck.put(ID,totalcheck.get(ID) - t.getIndebted().get(ID));
                else
                    System.out.format("Person %s in ticket %s not found", ID.toString(), t.getID().toString());
            }
        }
        return totalcheck;
    }
    private UUID getKeyFromValue(HashMap<UUID,Double> hm, Double value)
    {
        for (UUID id : hm.keySet())
        {
            if(hm.get(id).equals(value))
                return id;
        }
        return null;
    }


    // Algorithm found on Github -> https://github.com/soumyasethy/ShortestPath-CashFlow-Algorithm-Splitwise
    private void whoOwesWho(HashMap<UUID, Double> listing)
    {

        Double maxPrice = Collections.max(listing.values());
        Double minPrice = Collections.min(listing.values());
        if(Math.abs(maxPrice-minPrice)>0.1)
        {
            UUID maxPricePerson = getKeyFromValue(listing, maxPrice);
            UUID minPricePerson = getKeyFromValue(listing, minPrice);
            double result = maxPrice + minPrice;
            result = Math.round(result* 100.0)/100.0;
            if(result>=0.1)
            {
                solution.add(String.format("%s -> %s : %.2f\n",Database.getUserDB().get(minPricePerson).getName(),Database.getUserDB().get(maxPricePerson).getName(),Math.abs(minPrice)));
                listing.put(maxPricePerson, result);
                listing.put(minPricePerson, 0.0);
            }
            else
            {
                solution.add(String.format("%s -> %s : %.2f\n",Database.getUserDB().get(minPricePerson).getName(),Database.getUserDB().get(maxPricePerson).getName(),Math.abs(maxPrice)));
                listing.put(minPricePerson, result);
                listing.put(maxPricePerson, 0.0);
            }
            whoOwesWho(listing);
        }

    }

    public String print()
    {
        this.totalcheck = calculateCheck();
        solution.clear();
        whoOwesWho(this.totalcheck);
        StringBuilder string = new StringBuilder();

        solution.forEach(string::append);
        return string.toString();
    }

}
