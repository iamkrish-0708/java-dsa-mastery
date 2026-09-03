package oops.abstraction;

/*
IN JAVA , CHILD CLASS CANNOT HAVE MULTIPLE PARENTS SO TO OVERCOME THIS INTERFACE COME TO PICTURE
INTERFACE HAVE CONSTANTS ONLY I.E. COMPLETELY INSTANCE FREE
INTERFACE USES implements KEYWORDS FOR USAGE IN CLASS INSTEAD OF extends KEYWORDS
INTERFACE CAN'T HAVE CONSTRUCTORS AS IT IS INSTANCE FREE
ALL METHODS IN INTERFACE ARE IMPLICITYLY public abstract
*/

public class Abstraction3 {
    interface Payable{
        void processPayment(double amount);
    }
    interface Trackable{
        void trackPackage(String trackingId);
    }
    static class ExpressOrder implements Payable,Trackable{
        @Override
        public void processPayment(double amount){
            System.out.println("Express payment of $" + amount + " processed instantly! 💳");
        }
        @Override
        public void trackPackage(String trackingId){
            System.out.println("Express package " + trackingId + " is in transit via Air Cargo! ✈️");
        }
    }
    public static void main(String[] args) {
        ExpressOrder o1=new ExpressOrder();
        o1.processPayment(150);
        o1.trackPackage("TRK-99201");
    }
}
