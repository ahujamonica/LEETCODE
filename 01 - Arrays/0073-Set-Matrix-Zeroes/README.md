# 🚀 73. Set Matrix Zeroes

> 📂 Topic: Arrays, Matrix  
> 🎯 Pattern: Matrix Traversal, In-place Marking  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(m × n) | 💾 Space: O(1)

---

# 🔗 Problem Link

https://leetcode.com/problems/set-matrix-zeroes/

---

# 🧠 Pattern Recognition

Whenever the problem says

- "Entire row"
- "Entire column"
- "In-place"

think

```
Markers

↓

Can I store them somewhere?

↓

Can I reuse the matrix itself?
```

This is a classic **Space Optimization** problem.

---

# 💡 Intuition

The biggest mistake beginners make is:

```
Find a zero

↓

Immediately convert its row & column to zero
```

This is **incorrect**.

Why?

Because the newly created zeros will again trigger more row/column updates.

Example

```
1 1 1

1 0 1

1 1 1
```

If we immediately convert row 1 and column 1,

new zeros appear,

and those zeros incorrectly start affecting other rows and columns.

Therefore,

we first identify **which rows and columns need to become zero**,

and only then modify the matrix.

This naturally leads to three approaches.

---

# 🐢 Approach 1 — Brute Force

## Idea

Whenever a zero is found,

mark its row and column using a temporary value (`-1`).

Why `-1`?

Because changing values directly to `0` would create new zeros during traversal.

After the traversal,

replace every `-1` with `0`.

---

### Complexity

Time

```
O((m×n) × (m+n))
```

Space

```
O(1)
```

---

# ⚡ Approach 2 — Better

## Idea

Instead of repeatedly traversing rows and columns,

maintain two marker arrays.

```
row[]

col[]
```

Whenever

```
matrix[i][j]==0
```

mark

```
row[i]=1

col[j]=1
```

Later,

for every cell,

if

```
row[i]==1

OR

col[j]==1
```

make it zero.

---

### Complexity

Time

```
O(m×n)
```

Space

```
O(m+n)
```

---

# 🚀 Approach 3 — Optimized

## Key Observation

In the previous solution,

the extra arrays were only storing markers.

Instead of allocating

```
row[]

col[]
```

we can reuse the matrix itself.

Use

```
matrix[i][0]
```

to mark **rows**

and

```
matrix[0][j]
```

to mark **columns**.

This removes the need for extra arrays.

---

# 🔍 Why does this work?

Every row has exactly one cell in the first column.

```
matrix[0][0]

matrix[1][0]

matrix[2][0]
```

Therefore

```
matrix[i][0]=0
```

means

> Row i should become zero later.

Similarly,

every column has exactly one cell in the first row.

```
matrix[0][0]

matrix[0][1]

matrix[0][2]
```

Therefore

```
matrix[0][j]=0
```

means

> Column j should become zero later.

---

# 📝 Dry Run

Suppose

```
1 2 3

4 0 6

7 8 9
```

Found zero

```
(1,1)
```

Mark

```
matrix[1][0]=0

matrix[0][1]=0
```

Matrix becomes

```
1 0 3

0 0 6

7 8 9
```

Notice

First column now stores

```
Which rows become zero.
```

First row now stores

```
Which columns become zero.
```

After processing the inner matrix,

the answer becomes

```
1 0 3

0 0 0

7 0 9
```

Finally,

process the first row and first column if required.

---

# 🤔 Things That Confused Me

### ❓1. Why do we need `firstRow` and `firstCol`?

The first row and first column now have **two responsibilities**.

1. They are part of the original matrix.
2. They also act as marker arrays.

While storing markers,

their original information gets overwritten.

Therefore,

before modifying anything,

we store

```
firstRow

↓

Did the original first row contain any zero?

firstCol

↓

Did the original first column contain any zero?
```

These variables are **not markers**.

They only preserve the original state.

---

### ❓2. Why does

```java
matrix[i][0]
```

represent a **row**?

Because

```
i
```

is the **row index**.

Every row has exactly one first-column cell.

```
Row 0 → matrix[0][0]

Row 1 → matrix[1][0]

Row 2 → matrix[2][0]
```

Setting

```
matrix[i][0]=0
```

marks

> Row i.

---

### ❓3. Why does

```java
matrix[0][j]
```

represent a **column**?

Because

```
j
```

is the column index.

Every column has exactly one first-row cell.

```
Column 0 → matrix[0][0]

Column 1 → matrix[0][1]

Column 2 → matrix[0][2]
```

Setting

```
matrix[0][j]=0
```

marks

> Column j.

---

### ❓4. Why do we zero the first row using

```java
for(int j...)
```

instead of

```java
for(int i...)
```

When zeroing the **first row**,

the row index remains fixed.

```
matrix[0][0]

matrix[0][1]

matrix[0][2]
```

Only the **column changes**.

Hence,

we iterate using

```java
j
```

Similarly,

for the first column,

the column remains fixed,

and only the row changes.

Hence,

we iterate using

```java
i
```

---

### ❓5. Why can't we simply use

```
matrix[0][0]
```

instead of `firstRow` and `firstCol`?

Because

```
matrix[0][0]
```

belongs to both

- first row
- first column

It cannot tell

whether

- the first row originally had a zero,
- the first column originally had a zero,
- or both.

Hence,

we separately maintain

```
firstRow

firstCol
```

---

### ❓6. Why don't we update the first row and first column immediately?

Because they are acting as **marker arrays**.

Updating them too early would destroy the marker information needed to process the remaining cells.

Hence,

they are processed at the very end.

---

# ⏱️ Complexity Analysis

## Brute Force

Time

```
O((m×n)(m+n))
```

Space

```
O(1)
```

---

## Better

Time

```
O(m×n)
```

Space

```
O(m+n)
```

---

## Optimal

Time

```
O(m×n)
```

Space

```
O(1)
```

---

