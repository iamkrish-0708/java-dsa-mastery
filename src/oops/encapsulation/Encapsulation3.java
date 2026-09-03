package oops.encapsulation;

// WRITE ONLY CLASS EXAMPLE HERE WE CAN SET ONLY AND DON'T USE GET METHOD

public class Encapsulation3 {
    private String password;

    public Encapsulation3(String password){
        this.password=password;
    }

    public void newPass(String newPassword){
        if(newPassword.equals(this.password)){
            System.out.println("NEW PASSWORD CAN'T BE SAME AS PREVIOUS PASSWORD !");
        }
        else if(newPassword.length()<6){
            System.out.println("Password Should Contain Minimum 6 Characters!");
        }
        else{
            this.password=newPassword;
            System.out.println("Password Updated Successfully.");
        }
    }
}
