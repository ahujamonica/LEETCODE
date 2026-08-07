# 🚀 56. Merge Intervals

> 📂 Topic: Arrays, Sorting
> 🎯 Pattern: Intervals, Greedy
> ⭐ Difficulty: Medium
> ⏱️ Time: O(n log n) | 💾 Space: O(n)

---

# 🔗 Problem Link

https://leetcode.com/problems/merge-intervals/

---

# 🧠 Pattern Recognition

Whenever the problem says:

- Merge overlapping intervals
- Combine meeting times
- Employee schedules
- Time ranges
- Calendar bookings

Think:

```
Sort by starting time

↓

Merge overlapping intervals
```

This is one of the most common **Intervals Pattern** questions asked in coding interviews.

---

# 💡 Intuition

The goal is to merge all overlapping intervals.

Example:

```
[1,3]
[2,6]
```

Since

```
2 <= 3
```

they overlap.

Merged interval becomes

```
[1,6]
```

The biggest challenge is efficiently determining which intervals overlap.

Without sorting, every interval could overlap with any other interval, making the problem much harder.

Once the intervals are sorted by their **starting time**, all overlapping intervals become adjacent, allowing us to merge them in a single traversal.

---

# 📈 Evolution of the Solution

```
Compare every interval
with every other interval

↓

Too many comparisons

↓

Sort intervals

↓

Current interval only needs
to be compared with the
last merged interval

↓

Single traversal after sorting
```

---

# 🐢 Approach 1 — Brute Force

## Idea

1. Sort all intervals based on their starting point.
2. Pick one interval.
3. Keep checking future intervals until overlap stops.
4. Store the merged interval.
5. Skip intervals that are already merged.

Although correct, this repeatedly scans future intervals, leading to unnecessary work.

---

## Complexity

**Time**

```
O(n²)
```

**Space**

```
O(n)
```

---

# 🚀 Approach 2 — Optimal

## Key Observation

After sorting,

```
All overlapping intervals
become adjacent.
```

Instead of comparing every interval with every other interval,

we only compare the current interval with the **last merged interval**.

If

```
current.start <= merged.end
```

they overlap.

Simply extend the merged interval.

Otherwise,

start a new merged interval.

---

# 📝 Dry Run

Input

```
[
 [1,3],
 [2,6],
 [8,10],
 [15,18]
]
```

---

### Step 1

Sort by start time.

Already sorted.

```
[
 [1,3],
 [2,6],
 [8,10],
 [15,18]
]
```

---

### Step 2

Current merged interval

```
[1,3]
```

Answer

```
[
 [1,3]
]
```

---

### Step 3

Current interval

```
[2,6]
```

Check

```
2 <= 3
```

Overlap.

Extend

```
max(3,6)

↓

6
```

Answer

```
[
 [1,6]
]
```

---

### Step 4

Current interval

```
[8,10]
```

Check

```
8 <= 6
```

False.

No overlap.

Start a new merged interval.

Answer

```
[
 [1,6],
 [8,10]
]
```

---

### Step 5

Current interval

```
[15,18]
```

Check

```
15 <= 10
```

False.

Answer

```
[
 [1,6],
 [8,10],
 [15,18]
]
```

Done.

---

# 🤔 Things That Confused Me

---

## ❓1. Why do we sort by the first element?

Suppose the intervals are

```
[15,18]

[2,6]

[2,10]

[1,3]
```

After sorting,

```
[1,3]

[2,6]

[2,10]

[15,18]
```

Now every overlapping interval appears together.

Without sorting,

we wouldn't know which interval should be merged first.

Sorting converts the problem into a simple left-to-right traversal.

---

## ❓2. What does

```java
Arrays.sort(intervals,
    (a,b)->Integer.compare(a[0],b[0]));
```

mean?

Java compares **two intervals at a time**.

Example

```
a = [15,18]

b = [2,6]
```

It compares

```
15

vs

2
```

Since

```
15 > 2
```

Java places

```
[2,6]
```

before

```
[15,18]
```

