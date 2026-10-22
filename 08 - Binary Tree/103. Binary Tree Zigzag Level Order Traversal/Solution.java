class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) return result;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);

        // false = left to right, true = right to left
        boolean reverse = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {

                if (!reverse) {
                    // Process nodes from front to back
                    TreeNode node = queue.pollFirst();
                    level.add(node.val);

                    // Add children from left to right
                    if (node.left != null) queue.addLast(node.left);
                    if (node.right != null) queue.addLast(node.right);

                } else {
                    // Process nodes from back to front
                    TreeNode node = queue.pollLast();
                    level.add(node.val);

                    // Add children at the front in reverse order
                    if (node.right != null) queue.addFirst(node.right);
                    if (node.left != null) queue.addFirst(node.left);
                }
            }

            // Store the current level
            result.add(level);

            // Switch direction for the next level
            reverse = !reverse;
        }

        return result;
    }
}
