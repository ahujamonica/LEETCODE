class Solution {
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {

        // Base case: no node to process
        if(root == null) {
            return list;
        }

        // 1. Traverse the left subtree
        postorderTraversal(root.left);

        // 2. Traverse the right subtree
        postorderTraversal(root.right);

        // 3. Visit the current node last
        list.add(root.val);

        return list;
    }
}
