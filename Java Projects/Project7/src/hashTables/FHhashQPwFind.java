package hashTables;


import java.util.NoSuchElementException;

public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends
        FHhashQP<E> {


    protected E find(KeyType key) // This finds a key within the table
    {
        try {
            int currentPos = findPosKey(key);
            return contains((E) key) ? mArray[currentPos].data : null;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }


    protected int myHashKey(KeyType key)// This provides a modification to myHash() which uses the key rather than the object, to hash.
    {
        return myHash((E) key);
    }


    protected int findPosKey(KeyType key) // This provides a modification to findPos() which uses the key rather than the object, to get a position.
    {

        return findPos((E) key);

    }

}