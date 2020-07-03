package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User1", "Lastname1", "user1@mail.ru");

        Car car1 = new Car("1", "1");
        car1.setUser(user1);
        Car car2 = new Car("2", "2");
        car2.setUser(user2);

        userService.add(user1);
        userService.add(user2);
        carService.add(car1);
        carService.add(car2);

        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        }

        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("License Plate= " + car.getLicensePlate());
            System.out.println("Series = " + car.getSeries());
            System.out.println("User id = " + car.getUser().getId());
        }
        User user = carService.getCarsUser("1", "1");
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());

        context.close();
    }
}
