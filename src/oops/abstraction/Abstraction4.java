package oops.abstraction;

/*
default AND static ARE KEYWORDS USED FOR METHODS IN INTERFACE
default method- THESE ARE METHOD DEFINED WITH BODY IN INTERFACE ITSELF AND IT IS OPTIONAL FOR CLASS TO OVERRIDE IT
static method- THESE ARE METHOD DEFINED WITH BODY IN INTERFACE WHICH CAN'T BE OVERRIDEN BY ANY implements CLASSES. THESE ARE ACCESIBLE VIA InterfaceName.MethodName()
*/

public class Abstraction4 {
    interface Notifier{
        void send(String message);
        default void sendWithRetry(String message, int retries){
            System.out.println("Attempting send with " + retries + " retries...");
            for(int i=1;i<=retries;i++){
                send(message);
            }
        }
        static boolean isValidMessage(String message){
            return message != null && !message.isEmpty();
        }

    }

    static class EmailNotifier implements Notifier{
        @Override
        public void send(String message){
            System.out.println("Sending Email: " + message + " 📧");
        }
    }

    public static void main(String[] args) {
        System.out.println(Notifier.isValidMessage("System Alert"));
        EmailNotifier e=new EmailNotifier();
        e.send("Welcome to our platform!");
        e.sendWithRetry("Server Timeout", 2);

    }
}
