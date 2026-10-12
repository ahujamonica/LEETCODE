class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // Handle an empty tree
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();

            // Supports levels containing only negative values
            int largestValue = Integer.MIN_VALUE;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // Update the maximum value at this level
                largestValue = Math.max(largestValue, node.val);

                // Add children for the next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Store the maximum value of this level
            result.add(largestValue);
        }

        return result;
    }
}
