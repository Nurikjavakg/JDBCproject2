package peaksoft.dao;

import peaksoft.models.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieDao {
    void createTable(String tableName, List<String> columns);

    void save(Movie movie);

    Movie findMovieById(Long id);
    List<Movie> searchByName(String title);
    //<Map<Genre, List<Movie>> getMoviesByGenre(String genre);
   // Map<Genre, List<Movie>> getMoviesByGenre(String genre);
    List<Movie> sortByDuration(String ascOrDesc);
    List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime);
}
