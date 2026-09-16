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
