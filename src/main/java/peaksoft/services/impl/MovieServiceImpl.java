package peaksoft.services.impl;

import peaksoft.dao.MovieDao;
import peaksoft.dao.impl.MovieDaoImpl;
import peaksoft.models.Movie;
import peaksoft.services.MovieService;

import java.time.LocalDate;
import java.util.List;

public class MovieServiceImpl implements MovieService {
    MovieDao movieDao = new MovieDaoImpl();

    @Override
    public String creatMovie(String tableName, List<String> columns) {
        movieDao.createTable(tableName,columns);
        return "Successfully created table"+tableName;
    }

    @Override
    public String saveMovie(Movie movie) {
        movieDao.save(movie);
        return "Successfully saved!";
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieDao.findMovieById(id);

    }

    @Override
    public List<Movie> searchByName(String title) {
        return  movieDao.searchByName(title);

    }

    @Override
    public List<Movie> sortByDuration(String ascOrDesc) {
        return movieDao.sortByDuration(ascOrDesc);

    }

    @Override
    public List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime) {
        return movieDao.getMoviesByTheaterIdAndStartTime(theaterId,startTime);
    }
}
