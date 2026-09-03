package oops.polymorphism;

/*
        METHOD OVERLOADING(COMPILE TIME POLYMORPHISM): SAME METHOD NAME BUT DIFFERENT PARAMETERS IN CLASS.
        JAVA DECIDES WHICH METHOD TO USE WITH HELP OF UNIQUE PARAMETERS OF EACH REGARDLESS OF SAME METHOD NAME.
*/

public class Polymorphism1 {
    static class Logger{
        void log(String message){
            System.out.println("[INFO]: "+message);
        }
        void log(String message,String logLevel){
            System.out.println("[" + logLevel + "]: " + message);
        }
        void log(String message,int errorCode){
            System.out.println("[ERROR Code " + errorCode + "]: " + message);
        }
    }
    public static void main(String[] args) {
        Logger log1=new Logger();
        log1.log("Program started");
        log1.log("Something happened", "WARNING");
        log1.log("File not found", 404);
    }
}
