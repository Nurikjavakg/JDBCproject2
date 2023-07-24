package peaksoft.services;

import peaksoft.models.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    String creatMovie(String tableName, List<String> columns);

    String saveMovie(Movie movie);

    Movie findMovieById(Long id);
    List<Movie> searchByName(String title);
    //<Map<Genre, List<Movie>> getMoviesByGenre(String genre);
    // Map<Genre, List<Movie>> getMoviesByGenre(String genre);
    List<Movie> sortByDuration(String ascOrDesc);
    List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime);
}
