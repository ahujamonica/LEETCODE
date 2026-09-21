# Don't Overcomplicate the Solution ⚡ | String Parsing + Overflow Handling

🔗 **LeetCode:** https://leetcode.com/problems/string-to-integer-atoi/

## 🧠 Intuition

The goal is to convert a string into an integer while following a few rules:

```text
1. Remove leading spaces
2. Check for + or -
3. Read digits one by one
4. Stop at the first non-numeric character
5. Handle integer overflow
```

For example:

```text
"   -123abc"
     ↓
   "-123abc"
     ↓
   sign = -1
     ↓
   read 1 → 12 → 123
     ↓
   'a' → stop
     ↓
   return -123
```

The important variable is `res`.

It keeps storing the number we have built so far:

```text
1 → 12 → 123
```

When we encounter a non-digit, `break` exits the `while` loop, and `res` still contains the number we need to return.

## 💡 Approach

### 1. Remove leading spaces

```java
s = s.trim();
```

### 2. Handle the sign

```text
'-' → sign = -1
'+' → sign = 1
```

We also move `i` forward so that it points to the first digit.

### 3. Read the digits

For every character:

```java
if(ch < '0' || ch > '9')
```

If it is not a digit, we use:

```java
break;
```

This exits the `while` loop.

Otherwise, we add the digit to `res`:

```java
res = res * 10 + (ch - '0');
```

For example:

```text
res = 0

'1' → 0 * 10 + 1 = 1
'2' → 1 * 10 + 2 = 12
'3' → 12 * 10 + 3 = 123
```

### 4. Check overflow

Java `int` can only store:

```text
-2,147,483,648 to 2,147,483,647
```

So if the result goes beyond these limits, return the corresponding boundary value.

### 5. Return the final signed number

```java
return (int) (res * sign);
```

## 🧪 Dry Run

### Input

```text
s = "   -123abc"
```

After `trim()`:

```text
"-123abc"
```

Sign:

```text
sign = -1
i = 1
```

Now process the digits:

```text
'1' → res = 1
'2' → res = 12
'3' → res = 123
```

Now:

```text
"123abc"
   ↑
   a
```

`a` is not a digit:

```java
if(ch < '0' || ch > '9')
```

So:

```java
break;
```

The `while` loop stops.

But:

```text
res = 123
sign = -1
```

Therefore:

```text
res * sign
= 123 × -1
= -123
```

Output:

```text
-123
```

## 💻 Java Solution

```java
class Solution {
    public int myAtoi(String s) {

        // Remove leading and trailing spaces
        s = s.trim();

        // sign stores +1 or -1
        // i tells us which character we are currently reading
        int i = 0, sign = 1;

        // Store the number while building it
        // long helps us handle overflow safely
        long res = 0;

        // Empty string after trim
        if(s.length() == 0) {
            return 0;
        }

        // Check for negative sign
        if(s.charAt(0) == '-') {
            sign = -1;
            i++;
        }
        // Check for positive sign
        else if(s.charAt(0) == '+') {
            i++;
        }

        // Read digits
        while(i < s.length()) {

            char ch = s.charAt(i);

            // Stop at the first non-digit character
            if(ch < '0' || ch > '9') {
                break;
            }

            // Convert character to digit and add it to res
            res = res * 10 + (ch - '0');

            // Handle positive overflow
            if(sign * res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            // Handle negative overflow
            if(sign * res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            // Move to the next character
            i++;
        }

        // Apply the sign and return the result
        return (int) (res * sign);
    }
}
```

## 🔑 Key Trick

The most important line is:

```java
res = res * 10 + (ch - '0');
```

It builds the number digit by digit.

```text
"123"

0 → 1
1 → 12
12 → 123
```

And this:

```java
if(ch < '0' || ch > '9') {
    break;
}
```

means:

> **Stop reading as soon as we find a non-numeric character.**

The already-built number remains safely stored in `res`.

## ⏱️ Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

## 🎯 Key Takeaway

```text
Trim
 ↓
Sign
 ↓
Read digits
 ↓
Build res
 ↓
Stop at non-digit
 ↓
Check overflow
 ↓
Apply sign
```

> **Don't overcomplicate atoi — it's just careful string parsing + number construction + overflow handling.**

⭐ If this helped, please consider giving it an upvote!
