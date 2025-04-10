package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner s = new Scanner(System.in); // scanner is used to take input from the CLI

        int option = 0;
        UserBookingService userBookingService;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("There is something wrong");
            return;
        }

        while (option != 7) {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = s.nextInt();
            Train trainSelectedForBooking = new Train();

            switch (option) {
                case 1:
                    System.out.println("Enter the username to signup");
                    String usernameToSignUp = s.nextLine();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = s.nextLine();

                    // calling the User constructor to create a new user
                    User userToSignUp = new User(usernameToSignUp, passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUPUser(userToSignUp);
                    break;
                case 2:
                    System.out.println("Enter the username to login");
                    String usernameToLogin = s.next();
                    System.out.println("Enter the password to login");
                    String passwordToLogin = s.next();

                    User userToLogin = new User(usernameToLogin, passwordToLogin,
                            UserServiceUtil.hashPassword(passwordToLogin),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUPUser(userToLogin);

                    try {
                        userBookingService = new UserBookingService(userToLogin);
                    } catch (IOException ex) {
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    // taking user's input for source and destination
                    System.out.println("Enter your source station");
                    String source = s.next();
                    System.out.println("Enter your destination station");
                    String destination = s.next();

                    // calling the method from TrainService to get the list of trains
                    List<Train> trains = userBookingService.getTrains(source, destination);

                    // displaying searched trains one by one
                    int index = 1;
                    for (Train train : trains) {
                        System.out.println(index + " Train id: " + train.getTrainId());
                        for (Map.Entry<String, String> entry : train.getStationTimes().entrySet()) {
                            System.out.println("station " + entry.getKey() + " time : " + entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    trainSelectedForBooking = trains.get(s.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat by typing the row and column number");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row : seats) {
                        for (Integer val : row) {
                            System.out.println(val + "");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = s.nextInt();
                    System.out.println("Enter the column");
                    int col = s.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if (booked.equals(Boolean.TRUE)) {
                        System.out.println("Seat has been booked");
                    } else {
                        System.out.println("Can't book the seat");
                    }
                    break;
                default:
                    break;
            }

        }
    }
}
