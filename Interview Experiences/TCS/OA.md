# Question 1: Find a Pair With the Given Target Sum

## Problem Statement
You are given an integer array of size `N` and an integer `T` representing the target sum. Your task is to find two distinct elements in the array whose sum is equal to the target.

If a valid pair exists, print the pair of elements. If no such pair exists, print `-1`.

If multiple pairs exist, print the first pair found while traversing the array from left to right.

## Input Format
- The first line contains two integers, `N` and `T`, representing the size of the array and the target sum.
- The second line contains `N` space-separated integers representing the array elements.

## Output Format
Print the two elements whose sum equals the target. If no valid pair exists, print `-1`.

## Sample Input
5 9
2 7 11 15 4

## Sample Output
2 7

## Explanation
The given array is `[2, 7, 11, 15, 4]`, and the target sum is `9`.

Since `2 + 7 = 9`, the pair `2 7` is printed.

## Constraints
- `2 <= N <= 10^5`
- `-10^9 <= arr[i] <= 10^9`
- `-10^9 <= T <= 10^9`

## Expected Approach
Use nested loops to check every possible pair of distinct elements.

## Complexity
- **Time Complexity:** O(N²)
- **Space Complexity:** O(1)

---

# Question 2: Sort an Array Using Selection Sort

## Problem Statement
You are given an integer array containing `N` elements. Sort the array in ascending order using the Selection Sort algorithm.

Selection Sort repeatedly finds the smallest element in the unsorted portion of the array and swaps it with the first element of that portion.

## Input Format
- The first line contains an integer `N`, representing the number of elements in the array.
- The second line contains `N` space-separated integers representing the array elements.

## Output Format
Print the elements of the array in ascending order, separated by spaces.

## Sample Input
5
64 25 12 22 11

## Sample Output
11 12 22 25 64

## Explanation
Initially, the array is:

`[64, 25, 12, 22, 11]`

**Pass 1:** Find the minimum element, `11`, and swap it with `64`.

`[11, 25, 12, 22, 64]`

**Pass 2:** Find the minimum element in the remaining unsorted portion, `12`, and swap it with `25`.

`[11, 12, 25, 22, 64]`

**Pass 3:** Find the minimum element, `22`, and swap it with `25`.

`[11, 12, 22, 25, 64]`

**Pass 4:** The remaining elements are already sorted.

Final sorted array:

`[11, 12, 22, 25, 64]`

## Constraints
- `1 <= N <= 10^3`
- `-10^9 <= arr[i] <= 10^9`

## Expected Approach
1. Use an outer loop with variable `p` to select the current position.
2. Initialize the minimum element's index to `p`.
3. Use an inner loop with variable `q`, starting from `p + 1`, to traverse the remaining unsorted elements.
4. Update the minimum index whenever a smaller element is found.
5. Swap the minimum element with the element at index `p`.
6. Repeat until the array is sorted.

## Complexity
- **Time Complexity:** O(N²)
- **Space Complexity:** O(1)
