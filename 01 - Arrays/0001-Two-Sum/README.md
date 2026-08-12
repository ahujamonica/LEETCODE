# 🚀 1. Two Sum

> 📂 Topic: Arrays, HashMap
> 🎯 Pattern: Complement Lookup
> ⭐ Difficulty: Easy
> ⏱️ Optimal Time: O(n) | 💾 Space: O(n)

---

# 🔗 Problem Link

[LeetCode 1 — Two Sum](https://leetcode.com/problems/two-sum/)

---

# 🧠 HashMap — One-Line Idea

> For every number, calculate the number needed to reach the target and check whether that number has already been seen using a HashMap.

---

# 💡 Intuition

We need to find two numbers whose sum equals the given `target`.

For example:

```text
nums = [2, 7, 11, 15]
target = 9
```

We need:

```text
2 + 7 = 9
```

Therefore:

```text
Answer = [0, 1]
```

Instead of checking every possible pair using two loops, we can use a `HashMap`.

The key idea is:

```text
current number + required number = target
```

So:

```text
required number = target - current number
```

For example:

```text
target = 9
current = 7

required = 9 - 7
         = 2
```

If we have already seen `2`, we have found the pair.

---

# 🚀 Approach — HashMap

We store:

```text
number → index
```

inside the HashMap.

For example:

```text
2 → 0
7 → 1
```

This allows us to quickly find the index of a number we have already seen.

The process is:

```text
Current number
      ↓
Calculate required number
      ↓
target - current
      ↓
Have we seen it before?
      ↓
   YES       NO
    ↓         ↓
 Return     Store current
 indices    number + index
```

---

# 📝 Dry Run

Consider:

```text
nums = [2, 7, 11, 15]
target = 9
```

Initially:

```text
HashMap = {}
```

---

## Iteration 1

```text
i = 0
num = 2
```

Calculate:

```text
moreNeeded = target - num
           = 9 - 2
           = 7
```

Check:

```text
Is 7 present in the HashMap?
NO
```

So store:

```text
2 → 0
```

HashMap:

```text
{
    2 → 0
}
```

---

## Iteration 2

```text
i = 1
num = 7
```

Calculate:

```text
moreNeeded = 9 - 7
           = 2
```

Check:

```text
Is 2 present in the HashMap?
YES
```

The HashMap contains:

```text
2 → 0
```

Therefore:

```text
nums[0] + nums[1]
= 2 + 7
= 9
```

Return:

```text
[1, 0]
```

The order of the indices does not matter.

---

# 🔍 What Does Each Important Line Do?

### Create the HashMap

```java
HashMap<Integer, Integer> mpp = new HashMap<>();
```

Stores:

```text
Key   → number
Value → index
```

For example:

```text
2 → 0
```

means number `2` was found at index `0`.

---

### Get the current number

```java
int num = nums[i];
```

Stores the current array element in `num`.

---

### Find the required number

```java
int moreNeeded = target - num;
```

This calculates the number required to reach the target.

For:

```text
target = 9
num = 7
```

we get:

```text
moreNeeded = 9 - 7
           = 2
```

So we need a `2`.

---

### Check if we have already seen it

```java
mpp.containsKey(moreNeeded)
```

This asks:

> Have we already encountered the number we need?

HashMap lookup takes `O(1)` average time.

---

### Get the index

```java
mpp.get(moreNeeded)
```

If:

```text
mpp = {
    2 → 0
}
```

then:

```java
mpp.get(2)
```

returns:

```text
0
```

---

### Return the indices

```java
return new int[]{i, mpp.get(moreNeeded)};
```

Returns:

```text
current index
+
index of required number
```

For our example:

```text
i = 1
mpp.get(2) = 0
```

So:

```text
[1, 0]
```

---

### Store the current number

If the pair has not been found yet:

```java
mpp.put(num, i);
```

This stores:

```text
number → index
```

For example:

```text
mpp.put(2, 0);
```

creates:

```text
2 → 0
```

---

# 🛑 Why Does `return` Stop the Loop?

When we find the pair:

```java
if (mpp.containsKey(moreNeeded)) {
    return new int[]{i, mpp.get(moreNeeded)};
}
```

`return` exits the **entire method**, not just the `if` block.

Therefore:

```text
Pair found
    ↓
return
    ↓
for loop stops
    ↓
method ends
```

We don't need to continue searching because the problem asks for one valid pair.

The final:

```java
return new int[]{};
```

is reached only if the entire loop finishes without finding a pair.



---

# 🤔 Why Don't We Need `mpp.get(moreNeeded) != i`?

You may sometimes see:

```java
if (mpp.containsKey(moreNeeded) &&
    mpp.get(moreNeeded) != i)
```

But the second condition is unnecessary here.

We check the HashMap **before** storing the current element:

```java
if (mpp.containsKey(moreNeeded)) {
    ...
}

mpp.put(num, i);
```

Therefore, the current index `i` has not been inserted yet.

If `moreNeeded` exists in the map, it must belong to an earlier index.

So this is enough:

```java
if (mpp.containsKey(moreNeeded))
```

---

# 🎯 Key Takeaway

Remember the pattern:

```text
For every number:

1. Calculate:
   
   target - current

2. Check if the required number
   already exists in the HashMap.

3. If YES:
   
   Return both indices.

4. If NO:
   
   Store current number + index.
```

The core code is:

```java
int moreNeeded = target - nums[i];

if (mpp.containsKey(moreNeeded)) {
    return new int[]{i, mpp.get(moreNeeded)};
}

mpp.put(nums[i], i);
```

The mental model is:

```text
Current number
      +
Already-seen required number
      =
Target
```

---

# 📊 Complexity

We traverse the array once.

Each HashMap operation:

```text
containsKey()
get()
put()
```

takes `O(1)` average time.

Therefore:

```text
Time Complexity  = O(n)
Space Complexity = O(n)
```

The HashMap can store up to `n` elements in the worst case.

---

> **Use a HashMap to remember previously seen numbers. For every current number, calculate `target - current` and check whether that number has already been seen.**

Happy Coding! 🚀✨
