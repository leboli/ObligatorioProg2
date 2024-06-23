import TADexceptions.EmptyHashtableException;
import TADexceptions.EntityDoesntExist;
import TADs.Hash.CeldaHash;
import entities.Artist;
import entities.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) throws EntityDoesntExist, EmptyHashtableException {
        //String filePath = "scr/new_universal_top_spotify_songs.csv"; // Cambia esta ruta por la ruta de tu archivo
        String filePath = "scr/datos.csv";

        SpotifyImpl spotify = new SpotifyImpl();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dividir la línea usando "," como delimitador
                String[] values = parseLine(line);

                /*
                // Imprimir los valores separados
                for (String value : values) {
                    System.out.print(value + " | ");
                }
                System.out.println();

                 */


                // columnas del CSV:
                // 1 idS, 2 name, 3 artists, 4 rank, 5 daymov, 6 weekmov, 7 county, 8 daterank, 9 popul
                // 10 explicit, 11 duratMs, 12 albumN, 13 albumD, 14 danceab, 15 energy, 16 key, 17 ludness
                // 18 mode, 19 speech, 20 acoustic, 21 inst, 22 live, 23 valance, 24 tempo, 25 timeSig


                // Primero creo el pais
                String countryName = values[6].replace("\"", "");
                Country ourCountry = new Country(countryName);
                // Lo introducimos al hash de counties si aun no esta
                if (!(spotify.myCountries.contains(countryName))) {
                    spotify.myCountries.put(countryName, ourCountry);
                }

                // todo crear a la date como date para los ingresos
                // Creo la fecha de la tupla
                String[] dateParts = values[7].split("-");
                LocalDate myDate = LocalDate.parse(values[7], DateTimeFormatter.ofPattern("yyyy-MM-dd"));


                // Encuentro a los artistas:
                String[] artists = values[2].split(",");
                //System.out.println(Arrays.toString(artists));

                // Recorro la lista de artistas y los creo
                for (String artistName : artists) {
                    Artist myArtist = new Artist(artistName);
                    if (!(spotify.myArtists.contains(artistName))){
                        spotify.myArtists.put(artistName, myArtist);
                    }

                    // Ya le incremento la aparicion en el top
                    //myArtist.getArtistOccurrencesReport().get(countryName).get(values[7])++;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Se cargaron los datos correctamente");
        ArrayList<CeldaHash<String, Country>> myCountries = spotify.myCountries.getHash();
        System.out.println(myCountries);
        int valoresNull = 0;

        for (CeldaHash<String, Country> country : myCountries) {
            if (country != null) {
                System.out.println(country);
            } else {
                valoresNull++;
            }
        }
        System.out.println();
        System.out.println("Tiene una capacidad de " + spotify.myCountries.getCapacity());
        System.out.println("Tiene " + valoresNull + " valores null");
    }

    private static String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        boolean previousCharWasQuote = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                // Toggle the inQuotes flag when a quote is found
                if (!(previousCharWasQuote)) {
                    inQuotes = !inQuotes;
                }

                previousCharWasQuote = !previousCharWasQuote;
            } else if (c == ',' && !inQuotes) {
                // If we find a comma and we are not inside quotes, we consider it as a delimiter
                addField(result, sb.toString().trim());
                sb.setLength(0); // Reset the StringBuilder
                previousCharWasQuote = false;
            } else {
                sb.append(c); // Append the character to the current field
                previousCharWasQuote = false;
            }
        }
        // Add the last field
        addField(result, sb.toString().trim());

        return result.toArray(new String[0]);
    }

    private static void addField(List<String> result, String field) {
        // If the field is empty, replace it with "Global"
        if (field.isEmpty()) {
            result.add("Global");
        } else {
            result.add(field);
        }
    }

    /*
    private static String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        boolean previousCharWasQuote = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                // Toggle the inQuotes flag when a quote is found
                inQuotes = !inQuotes;
                previousCharWasQuote = !previousCharWasQuote;
            } else if (c == ',' && !inQuotes) {
                // If we find a comma and we are not inside quotes, we consider it as a delimiter
                addField(result, sb.toString().trim());
                sb.setLength(0); // Reset the StringBuilder
                previousCharWasQuote = false;
            } else {
                sb.append(c); // Append the character to the current field
                previousCharWasQuote = false;
            }
        }
        // Add the last field
        addField(result, sb.toString().trim());

        return result.toArray(new String[0]);
    }

    private static void addField(List<String> result, String field) {
        // If the field is empty, replace it with "Global"
        if (field.isEmpty()) {
            result.add("Global");
        } else {
            result.add(field);
        }
    }

     */


}

