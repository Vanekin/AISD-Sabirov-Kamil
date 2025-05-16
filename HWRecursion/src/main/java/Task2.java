public class Task2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(serialize(root));
    }

    public static String serialize(TreeNode root) {
        if (root == null) return "NULL";
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private static void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("NULL");
            return;
        }
        sb.append("(");
        sb.append(node.val);
        sb.append(", ");
        buildString(node.left, sb);
        sb.append(", ");
        buildString(node.right, sb);
        sb.append(")");
    }

}