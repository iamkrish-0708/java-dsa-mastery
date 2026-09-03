package oops.abstraction;

/*
ABSTRACT CLASS IS STRICT BLUE PRINT FOR ALL SUBCLASSES
ABSTRACT CLASS CAN'T BE USED TO CREATE AN INSTANCE LIKE A NORMAL CLASS
ABSTRACT METHOD ARE METHOD IN ABTRACT CLASS WITHOUT A SPECIFIC DEFINED BODY IN ABSTRACT CLASS
ALL ABSTRACT METHOD IN ABSTRACT CLASS MUST BE DEFINED BY SUBCLASSES TO AVOID COMPILE ERROR
CONCRETE METHOD ARE METHOD IN ABTRACT CLASS WHO HAS DEFINED BODY
*/

public class Abstraction1 {

    static abstract class Appliance{
        String brand;

        //concrete method
        void turnOn(){
            System.out.println(brand + " appliance is powered ON 🔌");
        }

        abstract void performFunction();
    }

    static class WashingMachine extends Appliance{

        @Override
        void performFunction(){
            System.out.println(brand + " washing machine is washing clothes... 🧺");
        }
    }

    public static void main(String[] args) {
        Appliance a=new WashingMachine();
        a.brand="samsung";
        a.turnOn();
        a.performFunction();
    }
}
