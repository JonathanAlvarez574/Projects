package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;


public class TableGenerator {


    ArrayList<String> genreNames; // Creates an Arraylist for genre's names


    FHhashQPwFind<Integer, SongCompInt> songInt; // The song as int


    FHhashQPwFind<String, SongsCompGenre> songString; // The song as a string


    public TableGenerator() // Instantiates a new Table to generate
    {
        songInt = new FHhashQPwFind<>();
        songString = new FHhashQPwFind<>();
        genreNames = new ArrayList<>();
    }


    public FHhashQPwFind<Integer, SongCompInt> populateIDtable // Add integers within a table
    (
            SongEntry[] allSongs) {

        for (int i = 0; i < allSongs.length; i++) {

            Integer intId = allSongs[i].getID();
            SongCompInt id = songInt.find(intId);
            if (id == null) {
                id = new SongCompInt(intId);
                songInt.insert(id);
            }

        }
        return songInt;
    }


    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs) // Adds String genre within table
    {
        for (int i = 0; i < allSongs.length; i++) {

            String genreString = allSongs[i].getGenre();
            SongsCompGenre genre = songString.find(genreString);

            if (genre == null) {
                genreNames.add(genreString);
                genre = new SongsCompGenre(genreString);
                songString.insert(genre);
            }

            genre.addSong(allSongs[i]);
        }

        return songString;
    }


    public ArrayList<String> getGenreNames() // Retrieves genre's names
    {

        return genreNames;
    }

}