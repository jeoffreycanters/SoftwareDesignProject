package be.uantwerpen.fti.ei.Database;

import be.uantwerpen.fti.ei.Users.User;

import java.util.HashMap;
import java.util.Observable;
import java.util.UUID;

public class DatabaseUsers<T> extends Observable implements Iterable<T> {
    static DatabaseUsers<User> userDB;
    private HashMap<UUID, T> DBMap;

    private DatabaseUsers(){
        this.DBMap = new HashMap<>();
    }

    public void add(UUID id, T t){
        this.DBMap.put(id, t);
        this.setChanged();
        this.notifyObservers();
    }

    public void remove(UUID ID){
        if(DBMap.containsKey(ID))
            this.DBMap.remove(ID);
        this.setChanged();;
        this.notifyObservers();
    }

    public T get(UUID ID){
        return this.DBMap.get(ID);
    }


}
