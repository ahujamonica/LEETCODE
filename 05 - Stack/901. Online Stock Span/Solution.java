class StockSpanner {

    // Stores indices of previous prices.
    // The stack is MONOTONIC DECREASING by price.
    // TOP = nearest previous GREATER price.
    Stack<Integer> indexStack;

    // Stores all prices because the stack stores only their indices.
    ArrayList<Integer> prices;

    // Current day's index: 0, 1, 2, 3...
    int day;

    public StockSpanner() {
        indexStack = new Stack<>();
        prices = new ArrayList<>();
        day = 0;
    }

    public int next(int price) {

        // Save today's price.
        // We need this later when we have an index from the stack.
        prices.add(price);


        // MONOTONIC STACK TRICK:
        //
        // Remove all previous prices that are <= today's price.
        //
        // Why?
        // If today's price is >= a previous price, that previous
        // price can never be the "previous greater price" for
        // future elements, so we can safely remove it.
        //
        // After this loop:
        // - stack is empty → no previous greater price exists
        // - stack is not empty → stack.peek() is the nearest
        //   previous greater price
        while(!indexStack.isEmpty() &&
              prices.get(indexStack.peek()) <= price) {

            indexStack.pop();
        }


        int span;


        // If the stack is empty:
        //
        // No previous price is greater than today's price.
        // Therefore, today's span includes ALL previous days
        // plus today.
        //
        // Example:
        // [100, 80, 60, 70, 60, 75]
        // If today's price is 75, we stop at 80.
        //
        // If there is no greater price at all:
        // span = number of days = day + 1
        if(indexStack.isEmpty()) {

            span = day + 1;
        }

        else {

            // The top of the stack is the index of the
            // nearest previous GREATER price.
            //
            // Example:
            //
            // index:  0   1   2   3
            // price: 100  80  60  70
            //                     ↑ today
            //
            // Previous greater = 80 at index 1
            //
            // span = current index - previous greater index
            //      = 3 - 1
            //      = 2
            span = day - indexStack.peek();
        }


        // Today's index may be useful for future prices.
        // Push it AFTER calculating today's span.
        indexStack.push(day);


        // Move to the next day.
        day++;


        // Return the span for today's price.
        return span;
    }
}
