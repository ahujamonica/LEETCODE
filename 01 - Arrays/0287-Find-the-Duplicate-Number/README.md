# 🚀 287. Find the Duplicate Number

> 📂 Topic: Arrays, Linked List
> 🎯 Pattern: Floyd's Cycle Detection, Two Pointers
> ⭐ Difficulty: Medium
> ⏱️ Optimal Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[https://leetcode.com/problems/find-the-duplicate-number/](https://leetcode.com/problems/find-the-duplicate-number/)

---

# 🧠 Floyd's Cycle Detection — One-Line Idea

> Treat the array like a linked list where `nums[i]` points to the next index, then use a slow pointer moving one step and a fast pointer moving two steps to find the cycle and its entrance.

---

# 💡 Intuition

The goal is to find the **duplicate number** in an array containing `n + 1` integers where every number is in the range `[1, n]`.

For example:

```text
nums = [2,6,4,1,3,1,5]
```

The duplicate is:

```text
1
```

There are several ways to solve this problem.

We can gradually improve the solution:

```text
Compare every pair
       ↓
O(n²)

Sort the array
       ↓
O(n log n)

Use a HashSet
       ↓
O(n) time + O(n) space

Use Floyd's Cycle Detection
       ↓
O(n) time + O(1) space
```

The final approach is the most interesting because it solves the problem **without modifying the array and without using extra memory**.

---

# 🐢 Approach 1 — Brute Force

## Idea

The most straightforward approach is to compare every element with every other element.

For each element:

1. Pick `nums[i]`.
2. Compare it with all elements after it.
3. If another element is equal to it, we found the duplicate.
4. Return that number.

For example:

```text
[2,6,4,1,3,1,5]
```

We start with:

```text
2
```

Compare it with:

```text
6,4,1,3,1,5
```

No duplicate.

Then move to:

```text
6
```

Continue until we find:

```text
1 == 1
```

Therefore:

```text
Duplicate = 1
```

## Why is it inefficient?

For every element, we may have to compare it with almost every other element.

That gives approximately:

```text
n × n
```

comparisons.

So the time complexity is:

```text
O(n²)
```

### Fallback

This is the easiest approach to understand and works without any special trick, but it becomes too slow for large inputs.

### Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`

---

# ⚡ Approach 2 — Sorting

## Idea

If we sort the array, duplicate elements will become adjacent.

For example:

```text
Before:

[2,6,4,1,3,1,5]
```

After sorting:

```text
[1,1,2,3,4,5,6]
```

Now we simply compare neighboring elements.

Whenever:

```text
nums[i] == nums[i-1]
```

we have found the duplicate.

Here:

```text
1 == 1
```

Therefore:

```text
Duplicate = 1
```

## Why is this better?

Instead of comparing every element with every other element, sorting organizes the array so that equal values are placed next to each other.

The expensive part is sorting:

```text
O(n log n)
```

After sorting, the linear scan takes:

```text
O(n)
```

So overall:

```text
O(n log n)
```

### Fallback

If you are not comfortable with Floyd's algorithm, sorting is a very practical solution because the idea is simple:

```text
Sort
 ↓
Check adjacent elements
 ↓
Duplicate found
```

However, sorting modifies the input array unless a copy is created.

### Complexity

- **Time:** `O(n log n)`
- **Space:** `O(1)` auxiliary space if the array is sorted in-place.
- **Important:** This approach modifies the input array.

---

# ⚡ Approach 3 — HashSet

## Idea

A `HashSet` stores values that we have already encountered.

Traverse the array from left to right.

For every number:

1. Check whether it already exists in the set.
2. If it exists, we found the duplicate.
3. Otherwise, add it to the set.

Example:

```text
nums = [2,6,4,1,3,1,5]
```

Process:

```text
2 → add
6 → add
4 → add
1 → add
3 → add
1 → already exists
```

Therefore:

```text
Duplicate = 1
```

## Why is this better?

HashSet lookup is approximately:

```text
O(1)
```

on average.

So we can find the duplicate in a single traversal.

Total:

```text
O(n)
```

However, the trade-off is memory.

In the worst case, we may store almost every element in the HashSet.

Therefore:

```text
Space = O(n)
```

### Fallback

This is probably the easiest **O(n) time** solution to implement.

The trade-off is:

```text
Fast
+
Simple
-
Uses extra memory
```

If the problem did not require constant extra space, this would be an excellent solution.

### Complexity

- **Time:** `O(n)` average
- **Space:** `O(n)`

---

# 🚀 Approach 4 — Floyd's Cycle Detection

## 🔑 Key Observation

The constraints are what make this approach possible:

```text
Array length = n + 1

Every value is between 1 and n
```

Therefore, every value can be treated as a valid index.

We can interpret:

```text
index → nums[index]
```

as:

```text
current node → next node
```

which effectively turns the array into a **linked-list-like structure**.

---

# 🧠 Building the Cycle

Take:

```text
nums = [2,6,4,1,3,1,5]
        0 1 2 3 4 5 6
```

Interpret every value as the next index:

```text
0 → nums[0] → 2
2 → nums[2] → 4
4 → nums[4] → 3
3 → nums[3] → 1
1 → nums[1] → 6
6 → nums[6] → 5
5 → nums[5] → 1
```

So the structure becomes:

```text
0 → 2 → 4 → 3 → 1 → 6 → 5
                    ↑       ↓
                    └───────┘
```

The cycle is:

```text
1 → 6 → 5 → 1
```

Therefore, the **cycle entrance is `1`**.

And `1` is exactly our duplicate.

---

# 🐢🐇 Why Does the Duplicate Create a Cycle?

There are:

```text
n + 1 elements
```

but only:

```text
n possible values
```

because values are restricted to:

```text
1 → n
```

Therefore, at least one value must occur more than once.

When we treat each value as a pointer to the next index, the repeated value causes two paths to point toward the same location.

Eventually, this creates a cycle.

So:

```text
Duplicate
    ↓
Cycle
    ↓
Cycle Entrance
    ↓
Duplicate
```

---

# 🐢 Phase 1 — Find the Meeting Point

We use two pointers:

```text
slow → moves 1 step
fast → moves 2 steps
```

In array form:

```java
slow = nums[slow];

fast = nums[nums[fast]];
```

The first phase only tells us:

> **There is a cycle, and the pointers have met somewhere inside it.**

It does **not** necessarily give us the duplicate.

This distinction is extremely important.

---

# 📝 Dry Run — Phase 1

For:

```text
nums = [2,6,4,1,3,1,5]
```

Start:

```text
slow = 0
fast = 0
```

### Iteration 1

```text
slow = nums[0] = 2

fast:
0 → 2 → 4
```

So:

```text
slow = 2
fast = 4
```

---

### Iteration 2

```text
slow = nums[2] = 4

fast:
4 → 3 → 1
```

So:

```text
slow = 4
fast = 1
```

---

### Iteration 3

```text
slow = nums[4] = 3

fast:
1 → 6 → 5
```

So:

```text
slow = 3
fast = 5
```

---

### Iteration 4

```text
slow = nums[3] = 1

fast:
5 → 1 → 6
```

So:

```text
slow = 1
fast = 6
```

---

### Iteration 5

```text
slow = nums[1] = 6

fast:
6 → 5 → 1
```

So:

```text
slow = 6
fast = 1
```

---

### Iteration 6

```text
slow = nums[6] = 5

fast:
1 → 6 → 5
```

So:

```text
slow = 5
fast = 5
```

🎯 They meet at:

```text
5
```

But **5 is not the duplicate**.

It is simply the **meeting point inside the cycle**.

---

# 🚀 Phase 2 — Find the Cycle Entrance

Now we know:

```text
slow = 5
fast = 5
```

This does NOT mean:

```text
Duplicate = 5
```

Instead, we reset one pointer to the beginning:

```text
slow = 0
```

Now:

```text
slow = 0
fast = 5
```

Both pointers now move **one step at a time**:

```text
slow = nums[slow]
fast = nums[fast]
```

---

## Iteration 1

```text
slow = nums[0] = 2

fast = nums[5] = 1
```

```text
slow = 2
fast = 1
```

---

## Iteration 2

```text
slow = nums[2] = 4

fast = nums[1] = 6
```

```text
slow = 4
fast = 6
```

---

## Iteration 3

```text
slow = nums[4] = 3

fast = nums[6] = 5
```

```text
slow = 3
fast = 5
```

---

## Iteration 4

```text
slow = nums[3] = 1

fast = nums[5] = 1
```

Now:

```text
slow = 1
fast = 1
```

🎯 They meet at:

```text
1
```

This time, the meeting point is the **cycle entrance**.

Therefore:

```text
Duplicate = 1
```

---

# 🤔 Why Can't We Just Return `nums[slow]` After Phase 1?

For this particular example:

```text
slow = 5

nums[slow]
= nums[5]
= 1
```

So it looks like we could simply return:

```text
1
```

But this is **not guaranteed**.

The first meeting point can be anywhere inside the cycle.

For example, a cycle could look like:

```text
2 → 4 → 7 → 9
    ↑         ↓
    └─────────┘
```

If the pointers meet at:

```text
7
```

then:

```text
nums[7] = 9
```

But the cycle entrance is:

```text
2
```

So:

```text
Meeting point ≠ Cycle entrance
```

The second phase is required to guarantee that we find the **cycle entrance**.

---

# 📊 Complexity Comparison

| Approach | Time | Space | Modifies Array? |
| -------- | ---- | ----- | --------------- |
| 🐢 Brute Force | `O(n²)` | `O(1)` | No |
| ⚡ Sorting | `O(n log n)` | `O(1)`* | Yes |
| ⚡ HashSet | `O(n)` | `O(n)` | No |
| 🚀 Floyd's Cycle Detection | `O(n)` | `O(1)` | No |

\* Assuming the array is sorted in-place.

---

# 🎯 Key Takeaway

The progression is:

```text
Brute Force
O(n²)
    ↓
Sort
O(n log n)
    ↓
HashSet
O(n) time + O(n) space
    ↓
Floyd's Cycle Detection
O(n) time + O(1) space
```

The most important mental model for the optimal approach is:

```text
nums[i]
  ↓
Next node

Array
  ↓
Linked List

Duplicate
  ↓
Cycle

Phase 1
  ↓
Find meeting point

Phase 2
  ↓
Find cycle entrance

Cycle entrance
  ↓
Duplicate number
```

The biggest thing to remember:

> **Floyd's first phase does NOT find the duplicate. It only finds a meeting point inside the cycle. The second phase finds the cycle's entrance, which is the duplicate.**
explanations and optimized Java solutions!

Happy Coding! 🚀✨
