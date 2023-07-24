package peaksoft.services.impl;

import peaksoft.dao.BookingDao;
import peaksoft.dao.impl.BookingDaoImpl;
import peaksoft.models.Booking;
import peaksoft.services.BookingService;

import java.time.LocalDateTime;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    BookingDao bookingDao = new BookingDaoImpl();

    @Override
    public String save(Long showTimeId, Long userId, int numberOfTickets, LocalDateTime booking_time) {
        return bookingDao.save(showTimeId,userId,numberOfTickets,booking_time);
    }

    @Override
    public void addColumnId() {
        bookingDao.addColumnId();
    }

    @Override
    public void findById(Long booking_id) {
        bookingDao.findById(booking_id);
        System.out.println(bookingDao);

    }

    @Override
    public String delete(Long booking_id) {
        bookingDao.delete(booking_id);
        return "Successfully deleted!";
    }

    @Override
    public void getAllBookings() {

    }

    @Override
    public List<Booking> getBookingByUserId(Long userId) {
        return null;
    }
}
