class Solution {
    public boolean isAnagram(String s, String t) {

        // Convert both strings to lowercase
        s = s.toLowerCase();
        t = t.toLowerCase();

        // Remove whitespace
        s = s.replaceAll("\\s", "");
        t = t.replaceAll("\\s", "");

        // Frequency array for 26 lowercase letters
        int[] count = new int[26];

        // Add frequency of characters in s
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }

        // Subtract frequency of characters in t
        for(int i = 0; i < t.length(); i++){
            count[t.charAt(i) - 'a']--;
        }

        // Check if all frequencies became zero
        for(int i = 0; i < count.length; i++){
            if(count[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
