package collections;

public class CartItem {
    int id;
    String name;
    double price;
    int quantity;

    CartItem(int id, String name, double price, int quantity){
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", Name: " + name + ", Price: $" + price + ", Qty: " + quantity + "]";
    }
    }

