public class Task1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        computeHeight(root);
        printHeights(root);
    }

    private static int computeHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = computeHeight(node.left);
        int rightHeight = computeHeight(node.right);
        node.height = 1 + Math.max(leftHeight, rightHeight);
        return node.height;
    }

    private static void printHeights(TreeNode node) {
        if (node == null) return;
        System.out.println("Узел " + node.val + ": высота = " + node.height);
        printHeights(node.left);
        printHeights(node.right);
    }
}