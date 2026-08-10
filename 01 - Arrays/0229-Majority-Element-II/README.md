# 🚀 229. Majority Element II

> 📂 Topic: Arrays, HashMap
> 🎯 Pattern: Frequency Counting
> ⭐ Difficulty: Medium
> ⏱️ Time: O(n) | 💾 Space: O(n)

---

# 🔗 Problem Link

[LeetCode 229 — Majority Element II](https://leetcode.com/problems/majority-element-ii/)

---

# 🧠 HashMap — One-Line Idea

> Count the frequency of every element using a HashMap, then return all elements whose frequency is greater than `n / 3`.

---

# 💡 Intuition

We need to find all elements that appear more than:

```text
⌊n / 3⌋
```

times.

For example:

```text
nums = [3, 2, 3]
```

Here:

```text
n = 3

n / 3 = 1
```

So we need elements whose frequency is:

```text
> 1
```

Frequency:

```text
3 → 2
2 → 1
```

Therefore:

```text
Answer = [3]
```

The simplest approach is to use a `HashMap`:

```text
number → frequency
```

We use two passes:

```text
Pass 1:
Count the frequency of every element.

        ↓

Pass 2:
Check which elements have frequency > n/3.
```

---

# 🚀 Approach — HashMap

## Step 1: Find the Array Size

```java
int n = nums.length;
```

`nums.length` gives the number of elements in the array.

For:

```text
nums = [3, 2, 3]
```

we get:

```text
n = 3
```

---

## Step 2: Create the HashMap

```java
Map<Integer, Integer> map = new HashMap<>();
```

The HashMap stores:

```text
Key   → Number
Value → Frequency
```

For example:

```text
3 → 2
2 → 1
```

means:

```text
3 appears 2 times
2 appears 1 time
```

---

## Step 3: Create the Result List

```java
List<Integer> result = new ArrayList<>();
```

This stores all elements whose frequency is greater than `n / 3`.

Initially:

```text
result = []
```

---

# 📊 Phase 1 — Count Frequencies

We use a traditional `for` loop:

```java
for (int i = 0; i < n; i++)
```

This visits every element in the array.

For every iteration:

```java
int num = nums[i];
```

stores the current element in `num`.

---

## If the Number Already Exists

```java
if (map.containsKey(num))
```

`containsKey()` checks whether the number is already present in the HashMap.

If it exists:

```java
map.put(num, map.get(num) + 1);
```

This increases its frequency by `1`.

For example:

```text
Before:

3 → 1
```

After seeing another `3`:

```text
3 → 2
```

Here:

```java
map.get(num)
```

gets the current frequency.

And:

```java
map.put(num, map.get(num) + 1)
```

updates the frequency.

---

## If the Number Does Not Exist

```java
else {
    map.put(num, 1);
}
```

This means:

> This is the first time we have seen this number, so store it with frequency `1`.

For example:

```text
map = {}
```

After seeing `3`:

```text
map = {
    3 → 1
}
```

---

# 📝 Example of Phase 1

For:

```text
nums = [3, 2, 3]
```

Initially:

```text
map = {}
```

### `i = 0`

```text
num = 3
```

`3` doesn't exist:

```text
map = {
    3 → 1
}
```

### `i = 1`

```text
num = 2
```

`2` doesn't exist:

```text
map = {
    3 → 1,
    2 → 1
}
```

### `i = 2`

```text
num = 3
```

`3` already exists.

Increase its frequency:

```text
map = {
    3 → 2,
    2 → 1
}
```

Now we know the frequency of every element.

---

# 🔍 Phase 2 — Find Majority Elements

Now we traverse the array again:

```java
for (int i = 0; i < n; i++)
```

For each number, we check:

```java
map.get(nums[i]) > n / 3
```

This means:

> Is the frequency of this number greater than `n / 3`?

---

# 🔑 Understanding the `if` Condition

The complete condition is:

```java
if (map.containsKey(nums[i]) &&
    map.get(nums[i]) > n / 3)
```

Let's break it down.

### First Part

```java
map.containsKey(nums[i])
```

Checks whether the number still exists in the map.

Why do we need this?

Because after adding an element to the result, we remove it from the map.

This prevents adding the same element multiple times.

---

### Second Part

```java
map.get(nums[i]) > n / 3
```

Gets the frequency of the current number and checks whether it satisfies the majority condition.

For:

```text
nums = [3, 2, 3]
```

we have:

```text
n = 3

n / 3 = 1
```

For `3`:

```text
map.get(3) = 2
```

Therefore:

```text
2 > 1
```

So `3` is a majority element.

---

# ➕ Add the Element to the Result

If the condition is true:

```java
result.add(nums[i]);
```

This adds the current number to the answer.

For example:

```text
result = [3]
```

---

# 🗑️ Remove the Element from the Map

After adding the element:

```java
map.remove(nums[i]);
```

Why?

Because the same number might appear multiple times in the original array.

For example:

```text
nums = [3, 2, 3]
```

Without removing `3`, we could do:

```text
i = 0 → add 3
i = 2 → add 3 again
```

giving:

```text
[3, 3]
```

which is incorrect.

After:

```java
map.remove(3);
```

the map no longer contains `3`.

So when we encounter `3` again:

```java
map.containsKey(3)
```

returns:

```text
false
```

and we skip it.

Therefore, `remove()` is used to:

> **Prevent duplicate elements from being added to the result.**

---

# 💻 Java Solution

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {

        // Store the size of the array
        int n = nums.length;

        // HashMap:
        // Key   → element
        // Value → frequency of that element
        Map<Integer, Integer> map = new HashMap<>();

        // Stores the elements appearing more than n/3 times
        List<Integer> result = new ArrayList<>();

        // STEP 1: Count the frequency of every element
        for (int i = 0; i < n; i++) {

            // Get the current element
            int num = nums[i];

            // If the element already exists,
            // increase its frequency by 1
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }

            // If it is appearing for the first time,
            // initialize its frequency to 1
            else {
                map.put(num, 1);
            }
        }

        // STEP 2: Find elements appearing more than n/3 times
        for (int i = 0; i < n; i++) {

            // Check:
            // 1. The element is still present in the map
            // 2. Its frequency is greater than n/3
            if (map.containsKey(nums[i]) &&
                map.get(nums[i]) > n / 3) {

                // Add the majority element to the result
                result.add(nums[i]);

                // Remove it so that it cannot be added again
                // when the same number appears later in the array
                map.remove(nums[i]);
            }
        }

        // Return all majority elements
        return result;
    }
}
```

---

# 🧠 Code in Simple English

The entire code can be understood as:

```text
1. Find the size of the array.
        ↓