Notice that **only the starting values are compared**.

---

## ❓3. What if two intervals have the same start?

Example

```
[2,6]

[2,10]
```

or

```
[2,10]

[2,6]
```

Both produce the same merged interval.

```
[2,10]
```

because

```
Merge

↓

[2,max(6,10)]
```

The order doesn't affect the final answer.

---

## ❓4. Why is

```java
newInterval
```

an

```java
int[]
```

instead of

```java
int[][]
```

Because

```
newInterval
```

stores only **one interval**.

Example

```
[1,6]
```

Its type is therefore

```java
int[]
```

while

```
intervals
```

stores many intervals.

```
[
 [1,3],
 [2,6],
 [8,10]
]
```

whose type is

```java
int[][]
```

---

## ❓5. Why is

```java
List<int[]>
```

used instead of

```java
int[][]
```

We don't know beforehand how many merged intervals the answer will contain.

Example

Input

```
10 intervals
```

Output could be

```
1 interval
```

or

```
10 intervals
```

Arrays in Java have fixed size.

Lists grow dynamically.

That's why we build the answer using

```java
List<int[]>
```

and finally convert it into

```java
int[][]
```

---

## ❓6. Why

```java
int[] newInterval = intervals[0];
```

instead of

```java
new int[]{...}
```

Because we don't need to create a copy.

`newInterval` simply points to

```
intervals[0]
```

Memory

```
intervals

↓

[1,3]

↑

newInterval
```

Both refer to the same array.

---

## ❓7. Why does

```java
newInterval[1]
```

mean

```
3
```

and not

```
[2,6]
```

Because

```
newInterval
```

is already a **single interval**.

Suppose

```
newInterval

↓

[1,3]
```

Then

```
newInterval[0]

↓

1
```

```
newInterval[1]

↓

3
```

Only

```java
intervals[1]
```

would return

```
[2,6]
```

because

```
intervals
```

is a 2D array.

---

## ❓8. Why don't we do

```java
result.add(newInterval);
```

inside the overlap case?

Because the merged interval is **already inside the list**.

Initially

```
result

↓

[1,3]
```

Both

```
result

and

newInterval
```

point to the same array.

Updating

```java
newInterval[1]=6;
```

changes

```
[1,3]

↓

[1,6]
```

Result automatically reflects the update.

No need to add it again.

---

## ❓9. Then why do we call

```java
result.add(newInterval);
```

inside the else block?

Because

```java
newInterval = intervals[i];
```

doesn't modify the old interval.

It changes **where the reference points**.

Before

```
newInterval

↓

[1,6]
```

After

```
newInterval

↓

[8,10]
```

Result still points to

```
[1,6]
```

Therefore,

we must explicitly add

```
[8,10]
```

into the list.

---

## ❓10. Why

```java
result.toArray(new int[result.size()][]);
```

LeetCode expects

```java
int[][]
```

But we built

```java
List<int[]>
```

This line converts

```
List<int[]>

↓

int[][]
```

so that it matches the required return type.

---

# ❌ Common Mistakes

- Forgetting to sort first.
- Comparing every interval with every previous interval.
- Creating unnecessary copies of intervals.
- Using `int[][]` instead of a dynamic `List<int[]>`.
- Forgetting to convert the list back to `int[][]`.

---

# ⏱️ Complexity Analysis

| Approach | Time | Space |
|----------|------|------|
| Brute Force | O(n²) | O(n) |
| Optimal | O(n log n) | O(n) |

---

# 🎯 Takeaway

- Sorting is the key that makes interval problems manageable.
- After sorting, you only need to compare the current interval with the **last merged interval**.
- Understanding the difference between **modifying an object** and **changing a reference** is crucial in Java.
- `List<int[]>` is used because the number of merged intervals is unknown until processing is complete.
- Think of `newInterval` as **the interval currently being built**. As long as intervals overlap, keep extending it. When they don't, start a new merged interval.



---

### ⭐ Enjoyed this explanation?


Happy Coding! 🚀✨
