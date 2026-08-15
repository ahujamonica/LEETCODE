class Solution {
    public int lengthOfLongestSubstring(String s) {

        // Stores characters currently present in the window
        Set<Character> sTrack = new HashSet<>();

        // Stores the maximum length found
        int maxLength = 0;

        // Left pointer of the sliding window
        int left = 0;

        // Move right pointer through the string
        for (int right = 0; right < s.length(); right++) {

            // If the current character already exists,
            // shrink the window from the left
            while (sTrack.contains(s.charAt(right))) {

                // Remove the character at the left side
                sTrack.remove(s.charAt(left));

                // Move left forward
                left++;
            }

            // Add the current character to the window
            sTrack.add(s.charAt(right));

            // Update the maximum window length
            maxLength = Math.max(
                maxLength,
                right - left + 1
            );
        }

        return maxLength;
    }
}
