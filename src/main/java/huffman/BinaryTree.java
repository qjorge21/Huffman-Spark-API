package huffman;

public class BinaryTree<E> {


    private BinaryTree<E> left, right;
    private E data;

    public BinaryTree(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /* Getters & setters */
    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public boolean isLeafNode() {
        return left == null && right == null;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public boolean equals(Object other) {
        if (other instanceof BinaryTree<?>) {
            @SuppressWarnings("unchecked")
            BinaryTree<E> t = (BinaryTree<E>) other;
            return (hasLeftChild() == t.hasLeftChild() && hasRightChild() == t.hasRightChild()
                    && data.equals(t.data) && (!hasLeftChild() || left.equals(t.left))
                    && (!hasRightChild() || right.equals(t.right)));
        } else
            return false;
    }

    public String toString() {
        return buildString("");
    }

    private String buildString(String indent) {
        String ret = "";

        if (hasRightChild())
            ret += right.buildString(indent + "  ");
        ret += indent + data + "\n";
        if (hasLeftChild())
            ret += left.buildString(indent + "  ");
        return ret;
    }
}
