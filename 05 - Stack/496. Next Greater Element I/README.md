
# 🔥 496. Next Greater Element I | Monotonic Stack Trick

🔗 **LeetCode:** https://leetcode.com/problems/next-greater-element-i/

## 🧠 Intuition

For every element in `nums1`, we need to find the **first greater element on its right in `nums2`**.

Instead of searching to the right for every element separately, we process `nums2` from **right to left** using a Stack.

The Stack keeps only the elements that can potentially be the **Next Greater Element**.

Once we calculate the answer for every element in `nums2`, we store it in:

```java
nextGreater[]
```

Then we simply use `nums1[i]` as an index to look up its answer.

---

## 💡 Approach

### Step 1: Traverse `nums2` from Right → Left

Why?

Because we are looking for elements on the **right**.

```text
nums2 = [1, 3, 4, 2]

Start:

2 → 4 → 3 → 1
```

---

### Step 2: Remove Smaller or Equal Elements

For the current element:

```java
while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
    stack.pop();
}
```

Any element smaller than or equal to the current element cannot be its Next Greater Element.

So we remove it.

---

### Step 3: Find the Next Greater Element

After popping:

```text
Stack empty?
    ↓
   Yes → -1

   No → stack.peek()
```

So:

```java
if (stack.isEmpty()) {
    nextGreater[nums2[i]] = -1;
} else {
    nextGreater[nums2[i]] = stack.peek();
}
```

---

### Step 4: Push the Current Element

After finding its answer:

```java
stack.push(nums2[i]);
```

The current element can now become a candidate for elements further to the left.

---

### Step 5: Answer `nums1`

We have already calculated the answer for every value in `nums2`.

So for each element in `nums1`:

```java
nums1[i] = nextGreater[nums1[i]];
```

This simply looks up the precomputed answer.

---

## 💻 Java Solution

```java
class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // nextGreater[value] stores the
        // Next Greater Element of that value
        int[] nextGreater = new int[10001];

        // Stack stores possible greater elements
        Stack<Integer> stack = new Stack<>();

        // Traverse nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {

            // Remove elements that are smaller than
            // or equal to the current element.
            // They cannot be the Next Greater Element.
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }

            // If stack is empty, there is no greater
            // element on the right
            if (stack.isEmpty()) {
                nextGreater[nums2[i]] = -1;
            } 
            
            // Otherwise, the top of the stack is
            // the Next Greater Element
            else {
                nextGreater[nums2[i]] = stack.peek();
            }

            // Add current element to the stack
            // so it can be a candidate for elements
            // to its left
            stack.push(nums2[i]);
        }

        // Replace every element in nums1 with
        // its precomputed Next Greater Element
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nextGreater[nums1[i]];
        }

        // Return the final answer
        return nums1;
    }
}
```

---

## 🧪 Dry Run

```text
nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]
```

Process `nums2` from right to left.

### `2`

```text
Stack = []

No greater element

nextGreater[2] = -1

Stack = [2]
```

### `4`

```text
Stack = [2]

2 <= 4 → pop

Stack = []

No greater element

nextGreater[4] = -1

Stack = [4]
```

### `3`

```text
Stack = [4]

4 > 3

nextGreater[3] = 4

Stack = [4, 3]
```

### `1`

```text
Stack = [4, 3]

3 > 1

nextGreater[1] = 3

Stack = [4, 3, 1]
```

So our lookup table is:

```text
1 → 3
2 → -1
3 → 4
4 → -1
```

---

## 🔍 Now Process `nums1`

```text
nums1 = [4, 1, 2]
```

For `4`:

```java
nextGreater[4] → -1
```

For `1`:

```java
nextGreater[1] → 3
```

For `2`:

```java
nextGreater[2] → -1
```

Therefore:

```text
Answer = [-1, 3, -1]
```

---

## 🔑 Key Trick

The most important part is:

```java
while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
    stack.pop();
}
```

Think:

```text
Current element
      ↓
Remove smaller/equal elements
      ↓
Check Stack
      ↓
Stack empty → -1
Stack not empty → Stack top
      ↓
Push current element
```

This is the **Monotonic Stack pattern**.

---

## 🧠 Why Right → Left?

We need:

```text
Next Greater Element → on the RIGHT
```

So by traversing from right to left:

```text
[1, 3, 4, 2]
          ← ← ←
```

when we reach an element, everything to its right has already been processed.

The Stack therefore contains useful candidates from the right side.

---

## 🎯 Key Takeaway

Remember this pattern:

```text
Next Greater Element
        ↓
Traverse Right → Left
        ↓
Use Stack
        ↓
Pop smaller/equal elements
        ↓
Stack top = Next Greater Element
        ↓
Push current element
```

### 🧠 One-Line Memory Trick

> **Pop the useless elements, then the Stack top is your answer.**

And once all answers are calculated:

```java
nums1[i] = nextGreater[nums1[i]];
```

simply performs a **lookup** instead of searching `nums2` again.

---

## ⏱️ Complexity

```text
Time → O(nums1.length + nums2.length)
Space → O(nums2.length)
```

Each element in `nums2` is pushed once and popped at most once.

> ⭐ If this helped you understand the Monotonic Stack approach, an upvote would be appreciated! ❤️
