
class Solution {
    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int children = 0;
        int cookie = 0;

        while (cookie < s.length && children < g.length) {

            if (s[cookie] >= g[children]) {
                children++;
            }

            cookie++;
        }

        return children;
    }
}
