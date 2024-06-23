package entities;

import TADs.LinkedList.MyList;

import java.util.Date;
import java.util.Objects;

public class Song implements Comparable<Song> {
    // ---------------------------------- ATRIBUTOS -----------------------------------------
    private String spotifyId;
    private String name;
    private MyList<Artist> artists = new MyList<>(null);
    private int dailyRank;
    private int dailyMovement;
    private int weeklyMovement;
    private String country;
    private Date date;
    private int popularity;
    private boolean isExplicit;
    private long durationMs;
    private String albumName;
    private Date albumReleaseDate;
    private float danceability;
    private float energy;
    private float loudness;
    private int mode;
    private float speechiness;
    private float acousticness;
    private float instrumentalness;
    private float liveness;
    private float valance;
    private float tempo;
    private int timeSignature;

    // --------------------------------- CONSTRUCTOR ----------------------------------------
    public Song(String spotifyId, String name, MyList<Artist> artists, int dailyRank, int dailyMovement, int weeklyMovement, String country, Date date, int popularity, boolean isExplicit, long durationMs, String albumName, Date albumReleaseDate, float danceability, float energy, float loudness, int mode, float speechiness, float acousticness, float instrumentalness, float liveness, float valance, float tempo, int timeSignature) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.artists = artists;
        this.dailyRank = dailyRank;
        this.dailyMovement = dailyMovement;
        this.weeklyMovement = weeklyMovement;
        this.country = country;
        this.date = date;
        this.popularity = popularity;
        this.isExplicit = isExplicit;
        this.durationMs = durationMs;
        this.albumName = albumName;
        this.albumReleaseDate = albumReleaseDate;
        this.danceability = danceability;
        this.energy = energy;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valance = valance;
        this.tempo = tempo;
        this.timeSignature = timeSignature;
    }

    // ------------------------------ SETTERS & GETTERS -------------------------------------
    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(MyList<Artist> artists) {
        this.artists = artists;
    }

    public int getDailyRank() {
        return dailyRank;
    }

    public void setDailyRank(int dailyRank) {
        this.dailyRank = dailyRank;
    }

    public int getDailyMovement() {
        return dailyMovement;
    }

    public void setDailyMovement(int dailyMovement) {
        this.dailyMovement = dailyMovement;
    }

    public int getWeeklyMovement() {
        return weeklyMovement;
    }

    public void setWeeklyMovement(int weeklyMovement) {
        this.weeklyMovement = weeklyMovement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isExplicit() {
        return isExplicit;
    }

    public void setExplicit(boolean explicit) {
        isExplicit = explicit;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(Date albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }

    public float getDanceability() {
        return danceability;
    }

    public void setDanceability(float danceability) {
        this.danceability = danceability;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getLoudness() {
        return loudness;
    }

    public void setLoudness(float loudness) {
        this.loudness = loudness;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(float speechiness) {
        this.speechiness = speechiness;
    }

    public float getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(float acousticness) {
        this.acousticness = acousticness;
    }

    public float getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(float instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public float getLiveness() {
        return liveness;
    }

    public void setLiveness(float liveness) {
        this.liveness = liveness;
    }

    public float getValance() {
        return valance;
    }

    public void setValance(float valance) {
        this.valance = valance;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public int getTimeSignature() {
        return timeSignature;
    }

    public void setTimeSignature(int timeSignature) {
        this.timeSignature = timeSignature;
    }

    // ----------------------------------- METODOS ------------------------------------------
    @Override
    public int compareTo(Song o) {
        if (this.dailyRank < o.dailyRank) {
            return -1;
        } else if (this.dailyRank > o.dailyRank) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(spotifyId, song.spotifyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotifyId);
    }
}
