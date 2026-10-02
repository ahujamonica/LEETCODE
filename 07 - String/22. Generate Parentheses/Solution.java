

class Solution {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        // Start with no brackets used
        dfs(n, 0, 0, "");

        return res;
    }

    private void dfs(int n, int open, int closed, String s) {

        // All n opening and n closing brackets are used
        if(open == n && closed == n) {
            res.add(s);
            return;
        }

        // We can add '(' if we still have opening brackets left
        if(open < n) {
            dfs(n, open + 1, closed, s + "(");
        }

        // We can add ')' only if there is an unmatched '('
        if(closed < open) {
            dfs(n, open, closed + 1, s + ")");
        }
    }
}
