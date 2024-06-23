import TADs.Hash.CeldaHash;
import entities.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {
    public static void main(String[] args) {
        String archivoCSV = "scr/datos.csv";

        SpotifyImpl spotify = new SpotifyImpl();

        String linea;
        String separador = ";"; // todo fijarse cual es
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {

            while ((linea = br.readLine()) != null) {
                String[] values = linea.split(separador);

                // columnas del CSV:
                // 1 idS, 2 name, 3 artists, 4 rank, 5 daymov, 6 weekmov, 7 county, 8 daterank, 9 popul
                // 10 explicit, 11 duratMs, 12 albumN, 13 albumD, 14 danceab, 15 energy, 16 key, 17 ludness
                // 18 mode, 19 speech, 20 acoustic, 21 inst, 22 live, 23 valance, 24 tempo, 25 timeSig


                // Primero creo el pais
                String countryName = values[6].replace("\"", "");
                if (countryName.isEmpty()) {
                    countryName = "Global";
                }
                Country ourCountry = new Country(countryName);
                contador++;
                System.out.println( contador + ") " + ourCountry.getNombrePais());

                // Lo introducimos al hash de counties si aun no esta
                if (!(spotify.myCountries.contains(countryName))) {
                    spotify.myCountries.put(countryName, ourCountry);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<CeldaHash<String, Country>> paises = spotify.getMyCountries().getHash();
        for (int i = 0; i < 10; i++) {
            System.out.println(i+1 + ") " + paises.get(i));

        }

    }
}
