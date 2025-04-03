package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserBookingService {
    private User user;

    // variable userList of type
    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    // final is used so when it gets initialized no one can change it
    private static final String USERS_PATH = "../localDb/users.json";

    // constructor
    public UserBookingService(User user) throws IOException {
        // throws ioException is used to handle scenarios where file is not found or path to the file is wrong
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        }); // assigning userList variable to json Data
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user ->{
            return user.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1)
        })
    }
}
