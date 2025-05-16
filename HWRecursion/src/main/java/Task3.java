import java.util.List;
import java.util.ArrayList;

class Node {
    int val;
    List<Node> children;
    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class Task3 {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        node2.children.add(new Node(4));
        node2.children.add(new Node(5));
        Node node3 = new Node(3);
        node3.children.add(new Node(6));
        root.children.add(node2);
        root.children.add(node3);
        System.out.println(serialize(root));
    }
    public static String serialize(Node root) {
        if (root == null) return "NULL";
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private static void buildString(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append("NULL");
            return;
        }
        sb.append("(").append(node.val).append(", ");
        if (node.children.isEmpty()) {
            sb.append("NULL");
        } else {
            for (int i = 0; i < node.children.size(); i++) {
                buildString(node.children.get(i), sb);
                if (i < node.children.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(")");
    }
}