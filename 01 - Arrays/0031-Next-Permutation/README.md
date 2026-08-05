# 🚀 31. Next Permutation

> 📂 Topic: Arrays  
> 🎯 Pattern: Greedy, Lexicographical Order  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem Link

https://leetcode.com/problems/next-permutation/

---

# 🧠 Pattern Recognition

When the problem mentions:

- Next lexicographical permutation
- Next greater arrangement
- Rearrange in-place

➡️ Think:

```
Find Pivot
      ↓
Swap
      ↓
Reverse Suffix
```

---

# 💡 Intuition

A brute force solution would be:

- Generate all permutations.
- Sort them lexicographically.
- Find the current permutation.
- Return the next one.

However,

```
Time Complexity = O(n!)
```

which is impractical.

Instead, notice an important property.

Starting from the right, the array is usually in **descending order**.

Example

```
1 3 5 4 2
```

The suffix

```
5 4 2
```

is already the **largest possible arrangement**.

To obtain the next permutation, we only need to increase the number by the **smallest possible amount**.

---

# 🔍 Key Observation

The algorithm always consists of four fixed steps.

### Step 1

Find the **pivot**

```
nums[i] < nums[i+1]
```

moving from right to left.

---

### Step 2

If no pivot exists,

the array is already the largest permutation.

Reverse the entire array.

---

### Step 3

Find the first element greater than the pivot from the right.

Swap them.

---

### Step 4

Reverse everything after the pivot.

This converts the descending suffix into ascending order, producing the smallest possible suffix.

---

# 📝 Dry Run

## Input

```
1 3 5 4 2
```

### Find Pivot

```
1 3 5 4 2
    ^
```

Pivot = 3

---

### Find Next Greater

```
4
```

Swap

```
1 4 5 3 2
```

---

### Reverse Suffix

Before

```
5 3 2
```

After

```
2 3 5
```

Final Answer

```
1 4 2 3 5
```

---

# ⏱️ Complexity Analysis

### Time Complexity

- Find Pivot → O(n)
- Find Next Greater → O(n)
- Reverse Suffix → O(n)

Overall

```
O(n)
```

---

### Space Complexity

Only constant extra variables are used.

```
O(1)
```

---

# 🤔 Things That Confused Me

### ❓1. Why do we scan from the right?

Because the suffix after the pivot is always in descending order.

Scanning from the right guarantees that the first greater element we find is the **smallest possible greater element**.

This ensures the next permutation is just one step larger.

---

### ❓2. Why do we reverse instead of sorting?

After swapping,

the suffix is still in descending order.

Example

```
5 3 2
```

To obtain the smallest possible arrangement,

we need

```
2 3 5
```

Since the suffix is already descending,

reversing it directly gives ascending order.

Sorting would also work,

but reversing is linear.

```
Reverse → O(n)

Sorting → O(n log n)
```

---

### ❓3. Why do we `break` after swapping?

Only **one swap** is required.

The first greater element from the right is already the optimal choice.

Continuing the loop would perform additional swaps and produce an incorrect permutation.

---

### ❓4. Why do we search for

```
nums[i] < nums[i+1]
```

instead of

```
nums[i] > nums[i+1]
```

We are looking for the first place where the sequence **stops decreasing**.

That point is the pivot that can be increased.

---

### ❓5. Why reverse the whole array if no pivot exists?

Example

```
5 4 3 2 1
```

This is already the largest possible permutation.

The next permutation should wrap around to the smallest permutation.

```
1 2 3 4 5
```

which is obtained by reversing the array.

---

### ❓6. My Mistake

Initially,

I wrote

```java
for(int i = n-2; i > 0; i--)
```

This never checked index `0`.

The correct loop is

```java
for(int i = n-2; i >= 0; i--)
```

---

### ❓7. My Mistake

Initially,

I forgot to write

```java
break;
```

after swapping.

This caused multiple swaps and an incorrect answer.

Only one swap should be performed.
