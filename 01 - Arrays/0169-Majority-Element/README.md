# 🚀 169. Majority Element

> 📂 Topic: Arrays
> 🎯 Pattern: Boyer-Moore Voting Algorithm
> ⭐ Difficulty: Easy
> ⏱️ Optimal Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 169 — Majority Element](https://leetcode.com/problems/majority-element/)

---

# 🧠 Boyer-Moore Voting Algorithm — One-Line Idea

> Maintain a candidate and its vote count, increasing the count when the current element matches the candidate and decreasing it when it differs; since the majority element appears more than `n / 2` times, it will always survive the cancellations.

---

# 💡 Intuition

The goal is to find the element that appears **more than `n / 2` times** in the array.

For example:

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

The majority element is:

```text
2
```

because it appears `4` times out of `7` elements.

A straightforward approach would be to count the frequency of every element using a `HashMap`, but that requires `O(n)` extra space.

Instead, we can use the **Boyer-Moore Voting Algorithm**, which finds the majority element using:

```text
O(n) time
O(1) space
```

The key idea is to treat every different element as cancelling one occurrence of the current candidate.

Since the majority element appears more than all other elements combined, it cannot be completely cancelled.

---

# 🚀 Approach — Boyer-Moore Voting Algorithm

We maintain two variables:

```java
int candidate = 0;
int count = 0;
```

Where:

```text
candidate → current possible majority element
count     → current vote count for the candidate
```

We then traverse the array.

---

## Step 1: If `count == 0`

When:

```text
count == 0
```

there is no active candidate.

So we choose the current element as the new candidate:

```java
candidate = nums[i];
```

---

## Step 2: Current Element Matches Candidate

If:

```java
nums[i] == candidate
```

the current element supports the candidate.

So:

```java
count++;
```

---

## Step 3: Current Element Is Different

If:

```java
nums[i] != candidate
```

the current element cancels one vote of the candidate:

```java
count--;
```

---

# 📝 Dry Run

Consider:

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

Initially:

```text
candidate = 0
count = 0
```

---

### Iteration 1

Current element:

```text
2
```

Since:

```text
count == 0
```

choose:

```text
candidate = 2
```

Since the current element matches:

```text
count = 1
```

---

### Iteration 2

Current element:

```text
2
```

It matches the candidate:

```text
count++
```

Now:

```text
candidate = 2
count = 2
```

---

### Iteration 3

Current element:

```text
1
```

It differs from the candidate:

```text
count--
```

Now:

```text
candidate = 2
count = 1
```

One vote for `2` has been cancelled by `1`.

---

### Iteration 4

Current element:

```text
1
```

Again, it differs:

```text
count--
```

Now:

```text
candidate = 2
count = 0
```

The votes have cancelled each other.

---

### Iteration 5

Current element:

```text
1
```

Since:

```text
count == 0
```

choose:

```text
candidate = 1
```

Then:

```text
count++
```

Now:

```text
candidate = 1
count = 1
```

---

### Iteration 6

Current element:

```text
2
```

Different from candidate:

```text
count--
```

Now:

```text
candidate = 1
count = 0
```

---

### Iteration 7

Current element:

```text
2
```

Since:

```text
count == 0
```

choose:

```text
candidate = 2
```

Then:

```text
count++
```

Final:

```text
candidate = 2
count = 1
```

Therefore:

```text
Majority Element = 2
```

---

# 🧠 Why Does This Work?

The problem guarantees that the majority element appears:

```text
> n / 2 times
```

Therefore, the majority element appears **more times than all other elements combined**.

For:

```text
[2, 2, 1, 1, 1, 2, 2]
```

we have:

```text
2 → 4 occurrences
1 → 3 occurrences
```

We can pair different elements and cancel them:

```text
2 ↔ 1
2 ↔ 1
2 ↔ 1
```

After cancelling:

```text
2
```

is still left.

This is what the `count` variable represents.

As long as the majority element has more occurrences than all other elements combined, it will survive the cancellation process.

---

# 📊 Complexity

We traverse the array only once.

Therefore:

- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)`

This is the **optimal approach** because we must inspect the elements of the array, giving us a minimum time complexity of `O(n)`.

---

# 🎯 Key Takeaway

Remember Boyer-Moore as:

```text
count = 0
    ↓
Choose current element as candidate
    ↓
Same as candidate?
    ├── YES → count++
    └── NO  → count--
    ↓
count becomes 0?
    ↓
Choose a new candidate
```

The core idea is:

```text
Majority element
        ↓
Appears more than n / 2 times
        ↓
More occurrences than all other elements combined
        ↓
Can cancel out every non-majority occurrence
        ↓
Still survives
        ↓
Final candidate = majority element
```

> **Think of Boyer-Moore as a cancellation game: every different element cancels one vote of the current candidate, and because the majority element has more votes than everything else combined, it is guaranteed to survive.**

---

Happy Coding! 🚀✨
