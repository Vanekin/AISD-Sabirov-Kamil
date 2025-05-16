public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public int height;  // Добавляем поле для хранения высоты

    public TreeNode(int val) {
        this.val = val;
        this.height = 0;  // Инициализируем по умолчанию
    }
}