// Approach 1 - Brute Force

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

// Approach 2 - Optimal — Three Pointers

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
