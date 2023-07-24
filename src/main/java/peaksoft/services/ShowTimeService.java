package peaksoft.services;

import peaksoft.models.Movie;
import peaksoft.models.ShowTime;
import peaksoft.models.Theater;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ShowTimeService {
    String save(ShowTime showTime);

    String assign(Long showTimeId, Long movieId, Long theaterId);
    List<ShowTime> getAll();
    String deleteShowTimeByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime);
    List<Map<Theater, List<Movie>>> getMoviesGroupByTheater();
}
