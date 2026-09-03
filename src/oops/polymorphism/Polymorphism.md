# 📚 Java OOP Pillar 3: Polymorphism (Complete Revision Notes)

---

## 1. Core Concept
- **Definition:** Polymorphism means **"many forms"**. It allows the same method name or object reference to behave differently based on context.
- **Two Main Types:**
    1. **Compile-Time Polymorphism** (Method Overloading / Static Binding)
    2. **Runtime Polymorphism** (Method Overriding / Dynamic Binding)

---

## 2. Compile-Time Polymorphism (Method Overloading)
- **Definition:** Having multiple methods in the **same class** with the **same name** but **different parameter lists**.
- **Rules:**
    - Must differ in **number of parameters**, **data types**, or **order of parameters**.
    - **Changing ONLY the return type is NOT allowed** (causes compile error).
    - Resolved by compiler at compile-time.

### Example:
```java
class Calculator {
    int add(int a, int b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; } // Overloaded by count
    double add(double a, double b) { return a + b; }  // Overloaded by type
}
```

---

## 3. Runtime Polymorphism (Method Overriding & Upcasting)
- **Upcasting:** Assigning a Child object to a Parent reference variable (`Parent p = new Child()`). Always safe and automatic.
- **Dynamic Method Dispatch:** When `p.method()` is invoked, Java determines at **runtime** which overridden method to execute based on the actual object in memory (`new Child()`).

### Key Difference:
| Feature | Method Overloading | Method Overriding |
| :--- | :--- | :--- |
| **Where it occurs** | Same class | Parent & Child classes |
| **Parameters** | Must be different | Must be exact same |
| **Resolution Time** | Compile-Time | Run-Time |

---

## 4. Polymorphic Method Arguments
Passing parent references to methods allows a single method to handle **any current or future subclass** automatically.

```java
void processVehicle(Vehicle v) {
    v.calculateFare(); // Works for Taxi, Bus, Train, etc.
}
```

---

## 5. Downcasting & `instanceof` Operator

### Why Downcasting?
Parent references (`Parent p`) can ONLY call methods defined in `Parent`. To call **child-unique methods**, you must downcast `p` back to `Child`.

### Preventing `ClassCastException`:
Always check type before downcasting using `instanceof`:

```java
// Modern Java Pattern Matching (Java 16+):
if (p instanceof EmergencyPatient ep) {
    ep.triggerICUAlert(); // Automatically checked and safely cast
}
```

---

## ⚡ 60-Second Master Code Snippet (All Concepts in One)

```java
// Parent Class
class DataStream {
    String data;
    DataStream(String data) { this.data = data; }
    void process() { System.out.println("Processing raw stream..."); }
}

// Child Class
class JsonData extends DataStream {
    JsonData(String data) { super(data); }

    @Override
    void process() { System.out.println("Parsing JSON: " + data); }

    void validateSchema() { System.out.println("Schema Validated! ✅"); }
}

// Handler Class (Overloading + Polymorphism + Downcasting)
class Processor {
    // Polymorphic Argument
    void run(DataStream ds) {
        ds.process(); // Runtime Polymorphism
        if (ds instanceof JsonData json) {
            json.validateSchema(); // Safe Downcasting
        }
    }

    // Overloaded Method
    void run(DataStream ds, boolean verbose) {
        run(ds);
        if (verbose) System.out.println("Done processing!");
    }
}

public class MasterPolymorphism {
    public static void main(String[] args) {
        Processor p = new Processor();
        DataStream stream = new JsonData("{\"id\": 1}"); // Upcasting
        p.run(stream, true);
    }
}
```