# 🌟 118. Pascal's Triangle

> **Difficulty:** Easy  
> **Topics:** Arrays, Dynamic Programming (Simulation)

---

## 🔗 Problem Link

https://leetcode.com/problems/pascals-triangle/

---

# 🧠 Pattern Recognition

### When to think of this approach?

If the problem says:

- Build rows one by one
- Current row depends on the previous row
- Triangle/Grid construction

➡️ Think of **Dynamic Construction** (build using previously computed results).

---

# 💡 Intuition

Pascal's Triangle follows a very simple pattern.

- Every row starts with **1**.
- Every row ends with **1**.
- Every middle element is the sum of the **two adjacent elements** from the previous row.

For example,

```text
            1
          1   1
        1   2   1
      1   3   3   1
    1   4   6   4   1
```

Observe,

```
2 = 1 + 1

3 = 1 + 2

3 = 2 + 1

6 = 3 + 3
```

Instead of calculating every value independently, we simply use the row already generated.

---

# 🚀 Approach

### Step 1

Create an empty answer list.

```java
List<List<Integer>> result = new ArrayList<>();
```

---

### Step 2

The first row is always

```
[1]
```

Add it directly.

---

### Step 3

Generate one row at a time.

For every row,

- Add the starting `1`
- Compute all middle elements using the previous row
- Add the ending `1`
- Append the row to the answer

---

## 🔍 Key Observation

Suppose the previous row is

```
1   3   3   1
```

The next row becomes

```
1
```

Middle elements:

```
1 + 3 = 4

3 + 3 = 6

3 + 1 = 4
```

Finally,

```
1 4 6 4 1
```

The formula is simply

```java
currentRow.add(previousRow.get(j) + previousRow.get(j + 1));
```

---

# 📝 Dry Run

## numRows = 5

Initially

```
result = []
```

---

### Row 1

```
[1]
```

---

### Row 2

Previous Row

```
1
```

Current Row

```
1 1
```

---

### Row 3

Previous Row

```
1 1
```

Current Row

```
1

1+1 = 2

1
```

Result

```
1 2 1
```

---

### Row 4

Previous Row

```
1 2 1
```

Current Row

```
1

1+2 = 3

2+1 = 3

1
```

Result

```
1 3 3 1
```

---

### Row 5

Previous Row

```
1 3 3 1
```

Current Row

```
1

1+3 = 4

3+3 = 6

3+1 = 4

1
```

Result

```
1 4 6 4 1
```

---

# ⏱️ Complexity Analysis

### Time Complexity

Each row contains one more element than the previous row.

Total elements generated

```
1 + 2 + 3 + ... + n
```

which equals

```
n(n+1)/2
```

Therefore,

**Time Complexity:** `O(n²)`

---

### Space Complexity

The answer itself stores

```
1 + 2 + ... + n
```

elements.

Hence,

**Space Complexity:** `O(n²)`

---

# 🤔 Doubts I Had While Solving

### ❓1. Why do we write

```java
List<Integer> prevRow = result.get(i - 1);
```

instead of

```java
List<Integer> prevRow = new ArrayList<>();
```

#### Explanation

We are **not creating a new previous row**.
Instead, we need to **access the row that has already been generated**, because every row of Pascal's Triangle is built using the row immediately above it.

Suppose

```text
result =
[
 [1],
 [1,1],
 [1,2,1]
]
```

While generating the 4th row (`i = 3`),

```java
result.get(i - 1)
```

returns

```text
[1,2,1]
```

which becomes our previous row.

If we instead wrote

```java
new ArrayList<>()
```

we would simply create an empty list.

Trying to access

```java
prevRow.get(0)
```

would immediately result in an `IndexOutOfBoundsException`.

---

### ❓2. Why don't we create every row from scratch?

Every row depends on the previous row.

For example,

```
1 2 1
```

produces

```
1 3 3 1
```

because

```
3 = 1 + 2

3 = 2 + 1
```

Instead of recomputing values, we reuse the row that is already available.

This reduces unnecessary work and makes the implementation much simpler.

---

### ❓3. Why is the loop

```java
for(int j = 0; j < i - 1; j++)
```

and not

```java
j < i
```

or

```java
j <= i
```

The first and last element of every row are always **1**.

Therefore, the loop only computes the **middle elements**.

For the `i-th` row,

the number of middle elements is

```
i - 1
```

Hence,

```java
j < i - 1
```

correctly iterates over only the adjacent pairs in the previous row.

---

### ❓4. Why do we use

```java
List<List<Integer>>
```

instead of a 2D array?

Each row of Pascal's Triangle has a **different length**.

```
1

1 1

1 2 1

1 3 3 1
```

Since the rows are not of equal size,

a `List<List<Integer>>` is more suitable than a fixed-size 2D array.

---

# 💡 What I Learned

- Every row can be built directly from the previous row.
- Store the answer as you generate it instead of calculating everything at once.
- `result.get(i - 1)` returns a reference to the already generated row—it does **not** create a copy.
- When a problem says *"build the current state using the previous state"*, think of **Dynamic Construction**.
---


# 💼 Interview Notes

When you hear

> "Build the current row using the previous row"

immediately think

> **Dynamic Construction**

No recursion or DP table is required here.

Simply reuse the row already present in the answer.

