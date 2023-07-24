package peaksoft.dao.impl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.ShowTimeDao;
import peaksoft.models.Movie;
import peaksoft.models.ShowTime;
import peaksoft.models.Theater;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ShowTimeDaoImpl implements ShowTimeDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void save(ShowTime showTime) {
        try{
        PreparedStatement preparedStatement = connection.prepareStatement("""
                    insert into showtimes(movie_id,theater_id,start_time,end_time)
                    values (?,?,?,?)
                    """);

           preparedStatement.setLong(1,showTime.getMovie_id());
           preparedStatement.setLong(2,showTime.getTheater_id());
           preparedStatement.setTimestamp(3, Timestamp.valueOf(showTime.getStartTime()));
           preparedStatement.setTimestamp(4,Timestamp.valueOf(showTime.getEndTime()));

           preparedStatement.execute();
           preparedStatement.close();

    }catch (SQLException e){
            throw new RuntimeException(e);
        }
}

    @Override
    public ShowTime findById(Long showTimeId) {
        ShowTime showTime = new ShowTime();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(""" 
            select * from showtimes where id=?""");
            preparedStatement.setLong(1,showTimeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!(resultSet.next())){
                throw new RuntimeException("Movie with id:"+showTimeId+" not found!");
            }
            else {
                showTime.setId(resultSet.getLong("id"));
                showTime.setMovie_id(resultSet.getLong("movie_id"));
                showTime.setTheater_id(resultSet.getLong("theater_id"));
                showTime.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
                showTime.setStartTime(resultSet.getTimestamp("end_time").toLocalDateTime());
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
      return showTime;
    }

    @Override
    public void assign(ShowTime showTime) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
            update showtimes set 
            movie_id=?,
            theater_id=?
            where id=?
        """);
            preparedStatement.setLong(1, showTime.getMovie_id());
            preparedStatement.setLong(2, showTime.getTheater_id());
            preparedStatement.setLong(3,showTime.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

}


    @Override
    public List<ShowTime> getAll() {
        List<ShowTime>showTimes = new ArrayList<>();
           String sql = "select * from showtimes";
           try (Connection connection = JdbcConfig.getConnection();
                Statement statement = connection.createStatement())
           {
               ResultSet resultSet = statement.executeQuery(sql);
               while (resultSet.next()){
                   showTimes.add(new ShowTime(
                           resultSet.getLong("id"),
                   resultSet.getLong("movie_id"),
                   resultSet.getLong("theater_id"),
                   resultSet.getTimestamp("start_time").toLocalDateTime(),
                   resultSet.getTimestamp("end_time").toLocalDateTime()

                   ));
               }
           }catch (SQLException e){
               throw new RuntimeException(e);
           }
        return showTimes;
    }

    @Override
    public String deleteShowTimeByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        String sql="delete from showtimes where start_time=? and end_time=?";
        try (Connection connection = JdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        preparedStatement.setTimestamp(1,Timestamp.valueOf(startTime));
        preparedStatement.setTimestamp(2,Timestamp.valueOf(endTime));
         preparedStatement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "Successfully deleted!";
    }

    @Override
    public List<Map<Theater, List<Movie>>> getMoviesGroupByTheater() {
       String sql="select * from showtimes s join theaters t on " +
               "s.theater_id=t.id join movies m on s.movie_id=m.id ";

        List<Map<Theater, List<Movie>>> resultList = new ArrayList<>();


        try(Connection connection = JdbcConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                  resultList.add(Map.of(new Theater(resultSet.getLong("id"),
                          resultSet.getString("name"),
                          resultSet.getString("location")),List.of(new Movie(
                                                resultSet.getLong("id"),
                                                resultSet.getString("title"),
                                                resultSet.getString("genre"),
                                                resultSet.getInt("duration")))));

        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }return resultList;

    }
    }
