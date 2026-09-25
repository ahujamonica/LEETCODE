# Don't Overcomplicate the Solution ⚡ | Split + Numeric Revision Comparison

🔗 **LeetCode:** https://leetcode.com/problems/compare-version-numbers/

## 🧠 Intuition

A version number like:

```text
"1.02.10"
```

is made up of separate **revisions**:

```text
1 . 02 . 10
```

We split both versions using `"."` and compare the revisions one by one.

Important points:

- `"01"` and `"001"` both represent `1`.
- If one version has no revision at a particular position, treat it as `0`.
- The first revision that is different decides the answer.

```text
num1 > num2 → return 1
num1 < num2 → return -1
all equal    → return 0
```

## 💡 Approach

### 1. Split both versions

```java
String[] v1 = version1.split("\\.");
String[] v2 = version2.split("\\.");
```

For example:

```text
"1.02.10" → ["1", "02", "10"]
```

### 2. Find the longer version

```java
int maxLength = Math.max(v1.length, v2.length);
```

We need to check every revision that exists in either version.

### 3. Compare revision by revision

For every index:

```java
int num1 = 0;
int num2 = 0;
```

We initially keep both as `0`.

If the revision exists:

```java
if(i < v1.length){
    num1 = Integer.parseInt(v1[i]);
}
```

Otherwise, it stays `0`.

The same is done for `v2`.

### 4. Compare the numbers

```java
if(num1 > num2) return 1;
if(num1 < num2) return -1;
```

If they are equal, move to the next revision.

If every revision is equal:

```java
return 0;
```

## 🧪 Dry Run

```text
version1 = "1.01"
version2 = "1.001"
```

After splitting:

```text
v1 = ["1", "01"]
v2 = ["1", "001"]
```

### i = 0

```text
num1 = 1
num2 = 1

1 == 1
```

Continue.

### i = 1

```text
num1 = Integer.parseInt("01")  → 1
num2 = Integer.parseInt("001") → 1

1 == 1
```

All revisions are equal.

```text
return 0
```

Therefore:

```text
"1.01" == "1.001"
```

## 💻 Java Solution

```java
class Solution {
    public int compareVersion(String version1, String version2) {

        // Split both version strings into revisions
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        // We need to check all revisions from both versions
        int maxLength = Math.max(v1.length, v2.length);

        for(int i = 0; i < maxLength; i++){

            // Missing revisions are treated as 0
            int num1 = 0;
            int num2 = 0;

            // Get revision from version1 if it exists
            if(i < v1.length){
                num1 = Integer.parseInt(v1[i]);
            }

            // Get revision from version2 if it exists
            if(i < v2.length){
                num2 = Integer.parseInt(v2[i]);
            }

            // Compare current revisions
            if(num1 > num2) {
                return 1;
            }

            if(num1 < num2) {
                return -1;
            }
        }

        // All revisions are equal
        return 0;
    }
}
```

## 🔑 Key Trick

The most important idea is:

```text
Split
  ↓
Convert each revision to int
  ↓
Compare same positions
  ↓
Missing revision = 0
  ↓
First difference gives the answer
```

For example:

```text
"1.0"     → 1, 0, 0
"1.0.0.0" → 1, 0, 0, 0
```

So they are equal.

> ⭐ If this helped, please consider giving it an upvote!
