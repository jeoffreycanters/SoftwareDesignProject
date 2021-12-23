package be.uantwerpen.fti.ei.Register_entry;

import be.uantwerpen.fti.ei.Tickets.TicketEven;
import be.uantwerpen.fti.ei.Tickets.Tickets;

public class TicketRegisterEntry {
    protected boolean Split;
    protected int value;

    public TicketRegisterEntry(boolean Split){
        this.Split = Split;
        this.value = Tickets.getValue();
    }

    public boolean isSplit(){
        return Split;
    }

    public int getValue(){
        return value;
    }


    @Override
    public String toString()
    {
        String status;

        if(this.Split)
        {
            status = "Split even";
        } else
        {
            status = "Split random";
        }
        return String.format("%02d is %s", getValue(), status);
    }
}
