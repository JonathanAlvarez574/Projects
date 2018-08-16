package hashTables;

public class FHhashQP<E> {
    protected static final int ACTIVE = 0;
    protected static final int EMPTY = 1;
    protected static final int DELETED = 2;

    static final int INIT_TABLE_SIZE = 97;
    static final double INIT_MAX_LAMBDA = 0.49;

    protected HashEntry<E>[] mArray;
    protected int mSize;
    protected int mLoadSize;
    protected int mTableSize;
    protected double mMaxLambda;
    protected int collisions = 0;

    //  -------------- Public Methods ---------------------------------
    public FHhashQP(int tableSize) {
        mLoadSize = mSize = 0;
        if (tableSize < INIT_TABLE_SIZE)
            mTableSize = INIT_TABLE_SIZE;
        else
            mTableSize = nextPrime(tableSize);

        allocateArray();  // This uses mTableSize
        mMaxLambda = INIT_MAX_LAMBDA;

    }

    public FHhashQP()  //Instantiates a new hash qp.
    {
        this(INIT_TABLE_SIZE);
    }

    protected static int nextPrime(int a) // Gets next prime number
    {
        {
            int b, can, loop;


            if (a <= 2)
                return 2;
            else if (a == 3)
                return 3;

            for (can = (a % 2 == 0) ? a + 1 : a; true; can += 2) {

                loop = (int) ((Math.sqrt((double) can) + 1) / 6);


                if (can % 3 == 0)
                    continue;


                for (b = 1; b <= loop; b++) {
                    if (can % (6 * b - 1) == 0)
                        break;
                    if (can % (6 * b + 1) == 0)
                        break;
                }
                if (b > loop)
                    return can;
            }
        }
    }

    public boolean insert(E x) // inserts data
    {

        int bucket = findPos(x);

        if (mArray[bucket].state == ACTIVE)
            return false;

        mArray[bucket].data = x;
        mArray[bucket].state = ACTIVE;
        mSize++;

        // check load factor
        if (++mLoadSize > mMaxLambda * mTableSize)
            rehash();

        return true;

    }

    public boolean remove(E x) {
        int bucket = findPos(x);

        if (mArray[bucket].state != ACTIVE)
            return false;

        mArray[bucket].state = DELETED;
        mSize--;  // mLoadSize not dec'd: it counts non-EMPTY loc
        return true;
    }

    public boolean contains(E x) {
        return mArray[findPos(x)].state == ACTIVE;
    }

    public void makeEmpty()  // method that makes something empty
    {

        int i, size = mArray.length;

        for (i = 0; i < size; i++)
            mArray[i].state = EMPTY;
        mSize = mLoadSize = 0;
    }

    public int size()  //returns msize
    {
        return mSize;
    }

    public boolean setMaxLambda(double lambda) // Sets max value for lambda
    {
        if (lambda < .1 || lambda > 100.)
            return false;
        mMaxLambda = lambda;
        return true;
    }

    // ---------------- protected methods  ----------------------
    int findPos(E x) // Finds position
    {

        int nthOddNum = 1;
        int index = myHash(x);

        while (mArray[index].state != EMPTY
                && !mArray[index].data.equals(x)) {
            index += nthOddNum;
            ++collisions;
            nthOddNum += 2;
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }

    protected void rehash()  // rehashes data
    {

        HashEntry<E>[] oldArray = mArray;
        int k, oldTableSize = mTableSize;


        mTableSize = nextPrime(2 * oldTableSize);

        allocateArray(); // refers to mTableSize;

        mSize = mLoadSize = 0;

        for (k = 0; k < oldTableSize; k++)
            if (oldArray[k].state == ACTIVE)
                insert(oldArray[k].data);
    }

    protected int myHash(E x)  // refers to current hash
    {

        int hashVal;

        hashVal = x.hashCode() % mTableSize;
        if (hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }

    @SuppressWarnings("unchecked")
    private void allocateArray()  // Allocated array data
    {
        int a;

        mArray = new HashEntry[mTableSize];
        for (a = 0; a < mTableSize; a++)
            mArray[a] = new HashEntry<E>();
    }
}


