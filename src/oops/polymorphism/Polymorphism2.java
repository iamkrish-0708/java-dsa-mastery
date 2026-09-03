package oops.polymorphism;

/*
          METHOD OVERRIDING(RUN TIME POLYMORPHISM)- CHILD CLASS USING PARENT CLASS METHOD BUT METHOD BEHAVE DIFFERENTLY
          DEPENDING ON CLASS

          UPCASTING IS ASSIGNING CHILD OBJECT  TO PARENT REFERENCE VARIABLE
*/

public class Polymorphism2 {
    static class Weapon{
        void attack(){
            System.out.println("Attacks with a basic weapon!");
        }
    }
    static class Sword extends Weapon{
        @Override
        void attack(){
            System.out.println("Slashes with a sharp sword for 50 damage! ⚔️");
        }
    }
    static class Bow extends Weapon{
        @Override
        void attack(){
            System.out.println("Fires a swift arrow for 30 damage! 🏹");
        }
    }
    public static void main(String[] args) {
        Weapon w1=new Sword();
        Weapon w2=new Bow();
        w1.attack();
        w2.attack();
    }
}
