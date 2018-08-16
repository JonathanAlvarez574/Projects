package hashTables;

class HashEntry<E> {
    E data;
    int state;

    public HashEntry(E x, int st)  // Creates HashEntry method with data and state variables
    {
        data = x;
        state = st;
    }

    public HashEntry()  // Hash entry without parameters
    {
        this(null, FHhashQP.EMPTY);
    }
}