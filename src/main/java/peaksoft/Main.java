package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
//       userService.createUsersTable();

        userService.saveUser("Aichurok", "Turgunbaeva", (byte) 28);
        userService.saveUser("Chynara", "Mamatalieva", (byte) 18);
        userService.saveUser("Kunzaada", "Bekzhanova", (byte) 25);
        userService.saveUser("Dilbara", "Asanalieva", (byte) 19);
        userService.saveUser("Dinara", "Rahatbek kyzy", (byte) 18);

//        userService.removeUserById(2);

//        List<User> allUsers = userService.getAllUsers();
//        allUsers.forEach(System.out::println);

//        System.out.println(userService.existsByFirstName("Dilbara"));

//       userService.cleanUsersTable();

 //       userService.dropUsersTable();

    }
}
