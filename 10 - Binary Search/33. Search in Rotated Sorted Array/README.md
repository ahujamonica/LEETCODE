# 🔄 Find the Target in a Rotated Array | Modified Binary Search in Java

🔗 **LeetCode 33:** [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)

## 🧠 Intuition

A sorted array is rotated at some pivot, for example:

```text
Original: [0, 1, 2, 4, 5, 6, 7]
Rotated:  [4, 5, 6, 7, 0, 1, 2]
```

We need to find the target and return its index. If the target doesn't exist, return `-1`.

The key idea is that **at least one half of the array is always sorted**.

We identify the sorted half and check whether the target belongs to it. If it does, search that half; otherwise, search the other half.

## 💡 Approach

1. Initialize `left = 0` and `right = nums.length - 1`.
2. While `left <= right`:
   - Calculate `mid`.
   - If `nums[mid] == target`, return `mid`.
   - If `nums[left] <= nums[mid]`, the **left half is sorted**:
     - If the target lies between `nums[left]` and `nums[mid]`, move `right = mid - 1`.
     - Otherwise, move `left = mid + 1`.
   - Otherwise, the **right half is sorted**:
     - If the target lies between `nums[mid]` and `nums[right]`, move `left = mid + 1`.
     - Otherwise, move `right = mid - 1`.
3. If the target is not found, return `-1`.

## 💻 Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Target found
            if (nums[mid] == target) return mid;

            // Check if the left half is sorted
            if (nums[left] <= nums[mid]) {

                // Is the target inside the sorted left half?
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else {
                // Otherwise, the right half is sorted

                // Is the target inside the sorted right half?
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }
}
```

## 📝 Dry Run

**Input:**

```text
nums = [4, 5, 6, 7, 0, 1, 2]
target = 0
```

| Step | left | right | mid | nums[mid] | Action |
|---|---:|---:|---:|---:|---|
| 1 | 0 | 6 | 3 | 7 | Left half is sorted; target isn't there. Search right. |
| 2 | 4 | 6 | 5 | 1 | Right half is sorted; target is in its range. Search left. |
| 3 | 4 | 4 | 4 | 0 | Target found! |

**Output:** `4`

## 🔑 Key Trick

Remember these four rules:

- Left half sorted + target inside it → `right = mid - 1`
- Left half sorted + target outside it → `left = mid + 1`
- Right half sorted + target inside it → `left = mid + 1`
- Right half sorted + target outside it → `right = mid - 1`

We use `mid + 1` and `mid - 1` because the middle element has already been checked.

## ⏱️ Complexity

- **Time:** \(O(\log n)\) — each step eliminates approximately half the search space.
- **Space:** \(O(1)\) — only a few variables are used.

## 🎯 Key Takeaway

**Find the sorted half → check whether the target belongs there → eliminate the other half.**

If this helped, don't forget to **upvote!** 🚀
