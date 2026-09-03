# 📚 Java Collections Framework (JCF) - Part 2: Set & Queue Interfaces

---

## 📌 1. Overview of Set & Queue Hierarchy

```text
                     Collection (Interface)
                               │
            ┌──────────────────┴──────────────────┐
      Set (Interface)                      Queue (Interface)
  ┌─────────┼─────────┐                  ┌────────┴────────┐
HashSet LinkedHashSet TreeSet        LinkedList       PriorityQueue
(Unordered) (Order)  (Sorted)        (FIFO Queue)   (Min/Max Heap)
```

---

## 🛡️ 2. The `Set` Interface (No Duplicates Allowed)

A `Set` is a collection that contains **no duplicate elements**. Adding a duplicate returns `false` and is safely ignored.

| Implementation | Order Guarantee | Internal Structure | Time Complexity (`add`, `remove`, `contains`) |
| :--- | :--- | :--- | :--- |
| **`HashSet`** | **No Order** (Random) | `HashMap` Key Table | **$\mathcal{O}(1)$** (Constant / Super Fast) |
| **`LinkedHashSet`** | **Insertion Order** | `HashMap` + Doubly LinkedList | **$\mathcal{O}(1)$** (Preserves order added) |
| **`TreeSet`** | **Sorted Order** (Ascending) | Red-Black Self-Balancing Tree | **$\mathcal{O}(\log N)$** (Binary Search Tree) |

---

## 🌲 3. `TreeSet` Special Navigational Methods

Because `TreeSet` keeps elements sorted, it provides powerful search and range methods:

```java
TreeSet<Integer> set = new TreeSet<>();
set.add(50); set.add(10); set.add(30); // Automatically sorted: [10, 30, 50]

int min = set.first(); // Returns 10 (Lowest element)
int max = set.last();  // Returns 50 (Highest element)

// Subsets & Ranges
Set<Integer> gte30 = set.tailSet(30); // Elements >= 30 -> [30, 50]
Set<Integer> lt30  = set.headSet(30); // Elements < 30  -> [10]
```

---

## 🚦 4. The `Queue` Interface (FIFO Processing)

A `Queue` processes elements in **First-In, First-Out (FIFO)** order.

### Safe Queue Methods (Preferred for Production):

| Operation | Safe Method (Returns `null`/`false`) | Exception Method (Throws Error if Empty) |
| :--- | :--- | :--- |
| **Enqueue (Insert)** | `queue.offer(element)` | `queue.add(element)` |
| **Dequeue (Remove)** | `queue.poll()` | `queue.remove()` |
| **Peek (Examine)** | `queue.peek()` | `queue.element()` |

```java
Queue<String> queue = new LinkedList<>();
queue.offer("Customer A");
queue.offer("Customer B");

String front = queue.peek(); // "Customer A" (Does not remove)
String served = queue.poll(); // Removes and returns "Customer A"
```

---

## ⚡ 5. `PriorityQueue` (Min-Heap vs Max-Heap)

Instead of standard FIFO, a `PriorityQueue` processes elements based on **priority**.

* **Min-Heap (Default):** Smallest element has highest priority and comes out first.
* **Max-Heap (`Collections.reverseOrder()`):** Largest element has highest priority and comes out first.

```java
// A) MIN-HEAP (Smallest out first)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(50); minHeap.offer(10); minHeap.offer(30);
System.out.println(minHeap.poll()); // Prints 10

// B) MAX-HEAP (Largest out first)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(50); maxHeap.offer(10); maxHeap.offer(30);
System.out.println(maxHeap.poll()); // Prints 50
```

---

## ⏱️ 6. Time Complexity Reference Table

| Data Structure | Insertion (`add`/`offer`) | Removal (`poll`/`remove`) | Search (`contains`) |
| :--- | :--- | :--- | :--- |
| **`HashSet`** | $\mathcal{O}(1)$ | $\mathcal{O}(1)$ | $\mathcal{O}(1)$ |
| **`TreeSet`** | $\mathcal{O}(\log N)$ | $\mathcal{O}(\log N)$ | $\mathcal{O}(\log N)$ |
| **`LinkedList Queue`** | $\mathcal{O}(1)$ | $\mathcal{O}(1)$ | $\mathcal{O}(N)$ |
| **`PriorityQueue`** | $\mathcal{O}(\log N)$ | $\mathcal{O}(\log N)$ | $\mathcal{O}(N)$ |

---

## ⚠️ 7. Key Interview Tricks & Rules

1. **`Set.add()` returns boolean:**
   ```java
   boolean added = set.add("Email"); // Returns false if duplicate!
   ```
2. **`PriorityQueue` Direct Printing Trick:**
   Printing a `PriorityQueue` directly (`System.out.println(pq)`) outputs the internal binary heap array, which may not look sorted. However, calling `.poll()` is **always guaranteed** to retrieve elements in strict priority order!

---
*Created for Java DSA & LeetCode Practice Track | Target: Summer 2027 Internships*
