package peaksoft.models;

import java.time.LocalDateTime;

public class ShowTime {
    private Long id;
    private Long movie_id;
    private Long theater_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ShowTime(Long movie_id, Long theater_id, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie_id = movie_id;
        this.theater_id = theater_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ShowTime(Long id, Long movie_id, Long theater_id, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.movie_id = movie_id;
        this.theater_id = theater_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ShowTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ShowTime() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public Long getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(Long theater_id) {
        this.theater_id = theater_id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "\nShowTime{" +
                "id=" + id +
                ", movie_id=" + movie_id +
                ", theater_id=" + theater_id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
