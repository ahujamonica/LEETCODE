13. Roman to Integer

🔗 **LeetCode:** https://leetcode.com/problems/roman-to-integer/

## 🧠 Intuition

The main idea is to compare the **current Roman numeral** with the **next Roman numeral**.

Normally, we add the values:

```text
VI = 5 + 1 = 6
```

But when a smaller value comes before a larger value, we subtract it:

```text
IV = -1 + 5 = 4
IX = -1 + 10 = 9
```

So the rule is:

```text
current < next  → subtract current
current >= next → add current
```

That's the entire trick.

## 💡 Approach

1. Store the value of each Roman character in a `HashMap`.
2. Traverse the string from left to right.
3. Get the current character's value as `curr`.
4. Get the next character's value as `next`.
5. If `curr < next`, subtract `curr`.
6. Otherwise, add `curr`.
7. For the last character, there is no next character, so `next = 0`.

For example:

```text
MCMIV

M → 1000, next = 100   → +1000
C → 100,  next = 1000  → -100
M → 1000, next = 1      → +1000
I → 1,    next = 5      → -1
V → 5,    next = 0      → +5

1000 - 100 + 1000 - 1 + 5 = 1904
```

## 💻 Java Solution

```java
class Solution {
    public int romanToInt(String s) {

        Map<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ans = 0;

        for(int i = 0; i < s.length(); i++){

            // Value of current character
            int curr = map.get(s.charAt(i));

            // Value of next character
            int next = 0;

            // Check if next character exists
            if(i + 1 < s.length()){
                next = map.get(s.charAt(i + 1));
            }

            // Smaller value before larger value → subtract
            if(curr < next){
                ans = ans - curr;
            }
            else{
                ans = ans + curr;
            }
        }

        return ans;
    }
}
```

## 🧪 Dry Run

For:

```text
s = "IV"
```

### i = 0

```text
curr = I = 1
next = V = 5

1 < 5
→ subtract 1

ans = -1
```

### i = 1

```text
curr = V = 5
next = 0

5 < 0 ❌
→ add 5

ans = -1 + 5
    = 4
```

Output:

```text
4
```

## 🔑 Key Trick

```text
Current < Next
      ↓
   SUBTRACT

Current >= Next
      ↓
      ADD
```

This automatically handles:

```text
IV → 4
IX → 9
XL → 40
XC → 90
CD → 400
CM → 900
```

No need to write separate conditions for these cases.

## ⏱️ Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

Only 7 Roman numeral mappings are stored.

## 🎯 Key Takeaway

> **Roman to Integer = Compare the current value with the next value.**

```text
Smaller before bigger → subtract
Otherwise             → add
```

⭐ If this helped, please consider giving it an upvote!
