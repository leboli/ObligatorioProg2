import TADexceptions.EmptyHashtableException;
import TADexceptions.EntityDoesntExist;
import TADexceptions.ListaVaciaExcepcion;
import TADs.Hash.ClosedHash;
import TADs.Heap.MyHeap;
import TADs.LinkedList.MyList;
import entities.Artist;
import entities.Country;
import entities.Song;

import java.util.Date;

public class SpotifyImpl implements Spotify {

    // ---------------------------------- ATRIBUTOS -----------------------------------------
    ClosedHash<String, Artist> myArtists;
    ClosedHash<String, Country> myCountries;
    ClosedHash<String, ClosedHash<Date, MyHeap<Song>>> myTops50;

    // --------------------------------- CONSTRUCTOR ----------------------------------------
    public SpotifyImpl() {
        this.myArtists = new ClosedHash<>(50);
        this.myCountries = new ClosedHash<>(73);
        this.myTops50 = new ClosedHash<>(50);
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

    public ClosedHash<String, ClosedHash<Date, MyHeap<Song>>> getMyTops50() {
        return myTops50;
    }

    public void setMyTops50(ClosedHash<String, ClosedHash<Date, MyHeap<Song>>> myTops50) {
        this.myTops50 = myTops50;
    }


    // ----------------------------------- METODOS ------------------------------------------
    @Override
    public void countysTopTen(String country, Date date) throws EntityDoesntExist, EmptyHashtableException, ListaVaciaExcepcion {
        /*
    Top 10 canciones en un país en un día dado. Este reporte debe incluir el nombre de
    la canción, el artista, y en qué puesto se encuentra en el top. Las canciones deben
    estar ordenadas de manera descendente. El día será ingresado en el formato
    YYYY-MM-DD.
     */
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
    public void top5OfAllTops(Date date) {

    }

    @Override
    public int anArtistInTop50(Artist artist, String country, Date date) {
        return 0;
    }

}
