
# 🚀 18. 4Sum

> 📂 Topic: Arrays, Sorting, Two Pointers, HashSet  
> 🎯 Pattern: Fix Two Elements + Two Pointers  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n³) | 💾 Space: O(n²)

---

# 🔗 Problem Link

[LeetCode 18 — 4Sum](https://leetcode.com/problems/4sum/)

---

# 🧠 One-Line Idea

> Sort the array, fix the first two elements, and use two pointers to find the remaining two elements whose total equals the target.

---

# 💡 Intuition

The goal is to find all **unique quadruplets** whose sum is equal to `target`.

For example:

```text
nums = [1, 0, -1, 0, -2, 2]
target = 0
```

The valid quadruplets are:

```text
[-2, -1, 1, 2]
[-2, 0, 0, 2]
[-1, 0, 0, 1]
```

The key idea is to extend the approach used in **3Sum**.

For 3Sum:

```text
Fix 1 element
      ↓
Use 2 pointers
```

For 4Sum:

```text
Fix 2 elements
      ↓
Use 2 pointers
```

So the structure becomes:

```text
first
  ↓
second
  ↓
left                right
 ↓                    ↓
[ ?, ?, ?, ..., ?, ? ]
```

---

# 🚀 Approach

## 1. Handle Invalid Input

A quadruplet requires at least 4 elements.

```java
if (nums == null || nums.length < 4) {
    return new ArrayList<>();
}
```

If the array is `null` or contains fewer than 4 elements, no quadruplet can be formed.

---

## 2. Sort the Array

```java
Arrays.sort(nums);
```

For example:

```text
Before:

[1, 0, -1, 0, -2, 2]
```

After sorting:

```text
[-2, -1, 0, 0, 1, 2]
```

Sorting is important because it allows us to use the **two-pointer technique**.

Since the array is sorted:

```text
left++  → moves toward larger values
right-- → moves toward smaller values
```

---

# 3. Fix the First Element

```java
for (int first = 0; first <= nums.length - 4; first++)
```

The `first` pointer fixes the first element of the quadruplet.

For example:

```text
first
  ↓
[-2, -1, 0, 0, 1, 2]
```

After fixing `first`, we need three more elements.

---

# 4. Fix the Second Element

Inside the first loop:

```java
for (int second = first + 1; second <= nums.length - 3; second++)
```

The `second` pointer fixes the second element.

Now:

```text
first < second
```

For example:

```text
first
  ↓
[-2, -1, 0, 0, 1, 2]
      ↑
    second
```

After fixing the first two elements, we only need to find the remaining two.

This reduces the problem to a **Two Sum-like problem**.

---

# 5. Use Two Pointers

Initialize:

```java
int left = second + 1;
int right = nums.length - 1;
```

Therefore:

```text
first < second < left < right
```

For example:

```text
 first   second   left                 right
   ↓       ↓       ↓                     ↓
[-2,     -1,      0,      0,      1,      2]
```

Now repeatedly calculate:

```java
long sum = (long) nums[first]
         + nums[second]
         + nums[left]
         + nums[right];
```

---

# 🧠 Pointer Movement

There are three cases.

## Case 1 — `sum == target`

We found a valid quadruplet.

```java
if (sum == target)
```

For example:

```text
-2 + (-1) + 1 + 2 = 0
```

Add it:

```java
result.add(
    Arrays.asList(
        nums[first],
        nums[second],
        nums[left],
        nums[right]
    )
);
```

Then move both pointers:

```java
left++;
right--;
```

---

## Case 2 — `sum < target`

The current sum is too small.

We need a larger value.

Because the array is sorted:

```java
left++;
```

Moving `left` forward gives us a larger value.

```text
sum < target
      ↓
Need larger sum
      ↓
left++
```

---

## Case 3 — `sum > target`

The current sum is too large.

We need a smaller value.

Because the array is sorted:

```java
right--;
```

Moving `right` backward gives us a smaller value.

```text
sum > target
      ↓
Need smaller sum
      ↓
right--
```

---

# 🔢 Why Use `long`?

This is important in 4Sum because adding four `int` values can cause integer overflow.

For example:

```text
1,000,000,000
+1,000,000,000
+1,000,000,000
+1,000,000,000
----------------
4,000,000,000
```

But Java `int` can store only up to:

```text
2,147,483,647
```

Therefore:

```java
long sum = (long) nums[first]
         + nums[second]
         + nums[left]
         + nums[right];
```

Using `long` prevents overflow.

---

# 🧠 Why Use a HashSet?

We use:

```java
Set<List<Integer>> result = new HashSet<>();
```

A `HashSet` stores only unique elements.

Each 4Sum answer is a quadruplet such as:

```text
[-2, -1, 1, 2]
```

which is represented as:

```text
List<Integer>
```

Therefore:

```text
Set<List<Integer>>
```

means:

```text
Set
 ↓
Unique quadruplets
```

If the same quadruplet is found multiple times, the `HashSet` prevents duplicate entries.

This makes duplicate handling easier than manually skipping duplicates with multiple `while` loops.

---

# 🔍 What Does `Arrays.asList()` Do?

This:

```java
Arrays.asList(
    nums[first],
    nums[second],
    nums[left],
    nums[right]
)
```

creates a `List` containing the four values.

For example:

```java
Arrays.asList(-2, -1, 1, 2)
```

creates:

```text
[-2, -1, 1, 2]
```

Then:

```java
result.add(...)
```

adds that entire quadruplet to the `HashSet`.

---

# 🔍 Why `return new ArrayList<>(result)`?

The method expects:

```java
List<List<Integer>>
```

but `result` is:

```java
Set<List<Integer>>
```

So we convert the Set into a List:

```java
return new ArrayList<>(result);
```

This creates a new `ArrayList` containing all the unique quadruplets.

```text
HashSet
   ↓
Unique quadruplets
   ↓
new ArrayList<>(result)
   ↓
List<List<Integer>>
   ↓
return
```

---

# 📝 Dry Run

Consider:

```text
nums = [1, 0, -1, 0, -2, 2]
target = 0
```

After sorting:

```text
[-2, -1, 0, 0, 1, 2]
```

Suppose:

```text
first = 0
second = 1
left = 2
right = 5
```

Values:

```text
first  → -2
second → -1
left   → 0
right  → 2
```

Calculate:

```text
sum = -2 + (-1) + 0 + 2
    = -1
```

Target:

```text
0
```

Since:

```text
sum < target
```

we need a larger sum.

Therefore:

```text
left++
```

Now:

```text
left = 3
```

Still:

```text
sum = -2 + (-1) + 0 + 2
    = -1
```

Move:

```text
left++
```

Now:

```text
left = 4 → 1
```

Calculate:

```text
sum = -2 + (-1) + 1 + 2
    = 0
```

🎯 Found:

```text
[-2, -1, 1, 2]
```

Add it to the result and continue searching.

---

# 💻 Java Solution

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        // A quadruplet requires at least 4 elements
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }

        // Sort the array so we can use two pointers
        Arrays.sort(nums);

        // HashSet stores only unique quadruplets
        Set<List<Integer>> result = new HashSet<>();

        // Fix the first element
        for (int first = 0; first <= nums.length - 4; first++) {

            // Fix the second element
            for (int second = first + 1;
                 second <= nums.length - 3;
                 second++) {

                // Left pointer starts after second
                int left = second + 1;

                // Right pointer starts at the last element
                int right = nums.length - 1;

                // Search for the remaining two elements
                while (left < right) {

                    // Use long to prevent integer overflow
                    long sum = (long) nums[first]
                             + nums[second]
                             + nums[left]
                             + nums[right];

                    if (sum == target) {

                        // Found a valid quadruplet
                        result.add(
                            Arrays.asList(
                                nums[first],
                                nums[second],
                                nums[left],
                                nums[right]
                            )
                        );

                        // Move both pointers to continue searching
                        left++;
                        right--;

                    } else if (sum < target) {

                        // Sum is too small.
                        // Move left forward to get a larger value.
                        left++;

                    } else {

                        // Sum is too large.
                        // Move right backward to get a smaller value.
                        right--;
                    }
                }
            }
        }

        // Convert Set of unique quadruplets into List
        return new ArrayList<>(result);
    }
}
```

---

# 🎯 Key Takeaway

4Sum is basically an extension of 3Sum:

```text
2Sum
 ↓
