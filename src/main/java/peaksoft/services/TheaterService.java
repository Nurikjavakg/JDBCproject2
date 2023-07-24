package peaksoft.services;

import peaksoft.models.Movie;
import peaksoft.models.Theater;

import java.util.List;
import java.util.Map;

public interface TheaterService {
    List<Map<Movie, List<Theater>>>  getAllMoviesByTime(int hour);
}
