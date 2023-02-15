package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Audio", 22);
        Car car2 = new Car("Bmw", 23);
        Car car3 = new Car("Audio", 22);
        Car car4 = new Car("Honda", 25);
        User user1 = new User("Мария", "Петрова", "mariya@mail.ru");
        User user2 = new User("Петр", "Иванов", "petr@mail.ru");
        User user3 = new User("Василий", "Петров", "vasily3@mail.ru");
        User user4 = new User("Игорь", "Попов", "igor@mail.ru");
        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        car4.setUser(user4);
        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Avto = " + user.getCar());
            System.out.println();
        }
        List<User> userToAvto = userService.getUserToAvto("Audio", 22);
        userToAvto.stream().forEach(System.out::println);
        context.close();
    }
}
