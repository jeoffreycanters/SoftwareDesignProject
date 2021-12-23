package be.uantwerpen.fti.ei.Tickets;

public class Tickets {
    private String name;
    private String function;
    private static int value;

    public Tickets(String name, String function, int value){
        this.name = name;
        this.function = function;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public String getFunction(){
        return function;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFunction(String function){
        this.function = function;
    }

    public static int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
