package oops.encapsulation;

// ENCAPSULATION FOCUS ON DATA HIDING/DATA SECURITY WHILE ABSTRACTION FOCUS ON HIDING IMPLEMENTATION AND REDUCING COMPLEXITY
// WE CAN SEE EXAMPLE OF BOTH ENCAPSULATION AND ABSTRACTION BELOW

public class Encapsulation {
    private String accNum;
    private String accHolder;
    private double accBalance;

    public Encapsulation(String accNum, String accHolder, double accBalance){
        this.accNum=accNum;
        this.accBalance=accBalance;
        this.accHolder=accHolder;
    }

    public void AccDetails(){
        System.out.println("---------------------------------------------------------");
        System.out.println("ACCOUNT NUMBER:"+this.accNum);
        System.out.println("ACCOUNT HOLDER:"+this.accHolder);
        System.out.println("ACCOUNT BALANCE:"+this.accBalance);
        System.out.println("---------------------------------------------------------");
    }

    public double getBalance(){
        return this.accBalance;
    }
    public String getAccHolder(){
        return this.accHolder;
    }

    public void deposit(double amt){
        if(amt<=0){
            System.out.println("ENTER A VALID AMOUNT !");
        }
        else{
            this.accBalance+=amt;
            System.out.println("Deposit Successful !");
        }
    }

    public void withdraw(double amt){
        if(amt<=0){
            System.out.println("ENTER A VALID AMOUNT !");
        }
        else if(amt>this.accBalance){
            System.out.println("Insufficient Funds !");
        }
        else {
            this.accBalance-=amt;
            System.out.println("Withdrawal Successfull !");
        }
    }

    public void transferfunds(Encapsulation targetacc,double amt){
        if(amt<=0){
            System.out.println("ENTER A VALID AMOUNT!");
        }
        else if(amt>this.accBalance){
            System.out.println("Insufficient Funds!");
        }
        else{
            this.accBalance-=amt;
            targetacc.deposit(amt);
            System.out.println("Transaction Completed.");
        }
    }

}
