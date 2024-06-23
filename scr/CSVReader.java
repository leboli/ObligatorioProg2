import TADexceptions.EmptyHashtableException;
import TADexceptions.EntityDoesntExist;
import TADs.Hash.ClosedHash;
import TADs.Heap.MyHeap;
import TADs.Heap.MyHeapImpl;
import TADs.LinkedList.MyList;
import entities.Artist;
import entities.Country;
import entities.Song;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) throws EntityDoesntExist, EmptyHashtableException {
        String filePath = "scr/new_universal_top_spotify_songs.csv"; // Cambia esta ruta por la ruta de tu archivo
        //String filePath = "scr/datos.csv";

        SpotifyImpl spotify = new SpotifyImpl();
        int contador = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dividir la línea usando "," como delimitador
                String[] values = parseLine(line);


                System.out.println(contador + ") ");
                // Imprimir los valores separados
                for (String value : values) {
                    System.out.print(value + " | ");
                }
                System.out.println();
                contador++;




                // Primero creo el pais
                String countryName = values[6].replace("\"", "");
                Country ourCountry = new Country(countryName);
                // Lo introducimos al hash de counties si aun no esta
                if (!(spotify.myCountries.contains(countryName))) {
                    spotify.myCountries.put(countryName, ourCountry);
                }


                // Creo la fecha de la tupla
                LocalDate myDate = LocalDate.parse(values[7], DateTimeFormatter.ofPattern("yyyy-MM-dd"));


                // Encuentro a los artistas:
                String[] artists = values[2].split(",");
                MyList<Artist> myArtistsList = new MyList<>(null);
                // Recorro la lista de artistas y los creo
                for (String artistName : artists) {
                    artistName = artistName.trim();
                    Artist myArtist = new Artist(artistName);
                    if (!(spotify.myArtists.contains(artistName))) {
                        spotify.myArtists.put(artistName, myArtist);
                    } else {
                        myArtist = spotify.myArtists.get(artistName);
                    }

                    myArtistsList.add(myArtist);

                    ClosedHash<String, ClosedHash<LocalDate, Integer>> occurrences = myArtist.getArtistOccurrencesReport();
                    // Si no se ha ingresado el pais aun lo ingreso, ademas tambien ingreso la fecha y le suma una aparicion
                    if (!(occurrences.contains(countryName))) {
                        occurrences.put(countryName, new ClosedHash<>(50));
                        occurrences.get(countryName).put(myDate, 1);

                        // Si no se ha ingresado para ese pais nuestra fecha la ingreso y le sumo una aparicion
                    } else if (!(occurrences.get(countryName).contains(myDate))) {
                        occurrences.get(countryName).put(myDate, 1);

                        // Si esta ingresado el pais y la fecha, solo hay que sumar 1 aparicion
                    } else {
                        ClosedHash<LocalDate, Integer> occurrencesByCountry = occurrences.get(countryName);
                        int actualOccurrences = occurrencesByCountry.get(myDate);
                        occurrencesByCountry.put(myDate, actualOccurrences + 1);
                    }

                }


                // Ahora creo la cancion:
                // columnas del CSV:
                // 1 idS, 2 name, 3 artists, 4 rank, 5 daymov, 6 weekmov, 7 county, 8 daterank, 9 popul
                // 10 explicit, 11 duratMs, 12 albumN, 13 albumD, 14 danceab, 15 energy, 16 key, 17 ludness
                // 18 mode, 19 speech, 20 acoustic, 21 inst, 22 live, 23 valance, 24 tempo, 25 timeSig

                boolean datosInvalidos = false;

                String spotifyId = values[0];
                String name = values[1];
                // myArtistsList
                int rank = Integer.parseInt(values[3]);
                int dailyMovement = Integer.parseInt(values[4]);
                int weeklyMovement = Integer.parseInt(values[5]);
                // ourCountry | countryName <---
                // myDate
                int popularity = Integer.parseInt(values[8]);
                boolean isExplicit = Boolean.parseBoolean(values[9]);
                long durationMs = Long.parseLong(values[10]);
                String albumName = values[11];

                LocalDate albumReleaseDate;
                try {
                    albumReleaseDate = LocalDate.parse(values[12], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeException e) {
                    albumReleaseDate = LocalDate.now();
                }


                float danceability = Float.parseFloat(values[13]);
                float energy = Float.parseFloat(values[14]);
                int key = Integer.parseInt(values[15]);
                float loudness = Float.parseFloat(values[16]);
                int mode = Integer.parseInt(values[17]);
                float speechiness = Float.parseFloat(values[18]);
                float acousticness = Float.parseFloat(values[19]);
                float instrumentalness = Float.parseFloat(values[20]);
                float liveness = Float.parseFloat(values[21]);
                float valance = Float.parseFloat(values[22]);
                float tempo = Float.parseFloat(values[23]);
                int timeSignature = Integer.parseInt(values[24].replace(";", ""));

                // creo la cancion
                Song mySong = new Song(spotifyId, name, myArtistsList, rank, dailyMovement, weeklyMovement, countryName,
                        myDate, popularity, isExplicit, durationMs, albumName, albumReleaseDate, danceability, energy,
                        loudness, mode, speechiness, acousticness, instrumentalness, liveness, valance, tempo, timeSignature);

                // ahora inserto la cancion en el top:
                // si el pais aun no esta ingresado:
                ClosedHash<String, ClosedHash<LocalDate, MyHeap<Song>>> myTop50 = spotify.myTops50;
                if (!(myTop50.contains(countryName))) {
                    myTop50.put(countryName, new ClosedHash<>(53));
                    myTop50.get(countryName).put(myDate, new MyHeapImpl<>(50));
                    myTop50.get(countryName).get(myDate).insert(mySong);

                // Si el pais esta ingresado pero la fecha no
                } else if (!(myTop50.get(countryName).contains(myDate))) {
                    myTop50.get(countryName).put(myDate, new MyHeapImpl<>(50));
                    myTop50.get(countryName).get(myDate).insert(mySong);

                // Si ya esta to do ingresado
                } else {
                    myTop50.get(countryName).get(myDate).insert(mySong);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Datos cargados exitosamente");

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
}