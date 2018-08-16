package lazyTrees;

import java.util.NoSuchElementException;


public class LazySearchTree<E extends Comparable<? super E>>
        implements Cloneable {
    protected int mSize;
    protected LazySTNode mRoot;
    protected int mSizeHard;


    public LazySearchTree() {
        clear();
    }

    public boolean empty() {
        return (mSize == 0);
    }

    public int size() {
        return mSize;
    }

    public int sizeHard() {
        return mSizeHard;
    }

    public void clear() {
        mSize = 0;
        mRoot = null;
    }

    public E findMin() { //finds the Minimum
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    protected LazySTNode findMin(LazySTNode root) { //checks if the left branch if empty
        if (root == null)
            return null;
        if (root.lftChild == null)
            return root;
        return findMin(root.lftChild);
    }

    public E findMax() { //finds maximum
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }


    protected LazySTNode findMax(LazySTNode root) { //checks the right root to see if empty
        if (root == null)
            return null;
        if (root.rtChild == null)
            return root;
        return findMin(root.rtChild);
    }

    public boolean insert(E x) {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    public boolean remove(E x) {
        int oldSize = mSize;
        remove(mRoot, x); //  Lazy  deletion
        return (mSize != oldSize);

    }

    // public version available to client
    public E find(E x) { //implement lazy deletion such that it will ignore any nodes that are marked as "deleted".
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    protected LazySTNode insert(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null) {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null, false);
        }

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            root.lftChild = insert(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = insert(root.rtChild, x);

        return root;
    }

    protected void remove(LazySTNode root, E x) {
        if (root == null)
            return;
            // found the node
        else if (find(root, x) != null) {
            find(root, x).deleted = true;
            mSize--;

        }
        return;
    }

    //traverse all nodes in the LazySearchTree, which means both "deleted" and non-deleted nodes.
    public <F extends Traverser<? super E>>
    void traverseHard(F func) {
        traverseHard(func, mRoot);
    }

    //receive a generic argument for the functor to use while traversing the current instance of LazySearchTree.
    protected <F extends Traverser<? super E>>
    void traverseHard(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }

    // traverses nodes that have not been marked as deleted in the LazySearchTree.
    public <F extends Traverser<? super E>>
    void traverseSoft(F func) {
        traverseSoft(func, mRoot);
        if (mSize == 0)
            System.out.println("Empty");
    }

    protected <F extends Traverser<? super E>>
    void traverseSoft(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;
        traverseSoft(func, treeNode.lftChild);

        if (!treeNode.deleted) {
            func.visit(treeNode.data);
            traverseSoft(func, treeNode.rtChild);
        }
    }

    // private version that does most of the work
    protected LazySTNode find(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return find(root.lftChild, x);
        if (compareResult > 0)
            return find(root.rtChild, x);
        return root;   // found!
    }

    boolean contains(E x) {
        return find(mRoot, x) != null;
    }

    //Clones items in LazySearchTree
    public Object clone() throws CloneNotSupportedException {
        LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;

        return newObject;
    }

    //clones the subtree
    protected LazySTNode cloneSubtree(LazySTNode root) {
        LazySTNode newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode(
                root.data,
                cloneSubtree(root.lftChild),
                cloneSubtree(root.rtChild)
                , root.deleted);
        return newNode;
    }

    //examines the height of the tree
    protected int findHeight(LazySTNode treeNode, int height) {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    public interface Traverser<E> {
        void visit(E x);
    }


    //Inner Class
    private class LazySTNode {
        // use protected access so the tree, in the same package,
        // or derived classes can access members
        protected LazySTNode lftChild, rtChild;
        protected E data;
        protected LazySTNode myRoot; // needed to test for certain error
        boolean deleted;

        public LazySTNode(E d, LazySTNode lft, LazySTNode rt, boolean del) {
            lftChild = lft;
            rtChild = rt;
            deleted = del;
            data = d;
        }
    }
}