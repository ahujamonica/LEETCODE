# Technical Assessment Summary & Solutions

This document summarizes the two core questions covered in the assessment session:
1. **DSA Coding Question:** Array Propagation Cost Minimization (`getMinCost`)
2. **Repository Debugging Task:** Spring Boot Issue Tracker Comment Management

---

## 1. DSA Coding Problem: `getMinCost`

### Problem Description
You are given an array of integers $arr$ of length $n$. You can perform two types of operations to make all elements in the array equal:
1. **Left Propagation:** Choose index $i$ ($1 \le i \le n-1$), and set all elements at indices $0, 1, \dots, i-1$ to $arr[i]$. The cost incurred is $i \times arr[i]$.
2. **Right Propagation:** Choose index $i$ ($0 \le i \le n-2$), and set all elements at indices $i+1, i+2, \dots, n-1$ to $arr[i]$. The cost incurred is $(n - 1 - i) \times arr[i]$.

The goal is to find the **minimum total cost** required to make all elements in the array identical.

---

### Key Intuition & Optimal Approach
* Any valid end state results in the entire array taking the value of some element already present in the array.
* Instead of evaluating individual elements or only considering the global minimum, consider **maximal contiguous subarrays $[l, r]$** of identical elements where:
  $$arr[l] = arr[l+1] = \dots = arr[r] = V$$
* For any such maximal block $[l, r]$:
  * **Left sweep:** If $l > 0$, the prefix $0 \dots l-1$ must be overwritten using index $l$ at cost:
    $$\text{cost}_{\text{left}} = l \times V$$
    If $l = 0$, the prefix is already homogeneous ($\text{cost}_{\text{left}} = 0$).
  * **Right sweep:** If $r < n - 1$, the suffix $r+1 \dots n-1$ must be overwritten using index $r$ at cost:
    $$\text{cost}_{\text{right}} = (n - 1 - r) \times V$$
    If $r = n - 1$, the suffix is already homogeneous ($\text{cost}_{\text{right}} = 0$).
  * **Total block cost:** $\text{cost}_{\text{left}} + \text{cost}_{\text{right}}$.
* By scanning the array linearly and testing each maximal contiguous block in a single pass, we achieve an optimal $O(n)$ time complexity and $O(1)$ auxiliary space complexity.

---

### Complexity Analysis
* **Time Complexity:** $O(n)$ — Each index is visited at most twice during two-pointer segment traversal.
* **Space Complexity:** $O(1)$ — Only constant auxiliary state is maintained.
* **Large Input Handling:** Casts to `long` prevent 32-bit signed integer overflow when $n = 2 \times 10^5$ and $arr[i] = 10^9$.

---

## 2. Code Repository Task: Spring Boot Issue Tracker (Question 2)

### Task Overview
* **Component:** Issue comments backend module (`CommentService.java`).
* **Symptom:** When a user creates a new comment or modifies an existing comment, the UI does not display the new content, user metadata, or updated status.

---

### Root Cause Analysis

1. **Comment Creation (`createComment`):**
   * The user identity was not being attached to the `Comment` entity prior to persisting.
   * `commentRepository.save(...)` was omitted, so the new comment was never written to the database.
   * The endpoint was returning an empty DTO builder (`CommentResponse.builder().build()`) instead of the persisted data mapped via `convertCommentToDTO`.

2. **Comment Updating (`updateComment`):**
   * Although `isEdited(true)` was set, `comment.setContent(request.getContent())` was missing; the stored text was never modified.
   * Changes were not persisted via `commentRepository.save(...)`.
   * Return type inconsistencies (`CommentResponse` vs. `CommentDTO`) caused compilation and serialization issues.

---
