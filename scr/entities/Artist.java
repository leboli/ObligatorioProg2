
package entities;

import TADexceptions.EmptyHashtableException;
import TADexceptions.EntityDoesntExist;
import TADs.Hash.ClosedHash;
import TADs.Hash.HashTable;
import TADs.LinkedList.MyList;

import java.time.LocalDate;
import java.util.Date;

public class Artist {
    // ---------------------------------- ATRIBUTOS -----------------------------------------
    private String name;
    private ClosedHash<String, ClosedHash<LocalDate, Integer>> artistOccurrencesReport;

    // --------------------------------- CONSTRUCTOR ----------------------------------------
    public Artist(String name) {
        this.name = name;
        this.artistOccurrencesReport = new ClosedHash<>(50);
    }


    // ------------------------------ SETTERS & GETTERS -------------------------------------
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ClosedHash<String, ClosedHash<LocalDate, Integer>> getArtistOccurrencesReport() {
        return artistOccurrencesReport;
    }

    // ----------------------------------- METODOS ------------------------------------------


}

