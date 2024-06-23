import TADexceptions.EmptyHashtableException;
import TADs.BinaryTree.BinaryTree;
import TADs.BinaryTree.SearchBinaryTree;
import TADs.Hash.CeldaHash;
import TADs.Heap.MyHeapImpl;
import exceptions.EntityDoesntExist;
import TADexceptions.ListaVaciaExcepcion;
import TADs.Hash.ClosedHash;
import TADs.Heap.MyHeap;
import TADs.LinkedList.MyList;
import entities.Artist;
import entities.Country;
import entities.Song;
import exceptions.InvalidDataException;


import java.time.LocalDate;
import java.util.ArrayList;

public class SpotifyImpl implements Spotify {

    // ---------------------------------- ATRIBUTOS -----------------------------------------
    ClosedHash<String, Artist> myArtists;
    ClosedHash<String, Country> myCountries;
    ClosedHash<String, String> mySongs;
    ClosedHash<String, ClosedHash<LocalDate, Integer>> songsAppereancesByDay;
    ClosedHash<String, ClosedHash<LocalDate, MyHeap<Song>>> myTops50;

    // --------------------------------- CONSTRUCTOR ----------------------------------------
    public SpotifyImpl() {
        this.myArtists = new ClosedHash<>(53);
        this.myCountries = new ClosedHash<>(73);
        this.mySongs = new ClosedHash<>(191);
        this.songsAppereancesByDay = new ClosedHash<>(53);
        this.myTops50 = new ClosedHash<>(53);
    }

    // ------------------------------ SETTERS & GETTERS -------------------------------------

    public ClosedHash<String, Artist> getMyArtists() {
        return myArtists;
    }

    public void setMyArtists(ClosedHash<String, Artist> myArtists) {
        this.myArtists = myArtists;
    }

    public ClosedHash<String, Country> getMyCountries() {
        return myCountries;
    }

    public void setMyCountries(ClosedHash<String, Country> myCountries) {
        this.myCountries = myCountries;
    }

    public ClosedHash<String, ClosedHash<LocalDate, MyHeap<Song>>> getMyTops50() {
        return myTops50;
    }

    public void setMyTops50(ClosedHash<String, ClosedHash<LocalDate, MyHeap<Song>>> myTops50) {
        this.myTops50 = myTops50;
    }

    public ClosedHash<String, ClosedHash<LocalDate, Integer>> getSongsAppereancesByDay() {
        return songsAppereancesByDay;
    }

    public void setSongsAppereancesByDay(ClosedHash<String, ClosedHash<LocalDate, Integer>> songsAppereancesByDay) {
        this.songsAppereancesByDay = songsAppereancesByDay;
    }

    public ClosedHash<String, String> getMySongs() {
        return mySongs;
    }

    public void setMySongs(ClosedHash<String, String> mySongs) {
        this.mySongs = mySongs;
    }

    // ----------------------------------- METODOS ------------------------------------------
    @Override
    public void countriesTopTen(String country, LocalDate date) throws EntityDoesntExist, EmptyHashtableException, ListaVaciaExcepcion, InvalidDataException {
        /*
    Top 10 canciones en un país en un día dado. Este reporte debe incluir el nombre de
    la canción, el artista, y en qué puesto se encuentra en el top. Las canciones deben
    estar ordenadas de manera descendente. El día será ingresado en el formato
    YYYY-MM-DD.
     */
        if (!(myCountries.contains(country))) {
            throw new EntityDoesntExist();
        }
        if (!(myTops50.get(country).contains(date))) {
            throw new InvalidDataException();
        }

        MyHeap<Song> myTop50 = this.getMyTops50().get(country).get(date);
        MyList<Song> myTop10 = new MyList<>(null);
        Song mySong;
        // Saco los valores que necesito
        for (int i = 0; i < 10; i++) {
            mySong = myTop50.delete();
            System.out.println("(" + mySong.getDailyRank() + ") " + mySong.getName() + " | " + mySong.getArtists());
            myTop10.add(mySong);
        }

        // Los devuelvlo
        for (int i = 0; i < 10; i++) {
            myTop50.insert(myTop10.get(i).getValue());
        }
    }

    @Override
    public void top5OfAllTops(LocalDate date) throws EntityDoesntExist, EmptyHashtableException, InvalidDataException {

        MyHeapImpl<Song> myTop = new MyHeapImpl();
        ArrayList<CeldaHash<String,ClosedHash<LocalDate, Integer>>> artistsListApp = songsAppereancesByDay.getHash();
        for (CeldaHash<String,ClosedHash<LocalDate, Integer>> artist : artistsListApp) {
            if (artist != null) {

                if (!artist.getValue().contains(date)) {
                    throw new InvalidDataException();
                }

                int value = artist.getValue().get(date);
                String name = mySongs.get(artist.getKey());
                Song auxSong = new Song(artist.getKey(), name, value);
                myTop.insert(auxSong);
            }
        }

        for (int i = 0; i < 7; i++) {
            Song mySong = myTop.delete();
            System.out.println(mySong.getName() + "Con " + mySong.getDailyRank() + " apariciones");
        }

    }

    @Override
    public void top7Artists(Artist artist, String country, LocalDate date1, LocalDate date2) {

    }

    @Override
    public int amountOfAppereancesByDate(Artist artist, String country, LocalDate date) {
        return 0;
    }

    @Override
    public int amountOfSongsByTempoAndDate(int tempo1, int tempo2, LocalDate date1, LocalDate date2) {
        return 0;
    }
}

