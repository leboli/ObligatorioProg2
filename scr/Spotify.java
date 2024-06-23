import TADexceptions.EmptyHashtableException;
import TADexceptions.EntityDoesntExist;
import TADexceptions.ListaVaciaExcepcion;
import entities.Artist;

import java.util.Date;

public interface Spotify {
    void countysTopTen(String country, Date date) throws EntityDoesntExist, EmptyHashtableException, ListaVaciaExcepcion;
    /*
    Top 10 canciones en un país en un día dado. Este reporte debe incluir el nombre de
    la canción, el artista, y en qué puesto se encuentra en el top. Las canciones deben
    estar ordenadas de manera descendente. El día será ingresado en el formato
    YYYY-MM-DD.
     */


    void top5OfAllTops(Date date);
    /*
    Top 5 canciones que aparecen en más top 50 en un día dado. Las canciones deben
    estar ordenadas de manera descendente. Se espera que esta operación sea de
    orden n en notación Big O.
     */


    int anArtistInTop50(Artist artist, String country, Date date);
    /*
    Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.
     */


    /*
    Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado. Cada
    aparición (como cada canción) distinta debe contarse, y se debe separar las
    canciones que tengan más de un artista contabilizando una aparición para cada uno.
    Si un artista tiene 10 canciones en el top 50, deben contabilizarse 10 ocurrencias
     */


}
