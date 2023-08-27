package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.*;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            List<User> users = new ArrayList<>(Arrays.asList(
                    new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 1)),
                    new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW", 2)),
                    new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 3))
            ));

            UserService userService = context.getBean(UserService.class);
            for (User user : users) {
                userService.add(user);
            }

            userService.listUsers().forEach(System.out::println);


            Optional<User> resultSearch = userService.getUserByCar("BMW", 3);
            System.out.println(resultSearch.isPresent() ? resultSearch.get() : "Not found!");
        }

    }
}