# 🔥 239. Sliding Window Maximum | Monotonic Deque Trick

🔗 **LeetCode:** https://leetcode.com/problems/sliding-window-maximum/

## 🧠 Intuition

We need to find the maximum element in every sliding window of size `k`.

For example:

```text
nums = [1,3,-1,-3,5,3,6,7]
k = 3
```

The windows are:

```text
[1,3,-1]  → 3
[3,-1,-3] → 3
[-1,-3,5] → 5
[-3,5,3]  → 5
[5,3,6]   → 6
[3,6,7]   → 7
```

So the answer is:

```text
[3,3,5,5,6,7]
```

A brute-force approach would find the maximum of every window separately, which takes `O(n * k)` time.

To optimize this, we use a **Deque**.

The deque stores **indices**, not values.

We maintain the deque so that the values corresponding to those indices are in decreasing order.

```text
Front → largest value
        ↓
        smaller values
        ↓
Back
```

Therefore, the front of the deque always contains the index of the current maximum.

---

## 💡 Approach

There are 3 main things we do:

1. Remove expired indices from the **front**
2. Remove smaller/equal elements from the **back**
3. The **front** gives the current maximum

---

## 1️⃣ Build the First Window

First, process the first `k` elements:

```java
for(int i = 0; i < k; i++)
```

For:

```text
nums = [1,3,-1,-3,5,3,6,7]
k = 3
```

the first window is:

```text
[1,3,-1]
```

When processing a new element, remove smaller/equal elements from the back:

```java
while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
    deque.pollLast();
}
```

For example, when `3` arrives:

```text
1 <= 3
```

So `1` is removed because it can never become the maximum while `3` is present.

Then add the current index:

```java
deque.offerLast(i);
```

After processing the first window:

```text
deque indices → [1,2]
deque values  → [3,-1]
```

The front contains index `1`.

Therefore:

```text
nums[1] = 3
```

So we store the maximum of the first window:

```java
result[0] = nums[deque.peekFirst()];
```

---

## 2️⃣ Process the Remaining Windows

Now we process the remaining elements:

```java
for(int i = k; i < n; i++)
```

Why do we start from `i = k`?

Because the first `k` elements have already been processed.

If:

```text
k = 3
```

the first window contains:

```text
indices 0,1,2
```

So the next new element is:

```text
index 3
```

Therefore:

```java
for(int i = k; i < n; i++)
```

starts from index `3`.

---

## 🪟 How Does the Window Slide?

There is no actual `window` variable in the code.

The window is represented indirectly using indices.

For `k = 3`:

```text
First window:
[0 1 2]

Then:
[1 2 3]

Then:
[2 3 4]

Then:
[3 4 5]

Then:
[4 5 6]

Then:
[5 6 7]
```

The window moves forward because `i` increases.

The expired-index condition removes elements that are no longer inside the current window.

---

## 3️⃣ Remove Expired Elements

We use:

```java
if(deque.peekFirst() <= i-k){
    deque.pollFirst();
}
```

The deque stores indices.

If the index at the front has gone outside the current window, we remove it.

For example:

```text
k = 3
i = 4
```

Then:

```text
i-k = 4-3 = 1
```

So the condition becomes:

```java
if(deque.peekFirst() <= 1)
```

This means:

> If the front index is `1` or smaller, it has expired and should be removed.

For `i = 4`, the current window contains:

```text
indices:

0 1 2 3 4
    └─────┘
     2 3 4
```

So index `1` is outside the current window.

---

## 4️⃣ Remove Smaller Elements

Next:

```java
while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
    deque.pollLast();
}
```

Suppose:

```text
deque values = [5,3]
current value = 6
```

We remove:

```text
3 <= 6 → remove
5 <= 6 → remove
```

Then we add `6`.

Why can we remove them?

Because `6` is larger than both.

As long as `6` is inside the window, `5` and `3` can never be the maximum.

This is the **monotonic deque** idea.

---

## 5️⃣ Add the Current Index

After removing smaller elements:

```java
deque.offerLast(i);
```

We add the current index.

Now the deque contains only useful candidates for the maximum.

---

## 6️⃣ Store the Maximum

The front of the deque contains the index of the maximum.

So:

```java
result[i-k+1] = nums[deque.peekFirst()];
```

stores the current maximum.

For example:

```text
i = 3
k = 3

i-k+1
= 3-3+1
= 1
```

So the maximum of the second window is stored in:

```text
result[1]
```

Important:

`i-k+1` here is the **position in the result array**.

It is not creating the window.

---

## 🧪 Dry Run

### Input

```text
nums = [1,3,-1,-3,5,3,6,7]
k = 3
```

### First Window

```text
[1,3,-1]
```

Deque processing:

```text
1  → [0]
3  → remove 1 → [1]
-1 → [1,2]
```

Maximum:

```text
nums[1] = 3
```

So:

```text
result[0] = 3
```

---

### Second Window

```text
[3,-1,-3]
```

Maximum:

```text
3
```

So:

```text
result[1] = 3
```

---

### Third Window

```text
[-1,-3,5]
```

When `5` arrives, smaller elements are removed.

Maximum:

```text
5
```

So:

```text
result[2] = 5
```

---

### Remaining Windows

```text
[-3,5,3] → 5
[5,3,6]  → 6
[3,6,7]  → 7
```

Final result:

```text
[3,3,5,5,6,7]
```

---

## 💻 Java Solution

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> deque = new ArrayDeque<>();

        int[] result = new int[n-k+1];

        // Build the first window
        for(int i = 0; i < k; i++){

            // Remove smaller/equal elements from the back
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);
        }

        // Store maximum of the first window
        result[0] = nums[deque.peekFirst()];

        // Process remaining windows
        for(int i = k; i < n; i++){

            // Remove expired index
            if(deque.peekFirst() <= i-k){
                deque.pollFirst();
            }

            // Remove smaller/equal elements from the back
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);

            // Store current maximum
            result[i-k+1] = nums[deque.peekFirst()];
        }

        return result;
    }
}
```

---

## 🔑 Key Trick

Remember these 3 rules:

```text
BACK  → remove smaller/equal elements
FRONT → remove expired elements
FRONT → current maximum
```

The deque does **not** contain every element in the window.

It only contains indices of elements that are still useful candidates for becoming the maximum.

---

## ⏱️ Complexity

### Time: `O(n)`

Every index is:

- Added to the deque once
- Removed from the deque at most once

Therefore, the total time complexity is `O(n)`.

### Space: `O(k)`

The deque stores at most `k` useful indices.

---

## 🎯 Key Takeaway

The pattern to remember is:

```text
Sliding Window Maximum
          ↓
   Monotonic Deque
          ↓
     Store Indices
          ↓
Remove smaller → BACK
Remove expired → FRONT
Maximum        → FRONT
```

The biggest idea:

> **Don't keep elements that are already useless.**

If a bigger element arrives, smaller elements behind it can be removed immediately.

⭐ If this helped you understand the Sliding Window + Monotonic Deque trick, please consider giving it an upvote! 🚀
