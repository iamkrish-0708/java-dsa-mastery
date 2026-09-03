package oops.polymorphism;

/*
Upcasting & Polymorphic Parameters:
You correctly identified that upcasting allows a single method (processPatient(Patient p)) to accept any current or future subclass. You don't need to write duplicate overloaded methods for every new patient type!

Downcasting & instanceof:
You nailed the reason for downcasting: because the compiler only sees the Parent reference type (Patient), it won't let you call child-unique methods (triggerICUAlert()) directly. Downcasting gives you access to those unique methods, and using instanceof prevents a ClassCastException crash.
*/

public class Polymorphism4 {
    static class Patient{
        String name;
        Patient(String name){
            this.name=name;
        }
        void receiveTreatment(){
            System.out.println("Patient " + name + " is receiving standard treatment.");
        }
    }

    static class EmergencyPatient extends Patient{
        EmergencyPatient(String name){
            super(name);
        }
        @Override
        void receiveTreatment(){
            System.out.println("Patient " + name + " is receiving IMMEDIATE emergency care! 🚨");
        }

        void triggerICUAlert(){
            System.out.println("ALERT: ICU bed reserved for " + name + "!");
        }
    }

    static class OutPatient extends Patient{
        OutPatient(String name){
            super(name);
        }
        @Override
        void receiveTreatment(){
            System.out.println("Patient " + name + " is receiving routine outpatient care. 🩺");
        }

        void scheduleFollowUp(){
            System.out.println("Follow-up appointment scheduled for " + name + ".");
        }
    }

    static void processPatient(Patient p){
        p.receiveTreatment();

        if(p instanceof EmergencyPatient ep){
            ep.triggerICUAlert();
        }
        else if(p instanceof OutPatient op){
            op.scheduleFollowUp();
        }
    }

    public static void main(String[] args) {
        processPatient(new EmergencyPatient("Rohan"));
        processPatient(new OutPatient("Priya"));
    }
}
