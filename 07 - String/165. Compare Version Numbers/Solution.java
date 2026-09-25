class Solution {
    public int compareVersion(String version1, String version2) {

        // Split both version strings into revisions
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        // We need to check all revisions from both versions
        int maxLength = Math.max(v1.length, v2.length);

        for(int i = 0; i < maxLength; i++){

            // Missing revisions are treated as 0
            int num1 = 0;
            int num2 = 0;

            // Get revision from version1 if it exists
            if(i < v1.length){
                num1 = Integer.parseInt(v1[i]);
            }

            // Get revision from version2 if it exists
            if(i < v2.length){
                num2 = Integer.parseInt(v2[i]);
            }

            // Compare current revisions
            if(num1 > num2) {
                return 1;
            }

            if(num1 < num2) {
                return -1;
            }
        }

        // All revisions are equal
        return 0;
    }
}
