package oops.polymorphism;

public class Polymorphism3 {

    static class Vehicle{
        double distanceInKm;
        Vehicle( double distanceInKm){
            this.distanceInKm=distanceInKm;
        }
        double calculateFare(){
            return this.distanceInKm*10;
        }
    }

    static class Taxi extends Vehicle{

        Taxi(double distanceInKm){
            super(distanceInKm);
        }
        @Override
        double calculateFare(){
            return this.distanceInKm*15;
        }
    }

    static class Bus extends Vehicle{

        Bus(double distanceInKm){
            super(distanceInKm);
        }

        @Override
        double calculateFare(){
            return this.distanceInKm*5;
        }
    }

    static class FareCalculator{
        void printReciept(Vehicle v){
            System.out.println("Ride Fare: $" + v.calculateFare());
        }
    }

    public static void main(String[] args) {
        FareCalculator fare=new FareCalculator();
        Vehicle t=new Taxi(10);
        Vehicle b=new Bus(20);

        fare.printReciept(t);
        fare.printReciept(b);

    }
}
