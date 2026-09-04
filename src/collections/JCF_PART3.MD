# 📚 Java Collections Framework (JCF) - Part 3: The Map Interface

---

## 📌 1. Overview of Map Hierarchy

A `Map` stores data in **Key-Value pairs (`K, V`)**.
* **Keys must be UNIQUE.** (Duplicate keys overwrite previous values).
* **Values CAN be duplicated.**

```text
                        Map (Interface)
         ┌─────────────────────┼─────────────────────┐
      HashMap           LinkedHashMap             TreeMap
  (Unordered, O(1))   (Insertion Order, O(1))   (Sorted Keys, O(log N))
```

---

## ⚡ 2. `HashMap` vs `LinkedHashMap` vs `TreeMap`

| Feature | `HashMap` | `LinkedHashMap` | `TreeMap` |
| :--- | :--- | :--- | :--- |
| **Key Order Guarantee** | **No Order** (Random) | **Insertion Order** | **Sorted Order** (Ascending Keys) |
| **Internal Implementation** | Hash Table (Array of Buckets + LinkedList/Red-Black Tree) | Hash Table + Doubly LinkedList | Red-Black Self-Balancing Binary Search Tree |
| **Time Complexity (`put`, `get`, `containsKey`)** | **$\mathcal{O}(1)$** (Constant / Super Fast) | **$\mathcal{O}(1)$** | **$\mathcal{O}(\log N)$** (Logarithmic) |
| **Null Key Support** | Allows `1` null key | Allows `1` null key | **No null keys** (Throws `NullPointerException`) |

---

## 🛠️ 3. Essential Map Methods Cheat Sheet

```java
Map<String, Integer> map = new HashMap<>();

// 1. PUT (Insert or Update)
map.put("Apple", 100);
map.put("Banana", 40);
map.put("Apple", 120); // Overwrites 100 with 120!

// 2. GET & GET OR DEFAULT (LeetCode Gold!)
int price1 = map.get("Banana");                    // 40
int price2 = map.getOrDefault("Orange", 0);         // Returns 0 if key not found!

// 3. SEARCH & CHECK
boolean hasKey = map.containsKey("Apple");         // true (O(1))
boolean hasValue = map.containsValue(40);          // true (O(N))

// 4. REMOVE & SIZE
map.remove("Banana");                              // Removes key and its value
int count = map.size();                            // Total key-value pairs
boolean empty = map.isEmpty();                     // Checks if empty
```

---

## 🔄 4. How to Iterate Over a Map

### Method 1: Iterating Over Key-Value Pairs (`entrySet()`) - RECOMMENDED & FASTEST
```java
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

### Method 2: Iterating Over Keys Only (`keySet()`)
```java
for (String key : map.keySet()) {
    System.out.println(key + " -> " + map.get(key));
}
```

---

## 💡 5. The Ultimate LeetCode Pattern: Frequency Counter

Whenever a problem asks you to count occurrences of characters, numbers, or words, use this 1-line `getOrDefault()` trick:

```java
String[] items = {"A", "B", "A", "C", "A", "B"};
Map<String, Integer> freqMap = new HashMap<>();

for (String item : items) {
    // Replaces 7-line if-else block!
    freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
}

// Result: {A=3, B=2, C=1}
```

---

## 🌲 6. `TreeMap` Special Navigational Methods

Because `TreeMap` keeps **keys** sorted, it provides extra navigational methods:

```java
TreeMap<Integer, String> empMap = new TreeMap<>();
empMap.put(104, "David");
empMap.put(101, "Alice");

int minId = empMap.firstKey(); // 101 (Lowest Key)
int maxId = empMap.lastKey();  // 104 (Highest Key)

// Submaps
Map<Integer, String> tail = empMap.tailMap(103); // Keys >= 103
```

---

## ⚙️ 7. How `HashMap` Works Under the Hood (Interview Classic)

1. **Hash Function & Index Calculation:**
    * When you call `map.put(key, value)`, Java calls `key.hashCode()`.
    * Java calculates the bucket index: $\text{index} = \text{hashCode} \ \& \ (n - 1)$.
2. **Handling Hash Collisions:**
    * If two different keys map to the same bucket index, Java stores them as a **LinkedList** (Chaining).
    * **Java 8+ Optimization (Treeify):** If a single bucket's LinkedList grows beyond **8 elements**, Java automatically converts that bucket into a **Red-Black Tree**, reducing lookup time from $\mathcal{O}(N)$ to $\mathcal{O}(\log N)$!

---
*Created for Java DSA & LeetCode Practice Track | Target: Summer 2027 Internships*
