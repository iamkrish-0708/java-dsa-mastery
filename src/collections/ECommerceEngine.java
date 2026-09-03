package collections;

import java.util.*;

public class ECommerceEngine {
    public static void main(String[] args) {
        ArrayList<CartItem> cart=new ArrayList<>();
        // syntax 1 - pass value in constructor
        CartItem item1=new CartItem(101, "Laptop", 800.0, 1);
        cart.add(item1);
        // syntax 2 - pass value in constructor
        cart.add(new CartItem(102, "Mouse", 25.0, 2));
        cart.add(new CartItem(103, "Keyboard", 60.0, 1));

        // calculate total value of cart
        double totalCartValue=0;
        for(CartItem item:cart){
            totalCartValue=totalCartValue+(item.price*item.quantity);
        }

        // remove item using id
        boolean removalStatus=false;
        for(CartItem item:cart){
            if(item.id==102){
                cart.remove(item);
                removalStatus=true;
                /*
                BREAK IS CRITICAL HERE FOR 2 REASONS:
                1. Logic: Stops the loop immediately once target item is found & removed.
                2. Safety: Prevents ConcurrentModificationException (modifying a list
                while iterating over it with a for-each loop will crash the app).
                */
                break;
            }
        }
        if(removalStatus){
            System.out.println("Item removal successfull.");
        }
        else{
            System.out.println("Item not found!");
        }

        // undo or redo
        LinkedList<String> action=new LinkedList<>();
        action.add("Added Laptop");
        action.add("Added Mouse");
        action.add("Removed Mouse");

        String lastAction=action.removeLast();
        System.out.println("Undid Last Action: "+lastAction);


        // expensive item search
        CartItem maxPriceItem = cart.get(0); // Assume first item is max initially

        for (CartItem item : cart) {
            if (item.price > maxPriceItem.price) {
                maxPriceItem = item;
            }
        }

        System.out.println("Most Expensive Item: " + maxPriceItem.name + " ($" + maxPriceItem.price + ")");

    }
}
