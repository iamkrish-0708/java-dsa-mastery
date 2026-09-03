package oops.inheritance;

class Person{
    protected String name;
    protected int age;

    Person(String name,int age){
        this.name=name;
        this.age=age;
    }

}

class Employee extends Person{
    protected int employeeId;
    protected double baseSalary;

    Employee(String name,int age,int employeeId,double baseSalary){
        super(name,age);
        this.employeeId=employeeId;
        this.baseSalary=baseSalary;
    }

    void displayDetails(){
        System.out.println("----------------DETAILS-------------------");
        System.out.println("Name:"+this.name);
        System.out.println("Age:"+this.age);
        System.out.println("ID:"+this.employeeId);
        System.out.println("Base Salary:"+this.baseSalary+" rs");
    }
}

class Manager extends Employee{
    private double bonus;

    Manager(String name,int age,int employeeId,double baseSalary,double bonus){
        super(name,age,employeeId,baseSalary);
        this.bonus=bonus;
    }

    @Override
    void displayDetails(){
        super.displayDetails();
        double totalCompensation=this.baseSalary+this.bonus;
        System.out.println("Bonus:"+this.bonus+" rs | Total Pay:"+totalCompensation+" rs");
    }
}


public class InheritanceMain4 {
    public static void main(String[] args) {
    Manager m1=new Manager("Vikram", 35, 101, 80000.0, 15000.0);
    m1.displayDetails();
    }
}
