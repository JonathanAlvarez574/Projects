package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

public class SongsCompGenre implements Comparable<String> {


    private final String name; // Creates String called name

    private final ArrayList<SongEntry> songs; // Creates Arraylist for songs

    public SongsCompGenre(String name) // Instantiates a new songs compare their genres.
    {
        songs = new ArrayList<>();
        this.name = name;
    }


    public void addSong(SongEntry song) // Adds songs
    {
        songs.add(song);
    }

    @Override
    public boolean equals(Object obj)  // Overrides equals method that takes on an object parameter
    {
        return (obj instanceof SongsCompGenre && ((SongsCompGenre) obj)
                .getName().equals(name))
                || (obj instanceof String && ((String) obj).equals(name));
    }

    @Override // Overrides the toString method
    public String toString() {
        return name;
    }


    @Override
    public int hashCode()  // Overrides the method hashCode
    {
        return name.hashCode();
    }

    public String getName()  // This method getName retrieves the name of the song
    {
        return name;
    }

    @Override
    public int compareTo(String key)  // Overrides method CompareTo that takes on a String parameter
    {
        return name.compareTo(key);
    }

    public ArrayList<SongEntry> getData() // This method getData retrieves song data
    {
        return songs;
    }
}