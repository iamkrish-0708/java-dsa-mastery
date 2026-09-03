package oops.encapsulation;

public class Encapsulation2 {

    // READ ONLY CLASS EXAMPLE HERE WE CAN ONLY SET THINGS ONCE THEN WE CAN'T MAKE ANY CHANGES IN EXISTING DATA ONLY GETTER METHODS ARE AVAILABLE

    private final String cardId;
    private final String name;

    public Encapsulation2(String cardId,String name){
        this.cardId=cardId;
        this.name=name;
    }

    public String getIdCard(){
        return this.cardId;
    }

    public String getIdname(){
        return this.name;
    }
}
