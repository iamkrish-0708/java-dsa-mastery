# 📚 Java OOP Pillar 4: Abstraction (Complete Revision Notes)

---

## 1. Core Concept
- **Definition:** Abstraction means **hiding internal implementation details** and showing only essential features to the user.
- **Two Ways to Achieve Abstraction in Java:**
    1. **Abstract Classes** (0% to 100% partial abstraction)
    2. **Interfaces** (100% total contractual abstraction)

---

## 2. Abstract Classes (`abstract` keyword)
- **Cannot be instantiated** directly (`new AbstractClass()` ❌).
- Can contain **abstract methods** (no body `{}`) and **concrete methods** (with body `{}`).
- Can contain **fields**, **static members**, and **constructors** (called via `super()`).
- Any concrete subclass **MUST implement all abstract methods**.

### Example:
```java
abstract class Appliance {
    String brand;
    Appliance(String brand) { this.brand = brand; } // Constructor

    abstract void performFunction(); // Abstract method (no body)

    void turnOn() { System.out.println(brand + " is ON"); } // Concrete method
}
```

---

## 3. Interfaces (`interface` & `implements`)
- Defined using `interface` and implemented using `implements`.
- **100% Contractual:** Used to define *what* a class can do.
- Methods are implicitly `public abstract`.
- Fields are implicitly `public static final` (constants).
- **NO Constructors** (interfaces cannot hold instance state).
- **Multiple Inheritance:** A class CAN implement multiple interfaces (`implements A, B`).

### Example:
```java
interface Payable { void processPayment(double amount); }
interface Trackable { void trackPackage(String id); }

class ExpressOrder implements Payable, Trackable {
    @Override
    public void processPayment(double amount) { /* ... */ }
    @Override
    public void trackPackage(String id) { /* ... */ }
}
```

---

## 4. Modern Interface Features (Java 8+)
- **`default` Methods:** Methods with a body `{}` inside an interface. Implementing classes inherit default behavior automatically but can choose to override it.
- **`static` Methods:** Utility methods belonging to the interface. Called via `InterfaceName.methodName()`. Cannot be overridden.

```java
interface Notifier {
    void send(String msg);

    default void sendWithRetry(String msg, int retries) {
        for (int i = 1; i <= retries; i++) send(msg);
    }

    static boolean isValid(String msg) { return msg != null && !msg.isEmpty(); }
}
```

---

## ⚔️ Abstract Class vs Interface Comparison

| Feature | Abstract Class | Interface |
| :--- | :--- | :--- |
| **Keyword** | `abstract class` | `interface` |
| **Inheritance** | `extends` (Single only) | `implements` (Multiple supported) |
| **Constructors** | ✅ YES | ❌ NO |
| **Fields** | Any type (private, protected, instance) | Only `public static final` constants |
| **Methods** | Abstract & Concrete | Abstract, `default`, `static` |
| **When to use?** | When classes share identity/state (`IS-A`) | When classes share capabilities (`CAN-DO`) |

---

## ⚡ 60-Second Master Code Snippet (All Concepts in One)

```java
// Interface (Capability Contract)
interface Searchable {
    boolean search(int target);
}

// Abstract Class (Shared Blueprint + State)
abstract class CustomDataStructure {
    protected int size;
    CustomDataStructure() { this.size = 0; }
    int getSize() { return this.size; }
    abstract void add(int value);
}

// Concrete Class implementing both!
class IntArrayList extends CustomDataStructure implements Searchable {
    private int[] data;

    IntArrayList(int capacity) {
        super();
        this.data = new int[capacity];
    }

    @Override
    void add(int value) {
        if (size < data.length) data[size++] = value;
    }

    @Override
    public boolean search(int target) {
        for (int i = 0; i < size; i++) {
            if (data[i] == target) return true;
        }
        return false;
    }
}
```