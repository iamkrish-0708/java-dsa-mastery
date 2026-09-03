package collections;

import java.util.*;

//VIP Customer Support Ticket System
public class SupportTicketManager {
    public static void main(String[] args) {

        LinkedList<String> ticketQueue = new LinkedList<>();
        ticketQueue.add("Ticket #101");
        ticketQueue.add("Ticket #102");
        ticketQueue.add("Ticket #103");

        // vip ticket at first
        ticketQueue.addFirst("VIP Ticket #999");

        // normal ticket at last
        ticketQueue.addLast("Audit Ticket #000");

        System.out.println(ticketQueue.getFirst());
        System.out.println(ticketQueue.getLast());

        ticketQueue.removeFirst();
        ticketQueue.removeLast();

        // print all tickets
        for(String ticket:ticketQueue){
            System.out.println(ticket);
        }
    }
}
