# 🚀 128. Longest Consecutive Sequence

> 📂 Topic: Arrays, HashMap  
> 🎯 Pattern: HashMap + Sequence Expansion  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(n)

---

# 🔗 Problem Link

[LeetCode 128 — Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)

---

# 🧠 One-Line Idea

> Store every number in a `HashMap`, then expand from each number in both directions while marking numbers as explored so that no number is processed repeatedly.

---

# 💡 Intuition

The goal is to find the length of the longest consecutive sequence.

For example:

```text
nums = [100, 4, 200, 1, 3, 2]
```

The longest consecutive sequence is:

```text
1 → 2 → 3 → 4
```

Therefore:

```text
Answer = 4
```

---

# 🚫 Why Not Sort?

A simple approach would be:

```java
Arrays.sort(nums);
```

Then we could find consecutive numbers easily.

However, sorting takes:

```text
O(n log n)
```

The problem expects an:

```text
O(n)
```

solution.

So instead, we use a `HashMap`.

---

# 🚀 Approach

## Step 1 — Create a HashMap

```java
Map<Integer, Boolean> checkedMap = new HashMap<>();
```

The map stores:

```text
number → whether it has been explored
```

For:

```text
[100, 4, 200, 1, 3, 2]
```

initially:

```text
100 → false
4   → false
200 → false
1   → false
3   → false
2   → false
```

`false` means:

> We haven't explored this number yet.

---

# Step 2 — Add All Numbers to the Map

```java
for (int i = 0; i < nums.length; i++) {
    checkedMap.put(nums[i], false);
}
```

We use a traditional `for` loop.

For example:

```text
nums = [100, 4, 200, 1, 3, 2]
```

becomes:

```text
checkedMap:

100 → false
4   → false
200 → false
1   → false
3   → false
2   → false
```

The `HashMap` gives us approximately `O(1)` lookup.

So we can quickly check:

```java
checkedMap.containsKey(number)
```

---

# Step 3 — Process Every Number

```java
for (int i = 0; i < nums.length; i++) {
```

For every number, we start with:

```java
int currentLength = 1;
```

Why `1`?

Because the current number itself is already part of the sequence.

For example:

```text
nums[i] = 3
```

already gives us:

```text
3
```

So the initial sequence length is:

```text
1
```

---

# Step 4 — Mark the Current Number as Explored

```java
checkedMap.put(nums[i], true);
```

This means:

> We are currently processing this number, so mark it as explored.

For example:

```text
3 → true
```

This prevents the same number from being counted repeatedly.

---

# ➡️ Step 5 — Check Forward

We calculate:

```java
int nextNum = nums[i] + 1;
```

If:

```text
nums[i] = 1
```

then:

```text
nextNum = 2
```

We now want to check:

```text
1 → 2 → 3 → 4 → ...
```

---

# 🔄 Forward `while` Loop

```java
while (checkedMap.containsKey(nextNum)
        && checkedMap.get(nextNum) == false)
```

This condition has two parts.

### Part 1

```java
checkedMap.containsKey(nextNum)
```

Asks:

> Does the next consecutive number exist in the array?

For example:

```text
nextNum = 2
```

If `2` exists:

```text
true
```

If it doesn't:

```text
false
```

---

### Part 2

```java
checkedMap.get(nextNum) == false
```

Asks:

> Has this number already been explored?

We only want to process it if:

```text
false
```

---

Therefore the complete condition means:

```text
Does nextNum exist?
        AND
Has nextNum not been explored yet?
```

---

# 🔢 What Happens Inside the Forward Loop?

```java
currentLength++;
```

We found another consecutive number.

Then:

```java
checkedMap.put(nextNum, true);
```

Mark it as explored.

Finally:

```java
nextNum++;
```

Move to the next number.

So:

```text
1 → 2 → 3 → 4
```

is processed as:

```text
nextNum = 2
    ↓
length++

nextNum = 3
    ↓
length++

nextNum = 4
    ↓
length++

nextNum = 5
    ↓
5 doesn't exist
    ↓
STOP
```

---

# ⬅️ Step 6 — Check Backward

We also check numbers smaller than the current number.

```java
int prevNum = nums[i] - 1;
```

For example:

```text
nums[i] = 3
```

then:

```text
prevNum = 2
```

We can explore:

```text
3 → 2 → 1
```

---

# 🔄 Backward `while` Loop

```java
while (checkedMap.containsKey(prevNum)
        && checkedMap.get(prevNum) == false)
```

Again, we check:

```text
Does prevNum exist?
        AND
Has it not already been explored?
```

If yes:

```java
currentLength++;
checkedMap.put(prevNum, true);
prevNum--;
```

So:

```text
3 → 2 → 1
```

can be explored in the backward direction.

---

# 📝 Example

Consider:

```text
nums = [100, 4, 200, 1, 3, 2]
```

