package peaksoft.dao;

import peaksoft.models.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingDao {
    String save (Long showTimeId, Long userId, int numberOfTickets, LocalDateTime booking_time);
    void addColumnId();
    void findById(Long booking_id);
    String delete(Long booking_id);
    void getAllBookings();
    List<Booking> getBookingByUserId(Long userId);
}
