
# 🍪 455. Assign Cookies | 🎯 Greedy + Two Pointers

> 📂 Topic: Array / Greedy  
> 🎯 Pattern: Greedy + Two Pointers  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n log n + m log m) | 💾 Space: O(1) Auxiliary

---

## 🔗 Problem

[LeetCode 455 — Assign Cookies](https://leetcode.com/problems/assign-cookies/)

---

## 🧠 Intuition

Each child has a **greed factor** `g[i]`, which represents the minimum cookie size required to satisfy them.

Each cookie has a size `s[i]`.

A child is satisfied when:

```text
cookie size >= child's greed factor
```

To maximize the number of satisfied children, we use a greedy strategy:

> **Always try to satisfy the least greedy child with the smallest possible cookie.**

This leaves larger cookies available for children with higher greed factors.

---

## 🚀 Approach

### 1️⃣ Sort Both Arrays

```java
Arrays.sort(g);
Arrays.sort(s);
```

After sorting:

```text
Children:  1  2  3
Cookies:   1  1  2  3
```

We can process both from smallest to largest.

---

### 2️⃣ Use Two Pointers

We maintain:

```text
children → current child
cookie   → current cookie
```

Initially:

```java
int children = 0;
int cookie = 0;
```

---

### 3️⃣ Check if the Cookie Can Satisfy the Child

```java
if (s[cookie] >= g[children])
```

If the cookie is large enough:

```text
Cookie ≥ Greed
```

the child is satisfied.

So:

```java
children++;
```

We then move to the next child.

Regardless of whether the cookie was used, we move to the next cookie:

```java
cookie++;
```

If the cookie is too small, we simply discard it and try the next larger cookie.

---

## 💡 Example

```text
g = [1, 2, 3]
s = [1, 1]
```

After sorting:

```text
Children:  1  2  3
Cookies:   1  1
```

### Step 1

```text
cookie = 1
child = 1

1 >= 1 → ✅
```

Child `1` is satisfied.

```text
children = 1
cookie = 1
```

### Step 2

```text
cookie = 1
child = 2

1 >= 2 → ❌
```

Cookie is too small, so we discard it.

No more cookies are available.

Therefore:

```text
Answer = 1
```

---

## 💻 Java Solution

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int children = 0;
        int cookie = 0;

        while (cookie < s.length && children < g.length) {

            if (s[cookie] >= g[children]) {
                children++;
            }

            cookie++;
        }

        return children;
    }
}
```

---

## 🎯 Key Idea

```text
Sort both arrays
       ↓
Smallest cookie → Smallest child
       ↓
Can satisfy?
   ↙         ↘
 YES         NO
  ↓           ↓
Child++    Discard cookie
  ↓           ↓
Next child  Next cookie
```

The greedy strategy works because we don't waste a large cookie on a child who could be satisfied with a smaller one.

---

## 🔑 Important Condition

```java
s[cookie] >= g[children]
```

Where:

```text
s[cookie] → current cookie size
g[children] → current child's greed factor
```

If:

```text
cookie size >= greed factor
```

the child can be satisfied.

---

## 📊 Complexity

### Time Complexity

```text
O(n log n + m log m)
```

Sorting the children and cookies takes:

```text
O(n log n) + O(m log m)
```

The two-pointer traversal takes:

```text
O(n + m)
```

So the overall complexity is:

```text
O(n log n + m log m)
```

### Space Complexity

```text
O(1) Auxiliary
```

Only a few variables are used apart from the sorting implementation's internal space.

---

## 🏆 Final Complexity

```text
Time  : O(n log n + m log m)
Space : O(1) Auxiliary
```

---

## 🎯 Key Takeaway

> **Sort both arrays and use the smallest available cookie to satisfy the least greedy child. If the cookie is too small, discard it and try the next one.**

```text
g → Child Greed
s → Cookie Size

s[cookie] >= g[children]
          ↓
      Child satisfied
```

> ⭐ **If this greedy + two-pointer approach helped you, please upvote! 🙌**
