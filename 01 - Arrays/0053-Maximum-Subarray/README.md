# 🚀 53. Maximum Subarray

> 📂 Topic: Arrays  
> 🎯 Pattern: Kadane's Algorithm, Running Sum  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

https://leetcode.com/problems/maximum-subarray/

---

# 🧠 Pattern Recognition

Whenever the problem mentions:

- Maximum Sum
- Contiguous Subarray
- Largest Sum

Think:

```
Running Sum

↓

Should I continue this subarray?

OR

Start a new one?
```

This is the classic **Kadane's Algorithm** pattern.

---

# 💡 Intuition

The problem asks us to find the **maximum possible sum of a contiguous subarray**.

The most straightforward solution is to generate every possible subarray, calculate its sum, and keep track of the maximum.

However, this involves a lot of repeated work.

For example,

```
1 2 3 4
```

While calculating

```
1 2
```

we have already computed part of

```
1 2 3
```

Instead of recalculating these sums repeatedly, we can reuse the previously computed sum.

This improves the complexity from **O(n³)** to **O(n²)**.

Can we optimize further?

Yes.

Suppose our current running sum becomes negative.

```
Current Sum = -8

Next Number = 10
```

Continuing the current subarray gives

```
-8 + 10 = 2
```

Starting a new subarray gives

```
10
```

Clearly,

the negative running sum only hurts the future answer.

Therefore,

whenever the running sum becomes negative,

we discard it and start a new subarray.

This simple greedy observation gives us the optimal **O(n)** solution known as **Kadane's Algorithm**.

---

# 📈 Evolution of the Solution

```
Generate every subarray

↓

Brute Force

O(n³)

↓

Reuse previously calculated sums

↓

Better

O(n²)

↓

Discard negative running sums

↓

Kadane's Algorithm

O(n)
```

---

# 🐢 Approach 1 — Brute Force

## Idea

Generate every possible subarray.

For every subarray,

calculate its sum using another loop.

Update the maximum answer.

---

### Complexity

**Time**

```
O(n³)
```

**Space**

```
O(1)
```

---

# ⚡ Approach 2 — Better

## Idea

Instead of recalculating the subarray sum every time,

maintain a running sum.

```
sum += nums[j]
```

Now,

each subarray starting from index `i` is extended one element at a time.

This eliminates the innermost loop.

---

### Complexity

**Time**

```
O(n²)
```

**Space**

```
O(1)
```

---

# 🚀 Approach 3 — Kadane's Algorithm (Optimal)

## Key Observation

A negative running sum can never help us build a larger subarray.

Example

```
Running Sum = -5

Next Element = 8
```

Continuing

```
-5 + 8 = 3
```

Starting Fresh

```
8
```

Clearly,

starting fresh is always better.

Therefore,

whenever

```
sum < 0
```

we reset

```
sum = 0
```

and continue.

---

# 🔍 Visual Intuition

Consider

```
-2  1  -3  4  -1  2  1  -5  4
```

```
Running Sum

-2

↓

Negative

↓

Discard

---------------------

1

↓

Positive

↓

Keep extending

---------------------

1 + (-3)

↓

Negative

↓

Discard

---------------------

4

↓

4

↓

4 + (-1)

↓

3

↓

3 + 2

↓

5

↓

5 + 1

↓

6  ← Maximum
```

The maximum subarray is

```
4 -1 2 1
```

Sum

```
6
```

---

# 📝 Dry Run

Input

```
[-2,1,-3,4,-1,2,1,-5,4]
```

| Element | Running Sum | Maximum |
|---------:|------------:|---------:|
| -2 | -2 → 0 | -2 |
| 1 | 1 | 1 |
| -3 | -2 → 0 | 1 |
| 4 | 4 | 4 |
| -1 | 3 | 4 |
| 2 | 5 | 5 |
| 1 | 6 | 6 |
| -5 | 1 | 6 |
| 4 | 5 | 6 |

Answer

```
6
```

---

# 🤔 Things That Confused Me

### ❓1. Why do we reset the running sum when it becomes negative?

Because a negative sum will only decrease any future subarray.

Example

```
Current Sum = -7

Next Number = 9
```

Continue

```
2
```

Start Fresh

```
9
```

Starting fresh always gives a larger sum.

---

### ❓2. Why do we update the answer before resetting?

Consider

```
[-5]
```

If we reset first,

```
sum = 0
```

we would incorrectly return

```
0
```

Instead,

we first update

```
max = -5
```

and then reset the running sum.

This correctly handles arrays containing only negative numbers.

---

### ❓3. Why is

```java
max = Integer.MIN_VALUE;
```

used instead of

```java
max = 0;
```

Consider

```
[-8,-2,-5]
```

The answer should be

```
-2
```

Initializing

```
max = 0
```

would produce the wrong answer.

---



# ❌ Common Mistakes

- Initializing `max` as `0`
- Resetting the running sum before updating the answer
- Confusing `=+` with `+=`
- Thinking Kadane's Algorithm works only when positive numbers exist

---

# ⏱️ Complexity Analysis

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n³) | O(1) |
| Better | O(n²) | O(1) |
| Kadane's Algorithm | O(n) | O(1) |

