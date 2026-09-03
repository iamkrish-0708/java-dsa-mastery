package oops.abstraction;

/*
CONSTRUCTOR IN ABSTRACT CLASS IS SAME AS THE NORMAL CLASS AS WE USE IT TO INITIALIZE AN OBJECT WHEN IT IS CREATED
*/

public class Abstraction2 {
    static abstract class PaymentGateway{
        String merchantId;
        double feeRate;
        PaymentGateway(String merchantID,double feeRate){
            this.merchantId=merchantId;
            this.feeRate=feeRate;
        }
        void logTransaction(double amount){
            double fee = amount * feeRate;
            System.out.println("[MERCHANT " + merchantId + "]: Transaction of $" + amount + " logged | Fee: $" + fee);
        }
        abstract  boolean processPayment(double amount);
    }

    static class PayPalGateway extends PaymentGateway{
        PayPalGateway(String merchantId,double feeRate){
            super(merchantId,feeRate);
        }
        @Override
        boolean processPayment(double amount){
            System.out.println("Processing $" + amount + " payment via PayPal servers... 🅿️");
            return true;
        }
    }

    public static void main(String[] args) {
        PaymentGateway p=new PayPalGateway("PAYPAL_88",0.025);
        p.logTransaction(200.0);
        p.processPayment(200.0);
    }
}
