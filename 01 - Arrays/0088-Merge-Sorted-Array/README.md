# 🚀 88. Merge Sorted Array

> 📂 Topic: Arrays, Two Pointers
> 🎯 Pattern: Three Pointers, In-Place Merge
> ⭐ Difficulty: Easy
> ⏱️ Time: O(m + n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[https://leetcode.com/problems/merge-sorted-array/](https://leetcode.com/problems/merge-sorted-array/)

---

# 🧠 Pattern Recognition

Whenever you see:

- Two sorted arrays
- One array has extra space at the end
- Merge one sorted array into another
- In-place modification is required

Think:

```text
Two Sorted Arrays
        ↓
Extra Space at the End
        ↓
Merge from Right → Left
        ↓
Three Pointers
```

The key pattern is:

> **When merging sorted data in-place and the destination array has empty space at the end, process it backwards to avoid overwriting unprocessed elements.**

---

# 💡 Intuition

We are given two sorted arrays.

For example:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]
```

Here:

- `nums1` has `m = 3` valid elements.
- `nums2` has `n = 3` elements.
- The last `3` positions of `nums1` are available for the final merged result.

The expected result is:

```text
[1,2,2,3,5,6]
```

A natural idea would be to merge from left to right.

But that creates a problem.

If we place an element at the beginning of `nums1`, we may overwrite an element that we still need to process.

Instead, notice that the **empty positions are at the end**.

Therefore, we can start from the end and place the **largest remaining element** at the last available position.

This allows us to merge everything in-place without using another array.

---

# 📈 Evolution of the Solution

```text
Copy nums2 into nums1
        ↓
Sort the complete array
        ↓
Works, but sorting is unnecessary
        ↓
Both arrays are already sorted
        ↓
Compare from the end
        ↓
Place the larger element at the end
        ↓
Three-pointer O(m + n) solution
```

---

# 🐢 Approach 1 — Brute Force

## Idea

The simplest approach is:

1. Copy all elements of `nums2` into the empty positions of `nums1`.
2. Sort the complete `nums1` array.

For example:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]
```

After copying:

```text
[1,2,3,2,5,6]
```

After sorting:

```text
[1,2,2,3,5,6]
```

This is correct, but we are doing unnecessary sorting.

Both arrays were already sorted, so we should be able to do better.

---

## Complexity

**Time**

```text
O((m+n) log(m+n))
```

**Space**

```text
O(log(m+n))
```

depending on the sorting implementation.

---

## Java Solution

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Copy nums2 into the empty positions of nums1
        for(int i = 0; i < n; i++){
            nums1[m + i] = nums2[i];
        }

        // Sort the complete array
        Arrays.sort(nums1);
    }
}
```

---

# 🚀 Approach 2 — Optimal: Three Pointers

## Key Observation

Since both arrays are already sorted, we don't need to sort again.

We can directly merge them.

The important part is that we merge **from right to left**.

We maintain three pointers:

```text
i → Last valid element in nums1

j → Last element in nums2

k → Last available position in nums1
```

For example:

```text
nums1 = [1,2,3,0,0,0]
         ↑        ↑
         i        k

nums2 = [2,5,6]
            ↑
            j
```

Initially:

```java
int i = m - 1;
int j = n - 1;
int k = m + n - 1;
```

At every step:

```text
Compare nums1[i] and nums2[j]
             ↓
       Take the larger
             ↓
       Place it at nums1[k]
             ↓
 Move the selected pointer backward
             ↓
          k--
```

---

# 📝 Dry Run

Consider:

```text
nums1 = [1,2,3,0,0,0]
m = 3

nums2 = [2,5,6]
n = 3
```

Initial pointers:

```text
i = 2 → 3
j = 2 → 6
k = 5
```

---

### Step 1

Compare:

```text
3 vs 6
```

`6` is larger.

Place `6` at `nums1[5]`.

```text
[1,2,3,0,0,6]
```

Move:

```text
j--
k--
```

---

### Step 2

Compare:

```text
3 vs 5
```

`5` is larger.

Place `5`.

```text
[1,2,3,0,5,6]
```

Move:

```text
j--
k--
```

---

### Step 3

Compare:

```text
3 vs 2
```

`3` is larger.

Place `3`.

```text
[1,2,3,3,5,6]
```

Move:

```text
i--
k--
```

---

### Step 4

Compare:

```text
2 vs 2
```

The values are equal.

Our condition is:

```java
nums1[i] > nums2[j]
```

Since `2 > 2` is false, the `else` block executes.

Place `2` from `nums2`.

```text
[1,2,2,3,5,6]
```

Now all elements of `nums2` have been placed.

Final result:

```text
[1,2,2,3,5,6]
```

---

# 🤔 Things That Confused Me

---

## ❓1. Why do we merge from the end instead of the beginning?

Because `nums1` has empty space at the end.

Consider:

```text
[1,2,3,_,_,_]
```

If we start from the beginning, placing new values could overwrite:

```text
1, 2, 3
```

before we have processed them.

But if we start from the end:

```text
[1,2,3,_,_,_]
         ↑
       empty
