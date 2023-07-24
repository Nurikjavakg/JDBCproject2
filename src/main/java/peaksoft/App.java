package peaksoft;

import peaksoft.config.JdbcConfig;
import peaksoft.models.Movie;
import peaksoft.models.ShowTime;
import peaksoft.services.BookingService;
import peaksoft.services.MovieService;
import peaksoft.services.ShowTimeService;
import peaksoft.services.TheaterService;
import peaksoft.services.impl.BookingServiceImpl;
import peaksoft.services.impl.MovieServiceImpl;
import peaksoft.services.impl.ShowTimeServiceImpl;
import peaksoft.services.impl.TheaterServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
       // JdbcConfig.getConnection();


        MovieService movieService = new MovieServiceImpl();
        ShowTimeService showTimeService = new ShowTimeServiceImpl();
        TheaterService theaterService = new TheaterServiceImpl();
        BookingService bookingService = new BookingServiceImpl();




//        System.out.println(movieService.creatMovie(
//                "bookings",
//                List.of(
//                        "show_time_id int references showtimes(id) ",
//                        "user_id int references users(id)",
//                        "number_of_tickets int",
//                        "booking_time timestamp"
//                )
//        ));


        Scanner scannerForStr = new Scanner(System.in);
        Scanner scannerForNumber = new Scanner(System.in);

        while (true){
            switch (new Scanner(System.in).nextLine()){
                case "1","save"->{
                    System.out.println("Write title:");
                    String title = scannerForStr.nextLine();
                    System.out.println("Write genre:");
                    String genre = scannerForStr.nextLine();
                    System.out.println("Write duration:");
                    int duration = scannerForNumber.nextInt();
                    System.out.println(movieService.saveMovie(new Movie(title, genre, duration)));
                }
                case "2","find"->{
                    System.out.println("Write movie id");
                    System.out.println(movieService.findMovieById(scannerForNumber.nextLong()));
                }
                case "3","save date"->{
                    String save= showTimeService.save(new ShowTime(5L,1L,
                            LocalDateTime.of(2023,7,20,
                                    18,30,0),

                                    LocalDateTime.of(2023,7,20,
                                            20,30,0)));
                }
                case "4","assign"->{
                    System.out.println(showTimeService.assign(2L, 3L, 4L));;
                }
                case"5","searchMovie"->{
                    System.out.println("Write title");
                    System.out.println(movieService.searchByName(scannerForStr.nextLine()));
                }
                case "6","sortByDuration"->{
                    System.out.println("Write asc or desc");
                    System.out.println(movieService.sortByDuration(scannerForStr.nextLine()));
                }
                case "7"->{
                    System.out.println("Write movie Id and start date (yyyy-MM-dd):");
                    Long theaterId = scannerForNumber.nextLong();
                    String startDateInput = scannerForStr.next();
                    LocalDate localDate = LocalDate.parse(startDateInput);
                    System.out.println(movieService.getMoviesByTheaterIdAndStartTime(theaterId, localDate));
                }
                case "8","get all"->{
                    System.out.println(showTimeService.getAll());
                }
                case "9"->{
                   LocalDateTime startTime = LocalDateTime.of(2023,7,23,14,0,0);
                   LocalDateTime endTime = LocalDateTime.of(2023,7,23,17,0,0);
                    System.out.println(showTimeService.deleteShowTimeByStartAndEndTime(startTime, endTime));
                }
                case "10"->{
                    System.out.println(showTimeService.getMoviesGroupByTheater());
                }
                case "11"->{
                    System.out.println(theaterService.getAllMoviesByTime(2));
                }
                case "12"->{
                    LocalDateTime localDateTime = LocalDateTime.of(2023,7,24,14,0,0);
                    System.out.println(bookingService.save(2L, 3L, 4,localDateTime));
                }
                case "13"->{
                    bookingService.addColumnId();
                }
                case "14"->{
                    bookingService.findById(2L);
                }
                case "15"->{
                    bookingService.delete(2L);
                }

            }
        }
    }


}