The important sequence is:

```text
1 → 2 → 3 → 4
```

Suppose we start at:

```text
nums[i] = 3
```

Initially:

```text
currentLength = 1
```

Mark:

```text
3 → true
```

### Forward

```text
3 → 4
```

`4` exists and hasn't been explored.

So:

```text
currentLength = 2
```

Then:

```text
nextNum = 5
```

`5` doesn't exist, so forward exploration stops.

### Backward

Now:

```text
prevNum = 2
```

`2` exists and hasn't been explored.

So:

```text
currentLength = 3
```

Then:

```text
prevNum = 1
```

`1` exists.

So:

```text
currentLength = 4
```

Then:

```text
prevNum = 0
```

`0` doesn't exist.

Stop.

Therefore:

```text
currentLength = 4
```

The sequence is:

```text
1 → 2 → 3 → 4
```

---

# 🧠 Why Mark Numbers as Explored?

This is the key to maintaining `O(n)` time.

Suppose:

```text
nums = [1, 2, 3, 4]
```

Without tracking explored numbers, we could repeatedly do:

```text
1 → 2 → 3 → 4

2 → 3 → 4

3 → 4

4
```

The same numbers would be processed multiple times.

Instead, once we process:

```text
1 → 2 → 3 → 4
```

we mark them:

```text
1 → true
2 → true
3 → true
4 → true
```

Then we don't repeatedly process the same numbers.

This helps keep the total work approximately:

```text
O(n)
```

---

# 📌 Step 7 — Update the Longest Length

After exploring both directions:

```java
longestLength = Math.max(longestLength, currentLength);
```

This keeps track of the longest sequence found so far.

For example:

```text
currentLength = 4
longestLength = 3
```

Then:

```text
longestLength = 4
```

---

# 💻 Java Solution

```java
class Solution {
    public int longestConsecutive(int[] nums) {

        // Stores the longest consecutive sequence found
        int longestLength = 0;

        // Stores each number and whether it has been explored
        Map<Integer, Boolean> checkedMap = new HashMap<>();

        // Initially mark every number as unexplored
        for (int i = 0; i < nums.length; i++) {
            checkedMap.put(nums[i], false);
        }

        // Process every number
        for (int i = 0; i < nums.length; i++) {

            // The current number itself is part of the sequence
            int currentLength = 1;

            // Mark the current number as explored
            checkedMap.put(nums[i], true);

            // Check consecutive numbers in the forward direction
            int nextNum = nums[i] + 1;

            while (checkedMap.containsKey(nextNum)
                    && checkedMap.get(nextNum) == false) {

                // Found another consecutive number
                currentLength++;

                // Mark it as explored
                checkedMap.put(nextNum, true);

                // Move to the next number
                nextNum++;
            }

            // Check consecutive numbers in the backward direction
            int prevNum = nums[i] - 1;

            while (checkedMap.containsKey(prevNum)
                    && checkedMap.get(prevNum) == false) {

                // Found another consecutive number
                currentLength++;

                // Mark it as explored
                checkedMap.put(prevNum, true);

                // Move to the previous number
                prevNum--;
            }

            // Update the longest sequence found so far
            longestLength = Math.max(longestLength, currentLength);
        }

        return longestLength;
    }
}
```

---

# 🎯 Key Takeaway

The entire approach can be remembered as:

```text
Put every number in HashMap
        ↓
Mark everything as unexplored
        ↓
Pick a number
        ↓
Mark it explored
        ↓
Explore num + 1, num + 2, ...
        ↓
Explore num - 1, num - 2, ...
        ↓
Count the sequence
        ↓
Update maximum
```

The most important part is:

```text
HashMap
   ↓
number → explored?
```

and:

```text
nextNum = num + 1
prevNum = num - 1
```

Then keep expanding while the number:

```text
exists
AND
has not been explored
```

---

# 📊 Complexity

### Time Complexity

Every number is inserted into the map once:

```text
O(n)
```

Numbers are explored and marked so that they are not repeatedly processed.

Therefore the overall time complexity is:

```text
O(n)
```

### Space Complexity

The `HashMap` stores all numbers:

```text
O(n)
```

Therefore:

```text
Time  = O(n)
Space = O(n)
```

---

# ⚠️ Important Note

This solution uses a `HashMap<Integer, Boolean>` to explicitly track whether each number has been explored.

A more common LeetCode solution uses a `HashSet<Integer>` and identifies the **start of a sequence** using:

```text
num - 1 does not exist
```

That approach is shorter and also achieves:

```text
O(n) time
O(n) space
```

However, the `HashMap` approach is useful for understanding how **visited/explored tracking** works.

---

> **Use a HashMap to store every number and whether it has been explored, then expand in both directions while counting consecutive numbers and keep the maximum length.**

Happy Coding! 🚀✨
