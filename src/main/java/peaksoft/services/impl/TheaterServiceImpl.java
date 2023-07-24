package peaksoft.services.impl;

import peaksoft.dao.TheaterDao;
import peaksoft.dao.impl.TheaterDaoImpl;
import peaksoft.models.Movie;
import peaksoft.models.Theater;
import peaksoft.services.TheaterService;

import java.util.List;
import java.util.Map;

public class TheaterServiceImpl implements TheaterService {
    TheaterDao theaterDao = new TheaterDaoImpl();
    @Override
    public List<Map<Movie, List<Theater>>> getAllMoviesByTime(int hour) {
        return theaterDao.getAllMoviesByTime(hour);
    }
}
