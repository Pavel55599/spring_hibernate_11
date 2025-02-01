package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User user1 = new User("ПАША", "ВОЛЯ", "ПАВЛИК@mail.com");
      Car car1 = new Car("жигули", 1985);
      user1.setCar(car1);

      User user2 = new User("ИВАН", "ВЕТРОВ", "ИВАН@mail.com");
      Car car2 = new Car("МАЗДА", 3);
      user2.setCar(car2);

      User user3 = new User("ФЕДОР", "ДОСТОЕВСКИЙ", "ФЕДЯ@mail.com");
      Car car3 = new Car("МЕРСЕДЕС", 600);
      user3.setCar(car3);

      User user4 = new User("АЛЕКСАНДР", "ПУШКИН", "САНЯ@mail.com");
      Car car4 = new Car("ВОЛГА", 21);
      user4.setCar(car4);

      User user5 = new User("игорь", "васильев", "igor@gmail.com");
      Car car5 = new Car("kamaz", 6500);
      user5.setCar(car5);


      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }


      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("жигули", 1985));
      System.out.println("-----------------------------------");
      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("МАЗДА", 3));
      System.out.println("-----------------------------------");
      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("МЕРСЕДЕС", 600));
      System.out.println("-----------------------------------");
      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("ВОЛГА", 21));
      System.out.println("-----------------------------------");
      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("kamaz", 6500));

      context.close();
   }
}




