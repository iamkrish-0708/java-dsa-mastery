package oops.encapsulation;

public class LaptopMain {
    public static void main(String[] args) {
//        Laptop laptop1=new Laptop();
//        laptop1.brand="Dell";
//        laptop1.ramGB=16;
//        laptop1.price=75000.0;
//        laptop1.laptopSpecs();
//        laptop1.turnON();
//        laptop1.runCode();

        Laptop laptop2=new Laptop("ASUS", 32, 110000.0);
        laptop2.laptopSpecs();

        laptop2.setPrice(12000.00);
        System.out.println("Updated Price: " + laptop2.getPrice());
        laptop2.setPrice(-90);


//        Laptop laptop3=new Laptop(laptop2);
//        laptop3.price=99000.0;
//        laptop3.laptopSpecs();
//
//        System.out.println(laptop2.price);



    }
}

/*
 ====================================================================================
  📚 JAVA OOPs CHEAT SHEET & LECTURE 17 NOTES: OBJECTS, CONSTRUCTORS & ENCAPSULATION
 ====================================================================================

 1. CLASS vs. OBJECT:
 -------------------
 - Class  : A blueprint / template / user-defined data type (e.g., Laptop).
            Does NOT consume memory in RAM for data variables.
 - Object : A concrete physical instance created from the Class blueprint.
            Allocates memory in RAM (Heap) when instantiated using 'new'.

 2. MEMORY LAYOUT (Stack vs. Heap):
 ----------------------------------
   Laptop laptop1 = new Laptop("ASUS", 32, 110000.0);

   - Stack Memory : Stores the reference variable ('laptop1') which holds the
                    memory address of the Heap object.
   - Heap Memory  : Stores the actual Laptop object containing its fields
                    ('brand', 'ramGB', 'price').

 3. CONSTRUCTORS & 'this' KEYWORD:
 ---------------------------------
 - A Constructor is a special block executed AUTOMATICALLY during object creation.
 - Rules: Must match Class name exactly; HAS NO RETURN TYPE (not even void).

 Types of Constructors:
   a) Default (No-Arg) Constructor:
      Sets fallback values when no arguments are provided.
      Example: public Laptop() { this.brand = "Generic"; ... }

   b) Parameterized Constructor:
      Allows complete 1-line object setup at creation time.
      Example: public Laptop(String brand, int ramGB, double price) { ... }

   c) 'this' Keyword:
      Refers to the CURRENT object invoking the constructor/method.
      Used to resolve Variable Shadowing (when parameter name matches field name).
      Example: this.brand = brand; (Assigns parameter 'brand' to field 'this.brand')

 4. COPY CONSTRUCTOR vs. REFERENCE ASSIGNMENT:
 ---------------------------------------------
 - Reference Assignment ('Laptop laptop3 = laptop2;'):
   DANGER! Does NOT create a new object. Both laptop3 and laptop2 point to
   the SAME object in Heap memory. Changing laptop3 WILL alter laptop2!

 - Copy Constructor ('Laptop laptop3 = new Laptop(laptop2);'):
   Creates a BRAND NEW, independent object in Heap memory and copies field values.
   Example:
     public Laptop(Laptop other) {
         this.brand = other.brand;
         this.ramGB = other.ramGB;
         this.price = other.price;
     }

 5. ENCAPSULATION & ACCESS MODIFIERS (1st Pillar of OOP):
 --------------------------------------------------------
 - Encapsulation: Bundling data and methods together while HIDING raw internal data
   from direct outside modification.

 - Access Modifiers:
   * private   : Accessible ONLY inside the defining Class.
   * public    : Accessible from anywhere in the program.
   * protected : Accessible in same package & subclasses.
   * default   : Accessible in same package.

 - Getters & Setters:
   * Getter : Read-only access (e.g., public double getPrice() { return this.price; })
              Note: getPrice() RETURNS a value, so use System.out.println(laptop2.getPrice());
   * Setter : Write-only access with Validation Logic to protect data integrity.
              Example:
                public void setPrice(double price) {
                    if (price > 0) this.price = price;
                    else System.out.println("Invalid Price!");
                }
 ====================================================================================
*/
