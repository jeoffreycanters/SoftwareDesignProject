package be.uantwerpen.fti.ei.UI.Panels;

import be.uantwerpen.fti.ei.Controller.Controller;
import be.uantwerpen.fti.ei.Tickets.Tickets;
import be.uantwerpen.fti.ei.Users.User;

public interface TicketPanel {
    Tickets Create(String ticket, User user, double price, Controller controller);
}
