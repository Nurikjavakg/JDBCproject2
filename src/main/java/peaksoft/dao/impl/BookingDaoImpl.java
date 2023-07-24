package peaksoft.dao.impl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.BookingDao;
import peaksoft.models.Booking;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    @Override
    public String save(Long showTimeId, Long userId, int numberOfTickets, LocalDateTime booking_time) {
        String sql = "insert into bookings(show_time_id,user_id,number_of_tickets,booking_time)values(?,?,?,?)";
        try (Connection connection = JdbcConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, showTimeId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setInt(3, numberOfTickets);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(booking_time));
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }return "Successfully saved booking";
    }

    @Override
    public void addColumnId() {
        String sql = "alter table bookings add column id serial primary key ";
        try (Connection connection = JdbcConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void findById(Long booking_id) {
        String sql = "select bookings.* from bookings where id=?";
        try(Connection connection = JdbcConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,booking_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long showTimeId = resultSet.getLong("show_time_id");
                Long userId = resultSet.getLong("user_id");
                int numberOfTickets = resultSet.getInt("number_of_tickets");
                LocalDateTime bookingTime = resultSet.getTimestamp("booking_time").toLocalDateTime();

                Booking booking = new Booking(showTimeId,userId,numberOfTickets,bookingTime);
                System.out.println(booking);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public String delete(Long booking_id) {
        String sql = "delete from bookings where bookings.id=?";
        try (Connection connection= JdbcConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            preparedStatement.setLong(1,booking_id);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "Booking is successfully deleted" ;
    }

    @Override
    public void getAllBookings() {

    }

    @Override
    public List<Booking> getBookingByUserId(Long userId) {
        return null;
    }
}
