package oops.encapsulation;

public class Laptop {
    // 1. Private Fields (Encapsulation)
    private String brand;
    private int ramGB;
    private double price;

    // 2. Default Constructor
    public Laptop() {
        this.brand = "Generic";
        this.ramGB = 8;
        this.price = 35000.0;
        System.out.println("Default Constructor Executed.");
    }

    // 3. Parameterized Constructor
    public Laptop(String brand, int ramGB, double price) {
        this.brand = brand;
        this.ramGB = ramGB;
        this.price = price;
        System.out.println("Parameterized Constructor Executed for " + brand);
    }

    // 4. Copy Constructor
    public Laptop(Laptop other) {
        this.brand = other.brand;
        this.ramGB = other.ramGB;
        this.price = other.price;
        System.out.println("Copy Constructor Executed! Cloned from " + other.brand);
    }

    // 5. Getters & Setters with Validation
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("❌ Price cannot be negative!");
        }
    }

    public int getRamGB() {
        return this.ramGB;
    }

    public void setRamGB(int ramGB) {
        if (ramGB > 0) {
            this.ramGB = ramGB;
        } else {
            System.out.println("❌ RAM must be positive!");
        }
    }

    // 6. Member Methods
    public void laptopSpecs() {
        System.out.println("\n--- LAPTOP SPECS ---");
        System.out.println("Brand : " + brand);
        System.out.println("RAM   : " + ramGB + " GB");
        System.out.println("Price : Rs " + price);
    }

    public void turnOn() {
        System.out.printf("\n%s laptop with %dGB RAM is turning on...", brand, ramGB);
    }

    public void runCode() {
        System.out.printf("\n%s laptop is running Java code!", brand);
    }
}