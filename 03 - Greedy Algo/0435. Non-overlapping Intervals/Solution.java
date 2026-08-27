
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals.length == 0) {
            return 0;
        }

        // Sort intervals by end time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int previous_interval = 0;

        for (int i = 1; i < intervals.length; i++) {

            // Current start >= previous end
            if (intervals[i][0] >= intervals[previous_interval][1]) {
                previous_interval = i;
                count++;
            }
        }

        // Total intervals - intervals kept
        return intervals.length - count;
    }
}
