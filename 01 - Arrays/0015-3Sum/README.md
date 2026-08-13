# 🚀 15. 3Sum

> 📂 Topic: Arrays, Sorting, Two Pointers
> 🎯 Pattern: Fix One Element + Two Pointers
> ⭐ Difficulty: Medium
> ⏱️ Time: O(n²) | 💾 Space: O(n²) for the result

---

# 🔗 Problem Link

[LeetCode 15 — 3Sum](https://leetcode.com/problems/3sum/)

---

# 🧠 One-Line Idea

> Sort the array, fix one element, and use two pointers to find the other two elements whose sum makes the total `0`.

---

# 💡 Intuition

The goal is to find all **unique triplets** whose sum is `0`.

For example:

```text
nums = [-1, 0, 1, 2, -1, -4]
```

The valid triplets are:

```text
[-1, -1, 2]
[-1, 0, 1]
```

We need:

```text
nums[i] + nums[left] + nums[right] = 0
```

Instead of checking every possible combination using three loops, we can:

```text
Sort the array
      ↓
Fix one element
      ↓
Use two pointers for the remaining two elements
      ↓
Find all valid triplets
```

---

# 🚀 Approach

## Step 1 — Sort the Array

First:

```java
Arrays.sort(nums);
```

For example:

```text
Before:

[-1, 0, 1, 2, -1, -4]
```

After:

```text
[-4, -1, -1, 0, 1, 2]
```

Sorting is important because it allows us to decide how to move the two pointers.

---

# Step 2 — Fix One Element

Use a `for` loop:

```java
for (int i = 0; i < nums.length - 2; i++)
```

The `i` pointer represents the first element of the triplet.

For example:

```text
        i
        ↓
[-4, -1, -1, 0, 1, 2]
```

Once `i` is fixed, we need to find two more numbers:

```text
nums[left] + nums[right] = -nums[i]
```

---

# Step 3 — Use Two Pointers

Initialize:

```java
int left = i + 1;
int right = nums.length - 1;
```

So:

```text
        i      left                 right
        ↓       ↓                     ↓
[-4,   -1,    -1,     0,     1,     2]
```

Then:

```java
while (left < right)
```

keeps searching for the remaining two elements.

---

# 🧠 How Do We Move the Pointers?

Calculate:

```java
int sum = nums[i] + nums[left] + nums[right];
```

There are three possibilities.

---

## Case 1 — `sum == 0`

We found a valid triplet:

```text
nums[i] + nums[left] + nums[right] = 0
```

Add it:

```java
result.add(
    Arrays.asList(nums[i], nums[left], nums[right])
);
```

Then move both pointers:

```java
left++;
right--;
```

This allows us to search for another triplet.

---

## Case 2 — `sum < 0`

The sum is too small.

For example:

```text
sum = -3
```

We need to make the sum larger.

Because the array is sorted, moving `left` forward gives us a larger value:

```java
left++;
```

So:

```text
sum < 0
   ↓
Need larger sum
   ↓
left++
```

---

## Case 3 — `sum > 0`

The sum is too large.

For example:

```text
sum = 4
```

We need to make the sum smaller.

Because the array is sorted, moving `right` backward gives us a smaller value:

```java
right--;
```

So:

```text
sum > 0
   ↓
Need smaller sum
   ↓
right--
```

---

# 📝 Dry Run

Consider:

```text
nums = [-1, 0, 1, 2, -1, -4]
```

After sorting:

```text
[-4, -1, -1, 0, 1, 2]
```

---

## `i = 0`

```text
nums[i] = -4
left = 1
right = 5
```

Calculate:

```text
sum = -4 + (-1) + 2
    = -3
```

Since:

```text
sum < 0
```

move:

```text
left++
```

We need a larger value.

Continue moving `left` until we either find a valid triplet or `left >= right`.

---

## `i = 1`

Now:

```text
nums[i] = -1
left = 2
right = 5
```

Calculate:

```text
sum = -1 + (-1) + 2
    = 0
```

Found:

```text
[-1, -1, 2]
```

Add it to the result.

Move:

```text
left++
right--
```

Now:

```text
left = 3
right = 4
```

Calculate:

```text
sum = -1 + 0 + 1
    = 0
```

Found:

```text
[-1, 0, 1]
```

Final result:

```text
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

---

# 🧠 Why Use a `Set<List<Integer>>`?

The solution uses:

```java
Set<List<Integer>> result = new HashSet<>();
```

A `Set` stores only unique elements.

Each 3Sum answer is a triplet:

```text
[-1, 0, 1]
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
Unique Lists
 ↓
Unique Triplets
```

For example:

```text
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

If the same triplet is added again, the `HashSet` prevents a duplicate from being stored.

---

# 🔍 What Does `Arrays.asList()` Do?

This:

```java
Arrays.asList(nums[i], nums[left], nums[right])
```

creates a `List` containing the three values.

For example:

```java
Arrays.asList(-1, 0, 1)
```

creates:

```text
[-1, 0, 1]
```

Then:

```java
result.add(...)
```

adds that triplet to the Set.

So:

```java
result.add(
    Arrays.asList(nums[i], nums[left], nums[right])
);
```

means:

```text
Three numbers
     ↓
Put them into a List
     ↓
Add that List to the Set
```

---

# 🔍 Why `return new ArrayList<>(result)`?

The method must return:

```java
List<List<Integer>>
```

but our `result` is:

```java
Set<List<Integer>>
```

So we convert the Set into a List:

```java
return new ArrayList<>(result);
```

This means:

> Create a new `ArrayList` containing all the unique triplets stored in `result`.

The flow is:

```text
HashSet
   ↓
Unique triplets
   ↓
new ArrayList<>(result)
   ↓
List<List<Integer>>
   ↓
return
```

---

# 💻 Java Solution

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // If the array has fewer than 3 elements,
        // a triplet cannot be formed
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        // Sort the array so we can use two pointers
        Arrays.sort(nums);

        // HashSet stores only unique triplets
        Set<List<Integer>> result = new HashSet<>();

        // Fix the first element of the triplet
        for (int i = 0; i < nums.length - 2; i++) {

            // Left pointer starts after i
            int left = i + 1;

            // Right pointer starts at the end
            int right = nums.length - 1;

            // Search for the other two elements
            while (left < right) {

                // Calculate the sum of the three numbers
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    // Found a valid triplet
                    result.add(
                        Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                        )
                    );

                    // Move both pointers to continue searching
                    left++;
                    right--;

                } else if (sum < 0) {

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

        // Convert the Set of unique triplets into a List
        return new ArrayList<>(result);
    }
}
```

---

# 🎯 Key Takeaway

Remember this pattern:

```text
Sort
  ↓
