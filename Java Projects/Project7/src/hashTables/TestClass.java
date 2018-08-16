package hashTables;

import cs1c.SongEntry;
import cs1c.TimeConverter;


public class TestClass {

    static String DATAFILE = "resources/music_genre_subset.json";

    static FHhashQP<SongEntry> table = new FHhashQP<>();


    static SongEntry songTofind;


    static int N = 0;  //N = number of elements.


    public static void main(String[] args) {
        long startTime = 0, estimatedTime = 0;
        SongEntry songTofind;
        SongEntry[] songs = MyTunes.readSongsFromDataFile(DATAFILE);

        System.out
                .println("\n\nTesting quadratic probing hash table vs. sequential find:\n");

        N = 50;
        System.out.println("Number of elements, N =" + N);
        songTofind = songs[49];
        quadraticTest(songs, startTime, estimatedTime, songTofind, N);


        N = 500;
        System.out.println("Number of elements, N =" + N);
        songTofind = songs[499];
        quadraticTest(songs, startTime, estimatedTime, songTofind, N);


        N = 5000;
        System.out.println("Number of elements, N =" + N);
        songTofind = songs[4999];
        quadraticTest(songs, startTime, estimatedTime, songTofind, N);


        N = 59600;
        System.out.println("Number of elements, N =" + N);
        songTofind = songs[59599];
        quadraticTest(songs, startTime, estimatedTime, songTofind, N);

    }

  // Quadratic Test:
    public static void quadraticTest(SongEntry[] songs, long startTime,
                                     long estimatedTime, SongEntry songTofind, int N) {
        System.out.println("\nQuadratic Search:");
        for (int i = 0; i < N; i++) {
            table.insert(songs[i]);
        }

        System.out.println("Table length :" + table.mArray.length);
        System.out.println("Collisions :" + table.collisions);

        startTime = System.nanoTime();
        int songsFound = 0;
        for (int i = 0; i < N; i++) {
            if (table.contains(songTofind)) {
                songsFound++;
                break;
            }
        }

        System.out.println("Songs found : " + songsFound);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("Time elapsed: "
                + TimeConverter.convertTimeToString(estimatedTime));

    }

}
