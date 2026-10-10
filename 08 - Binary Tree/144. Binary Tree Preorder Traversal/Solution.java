
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {

        // Base case: no node to process
        if(root == null) {
            return res;
        }

        // 1. Visit the current node first
        res.add(root.val);

        // 2. Traverse the left subtree
        preorderTraversal(root.left);

        // 3. Traverse the right subtree
        preorderTraversal(root.right);

        return res;
    }
}
