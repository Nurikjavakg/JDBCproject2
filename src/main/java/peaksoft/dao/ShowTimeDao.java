package peaksoft.dao;

import peaksoft.models.Movie;
import peaksoft.models.ShowTime;
import peaksoft.models.Theater;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ShowTimeDao {
    void save(ShowTime showTime);

    ShowTime findById(Long showTimeId);

    void assign(ShowTime showTime);

    List<ShowTime> getAll();
    String deleteShowTimeByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime);
    List<Map<Theater, List<Movie>>> getMoviesGroupByTheater();
}
