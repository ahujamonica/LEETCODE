class Solution {
    public int repeatedStringMatch(String A, String B) {

        // Start with one copy of A
        int count = 1;

        // temp initially contains A
        StringBuilder temp = new StringBuilder(A);

        // Keep adding A until temp is at least as long as B
        while(temp.length() < B.length()) {
            temp.append(A);
            count++;
        }

        // Check if B is already present
        if(temp.toString().contains(B)) {
            return count;
        }

        // B may cross the boundary between two copies of A
        temp.append(A);

        if(temp.toString().contains(B)) {
            return count + 1;
        }

        // B cannot be formed
        return -1;
    }
}
