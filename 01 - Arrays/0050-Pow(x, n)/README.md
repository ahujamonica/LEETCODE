# 🚀 50. Pow(x, n)

> 📂 Topic: Math, Recursion, Binary Exponentiation  
> 🎯 Pattern: Exponentiation by Squaring  
> ⭐ Difficulty: Medium  
> ⏱️ Optimal Time: O(log n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 50 — Pow(x, n)](https://leetcode.com/problems/powx-n/)

---

# 🧠 Binary Exponentiation — One-Line Idea

> Instead of multiplying `x` repeatedly, reduce the exponent by half whenever it is even by squaring `x`, and accumulate `x` into the answer whenever the exponent is odd.

---

# 💡 Intuition

The goal is to calculate:

```text
x^n
```

A straightforward approach would multiply `x` by itself `n` times.

For example:

```text
2^10

= 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2
```

This takes `O(n)` time.

We can optimize this using **Binary Exponentiation**, also called **Exponentiation by Squaring**.

The key observations are:

```text
If n is EVEN:

x^n = (x²)^(n/2)
```

and:

```text
If n is ODD:

x^n = x × x^(n-1)
```

Therefore:

```text
Odd exponent
    ↓
Take one x into the answer
    ↓
Exponent becomes even

Even exponent
    ↓
Square x
    ↓
Divide exponent by 2
```

This reduces the exponent very quickly.

---

# 🚀 Approach — Binary Exponentiation

## Step 1: Handle Negative Exponents

A negative exponent can be rewritten as:

```text
x^(-n) = 1 / x^n
```

For example:

```text
2^(-3)
= 1 / 2^3
= 1 / 8
= 0.125
```

So when the exponent is negative:

```java
x = 1 / x;
exp = -exp;
```

We also use `long` for the exponent because `Integer.MIN_VALUE` cannot be converted to its positive value while remaining an `int`.

```text
Integer.MIN_VALUE = -2147483648
```

but:

```text
2147483648
```

is outside the `int` range.

---

# Step 2: Handle an Odd Exponent

Suppose:

```text
x = 4
exp = 5
```

Since `5` is odd:

```text
4^5 = 4 × 4^4
```

So we multiply the current `x` into our answer:

```java
ans = ans * x;
```

Then reduce the exponent:

```java
exp = exp - 1;
```

Now:

```text
5 → 4
```

The exponent has become even.

---

# Step 3: Handle an Even Exponent

Suppose:

```text
x = 4
exp = 4
```

We can write:

```text
4^4 = (4²)^2
```

So we square the base:

```java
x = x * x;
```

and halve the exponent:

```java
exp = exp / 2;
```

Now:

```text
x = 16
exp = 2
```

The value remains mathematically equivalent:

```text
4^4 = 16^2
```

---

# 📝 Dry Run

Consider:

```text
x = 2
n = 10
```

We want:

```text
2^10 = 1024
```

Initially:

```text
x = 2
exp = 10
ans = 1
```

### Iteration 1

```text
exp = 10 → EVEN
```

Square `x`:

```text
x = 2 × 2
  = 4
```

Divide the exponent:

```text
exp = 10 / 2
    = 5
```

Now:

```text
x = 4
exp = 5
ans = 1
```

---

### Iteration 2

```text
exp = 5 → ODD
```

Multiply `x` into the answer:

```text
ans = 1 × 4
    = 4
```

Reduce the exponent:

```text
exp = 5 - 1
    = 4
```

Now:

```text
x = 4
exp = 4
ans = 4
```

---

### Iteration 3

```text
exp = 4 → EVEN
```

Square `x`:

```text
x = 4 × 4
  = 16
```

Divide the exponent:

```text
exp = 4 / 2
    = 2
```

Now:

```text
x = 16
exp = 2
ans = 4
```

---

### Iteration 4

```text
exp = 2 → EVEN
```

Square `x`:

```text
x = 16 × 16
  = 256
```

Divide the exponent:

```text
exp = 2 / 2
    = 1
```

Now:

```text
x = 256
exp = 1
ans = 4
```

---

### Iteration 5

```text
exp = 1 → ODD
```

Multiply `x` into the answer:

```text
ans = 4 × 256
    = 1024
```

Then:

```text
exp = 1 - 1
    = 0
```

The loop ends.

Therefore:

```text
Answer = 1024
```

---

# 📊 Complexity

- **Time Complexity:** `O(log |n|)`
- **Space Complexity:** `O(1)`

---

# 🎯 Key Takeaway

The entire approach can be remembered with two rules:

```text
ODD exponent
    ↓
ans = ans × x
exp = exp - 1
```

```text
EVEN exponent
    ↓
x = x × x
exp = exp / 2
```

For negative exponents:

```text
x^(-n) = 1 / x^n
```

The main advantage is that instead of reducing the exponent one by one, we **halve it whenever it is even**.

Therefore:

```text
Normal multiplication
O(n)
    ↓
Binary Exponentiation
O(log n)
```

> **Binary Exponentiation reduces the exponent by half whenever possible, allowing us to calculate `x^n` in logarithmic time using constant extra space.**

---

Happy Coding! 🚀✨
