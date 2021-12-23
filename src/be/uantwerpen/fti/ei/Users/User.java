package be.uantwerpen.fti.ei.Users;

import java.util.UUID;

public class User {
    private String name;
    private UUID ID;

    public User(String name){
        this.name = name;
        this.ID = UUID.randomUUID();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public UUID getID() {
        return ID;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

}
