// Approach 1 - Optimal

class Solution {
    public int[][] merge(int[][] intervals) {

        // If there are 0 or 1 intervals, there is nothing to merge
        if(intervals.length <= 1) return intervals;

        // Sort the intervals based on their starting point
        // Example:
        // [15,18], [2,6], [1,3]
        // becomes
        // [1,3], [2,6], [15,18]
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        // Dynamic list to store the merged intervals
        // Each element inside this list is an int[] (one interval)
        List<int[]> result = new ArrayList<>();

        // Start with the first interval as the current merged interval
        // newInterval is just a reference to intervals[0]
        int[] newInterval = intervals[0];

        // Add the first interval to our answer
        result.add(newInterval);

        // Start checking from the second interval
        for(int i = 1; i < intervals.length; i++){

            // Current interval's start <= current merged interval's end
            // This means the two intervals overlap
            if(intervals[i][0] <= newInterval[1]){

                // Extend the merged interval if the current interval
                // ends later than the existing merged interval
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);

            } else{

                // No overlap found

                // Start a new merged interval by making newInterval
                // point to the current interval
                newInterval = intervals[i];

                // Add this new interval to the result
                result.add(newInterval);
            }
        }

        // Convert List<int[]> into int[][] because
        // LeetCode expects a 2D array as the return type
        return result.toArray(new int[result.size()][]);
    }
}
