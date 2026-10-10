
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {

        // Base case: no node to process
        if(root == null) {
            return res;
        }

        // 1. Traverse the left subtree
        inorderTraversal(root.left);

        // 2. Add the current node's value
        res.add(root.val);

        // 3. Traverse the right subtree
        inorderTraversal(root.right);

        return res;
    }
}
