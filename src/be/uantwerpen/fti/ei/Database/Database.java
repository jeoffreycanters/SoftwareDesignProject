package be.uantwerpen.fti.ei.Database;

import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;

import java.util.*;
import java.util.function.Consumer;

public class Database<T> extends Observable implements Iterable<T> {

    private static Database<Tickets> ticketDB;
    private static Database<User> userDB;
    private HashMap<UUID, T> dbMap;

    private Database() {
        this.dbMap = new HashMap<>();
    }

    public void add(UUID id, T t){
        this.dbMap.put(id, t);
        this.setChanged();
        this.notifyObservers();
    }

    public void remove(UUID id){
        if(dbMap.containsKey(id))
            this.dbMap.remove(id);
        this.setChanged();
        this.notifyObservers();
    }

    public T get(UUID id) {return this.dbMap.get(id);}

    public static Database<User> getUserDB() {
        if(userDB == null){
            userDB = new Database<>();
        }
        return userDB;
    }

    public static Database<Tickets> getTicketDB(){
        if(ticketDB == null){
            ticketDB = new Database<>();
        }
        return  ticketDB;
    }

    public Integer getSize() {
        return this.dbMap.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.dbMap.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        this.dbMap.values().forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return this.dbMap.values().spliterator();
    }
}
