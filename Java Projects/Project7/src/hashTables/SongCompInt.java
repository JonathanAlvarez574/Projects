package hashTables;


public class SongCompInt implements Comparable<Integer> {
    static String DATAFILE = "resources/music_genre_subset.json";
    Integer id;


    public SongCompInt(Integer id) // Instantiates a new song to compare
    {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    } // Overrides the toString method

    @Override
    public int compareTo(Integer key) {
        return id - key;
    } // // Overrides the compareTo method

    @Override
    public boolean equals(Object obj)  // // Overrides the equals method
    {
        return (obj instanceof SongCompInt)
                || (obj instanceof Integer && id.compareTo((Integer) obj) == 0);
    }

    public int hashCode() {
        return id.hashCode();
    } // Overrides the hashCode method

}