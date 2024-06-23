import TADexceptions.EmptyHashtableException;
import TADs.BinaryTree.SearchBinaryTree;
import TADexceptions.ListaVaciaExcepcion;
import entities.Artist;
import entities.Song;
import exceptions.EntityDoesntExist;
import exceptions.InvalidDataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List; // Importación correcta
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static SpotifyImpl spotifyService;

    public static void main(String[] args) throws EntityDoesntExist, InvalidDataException, ListaVaciaExcepcion, EmptyHashtableException {
        spotifyService = CSVReader.main(args);
        while (true) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    generateTop10SongsReport();
                    break;
                case 2:
                    generateTop5SongsInTop50Report();
                    break;
                case 3:
                    //generateTop7ArtistsReport();
                    break;
                case 4:
                    generateArtistOccurrencesReport();
                    break;
                case 5:
                    //generateTempoRangeReport();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Spotify Data Analysis ===");
        System.out.println("1. Generate Top 10 Songs Report");
        System.out.println("2. Generate Top 5 Songs in Top 50 Report");
        System.out.println("3. Generate Top 7 Artists Report");
        System.out.println("4. Generate Artist Occurrences Report");
        System.out.println("5. Generate Tempo Range Report");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }


    private static void generateTop10SongsReport() throws EntityDoesntExist, InvalidDataException, ListaVaciaExcepcion, EmptyHashtableException {
        System.out.print("Enter the country: ");
        String country = scanner.nextLine();
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            return; // Return early if the date format is invalid
        }

        try {
            spotifyService.countriesTopTen(country, date);
        } catch (EntityDoesntExist e) {
            System.out.println("The country does not exist.");
        } catch (InvalidDataException e) {
            System.out.println("We have no info available for your date");;
        }
    }

    private static void generateTop5SongsInTop50Report() {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            return; // Return early if the date format is invalid
        }

        try {
            spotifyService.top5OfAllTops(date);
        } catch (EntityDoesntExist e) {
            System.out.println("date problems");
        } catch (InvalidDataException e) {
            System.out.println("a");
        } catch (EmptyHashtableException e) {
            System.out.println("b");
        }
    }

/*
    private static void generateTop7ArtistsReport() {
        System.out.print("Enter the start date (YYYY-MM-DD): ");
        String startDateString = scanner.nextLine();
        System.out.print("Enter the end date (YYYY-MM-DD): ");
        String endDateString = scanner.nextLine();

        Date startDate = null;
        Date endDate = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            startDate = formatter.parse(startDateString);
            endDate = formatter.parse(endDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            return; // Return early if the date format is invalid
        }

        List<Artist> top7Artists = (List<Artist>) spotifyService.getTop7Artists(String.valueOf(startDate), String.valueOf(endDate));
        System.out.println("Top 7 Artists from " + startDate + " to " + endDate + ":");
        for (Artist artist : top7Artists) {
            System.out.println(artist);
        }
    }
*/

    private static void generateArtistOccurrencesReport() throws EntityDoesntExist, EmptyHashtableException {
        System.out.print("Enter the artist name: ");
        String artistName = scanner.nextLine();
        System.out.print("Enter the country: ");
        String country = scanner.nextLine();
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            return; // Return early if the date format is invalid
        }

        int occurrences = spotifyService.amountOfAppereancesByDate(artistName, country, date);
        System.out.println(artistName + " appeared " + occurrences + " times in the"+ country + " Top 50 on " + date);
    }

    /*
    private static void generateTempoRangeReport() {
        System.out.print("Enter the start tempo: ");
        double startTempo = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the end tempo: ");
        double endTempo = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the start date (YYYY-MM-DD): ");
        String startDateString = scanner.nextLine();
        System.out.print("Enter the end date (YYYY-MM-DD): ");
        String endDateString = scanner.nextLine();

        Date startDate = null;
        Date endDate = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            startDate = formatter.parse(startDateString);
            endDate = formatter.parse(endDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            return; // Return early if the date format is invalid
        }

        int count = spotifyService.getSongsInTempoRange(startTempo, endTempo, String.valueOf(startDate), String.valueOf(endDate));
        System.out.println("Number of songs with tempo in range " + startTempo + " to " + endTempo + " from " + startDate + " to " + endDate + ": " + count);
    }
    */


}
