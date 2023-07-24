package peaksoft.services.impl;

import peaksoft.dao.MovieDao;
import peaksoft.dao.ShowTimeDao;
import peaksoft.dao.TheaterDao;
import peaksoft.dao.impl.MovieDaoImpl;
import peaksoft.dao.impl.ShowTimeDaoImpl;
import peaksoft.dao.impl.TheaterDaoImpl;
import peaksoft.models.Movie;
import peaksoft.models.ShowTime;
import peaksoft.models.Theater;
import peaksoft.services.ShowTimeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShowTimeServiceImpl implements ShowTimeService {
    ShowTimeDao showTimeDao = new ShowTimeDaoImpl();
    TheaterDao theaterDao = new TheaterDaoImpl();
    MovieDao movieDao = new MovieDaoImpl();
    @Override
    public String save(ShowTime showTime) {
        theaterDao.findById(showTime.getTheater_id());
        movieDao.findMovieById(showTime.getMovie_id());
        showTimeDao.save(showTime);
        return "Successfully saved with id: "+showTime.getId();


    }

    @Override
    public String assign(Long showTimeId, Long movieId, Long theaterId) {
        ShowTime showTime=showTimeDao.findById(showTimeId);
        Movie movie = movieDao.findMovieById(movieId);
        Theater theater = theaterDao.findById(theaterId);
        showTime.setMovie_id(movie.getId());
        showTime.setTheater_id(theater.getId());
        showTimeDao.assign(showTime);
        return "Successfully assigned!";
    }

    @Override
    public List<ShowTime> getAll() {
        return showTimeDao.getAll();

    }

    @Override
    public String deleteShowTimeByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        showTimeDao.deleteShowTimeByStartAndEndTime(startTime,endTime);
        return "Successfully deleted!";
    }

    @Override
    public List<Map<Theater, List<Movie>>> getMoviesGroupByTheater() {
        return  showTimeDao.getMoviesGroupByTheater();

    }
}
