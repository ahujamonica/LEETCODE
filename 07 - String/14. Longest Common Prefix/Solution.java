class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // Store the common prefix
        StringBuilder result = new StringBuilder();

        // Sort the strings
        Arrays.sort(strs);

        // Get the first and last strings
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();

        // Compare characters at the same position
        for(int i = 0; i < first.length; i++){

            // Stop when characters are different
            if(first[i] != last[i]) {
                break;
            }

            // Add matching character to the result
            result.append(first[i]);
        }

        return result.toString();
    }
}
