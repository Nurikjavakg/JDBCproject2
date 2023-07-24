package peaksoft.dao;

import peaksoft.models.Movie;
import peaksoft.models.Theater;

import java.util.List;
import java.util.Map;

public interface TheaterDao {
    Theater findById(Long theaterId);
    List<Map<Movie, List<Theater>>>  getAllMoviesByTime(int hour);

}
