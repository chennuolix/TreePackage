/**
 * Created by chennuo on 2016/4/22.
 */
public interface Tree<E extends Comparable<E>> {

    //return true if the element in the tree
    public boolean search(E e);

    //return true if the element insert to tree successfully
    public boolean insert(E e);

    //return true if the element is deleted from the tree successfully
    public boolean delete(E e);

    //Inorder traversal from the root
    public void inorder();

    //Postorder traversal from the root
    public void postorder();

    //Preorder traversal from the root
    public void preorder();

    //get the number of nodes in the tree
    public int getSize();

    //return true if the tree is empty
    public boolean isEmpty();

    //return an iterator to traverse element in the tree
    public java.util.Iterator iterator();

}
