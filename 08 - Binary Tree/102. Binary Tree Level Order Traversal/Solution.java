class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        // Handle an empty tree
        if(root == null) return result;

        // Queue processes nodes level by level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();

            // Stores values for this level
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {

                // Remove the front node from the queue
                TreeNode node = queue.poll();

                // Add its value to the current level
                level.add(node.val);

                // Add children for the next level
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }

            // Store the completed level
            result.add(level);
        }

        return result;
    }
}
