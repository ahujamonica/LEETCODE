class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // Handle an empty tree
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();
            int lastValue = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // Keep updating; the final value is rightmost
                lastValue = node.val;

                // Add children for the next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Store the rightmost node of this level
            result.add(lastValue);
        }

        return result;
    }
}
