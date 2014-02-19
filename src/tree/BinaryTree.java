package tree;

public class BinaryTree {

    int val;
    BinaryTree left, right;

    public BinaryTree() {
        val = 0;
        left = right = null;
    }

    public BinaryTree(int val) {
        this.val = val;
        left = right = null;
    }

    public void addLeft(BinaryTree node) {
        left = node;
    }

    public void addRight(BinaryTree node) {
        right = node;
    }

    public static BinaryTree deepCopy(BinaryTree root) {

        if (root == null) {
            return null;
        }

        BinaryTree current = new BinaryTree();
        current.val = root.val;
        current.left = deepCopy(root.left);
        current.right = deepCopy(root.right);

        return current;

    }

    public static void printPreOrder(BinaryTree root) {

        if (root == null) {
            return;
        }

        System.out.println(root.val);
        printPreOrder(root.left);
        printPreOrder(root.right);

    }

    public static void printInOrder(BinaryTree root) {

        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.println(root.val);
        printInOrder(root.right);

    }

}
