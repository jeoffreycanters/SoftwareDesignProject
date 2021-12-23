package be.uantwerpen.fti.ei.Database;

import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;


import java.util.*;
import java.util.function.Consumer;

public class Database<T> extends Observable implements Iterable<T> {

    static Database<Tickets> ticketDB;
    static Database<User> userDB;
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

    public void remove(T t){
        if(dbMap.containsKey(t))
            this.dbMap.remove(t);
        this.setChanged();
        this.notifyObservers();
    }







    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
