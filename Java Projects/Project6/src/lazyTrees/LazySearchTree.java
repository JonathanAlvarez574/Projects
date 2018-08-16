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

    public E findMinHard() {
        LazySTNode resultNode;
        resultNode = findMinHard(mRoot);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
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

    public E findMaxHard() // finds the max hard items
    {
        LazySTNode resultNode;
        resultNode = findMaxHard(mRoot);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }


    public boolean insert(E x)  // Inserts data
    {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    public boolean remove(E x) // Removes data
    {
        int oldSize = mSize;
        remove(mRoot, x);
        return (mSize != oldSize);
    }

    // public version available to client
    public E find(E x) //implement lazy deletion such that it will ignore any nodes that are marked as "deleted".
    {
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    protected LazySTNode insert(LazySTNode root, E x) // inserts items
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null) {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null, false, 1, null);
        }

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            root.lftChild = insert(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = insert(root.rtChild, x);

        return root;
    }

    protected LazySTNode remove(LazySTNode root, E x) //lazy removal of items
    {
        if (root == null)
            return null;
        if (find(x) != null) {
            find(root, x).deleted = true;
            mSize--;
            return root;
        } else {
            return null;
        }
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


    public <F extends Traverser<? super E>> // traverses nodes that have not been marked as deleted in the LazySearchTree.
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

    public boolean containsHard(E x) {
        return findHard(mRoot, x) != null;
    }

    protected LazySTNode findHard(LazySTNode root, E x) {
        int compareResult; // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return findHard(root.lftChild, x);
        if (compareResult > 0)
            return findHard(root.rtChild, x);
        if (compareResult == 0)
            return root;
        return null;
    }

    //Clones items in LazySearchTree
    public Object clone() throws CloneNotSupportedException {
        LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;

        return newObject;
    }

    protected LazySTNode collectGarbage(LazySTNode root) {
        if (root == null) // look for nodes marked deleted in each child sub-tree and lastly
            // the root of the whole tree. Return the mRoot because it could
            return null; // have been changed.

        if (root.lftChild != null)
            root.lftChild = collectGarbage(root.lftChild);
        if (root.rtChild != null)
            root.rtChild = collectGarbage(root.rtChild);
        if (root.deleted)
            root = removeHard(root, root.data);
        return root;
    }


    protected LazySTNode cloneSubtree(LazySTNode root) //clones the subtree
    {
        LazySTNode newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode(
                root.data,
                cloneSubtree(root.lftChild),
                cloneSubtree(root.rtChild)
                , root.deleted, 1, null);
        return newNode;
    }


    protected int findHeight(LazySTNode treeNode, int height) //examines the height of the tree
    {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    public boolean collectGarbage() //collects garbage
    {
        mRoot = collectGarbage(mRoot);
        return true;
    }

    public E findHard(E x) //Find Hard delted items
    {
        LazySTNode resultNode;
        resultNode = findHard(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();

        return resultNode.data;
    }

    protected LazySTNode findMinHard(LazySTNode root)  //Find Min of Hard Deleted in BST
    {
        if (root == null)
            return null;
        if (root.lftChild == null)
            return root;
        return findMinHard(root.lftChild);

    }

    public boolean removeHard(E x) // Hard deletes items
    {
        int oldSize = mSize;
        LazySTNode temp = removeHard(mRoot, x);
        if (temp.equals(mRoot))
            mRoot = temp;
        return (mSize != oldSize);
    }

    protected LazySTNode removeHard(LazySTNode root, E x) // Implements Hard Deletion
    {
        int compareResult;
        LazySTNode putativeRightChild_min;

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            root.lftChild = removeHard(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = removeHard(root.rtChild, x);

            // Check to see if it has both children
        else if (root.lftChild != null && root.rtChild != null) {
            // if the node has not been previously marked as deleted
            if (!root.deleted)
                mSize--;
            putativeRightChild_min = findMinHard(root.rtChild);

            root.data = putativeRightChild_min.data;
            root.deleted = putativeRightChild_min.deleted;


            putativeRightChild_min.deleted = true;

            root.rtChild = removeHard(root.rtChild, root.data);

        } else
        // if there is only one child node
        {
            if (!root.deleted)
                mSize--;

            root = (root.lftChild != null) ? root.lftChild : root.rtChild;

            // adjust the Hard count
            mSizeHard--;
        }

        return root;
    }

    protected LazySTNode findMaxHard(LazySTNode root) // Find max of the Hard deletion in BST
    {
        if (root == null)
            return null;

        if (root.rtChild != null) {
            return findMaxHard(root.rtChild);
        } else {
            return root;
        }
    }


    public interface Traverser<E>  // Implements Traverse interface
    {
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

        public LazySTNode(E d, LazySTNode lft, LazySTNode rt, boolean del, int i, Object o) {
            lftChild = lft;
            rtChild = rt;
            deleted = del;
            data = d;
        }
    }
}