Two Pointers

3Sum
 ↓
Fix 1 element
 ↓
Two Pointers

4Sum
 ↓
Fix 2 elements
 ↓
Two Pointers
```

The complete pattern:

```text
Sort
  ↓
Fix first
  ↓
Fix second
  ↓
left = second + 1
right = last index
  ↓
Calculate sum
  ↓
        ┌─────────────────┐
        │                 │
   sum < target       sum > target
        │                 │
     left++            right--
        │                 │
        └────────┬────────┘
                 │
           sum == target
                 ↓
         Store quadruplet
                 ↓
          left++, right--
```

The most important rule:

```text
sum < target  → left++
sum > target  → right--
sum == target → store + move both
```

Because the array is sorted, moving `left` increases the sum and moving `right` decreases the sum.

---

# 📊 Complexity

### Sorting

```text
O(n log n)
```

### Nested loops + Two Pointers

The first loop takes:

```text
O(n)
```

The second loop takes:

```text
O(n)
```

For every pair of fixed elements, the two pointers take:

```text
O(n)
```

Therefore:

```text
O(n × n × n)
= O(n³)
```

Total:

```text
O(n log n) + O(n³)
```

Since `O(n³)` dominates `O(n log n)`:

```text
Time Complexity = O(n³)
```

### Space Complexity

The `HashSet` stores the resulting quadruplets.

In the worst case, there can be `O(n²)` unique quadruplets.

Therefore:

```text
Space Complexity = O(n²)
```

This HashSet-based version uses more space than manual duplicate skipping, but it is easier to understand while maintaining the standard `O(n³)` time complexity.

---

> **Sort the array, fix two elements, and use two pointers to find the remaining two. The HashSet automatically keeps the quadruplets unique.**

Happy Coding! 🚀✨
