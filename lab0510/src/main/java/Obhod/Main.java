package Obhod;

import List.TreeNode;

public class Main {

    public static void main(String[] args) {
        class TreeNode{
            int value;
            TreeNode left;
            TreeNode right;
            public TreeNode(int value) {
                this.value = value;
            }
        }
        TreeNode node = new TreeNode(0);
        node.left = new TreeNode(0);
        node.right = new TreeNode(0);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(0);
    }

    public static void countChild(TreeNode current) {
        if (current == null) return;
        System.out.println(current.value);
    }
}