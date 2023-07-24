package peaksoft.dao.impl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.TheaterDao;
import peaksoft.models.Movie;
import peaksoft.models.Theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TheaterDaoImpl implements TheaterDao {
    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public Theater findById(Long theaterId) {
        Theater theater = new Theater();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
               select * from theaters where id=?""");
      preparedStatement.setLong(1,theaterId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
          theater.setId((resultSet.getLong("id")));
          theater.setName(resultSet.getString("name"));
          theater.setLocation(resultSet.getString("location"));

      }
      else {
          throw new RuntimeException(String.format("Theater with id: %d not found",theater));
      }

      }catch (SQLException e){
            throw new RuntimeException(e);
        }return theater;



}

    @Override
    public List<Map<Movie, List<Theater>>> getAllMoviesByTime(int hour) {
        String sql="select * from showtimes s join theaters t on " +
                "s.theater_id=t.id join movies m on s.movie_id=m.id where m.duration=? ";

        List<Map<Movie, List<Theater>>> resultList = new ArrayList<>();


        try(Connection connection = JdbcConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,hour);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               resultList.add(Map.of(new Movie(
                       resultSet.getLong("id"),
                       resultSet.getString("title"),
                       resultSet.getString("genre"),
                       resultSet.getInt("duration")),
                List.of(new Theater(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("location")))));

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }return resultList;

    }
}


