# // ★ 901. Stock Spanner ★ \\

🔗 **LeetCode:** https://leetcode.com/problems/online-stock-span/

## 🧠 Intuition

For every new stock price, we need to find how many consecutive previous days had a price `<=` today's price.

The key is to find the **Previous Greater Element**.

Instead of checking backwards every time, we use a **Monotonic Stack**.

The stack stores **indices** of useful previous prices.

```text
Current price
      ↓
Pop all previous prices <= current price
      ↓
Find nearest previous greater price
      ↓
Current index - previous greater index
      ↓
      Span
```

---

## 💡 Approach

We maintain three things:

```java
Stack<Integer> indexStack;
ArrayList<Integer> prices;
int day;
```

- `prices` stores all prices received so far.
- `indexStack` stores indices of previous greater-price candidates.
- `day` represents the current day's index.

For every `next(price)`:

1. Store the current price.
2. Pop all prices from the stack that are `<= price`.
3. If the stack is empty, every previous price is `<=` today's price, so span is `day + 1`.
4. Otherwise, the stack top is the nearest previous greater price.
5. Span = `day - indexStack.peek()`.
6. Push today's index into the stack.

---

## 🧪 Example

For:

```text
100  80  60  70
```

When `70` arrives:

```text
60 <= 70 → pop
80 <= 70 → false
```

So `80` is the previous greater price.

```text
80  → index 1
70  → index 3
```

Therefore:

```text
span = 3 - 1 = 2
```

---

## 💻 Java Solution

```java
class StockSpanner {

    Stack<Integer> indexStack;
    ArrayList<Integer> prices;
    int day;

    public StockSpanner() {
        indexStack = new Stack<>();
        prices = new ArrayList<>();
        day = 0;
    }

    public int next(int price) {

        // Store current price
        prices.add(price);

        // Remove previous prices that are <= current price
        while(!indexStack.isEmpty() &&
              prices.get(indexStack.peek()) <= price) {
            indexStack.pop();
        }

        int span;

        // No previous greater price
        if(indexStack.isEmpty()) {
            span = day + 1;
        }
        else {
            // Distance from previous greater price
            span = day - indexStack.peek();
        }

        // Store current day's index
        indexStack.push(day);

        // Move to next day
        day++;

        return span;
    }
}
```

---

## 🔑 Key Trick

The stack is maintained as a **monotonic decreasing stack**.

```text
        TOP
         ↓
       60
       70
       80
      100
```

When a bigger price arrives, smaller/equal prices are popped because they can no longer be useful.

So:

```text
Pop smaller/equal
        ↓
Find previous greater
        ↓
Calculate distance
```

### One-line memory trick:

> **Stock Span = Previous Greater Element + Distance**

---

## ⏱️ Complexity

- **Time:** `O(n)` amortized
- **Space:** `O(n)`

Each index is pushed once and popped at most once.

---

## 🎯 Key Takeaway

Whenever you see:

> Find the previous greater element / span for every element

Think:

```text
~// ★ Monotonic Stack ★ \\~
```

Store **indices**, remove smaller/equal elements, and use the previous greater index to calculate the span.

> ⭐ If this helped, please consider giving it an upvote! 🚀
