package oops.encapsulation;

public class EncapsulationMain {
    public static void main(String[] args) {
        Encapsulation acc1 = new Encapsulation("ACC1001", "Rahul", 5000.0);
//        acc1.AccDetails();
//        acc1.accBalance=1000.0;   THROWS ERROR AS VARIABLE IS PRIVATE SO IT CAN BE ACCESSED WITHIN CLASS ONLY
//        acc1.deposit(3000.0);
//        acc1.withdraw(10000.0);
//        acc1.withdraw(4000.0);
//        System.out.println("Account Holder: " + acc1.getAccHolder());
//        System.out.println("Final Balance : " + acc1.getBalance());

        Encapsulation acc2 = new Encapsulation("ABB1002", "Priya", 2000.0);

        acc1.transferfunds(acc2, 6000);
        acc1.transferfunds(acc2, 0);
        acc1.transferfunds(acc2, 1500);
        System.out.println("Account Holder: " + acc2.getAccHolder());
        System.out.println("Final Balance : " + acc2.getBalance());
        System.out.println();
        System.out.println("Account Holder: " + acc1.getAccHolder());
        System.out.println("Final Balance : " + acc1.getBalance());
    }
}