Fix i
  ↓
left = i + 1
right = last index
  ↓
Calculate sum
  ↓
       ┌───────────────┐
       │               │
    sum < 0         sum > 0
       │               │
    left++          right--
       │               │
       └───────┬───────┘
               │
            sum == 0
               ↓
        Store triplet
               ↓
        left++, right--
```

The most important rule is:

```text
sum < 0 → left++
sum > 0 → right--
sum == 0 → store triplet + move both
```

This works because the array is sorted.

---

# 📊 Complexity

### Sorting

```text
O(n log n)
```

### Two-Pointer Search

For each fixed `i`, the `left` and `right` pointers together move through the array in:

```text
O(n)
```

Since `i` can take `O(n)` values:

```text
O(n) × O(n)
= O(n²)
```

Therefore:

```text
Total Time = O(n log n) + O(n²)
           = O(n²)
```

The `O(n log n)` sorting time is dominated by `O(n²)`.

### Space

The `HashSet` stores the triplets.

In the worst case, the output itself can contain `O(n²)` triplets.

Therefore:

```text
Time  = O(n²)
Space = O(n²) including the result
```

For an implementation that skips duplicates using pointers instead of a `HashSet`, the auxiliary space can be reduced to `O(1)` excluding the output.

---

> **Sort the array, fix one element, and use two pointers to find the other two. If the sum is too small move `left`; if it is too large move `right`.**

Happy Coding! 🚀✨
