# 🚀 48. Rotate Image

> 📂 Topic: Arrays, Matrix  
> 🎯 Pattern: Matrix Manipulation, Transpose, Two Pointers  
> 🏷️ Named Pattern: Transpose + Reverse  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n²) | 💾 Space: O(1)

---

# 🔗 Problem Link

https://leetcode.com/problems/rotate-image/

---

# 🧠 Pattern Recognition

Whenever the problem mentions:

- Rotate matrix by **90°**
- Rotate image clockwise
- In-place matrix transformation
- Square matrix (`n × n`)

Think:

```
Can I perform the rotation
without using another matrix?

↓

Transpose

↓

Reverse
```

This is one of the most common **Matrix Transformation** patterns asked in interviews.

---

# 💡 Intuition

The problem asks us to rotate an **n × n matrix** by **90° clockwise**.

The most intuitive solution is:

- Create another matrix.
- Place every element in its new rotated position.
- Copy the new matrix back.

Although simple, it requires an additional **O(n²)** space.

Can we rotate the matrix **in-place**?

Yes.

A beautiful observation is:

```
90° Clockwise Rotation

=

Transpose

+

Reverse Every Row
```

This transforms the matrix without requiring any extra matrix.

---

# 📈 Evolution of the Solution

```
Create another matrix

↓

Store rotated elements

↓

Copy back

O(n²) Space

↓

Observe Matrix Property

↓

Transpose

+

Reverse Every Row

↓

O(1) Extra Space
```

---

# 🐢 Approach 1 — Brute Force (Extra Matrix)

## Idea

Create another `n × n` matrix.

For every element,

```
matrix[i][j]
```

place it at

```
rotated[j][n-1-i]
```

Finally,

copy the rotated matrix back into the original matrix.

---

## Why does

```
matrix[i][j]

↓

rotated[j][n-1-i]
```

work?

Let's understand using a 3×3 matrix.

Original

```
1 2 3
4 5 6
7 8 9
```

Take

```
matrix[0][0]
```

which is

```
1
```

After rotating,

it becomes

```
rotated[0][2]
```

Similarly,

```
matrix[0][1]

↓

rotated[1][2]
```

```
matrix[2][0]

↓

rotated[0][0]
```

Eventually,

```
7 4 1
8 5 2
9 6 3
```

---

## Complexity

**Time**

```
O(n²)
```

**Space**

```
O(n²)
```

---

# 🚀 Approach 2 — Optimal (Transpose + Reverse)

## Key Observation

Instead of creating another matrix,

perform two operations.

```
Original

↓

Transpose

↓

Reverse Every Row
```

---

# Step 1 — Transpose

Transpose means

```
matrix[i][j]

↓

matrix[j][i]
```

Example

```
1 2 3
4 5 6
7 8 9
```

After transpose

```
1 4 7
2 5 8
3 6 9
```

Rows become columns.

Columns become rows.

---

## Why don't we traverse the entire matrix?

This is one of the most common doubts.

Suppose we write

```java
for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){

        swap(matrix[i][j], matrix[j][i]);
    }
}
```

Let's dry run.

Original

```
1 2
3 4
```

First,

swap

```
(0,1)

↓

(1,0)
```

Matrix becomes

```
1 3
2 4
```

Perfect.

But later,

we again visit

```
(1,0)

↓

(0,1)
```

and swap again.

Matrix becomes

```
1 2
3 4
```

We just undid our work!

Every pair gets swapped twice.

---

## Solution

Swap every pair **exactly once**.

That means

visit only

```
Upper Triangle

or

Lower Triangle
```

Never both.

Hence

```java
for(int i=0;i<n-1;i++){
    for(int j=i+1;j<n;j++){

        swap(...)
    }
}
```

or

```java
for(int i=0;i<n;i++){
    for(int j=0;j<i;j++){

        swap(...)
    }
}
```

Both are correct.

---

## Why

```java
i < n-1
```

instead of

```java
i < n
```

Suppose

```
n=4
```

Rows

```
0
1
2
3
```

If

```
i=3
```

then

```
j=i+1

↓

4
```

There is no

```
column 4
```

Therefore,

the inner loop never executes.

So,

we stop at

```
n-2
```

to avoid one unnecessary iteration.

---

# Step 2 — Reverse Every Row

Now we have

```
1 4 7
2 5 8
3 6 9
```

Reverse every row.

```
1 4 7

↓

7 4 1
```

```
2 5 8

↓

8 5 2
```

```
3 6 9

↓

9 6 3
```

Done.

---

# 📝 Dry Run

Input

```
1 2 3
4 5 6
7 8 9
```

---

### After Transpose

```
1 4 7
2 5 8
3 6 9
```

---

### After Row Reversal

```
7 4 1
8 5 2
9 6 3
```

Answer obtained.

---

# 🤔 Things That Confused Me

## ❓1. Why don't we traverse the whole matrix during transpose?

Because

```
(i,j)

↓

(j,i)
```

would later become

```
(j,i)

↓

(i,j)
```

The same pair gets swapped twice.

Hence,

we visit only one triangular half.

---

## ❓2. Upper triangle or lower triangle?

Both work.

You only need to swap every off-diagonal pair exactly once.

---

## ❓3. Why

```java
i < n-1
```

instead of

```java
i < n
```

The last row has no elements above the diagonal.

The inner loop would never execute.

Stopping early simply avoids one useless iteration.

---

## ❓4. Why reverse rows instead of columns?

After transpose,

rows become columns.

Reversing every row performs a **90° clockwise rotation**.

If instead you reverse every **column** after transpose,

you obtain a **90° anti-clockwise rotation**.

---

# ❌ Common Mistakes

- Traversing the entire matrix while transposing.
- Swapping diagonal elements unnecessarily.
- Forgetting to reverse every row after transpose.
- Reversing columns instead of rows (gives anti-clockwise rotation).
- Using an extra matrix when an in-place solution is expected.

---

# ⏱️ Complexity Analysis

| Approach | Time | Space |
|----------|------|------|
| Extra Matrix | O(n²) | O(n²) |
| Transpose + Reverse | O(n²) | O(1) |

---

# 💼 Interview Notes

Remember this identity:

```
90° Clockwise

=

Transpose

+

Reverse Rows
```

Similarly,

```
90° Anti-Clockwise

=

Transpose

+

Reverse Columns
```

This pattern appears frequently in matrix transformation problems.



Happy Coding! 🚀✨
