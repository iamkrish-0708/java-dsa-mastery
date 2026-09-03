# 📚 Java OOP Pillar 2: Inheritance (Complete Revision Notes)

---

## 1. Core Concept & The `extends` Keyword
- Definition: Inheritance allows a Child Class (Subclass) to acquire properties (fields) and behaviors (methods) from a Parent Class (Superclass).
- Key Keyword: `extends`
- Primary Benefit: Code Reusability and establishing an IS-A relationship (e.g., Doctor IS-A HospitalStaff).

Syntax:
class SuperClass {
    // Parent fields & methods
}

class SubClass extends SuperClass {
    // Inherits everything from SuperClass + adds unique features
}

---

## 2. The `super` Keyword & Constructor Chaining
The `super` keyword refers to the immediate parent class object.

Two Uses of `super`:
1. `super(...)` — Call Parent Constructor:
   - Used to pass initialization data to the parent class constructor.
   - CRITICAL RULE: `super(...)` MUST be the very first statement inside the child constructor!
2. `super.methodName()` — Call Parent Method:
   - Used to invoke a method from the parent class (especially when overridden in the child class).

Example:
class Parent {
    String name;
    Parent(String name) { this.name = name; }
}

class Child extends Parent {
    int age;
    Child(String name, int age) {
        super(name); // Must be first statement!
        this.age = age;
    }
}

---

## 3. Method Overriding (@Override) & Dynamic Method Dispatch

Method Overriding Rules:
- Occurs when a subclass provides its own implementation of a parent method.
- Must have the exact same method name, return type, and parameters as in the parent class.
- Always use the `@Override` annotation to let the Java compiler check for typos.

Dynamic Method Dispatch (Runtime Polymorphism):
A Parent reference variable can hold a Child object:
Parent p = new Child();
p.display(); // Calls Child's overridden display() at runtime!

Why use it? Allows storing different subclasses in a single parent array/list and processing them in one loop without duplicate code.

The `final` Keyword in Inheritance:
- `final class`: Cannot be extended (prevents inheritance entirely, e.g., String class).
- `final method`: Cannot be overridden by any subclass.

---

## 4. Types of Inheritance in Java

- Single Inheritance: A -> B (Supported)
- Multilevel Inheritance: A -> B -> C (Supported)
- Hierarchical Inheritance: A -> B & A -> C (Supported)
- Multiple Inheritance: A, B -> C (NOT Supported with classes due to Diamond Problem)

Why Java Doesn't Support Multiple Class Inheritance:
If Class A and Class B both have a display() method, and Class C extends A, B, calling c.display() creates ambiguity about which parent's method to execute (The Diamond Problem). Java solves this using Interfaces.

---

## 5. Access Modifiers & Visibility Matrix

- `private`: Visible inside same class only.
- `default` (package-private): Visible inside same package.
- `protected`: Visible to subclasses in any package (Best for Inheritance).
- `public`: Visible everywhere.

---

## ⚡ 60-Second Master Code Snippet (All Concepts in One)

// 1. Grandparent (Multilevel)
class Person {
    protected String name;
    Person(String name) { this.name = name; }
}

// 2. Parent (Single & Multilevel)
class Employee extends Person {
    protected double salary;

    Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    void showDetails() {
        System.out.println("Name: " + name + " | Salary: $" + salary);
    }
}

// 3. Child (Overriding & Polymorphism)
class Manager extends Employee {
    private double bonus;

    Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    @Override
    void showDetails() {
        super.showDetails();
        System.out.println("Bonus: $" + bonus + " | Total: $" + (salary + bonus));
    }
}

// 4. Main Execution
public class MasterRevision {
    public static void main(String[] args) {
        // Dynamic Method Dispatch (Parent ref -> Child object)
        Employee e = new Manager("Vikram", 80000, 15000);
        e.showDetails(); // Runs Manager's overridden showDetails()
    }
}