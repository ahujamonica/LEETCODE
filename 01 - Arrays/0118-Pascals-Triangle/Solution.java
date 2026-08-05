class Solution {
    public List<List<Integer>> generate(int numRows) {

        // Stores the complete Pascal's Triangle
        List<List<Integer>> result = new ArrayList<>();

        // Edge case: no rows requested
        if (numRows == 0)
            return result;

        // First row is always [1]
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result.add(firstRow);

        // If only one row is required, return immediately
        if (numRows == 1)
            return result;

        // Generate remaining rows one by one
        for (int i = 1; i < numRows; i++) {

            // Previous row is used to build the current row
            List<Integer> prevRow = result.get(i - 1);

            // Current row under construction
            List<Integer> row = new ArrayList<>();

            // Every row starts with 1
            row.add(1);

            // Compute all middle elements
            for (int j = 0; j < i - 1; j++) {
                row.add(prevRow.get(j) + prevRow.get(j + 1));
            }

            // Every row ends with 1
            row.add(1);

            // Add the completed row to the result
            result.add(row);
        }

        return result;
    }
}
