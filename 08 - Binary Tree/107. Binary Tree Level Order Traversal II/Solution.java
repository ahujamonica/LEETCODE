class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        // Handle an empty tree
        if(root == null) return result;

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();

            // Store values of the current level
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {

                TreeNode node = queue.poll();

                level.add(node.val);

                // Add children for the next level
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }

            // Add the completed level to the result
            result.add(level);
        }

        // Reverse the order of levels, not their values
        Collections.reverse(result);

        return result;
    }
}
