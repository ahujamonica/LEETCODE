# 🚀 435. Non-overlapping Intervals | 🔥 Greedy Interval Scheduling

> 📂 Topic: Intervals / Greedy  
> 🎯 Pattern: Activity Selection  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n log n) | 💾 Space: O(1) Auxiliary

---

## 🔗 Problem

[LeetCode 435 — Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

---

## 🧠 Intuition

We need to remove the **minimum number of intervals** so that the remaining intervals do not overlap.

Instead of thinking about which intervals to remove, we can think about:

> **What is the maximum number of intervals we can keep?**

The greedy strategy is to always choose the interval that **finishes earliest**.

Why?

An interval that finishes earlier leaves more room for the intervals that come after it.

---

## 🚀 Approach

### 1️⃣ Sort by End Time

Each interval is represented as:

```text
[start, end]
```

So:

```java
intervals[i][0] → start
intervals[i][1] → end
```

We sort all intervals by their end time:

```java
Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
```

---

### 2️⃣ Keep the First Interval

After sorting, the first interval has the earliest ending time.

```java
int count = 1;
int previous_interval = 0;
```

Here:

- `count` = number of intervals we have kept
- `previous_interval` = index of the last interval we kept

---

### 3️⃣ Check for Overlap

For every remaining interval, check:

```java
intervals[i][0] >= intervals[previous_interval][1]
```

This means:

```text
Current interval START >= Previous interval END
```

If true, the intervals don't overlap, so we keep the current interval.

```java
previous_interval = i;
count++;
```

If false, we skip the current interval.

---

### 4️⃣ Find Number of Intervals to Remove

If there are `n` total intervals and we can keep `count` intervals:

```text
Intervals to remove = n - count
```

Therefore:

```java
return intervals.length - count;
```

---

## 💡 Example

Input:

```text
[[1,2], [2,3], [3,4], [1,3]]
```

After sorting by end time:

```text
[1,2]
[2,3]
[1,3]
[3,4]
```

We keep:

```text
[1,2] → [2,3] → [3,4]
```

`[1,3]` overlaps with `[2,3]`, so we skip it.

Therefore:

```text
Total intervals = 4
Kept intervals  = 3

Removed = 4 - 3 = 1
```

---

## 🔍 Dry Run

```text
Intervals:

[1,2] → [2,3] → [1,3] → [3,4]
```

### Start

```text
count = 1
previous_interval = 0

Previous:
[1,2]
```

### i = 1

Current:

```text
[2,3]
```

Check:

```text
2 >= 2
```

✅ No overlap.

```text
count = 2
previous_interval = 1
```

### i = 2

Current:

```text
[1,3]
```

Check:

```text
1 >= 3
```

❌ Overlap.

Skip it.

### i = 3

Current:

```text
[3,4]
```

Check:

```text
3 >= 3
```

✅ No overlap.

```text
count = 3
```

Final:

```text
4 - 3 = 1
```

Answer:

```text
1
```

---

## 💻 Java Solution

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals.length == 0) {
            return 0;
        }

        // Sort intervals by end time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int previous_interval = 0;

        for (int i = 1; i < intervals.length; i++) {

            // Current start >= previous end
            if (intervals[i][0] >= intervals[previous_interval][1]) {
                previous_interval = i;
                count++;
            }
        }

        // Total intervals - intervals kept
        return intervals.length - count;
    }
}
```

---

## 🎯 Why Greedy Works

Suppose we have:

```text
[1,5]
[2,3]
```

If we choose:

```text
[1,5]
```

we cannot choose intervals that start before `5`.

But if we choose:

```text
[2,3]
```

we become free at time `3`.

So choosing the interval with the **earliest end time** gives us the maximum possible room for future intervals.

This is the same greedy principle used in **Activity Selection / N Meetings in One Room**.

---

## 🔑 Key Takeaway

Remember this pattern:

```text
Sort by END time
       ↓
Keep the earliest finishing interval
       ↓
Check:
current start >= previous end
       ↓
YES → Keep
NO  → Skip
       ↓
Total - Kept = Removed
```

The most important condition is:

```java
intervals[i][0] >= intervals[previous_interval][1]
```

Where:

```text
[0] → Start
[1] → End
```

---

## 📊 Complexity

### Time Complexity

```text
O(n log n)
```

Sorting takes `O(n log n)` and the traversal takes `O(n)`.

Overall:

```text
O(n log n)
```

### Space Complexity

```text
O(1) Auxiliary
```

We only use a few variables apart from the sorting implementation's internal space.

---

## 🏆 Final Complexity

```text
Time  : O(n log n)
Space : O(1) Auxiliary
```

> ⭐ **If this greedy approach helped you understand interval scheduling, please upvote! 🙌**
