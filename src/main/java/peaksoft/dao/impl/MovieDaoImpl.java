package peaksoft.dao.impl;

import peaksoft.config.JdbcConfig;
import peaksoft.dao.MovieDao;
import peaksoft.models.Movie;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void createTable(String tableName, List<String> columns) {
        StringBuilder stringBuilder = new StringBuilder(String.format("create table %s (", tableName));
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < columns.size(); i++) {
                stringBuilder.append(columns.get(i));
                if (i < columns.size() - 1) {
                    stringBuilder.append(",");
                }

            }
            stringBuilder.append(")");
            statement.executeUpdate(stringBuilder.toString());
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void save(Movie movie) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    insert into movies(title,genre,duration)
                    values (?,?,?)
                      """);){
              preparedStatement.setString(1,movie.getTitle());
              preparedStatement.setString(2,movie.getGenre());
              preparedStatement.setInt(3,movie.getDuration());
              preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Movie findMovieById(Long id) {
        Movie movie = new Movie();
        try{PreparedStatement preparedStatement = connection.prepareStatement(""" 
            select * from movies where id=?""");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!(resultSet.next())){
               throw new RuntimeException("Movie with id:"+id+" not found!");
            }
            else {
                movie.setId(resultSet.getLong("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setDuration(resultSet.getInt("duration"));
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return movie;
    }

    @Override
    public List<Movie> searchByName(String title) {
        List<Movie>movies = new ArrayList<>();
        try {
        PreparedStatement preparedStatement=connection.prepareStatement("""
                   select movies.* from movies where title=?""");
        preparedStatement.setString(1,title);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            movies.add(new Movie(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("genre"),
                            resultSet.getInt("duration")));
        }

    }catch (SQLException e){
        throw  new RuntimeException(e);
    }
        return movies;

      }

    @Override
    public List<Movie> sortByDuration(String ascOrDesc) {
        List<Movie> movies = new ArrayList<>();

        try {
            String sql = "SELECT * FROM movies ORDER BY duration " + ascOrDesc;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("duration")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }


    @Override
    public List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime) {
        List<Movie>movies = new ArrayList<>();
        try {
        PreparedStatement preparedStatement = connection.prepareStatement( "select m.*,st.*,t.* from showTimes st inner join movies m on st.movie_id=m.id inner join theaters t on st.theater_id=t.id where t.id=? or st.start_time=?");

        preparedStatement.setLong(1,theaterId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startTime.atStartOfDay()));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            movies.add(new Movie(
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("genre"),
                    resultSet.getInt("duration")));
            break;
        }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }



        return movies;
    }
}
