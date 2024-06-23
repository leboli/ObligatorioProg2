import TADexceptions.EmptyHashtableException;
import exceptions.EntityDoesntExist;
import exceptions.InvalidDataException;
import TADs.Hash.CeldaHash;
import TADs.Heap.MyHeapImpl;
import TADexceptions.ListaVaciaExcepcion;
import TADs.Hash.ClosedHash;
import TADs.Heap.MyHeap;
import TADs.LinkedList.MyList;
import entities.Artist;
import entities.Country;
import entities.Song;
import other.PrintFunc;


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
            System.out.println();
            System.out.print("(" + mySong.getDailyRank() + ") " + mySong.getName() + " | " );
            PrintFunc.printList(mySong.getArtists());

            myTop10.add(mySong);
        }
        System.out.println();

        // Los devuelvlo
        for (int i = 0; i < 10; i++) {
            myTop50.insert(myTop10.get(i).getValue());
        }
    }

    @Override
    public void top5OfAllTops(LocalDate date) throws EntityDoesntExist, EmptyHashtableException, InvalidDataException {

        MyHeapImpl<Song> myTop = new MyHeapImpl();
        ArrayList<CeldaHash<String,ClosedHash<LocalDate, Integer>>> songsListApp = songsAppereancesByDay.getHash();
        for (CeldaHash<String,ClosedHash<LocalDate, Integer>> songs : songsListApp) {
            if (songs != null) {

                if (!songs.getValue().contains(date)) {
                    throw new InvalidDataException();
                }

                int value = songs.getValue().get(date);
                String name = mySongs.get(songs.getKey());
                Song auxSong = new Song(songs.getKey(), name, value);
                myTop.insert(auxSong);
            }
        }

        System.out.println();
        for (int i = 0; i < 7; i++) {
            Song mySong = myTop.delete();
            System.out.println(mySong.getName() + " con " + mySong.getDailyRank() + " apariciones");
        }

    }

    @Override
    public void top7Artists(LocalDate date1, LocalDate date2) throws EntityDoesntExist, EmptyHashtableException {

        for ( CeldaHash<String, Artist> artist : myArtists.getHash()) {
            if (artist != null) {
                int apariciones = 0;
                for (LocalDate date = date1; date.isBefore(date2); date = date.plusDays(1)) {
                    //apariciones += artist.getValue().getArtistOccurrencesReport().get(artist.getValue().getName());
                    return;
                }

            }
        }



    }

    @Override
    public int amountOfAppereancesByDate(String artist, String country, LocalDate date) throws EntityDoesntExist, EmptyHashtableException {
        if (!(myCountries.contains(country))) {
            return 0;
        }
        if (!(myArtists.contains(artist))) {
            return 0;
        }
        Artist myArtist = myArtists.get(artist);
        return myArtist.getArtistOccurrencesReport().get(country).get(date);
    }

    @Override
    public int amountOfSongsByTempoAndDate(int tempo1, int tempo2, LocalDate date1, LocalDate date2) {
        return 0;
    }
}