2. Create a HashMap.
        ↓
3. Count how many times every number appears.
        ↓
4. Traverse the array again.
        ↓
5. Check if frequency > n/3.
        ↓
6. If yes, add it to the result.
        ↓
7. Remove it from the map to avoid duplicates.
        ↓
8. Return the result.
```

---

# 📝 Complete Example

Consider:

```text
nums = [1, 2, 1, 1, 2, 2, 2]
```

Here:

```text
n = 7

n / 3 = 2
```

We need:

```text
frequency > 2
```

After counting:

```text
1 → 3
2 → 4
```

Now check:

```text
1 → 3 > 2 → YES
2 → 4 > 2 → YES
```

Therefore:

```text
result = [1, 2]
```

---

# ⚠️ Important Observation

There can be **at most 2 majority elements** that appear more than `n / 3` times.

Why?

Suppose there were 3 such elements.

Each would need to appear more than:

```text
n / 3
```

times.

So together they would appear more than:

```text
n / 3 + n / 3 + n / 3
= n
```

times.

That is impossible because the array contains only `n` elements.

Therefore:

```text
Maximum possible answer size = 2
```

---

# 📊 Complexity

We traverse the array twice:

```text
First pass  → O(n)
Second pass → O(n)
```

Therefore:

- **Time Complexity:** `O(n)` average
- **Space Complexity:** `O(n)`

The HashMap can contain up to `n` distinct elements.

---

# 🎯 Key Takeaway

The HashMap approach is simply:

```text
nums[]
   ↓
Count frequencies
   ↓
HashMap
number → frequency
   ↓
Check frequency > n/3
   ↓
Add valid elements
   ↓
Remove after adding
   ↓
Return result
```

The most important lines to remember are:

```java
map.put(num, map.get(num) + 1);
```

> Increase the frequency of an existing number.

```java
map.put(num, 1);
```

> Add a new number with frequency `1`.

```java
map.get(nums[i]) > n / 3
```

> Check whether the number satisfies the majority condition.

```java
result.add(nums[i]);
```

> Add the majority element to the answer.

```java
map.remove(nums[i]);
```

> Prevent the same majority element from being added multiple times.

> **Count every element with a HashMap, then select the elements whose frequency is greater than `n / 3`.**

---


Happy Coding! 🚀✨
