/**
 * Created by chennuo on 2016/4/22.
 */
public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {

    //Inorder traversal from the root
    public void inorder() {
    }

    //Postorder traversal from the root
    public void postorder() {
    }

    //Preorder traversal from the root
    public void preorder() {
    }

    //return true if the tree is empty
    public boolean isEmpty() {
        return getSize() == 0;
    }

    //return an iterator to traverse element in the tree
    public java.util.Iterator iterator(){
        return null;
    }

}
