import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by chennuo on 2016/4/22.
 */
public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E> {


    protected TreeNode<E> root;
    protected int size;

    public BinaryTree() {
    }


    //create a binary search tree from array of objects
    public BinaryTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }


    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {            //element matches current.element
                return true;    //Element is found
            }
        }
        return false;
    }


    /**
     * Insert element e into the binary serach tree
     * return true if the element is inserted successfully
     */
    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = null;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true;
    }

    private TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }


    /**
     * inorder the tree
     */
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * postorder thr tree
     */
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * preorder the tree
     */
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * delete an element from the binary search tree
     * return true if the element is deleted successfully
     * return false if the element is not in the tree
     *
     * @param e
     * @return
     */
    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = null;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        if (current == null) {
            //the element is not in the tree
            return false;
        }

        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            /**
             * not understand
             */
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--;
        return true;
    }


    @Override
    public int getSize() {
        return size;
    }

    /**
     * return the root of the tree
     *
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * return a path from thr root leading to the specified element
     *
     * @param e
     * @return
     */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
        TreeNode<E> current = root;   //start from the root

        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list;
    }

    /**
     * obtain an iterator   use inorder
     * @return
     */
    public Iterator iterator() {
        return inorderIterator();
    }

    public Iterator inorderIterator() {
        return new InorderIterator();
    }

    class InorderIterator implements Iterator {

        private ArrayList<E> list = new ArrayList<E>();
        private int current = 0;

        public InorderIterator() {
            inorder();
        }

        private void inorder() {
            inorder(root);
        }

        /**
         * inorder traversal from a subtree
         * @param root
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return list.get(current++);
        }

        /**
         * remove the current element and refresh the list
         */
        @Override
        public void remove() {
            delete(list.get(current));
            list.clear();
            inorder();
        }
    }

    /**
     * remove all element from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
