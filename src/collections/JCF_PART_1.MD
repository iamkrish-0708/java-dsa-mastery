# 📚 Java Collections Framework (JCF) - Part 1: The List Interface

---

## 📌 1. Introduction to JCF

Plain Java Arrays (`int[] arr = new int[5]`) are **fixed in size**. The **Java Collections Framework (JCF)** provides dynamic data structures with built-in utility algorithms (searching, sorting, inserting, deleting).

### Core Hierarchy Overview:
```text
                  Iterable (Interface)
                     │
                 Collection (Interface)
       ┌─────────────┼──────────────┐
       │             │              │
     List           Set           Queue (Interfaces)
       │
 ┌─────┴───────────────┐
 │                     │
ArrayList          LinkedList (Classes)
```

---

## ⚡ 2. `ArrayList` vs `LinkedList`

| Feature | `ArrayList` | `LinkedList` |
| :--- | :--- | :--- |
| **Internal Implementation** | Dynamic Array (`Object[]`) | Doubly Linked List (Nodes with `prev` & `next` pointers) |
| **Random Access (`get(i)`)** | **$\mathcal{O}(1)$ Fast** (Direct memory lookup) | **$\mathcal{O}(N)$ Slow** (Must traverse node by node) |
| **Insert/Delete at Ends** | **$\mathcal{O}(1)$** (At back) / **$\mathcal{O}(N)$** (At front) | **$\mathcal{O}(1)$ Fast** at both Head and Tail |
| **Insert/Delete in Middle** | **$\mathcal{O}(N)$** (Requires element shifting) | **$\mathcal{O}(N)$** (Must find node first, pointer update is $O(1)$) |
| **Memory Consumption** | Low (Contiguous memory array) | Higher (Extra pointer overhead for `prev` and `next`) |

---

## 🛠️ 3. `ArrayList` Internal Mechanics

* **Default Initial Capacity:** `10`
* **Growth Formula:** When full (11th element added), a new larger array is allocated:
  $$\text{New Capacity} = \text{Old Capacity} + (\text{Old Capacity} \gg 1) = 1.5 \times \text{Old Capacity}$$
* **Element Shifting:**
    * Inserting at index `i` shifts all elements from `i` to `N-1` **right by 1 position**.
    * Deleting at index `i` shifts all elements from `i+1` to `N-1` **left by 1 position**.

---

## 🧰 4. Essential List Methods Cheat Sheet

```java
List<String> list = new ArrayList<>();

// 1. ADD / INSERT
list.add("Apple");           // Adds at end -> ["Apple"]
list.add(1, "Banana");       // Inserts at index 1

// 2. ACCESS & UPDATE
String item = list.get(0);   // Gets item at index 0
list.set(0, "Mango");        // Updates index 0 to "Mango"

// 3. REMOVE
list.remove(0);              // Removes by index
list.remove("Banana");       // Removes by value (first occurrence)

// 4. SEARCH & SIZE
boolean hasItem = list.contains("Mango"); // Checks existence (boolean)
int total = list.size();                  // Total elements count
boolean empty = list.isEmpty();           // Checks if size == 0

// 5. RESET
list.clear();                             // Removes all elements
```

---

## 🔄 5. Iteration Techniques

### Method 1: Enhanced `for-each` Loop (Most Common)
```java
for (String fruit : list) {
    System.out.println(fruit);
}
```

### Method 2: Java `Iterator` (Safe Removal During Iteration)
```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String current = it.next();
    System.out.println(current);
}
```

---

## ⚠️ 6. Key Interview Pitfalls & Best Practices

### 1. `List<Integer>.remove()` Overloading Trap
```java
List<Integer> nums = new ArrayList<>();
nums.add(10);
nums.add(20);

nums.remove(1); // ⚠️ Removes element at INDEX 1 (20), NOT the value 1!

// To remove integer by VALUE 1:
nums.remove(Integer.valueOf(1));
```

### 2. Preventing `ConcurrentModificationException`
Never remove elements from a list inside a `for-each` loop without breaking out of the loop:
```java
for (CartItem item : cart) {
    if (item.id == 102) {
        cart.remove(item);
        break; // 👈 ESSENTIAL to prevent ConcurrentModificationException!
    }
}
```

### 3. Custom Class Lists & `toString()` Override
When creating `List<CustomObject>`, override `public String toString()` inside your custom class to print readable text instead of memory hashes.

---

## 🏢 7. Real-World Code Pattern: Custom Object List

```java
class CartItem {
    int id;
    String name;
    double price;

    public CartItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", Name: " + name + ", Price: $" + price + "]";
    }
}
```

---
*Created for Java DSA & LeetCode Practice Track | Target: Summer 2027 Internships*
