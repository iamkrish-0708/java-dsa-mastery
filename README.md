# ☕ Java DSA & Object-Oriented Programming (OOP) Mastery

Comprehensive Java repository covering Object-Oriented Programming (OOP), Data Structures & Algorithms (DSA), Time & Space Complexity analysis, and the Java Collections Framework (JCF).

---

## 🏛️ Core Pillars of OOP (100% Complete)

* ✅ **1. Encapsulation:** Data hiding with `private` fields, getters/setters, state validation, and read-only/write-only class models.
* ✅ **2. Inheritance:** Code reusability via `extends`, superclass constructor delegation (`super()`), method overriding, and hierarchical architectures.
* ✅ **3. Polymorphism:** Compile-time (Method Overloading) vs Runtime (Dynamic Method Dispatch), polymorphic method arguments (`void run(DataStream ds)`), downcasting safety, and Java 16+ pattern matching with `instanceof`.
* ✅ **4. Abstraction:** Partial abstraction (`abstract class` with state & constructors) vs 100% Contractual Interfaces (`interface`, `implements`), multiple interface inheritance, Java 8+ `default` and `static` interface methods.

---

## 📦 Java Collections Framework (JCF) Track

* ✅ **List Interface:** `ArrayList` (Dynamic resizable array, $1.5\times$ growth formula) vs `LinkedList` (Doubly-linked nodes with $\mathcal{O}(1)$ head/tail operations), custom object lists (`ArrayList<CartItem>`), `Iterator` traversal, safe element removal.
* ✅ **Set Interface:** `HashSet` ($\mathcal{O}(1)$ unordered deduplication), `LinkedHashSet` ($\mathcal{O}(1)$ insertion order), `TreeSet` ($\mathcal{O}(\log N)$ Red-Black self-balancing sorted tree with navigational queries: `.first()`, `.last()`, `.tailSet()`, `.headSet()`).
* ✅ **Queue Interface:** Standard FIFO processing with safe methods (`offer()`, `poll()`, `peek()`) vs `PriorityQueue` (Min-Heap / Max-Heap via `Collections.reverseOrder()`, $\mathcal{O}(\log N)$ priority scheduling).

---

## ⏱️ Asymptotic Complexity Analysis

Documented in detail inside [`src/complexity/tc_sc.md`](./src/complexity/tc_sc.md):
* Big-O ($\mathcal{O}$), Big-$\Omega$, Big-$\Theta$ definitions and calculation rules.
* Mathematical hierarchy: $\mathcal{O}(1) < \mathcal{O}(\log N) < \mathcal{O}(N) < \mathcal{O}(N \log N) < \mathcal{O}(N^2) < \mathcal{O}(2^N) < \mathcal{O}(N!)$.
* Total vs Auxiliary (extra) space allocation distinctions.

---

## 📂 Project Structure

```text
src/
├── basics/               ➔ Math algorithms, reverse number, basic logic
├── strings/              ➔ String manipulation & algorithmic problem solving
├── oops/
│   ├── encapsulation/   ➔ Encapsulation files & revision guide
│   ├── inheritance/     ➔ Inheritance class hierarchies & revision guide
│   ├── polymorphism/    ➔ Polymorphism demos & master notes
│   └── abstraction/     ➔ Abstract classes, interfaces & master notes
├── complexity/           ➔ Asymptotic Time & Space Complexity master cheatsheet
└── collections/          ➔ Java Collections Framework (ArrayList, LinkedList, HashSet, TreeSet, PriorityQueue)
```

---
*Maintained by **[Krish](https://github.com/iamkrish-0708)** | Target: Summer 2027 Software Engineering Internships*
