package oops.inheritance;

class Notification{
    String recipient;

    Notification(String recipient){
        this.recipient=recipient;
    }

    void send(){
        System.out.println("Sending generic notification to "+this.recipient);
    }
}

class EmailNotification extends Notification{
    String subject;

    EmailNotification(String recipient,String subject){
        super(recipient);
        this.subject=subject;
    }

    @Override
    void send(){
        System.out.println("Sending Email to "+this.recipient+" | Subject: "+this.subject);
    }
}

class SMSNotification extends Notification{
    String phoneNumber;

    SMSNotification(String recipient,String phoneNumber){
        super(recipient);
        this.phoneNumber=phoneNumber;
    }

    @Override
    void send(){
        System.out.println("Sending SMS to "+this.recipient+" | Phone Number: "+this.phoneNumber);
    }
}
public class InheritanceMain3 {
    public static void main(String[] args) {
    EmailNotification n1=new EmailNotification("rahul@gmail.com", "Meeting Alert");
    SMSNotification n2=new SMSNotification("Rahul", "9876543210");
    n1.send();
    n2.send();
    }
}