```

we can safely place the largest values there.

Therefore:

> **Merge backwards when the destination array has extra space at the end.**

---

## ❓2. Why is `i = m - 1` and not `nums1.length - 1`?

Suppose:

```text
nums1 = [1,2,3,0,0,0]
```

The array length is `6`.

But only the first `3` elements are valid.

The last valid element is `3`, at index `2`.

Therefore:

```java
int i = m - 1;
```

not:

```java
int i = nums1.length - 1;
```

The last `n` positions are empty space, not actual elements.

---

## ❓3. What exactly is `k`?

`k` tells us **where to place the next largest element**.

The total final array contains:

```text
m + n
```

elements.

Since arrays are zero-indexed, the last position is:

```text
m + n - 1
```

Therefore:

```java
int k = m + n - 1;
```

For:

```text
m = 3
n = 3
```

we get:

```text
k = 5
```

So the first element we place goes into:

```java
nums1[5]
```

---

## ❓4. Why is the loop only `while(j >= 0)`?

We use:

```java
while(j >= 0)
```

because all elements of `nums2` must be placed.

Suppose `nums2` becomes empty first.

Then any remaining elements of `nums1` are already in their correct positions.

For example:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [4,5,6]
```

After merging:

```text
[1,2,3,4,5,6]
```

Once `nums2` is exhausted, there is nothing else to do.

---

## ❓5. Why do we still check `i >= 0`?

Inside the condition we have:

```java
if(i >= 0 && nums1[i] > nums2[j])
```

The `i >= 0` check prevents us from accessing an invalid index.

Consider:

```text
nums1 = [0]
m = 0

nums2 = [1]
n = 1
```

Then:

```text
i = m - 1
  = -1
```

We cannot access:

```java
nums1[-1]
```

Therefore, we first check:

```java
i >= 0
```

before accessing `nums1[i]`.

---

## ❓6. Why do we move only `i` or `j`, but always move `k`?

At every iteration, we place exactly **one element**.

If the element comes from `nums1`:

```java
nums1[k] = nums1[i];
i--;
```

If the element comes from `nums2`:

```java
nums1[k] = nums2[j];
j--;
```

But regardless of which array the element came from, one position has been filled.

Therefore:

```java
k--;
```

always happens.

---

## ❓7. What happens when both values are equal?

Suppose:

```text
nums1[i] = 2
nums2[j] = 2
```

Our condition is:

```java
nums1[i] > nums2[j]
```

which becomes:

```text
2 > 2
```

This is false.

So we take the value from `nums2`.

That's perfectly fine because both values are equal.

The final array remains sorted.

---

# ❌ Common Mistakes

- Starting the merge from the beginning.
- Using `nums1.length - 1` instead of `m - 1` for `i`.
- Forgetting the `i >= 0` check.
- Using `m + n` instead of `m + n - 1` for `k`.
- Moving both `i` and `j` after placing only one element.
- Sorting the entire array even though both input arrays are already sorted.
- Forgetting that `nums1` already contains extra space for `nums2`.

---

# ⏱️ Complexity Analysis

| Approach | Time Complexity | Space Complexity |
| -------- | --------------- | ---------------- |
| Brute Force + Sorting | O((m+n) log(m+n)) | O(log(m+n)) |
| Three Pointers | O(m+n) | O(1) |

---

# 💻 Java Solutions

## 🐢 Brute Force

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Copy nums2 into the empty positions of nums1
        for(int i = 0; i < n; i++){
            nums1[m + i] = nums2[i];
        }

        // Sort the complete array
        Arrays.sort(nums1);
    }
}
```

---

## 🚀 Optimal — Three Pointers

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Three pointer approach

        // i -> last valid element in nums1
        int i = m - 1;

        // j -> last element in nums2
        int j = n - 1;

        // k -> last available position in nums1
        int k = m + n - 1;

        // Continue until all elements of nums2 are placed
        while(j >= 0){

            // If nums1 still has elements and its current
            // element is larger, place it at the end
            if(i >= 0 && nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            } 
            else{
                // Otherwise, place the element from nums2
                nums1[k] = nums2[j];
                j--;
            }

            // Move to the previous available position
            k--;
        }
    }
}
```

---

# 🎯 Takeaway

- Both arrays are already sorted, so **don't sort again**.
- The extra space is at the end of `nums1`, so use it.
- Merge from **right to left** to avoid overwriting unprocessed elements.
- Use three pointers:
  - `i` → last valid element of `nums1`
  - `j` → last element of `nums2`
  - `k` → last available position of `nums1`
- Place the **larger element first**.
- Continue until all elements of `nums2` are placed.

The core pattern:

```text
Two Sorted Arrays
        ↓
Extra Space at End
        ↓
Start from the End
        ↓
Compare Largest Elements
        ↓
Place Larger Element
        ↓
Move Pointers Backward
```

This gives an optimal:

```text
O(m+n) Time
O(1) Space
```

---

# 🔁 Similar Problems

- 21. Merge Two Sorted Lists
- 977. Squares of a Sorted Array
- 349. Intersection of Two Arrays
- 350. Intersection of Two Arrays II

---

### ⭐ Enjoyed this explanation?

If this README helped you understand the **Three-Pointer In-Place Merge**, consider **starring ⭐ the repository**.

Your support motivates me to keep building a detailed Java DSA Playbook with:

- 🧠 Pattern recognition
- 💡 Intuition
- 🐢 Brute-force solutions
- 🚀 Optimized solutions
- 🤔 Personal doubts & clarifications
- 📝 Dry runs
- 💻 Clean Java implementations

Happy Coding! 🚀✨
